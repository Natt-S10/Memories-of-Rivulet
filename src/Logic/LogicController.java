package Logic;

import Input.InputUtils;
import Input.KeyMap;
import Items.Fish.Fish;
import Items.Fish.FishUtils;
import Renderer.*;
import UIcontainer.ListFish.ListFish;
import UIcontainer.MapChanger.*;
import UIcontainer.Menu.*;
import UIcontainer.Option.OptionFish;
import UIcontainer.Option.OptionMenu;
import UIcontainer.Option.OptionPuss;
import UIcontainer.SideLineStroke;
import InGamePanel.VisualFX.LoadingFX;
import Entity.Character;
import Entity.base.Collidable;
import Entity.base.Movable;
import javafx.stage.FileChooser;
import Map.GameMap;

import java.io.*;
import java.util.ArrayList;

public class LogicController  implements Serializable{
    private static final LogicController instance = new LogicController();
    private final ArrayList<Movable> movableEntities;
    private final ArrayList<Collidable> collidableEntities;
    private Character mainChar;
    private GameMap currentGameMap;
    private GameMap nextGameMap;
    private int money;
    private int MapLoadingT;




    //MENU SETTING
    private boolean isSetup;
    private  boolean buttonTriggered;

    private GameState menuOpuss;
    private boolean isResume;
    private boolean isMenu;

    private boolean[] fishAchievement;
    private double sfxVolume, musicVolume;



    private GameState gameState; //game status
    private int timeCount;

    //TODO: Baiting
    private double warpDist;

    //TODO: fishing state field
    private double initFishingDur, fishingTimeCount;
    private boolean[] qtState;
    private int trigCount;
    private static final double trigPenalty = 5;

    //TODO: AfterFishing state fields
    private static final int CongratAnimateDur = 120; // 2.5sec x 60fps
    private Fish caughtFish;



    //private static final Character mainChar;
    public LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
        fishAchievement = new boolean[FishUtils.fishStatMap.size()];
        sfxVolume = 0.30;
        setMusicVolume(0.30);

        gameState = GameState.WALK;
        isResume = false;
        MapLoadingT = 240;
        //for fishing
        qtState = new boolean[]{false, false, false, false};
        trigCount = 0;
        warpDist = 350;


        isSetup = false;
        buttonTriggered = false;

    }
    public static LogicController getInstance(){return instance;}

    public void addMovable(Movable m){

        movableEntities.add(m);
    }
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        ResourcesLoader.fishCaughtFX.update();
        currentGameMap.update();
        //System.out.println(LogicController.getInstance().getGameState());
        //System.out.println((LogicController.getInstance().isMenu()));
        //System.out.println(isMenu);
       // System.out.println(menuOpuss);
        //System.out.println((LogicController.getInstance().getMenuOpuss() == GameState.MENU));
        //System.out.println((LogicController.getInstance().getMenuOpuss() == GameState.MENU) ? GameState.MENU: GameState.PAUSE);

        switch (gameState){
            case WALK -> walkingState();
            case BAITING -> baitingState();
            case FISHING -> fishingState();
            case FISHRAISING -> fishRaisingState();
            case AFTERFISHING -> afterFishingState();
            case LOADING -> loading();
            case LOADED -> loadedMap();
            case MENU -> mainMenu();
            case NEW_GAME -> newGame();
            case LOAD_GAME -> loadGame(ResourcesLoader.saveData);
            case OPTIONM -> optionm();
            case OPTIONP -> optionp();
            case PAUSE -> pauseMenu();
            case EXIT -> exit();
            case SAVE -> save(ResourcesLoader.saveData);
            case RESUME -> resume();
            case LISTOFISH -> listFish();
        }
    }


    public void mainMenu(){
        if(!isSetup){
            LoadHoldingScreen();
            ResourcesLoader.saveData = ResourcesLoader.newsaveData;
            MenuButtonList.setVisible(true);
            isSetup = true;
            menuOpuss = GameState.MENU;
            isMenu = true;

        }
    }

    public void newGame(){ //save a new save file
        try{


            nextGameMap = new GameMap(ResourcesLoader.demo_map);
            ResourcesLoader.saveData = ResourcesLoader.newsaveData;
            ResourcesLoader.saveLogic = ResourcesLoader.defaultLogic;
            ResourcesLoader.saveLogic.mainChar = ResourcesLoader.mainChar;
            setMainChar(ResourcesLoader.mainChar);


            save(ResourcesLoader.newsaveData);
            MapLoadingT = 240;
            gameState = GameState.LOADING;

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void pauseMenu(){
        AudioAsset.playFootsteps(false);
        if(!isSetup){
            LoadHoldingScreen();
            PauseButtonList.setVisible(true);
            SideLineStroke.setVisible(true);
            isSetup = true;
            isMenu = false;
            menuOpuss = GameState.PAUSE;
            save(ResourcesLoader.saveData);
        }
    }

    private void LoadHoldingScreen() {
        try{
            RenderableHolder.getInstance().resetElements();
            this.currentGameMap = new GameMap(ResourcesLoader.Loading_map);
            RenderableHolder.getInstance().add(currentGameMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        OptionMenu.setVisible(false);
        OptionPuss.setVisible(false);
        ButtonList.setVisible(false);
        OptionFish.setVisible(false);
        ListFish.setOn(false);
        PauseButtonList.setVisible(false);
    }

    public void resume(){
        isResume = true;
        gameState = GameState.LOADING;
    }

    public void save(String saveData){ // save at old file
        try{
            FileOutputStream fos = new FileOutputStream(saveData);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(LogicController.getInstance());
            oos.flush();
            oos.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("-------------------SAVE-------------------");


    }
    public void loadGame(String saveData){ // open file chooser and read a save file

            try{
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SAVE File","*.sav"));
                File selectedFile = fc.showOpenDialog(null);

                if(selectedFile != null){

                    FileInputStream fis = new FileInputStream(selectedFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    ResourcesLoader.saveLogic = (LogicController) ois.readObject();
                    sfxVolume = ResourcesLoader.saveLogic.sfxVolume;
                    setMusicVolume(ResourcesLoader.saveLogic.musicVolume);

                    fishAchievement = ResourcesLoader.saveLogic.getFishAchievement();
                    money = ResourcesLoader.saveLogic.money;

                    nextGameMap = ResourcesLoader.saveLogic.getNextMap();
                    System.out.println(nextGameMap.getMapName());
                    setMainChar(ResourcesLoader.saveLogic.mainChar);

                    ResourcesLoader.saveData = selectedFile.toString();

                    ois.close();
                    MapLoadingT = 240;
                    setGameState(GameState.LOADING);

                } else{
                    setGameState(GameState.MENU);
                }
            }catch ( Exception e){
                e.printStackTrace();
        }

    }

    public void optionm(){
        if(!isSetup){
            try{
                RenderableHolder.getInstance().resetElements();
                this.currentGameMap = new GameMap(ResourcesLoader.Loading_map);
                RenderableHolder.getInstance().add(currentGameMap);
            }catch (Exception e){
                e.printStackTrace();
            }

            OptionMenu.setVisible(true);
            MenuButtonList.setVisible(false);

            isSetup = true;
        }

    }

    public void optionp(){
        if(!isSetup){
            try{
                RenderableHolder.getInstance().resetElements();
                this.currentGameMap = new GameMap(ResourcesLoader.Loading_map);
                RenderableHolder.getInstance().add(currentGameMap);
            }catch (Exception e){
                e.printStackTrace();
            }

            OptionPuss.setVisible(true);
            MenuButtonList.setVisible(false);

            isSetup = true;
        }

    }

    public void listFish(){
        ListFish.setOn(true);
        OptionFish.setVisible(true);
        SideLineStroke.setVisible(false);


    }

    public void exit(){
        System.exit(0);
    }



    public void loading(){

        AudioAsset.playFootsteps(false);
        ButtonList.setVisible(false);
            try{
                if(MapLoadingT == 240){

                    RenderableHolder.getInstance().resetElements();
                    this.currentGameMap = new GameMap(ResourcesLoader.Loading_map);
                    RenderableHolder.getInstance().add(currentGameMap);
                    LoadingFX loadingFX = new LoadingFX();
                    RenderableHolder.getInstance().add(loadingFX);
                    SideLineStroke.setVisible(true);

                }
            } catch (Exception e){
                e.printStackTrace();
            }
            if(MapLoadingT <0){
                gameState = GameState.LOADED;
            }
            MapLoadingT--;
    }

    private void loadedMap(){
        try{
            setCurrentMap(nextGameMap);
            buttonTriggered = false;
            ButtonList.setVisible(true);
            SideLineStroke.setVisible(false);

        } catch (Exception e){
            e.printStackTrace();
        }
        isResume = false;


        gameState = GameState.WALK;
    }
    private void walkingState(){


        for (Movable eM : movableEntities) {
            eM.move();
            eM.update();
        }
    }
    public void startBaiting(){
        gameState = GameState.BAITING;
        timeCount = (int)(300+Math.random()*900);
        mainChar.setBaitX(InputUtils.mouseX);
        mainChar.setBaitY(InputUtils.mouseY);
        mainChar.animateBating();
    }
    private void baitingState(){
        if(timeCount % 360 ==0) AudioAsset.splash.play(sfxVolume);


        if(InputUtils.isLeftClickTriggered()){
            AudioAsset.baitRetrieve.play(sfxVolume);
            gameState = GameState.WALK;
            return;
        }
        if(timeCount <0){
            startFishing();
            return;
        }
        timeCount--;
    }

    private void fishingState(){
        AudioAsset.playRopeTension(true);
        qtKeyProcess(KeyMap.W, 0);
        qtKeyProcess(KeyMap.A, 1);
        qtKeyProcess(KeyMap.S, 2);
        qtKeyProcess(KeyMap.D, 3);

        //terminating cond/
        boolean check = false;
        for(boolean e:qtState) check = check || e;
        if(!check){
            trigCount--;
            if(trigCount<0){
                finishFishing(true);
                return;
            }
            nextQTEvent();
        }
        if(fishingTimeCount < 0) {
            finishFishing(false);
            return;
        }
        fishingTimeCount -= 1;
    }

    private void qtKeyProcess(int w, int i) {
        if (InputUtils.isKeyTriggered(w)) {
            if (qtState[i]) {
                AudioAsset.correctKey.play(sfxVolume);
                qtState[i] = false;
            }
            else {
                AudioAsset.wrongKey.play(sfxVolume);
                fishingTimeCount -= trigPenalty;
            }
        }
    }

    public void startFishing(int trigCount){
        AudioAsset.fishCaught.play(sfxVolume);
        setGameState(GameState.FISHING);
        this.trigCount =trigCount;
        qtState = new boolean[]{false,false,false,false};
        nextQTEvent();
    }
    public void startFishing(){
        startFishing((int)(Math.random()*3+4));
    }
    private void nextQTEvent(){
        qtState[(int)(Math.random()*3)] = true;
        setInitFishingDur(70+Math.random()*15);
    }

    private void afterFishingState(){
        if(caughtFish != null){
            timeCount = CongratAnimateDur;
            gameState = GameState.FISHRAISING;
            return;
        }
    }

    private void fishRaisingState(){
        if(timeCount <0){
            gameState = GameState.WALK;
            return;
        }
        timeCount--;
    }

    private void finishFishing(boolean success){
        AudioAsset.playRopeTension(false);
        AudioAsset.ping.play(sfxVolume);
        if(success) {
            timeCount = CongratAnimateDur;
            caughtFish = null;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                caughtFish = new Fish();
                money+= caughtFish.getFishPrice();
                fishAchievement[FishUtils.fishIndex(caughtFish.getSpecies())] = true;
                AudioAsset.congrats.play(sfxVolume);
            });
            thread.start();
            gameState = GameState.AFTERFISHING;
        }
        else gameState = GameState.WALK;

    }




    public ArrayList<Movable> getMovableEntities() {
        return movableEntities;
    }

    public ArrayList<Collidable> getCollidableEntities() {
        return collidableEntities;
    }
    public Character getMainChar() {
        return mainChar;
    }

    public void setMainChar(Character mainChar) {

        if(this.mainChar == null) {
            this.mainChar = mainChar;
            addMovable(mainChar);
            return;
        }
        int id = movableEntities.indexOf(mainChar);

        if(id == -1){
            addMovable(mainChar);
            this.mainChar = mainChar;
            return;
        }

        movableEntities.set(id, (Movable) mainChar);
        this.mainChar = mainChar;

    }



    public Double getAnchorX(){
        double anchorX = (mainChar.getPosX() - GameScreen.screenWidth/2);
        if(anchorX <0) anchorX = 0.0;
        else if(anchorX > currentGameMap.getPhysicalWidth() - GameScreen.screenWidth) anchorX = currentGameMap.getPhysicalWidth() - GameScreen.screenWidth;
        return anchorX;
    }

    public Double getAnchorY(){
        double anchorY = (mainChar.getPosY() - GameScreen.screenHeight/2); // true location sys
        if(anchorY <0) anchorY = 0.0;
        else if(anchorY > currentGameMap.getPhysicalHeight() - GameScreen.screenHeight) anchorY = currentGameMap.getPhysicalHeight() - GameScreen.screenHeight;
        return anchorY;
    }

    public void setCurrentMap(GameMap gameMap){

        this.currentGameMap = gameMap;
        RenderableHolder.getInstance().resetElements();
        movableEntities.clear();

        RenderableHolder.getInstance().add(currentGameMap);
        setMainChar(ResourcesLoader.mainChar);
        RenderableHolder.getInstance().add(mainChar);
        if(!isResume) mainChar.setValidPOS(currentGameMap);



    }
    public GameMap getCurrentMap() {
        return currentGameMap;
    }


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public double getInitFishingDur() {
        return initFishingDur;
    }

    public void setInitFishingDur(double initFishingDur) {
        this.initFishingDur = initFishingDur;
        this.fishingTimeCount = initFishingDur;
    }

    public double getFishingTimeCount() {
        return fishingTimeCount;
    }
    public double getFishingTimeRatio(){
        return fishingTimeCount/initFishingDur;
    }

    public void setFishingTimeCount(double fishingTimeCount) {
        this.fishingTimeCount = fishingTimeCount;
    }

    public boolean[] getQtState() {
        return qtState;
    }

    public void setQtState(boolean[] qtState) {
        this.qtState = qtState;
    }

    public GameMap getNextMap() {
        return nextGameMap;
    }

    public void setNextMap(GameMap nextGameMap) {
        this.nextGameMap = nextGameMap;
    }

    public int getMapLoadingT() {
        return MapLoadingT;
    }

    public void setMapLoadingT(int mapLoadingT) {
        MapLoadingT = mapLoadingT;
    }

    public boolean isSetup() {
        return isSetup;
    }

    public void setSetup(boolean setup) {
        isSetup = setup;
    }

    public boolean isButtonTriggered() {
        return buttonTriggered;
    }

    public void setButtonTriggered(boolean buttonTriggered) {
        this.buttonTriggered = buttonTriggered;
    }

    public double getWarpDist() {
        return warpDist;
    }

    public GameState getMenuOpuss() {
        return menuOpuss;
    }

    public void setMenuOpuss(GameState menuOpuss) {
        this.menuOpuss = menuOpuss;
    }

    public boolean isFishCaught(){
        return (gameState == GameState.AFTERFISHING);
    }

    public Fish getCaughtFish() {
        return caughtFish;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int diff){
        money+=diff;
    }

    public boolean[] getFishAchievement() {
        return fishAchievement;
    }

    public void setMusicVolume(double musicVolume){
        musicVolume = Math.max(0,musicVolume);
        musicVolume = Math.min(1,musicVolume);
        this.musicVolume= musicVolume;
            AudioAsset.setMusicVolume(musicVolume);

    }

    public void setFXVolume(double musicVolume){
        musicVolume = Math.max(0,musicVolume);
        musicVolume = Math.min(1,musicVolume);
            this.sfxVolume = musicVolume;


    }

    public static double getSFXVol(){
        return LogicController.getInstance().sfxVolume;
    }
    public static double getMusicVol(){
        return LogicController.getInstance().musicVolume;
    }
}
