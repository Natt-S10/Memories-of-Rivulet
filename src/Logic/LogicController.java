package Logic;

import Input.InputUtils;
import Input.KeyMap;
import Renderer.GameScreen;
import Renderer.RenderableHolder;
import Renderer.ResourcesLoader;
import UIpanel.VisualFX.FishCaughtFX;
import UIpanel.VisualFX.LoadingFX;
import UIpanel.fishing.FishingPanel;
import entity.Character;
import entity.base.Collidable;
import entity.base.Movable;
import map.Map;

import java.util.ArrayList;

public class LogicController {
    private static final LogicController instance = new LogicController();
    private final ArrayList<Movable> movableEntities;
    private final ArrayList<Collidable> collidableEntities;
    private Character mainChar;
    private Map currentMap;
    private Map nextMap;
    private int MapLoadingT;
    private FishingPanel fishingPanel;
    private FishCaughtFX fishCaughtFX;



    private GameState gameState; //game status
    private int frameCount;
    //TODO: Baiting


    //TODO: fishing state field
    private double initFishingDur, fishingTimeCount;
    private boolean[] qtState;
    private int trigCount;
    private static final double trigPenalty = 5;

    //TODO: AfterFishing state fields
    //missing Type Fish
    private boolean isFishCaught;
    private static final int CongratAnimateDur = 120; // 2.5sec x 60fps



    //private static final Character mainChar;
    private LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();

        gameState = GameState.WALK;
        MapLoadingT = 240;
        //for fishing
        qtState = new boolean[]{false, false, false, false};
        fishingPanel = new FishingPanel(GameScreen.screenWidth,GameScreen.screenHeight);
        RenderableHolder.getInstance().add(fishingPanel);
        trigCount = 0;

        //for afterFishing
        isFishCaught = false;
        fishCaughtFX = new FishCaughtFX();
        RenderableHolder.getInstance().add(fishCaughtFX);


    }
    public static LogicController getInstance(){return instance;}

    public void addMovable(Movable m){movableEntities.add(m);}
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        fishingPanel.update();
        fishCaughtFX.update();
        switch (gameState){
            case WALK -> walkingState();
            case BAITING -> baitingState();
            case FISHING -> fishingState();
            case AFTERFISHING -> afterFishingState();
            case LOADING -> loading();
            case LOADED -> loadedMap();
        }
    }
    public void loading(){

            try{

                if(MapLoadingT == 240){

                    RenderableHolder.getInstance().resetElements();
                    this.currentMap = new Map(ResourcesLoader.Loading_map);
                    RenderableHolder.getInstance().add(currentMap);
                    LoadingFX loadingFX = new LoadingFX();
                    RenderableHolder.getInstance().add(loadingFX);

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
            setCurrentMap(nextMap);

        } catch (Exception e){
            e.printStackTrace();
        }
        gameState = GameState.WALK;
    }
    private void walkingState(){
        for (Movable eM : movableEntities) {
            eM.move();
            eM.update();
        }
        currentMap.update();
    }
    public void startBaiting(){
        gameState = GameState.BAITING;
        frameCount = (int)(300+Math.random()*900);
        mainChar.setBaitX(InputUtils.mouseX);
        mainChar.setBaitY(InputUtils.mouseY);
        mainChar.setBaitProgress(0.0);
    }
    private void baitingState(){
        if(InputUtils.isLeftClickTriggered()){
            gameState = GameState.WALK;
            return;
        }
        if(frameCount<0){
            startFishing();
            return;
        }
        frameCount--;
    }

    private void fishingState(){
        if(InputUtils.isKeyTriggered(KeyMap.W)) {
            if(qtState[0]) qtState[0]= false;
            else fishingTimeCount-=trigPenalty;
        }
        if(InputUtils.isKeyTriggered(KeyMap.A)) {
            if(qtState[1]) qtState[1]= false;
            else fishingTimeCount-=trigPenalty;
        }
        if(InputUtils.isKeyTriggered(KeyMap.S)) {
            if(qtState[2]) qtState[2]= false;
            else fishingTimeCount-=trigPenalty;
        }
        if(InputUtils.isKeyTriggered(KeyMap.D)) {
            if(qtState[3]) qtState[3]= false;
            else fishingTimeCount-=trigPenalty;
        }

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

    public void startFishing(int trigCount){
        setGameState(GameState.FISHING);
        this.trigCount =trigCount;
        isFishCaught = false;
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
        if(frameCount<0){
            gameState = GameState.WALK;
            return;
        }
        frameCount--;
    }
    private void finishFishing(boolean success){
        isFishCaught = success;
        gameState = GameState.AFTERFISHING;
        frameCount = CongratAnimateDur;
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
        else if(anchorX > currentMap.getPhysicalWidth() - GameScreen.screenWidth) anchorX = currentMap.getPhysicalWidth() - GameScreen.screenWidth;
        return anchorX;
    }

    public Double getAnchorY(){
        double anchorY = (mainChar.getPosY() - GameScreen.screenHeight/2); // true location sys
        if(anchorY <0) anchorY = 0.0;
        else if(anchorY > currentMap.getPhysicalHeight() - GameScreen.screenHeight) anchorY = currentMap.getPhysicalHeight() - GameScreen.screenHeight;
        return anchorY;
    }

    public void setCurrentMap(Map map){

            this.currentMap = map;
            RenderableHolder.getInstance().resetElements();
            RenderableHolder.getInstance().add(currentMap);
            setMainChar(ResourcesLoader.mainChar);
            RenderableHolder.getInstance().add(ResourcesLoader.mainChar);
            return;

    }
    public Map getCurrentMap() {
        return currentMap;
    }

    public FishingPanel getFishingPanel() {
        return fishingPanel;
    }

    public void setFishingPanel(FishingPanel fishingPanel) {
        this.fishingPanel = fishingPanel;
    }

    public FishCaughtFX getFishCaughtFX() {
        return fishCaughtFX;
    }

    public void setFishCaughtFX(FishCaughtFX fishCaughtFX) {
        this.fishCaughtFX = fishCaughtFX;
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

    public Map getNextMap() {
        return nextMap;
    }

    public void setNextMap(Map nextMap) {
        this.nextMap = nextMap;
    }

    public int getMapLoadingT() {
        return MapLoadingT;
    }

    public void setMapLoadingT(int mapLoadingT) {
        MapLoadingT = mapLoadingT;
    }
}
