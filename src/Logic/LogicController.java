package Logic;

import Input.InputUtils;
import Input.KeyMap;
import Renderer.GameScreen;
import Renderer.RenderableHolder;
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
    FishingPanel fishingPanel;


    private GameState gameState; //game status
    //fishing state field
    private double initFishingDur, fishingTimeCount;
    private boolean[] qtState;



    //private static final Character mainChar;
    public static LogicController getInstance(){return instance;}
    private LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
        gameState = GameState.WALK;

        //for fishing
        qtState = new boolean[]{false, false, false, false};
        fishingPanel = new FishingPanel(GameScreen.screenWidth,GameScreen.screenHeight);
        RenderableHolder.getInstance().add(fishingPanel);
    }


    public void addMovable(Movable m){movableEntities.add(m);}
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        fishingPanel.update();
        switch (gameState){
            case WALK -> walkingState();
            case FISHING -> fishingState();
        }
    }

    //TODO
    private void fishingState(){
        if(InputUtils.isKeyTriggered(KeyMap.W)) qtState[0] = false;
        if(InputUtils.isKeyTriggered(KeyMap.A)) qtState[1] = false;
        if(InputUtils.isKeyTriggered(KeyMap.S)) qtState[2] = false;
        if(InputUtils.isKeyTriggered(KeyMap.D)) qtState[3] = false;
        //terminating cond/
        boolean check = false;
        for(boolean e:qtState) check = check || e;
        if(!check){
            System.out.println("OK u got it");
            gameState = GameState.WALK;
        }
        if(fishingTimeCount < 0) {
            System.out.println("Stupid Shit");
            gameState = GameState.WALK;
        }
        fishingTimeCount -= 1;
    }
    private void walkingState(){
        for (Movable eM : movableEntities) {
            eM.move();
            eM.update();
        }
        currentMap.update();
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
        if(this.currentMap == null){
            this.currentMap = map;
            RenderableHolder.getInstance().add(currentMap);
            return;
        }

        //change-map method is asap

    }
    public Map getCurrentMap() {
        return currentMap;
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
}
