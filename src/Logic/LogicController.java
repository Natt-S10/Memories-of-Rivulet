package Logic;

import Renderer.GameScreen;
import Renderer.RenderableHolder;
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
    private boolean isFishing; //game status
    private double fishingDuration;
    //private static final Character mainChar;
    private LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
        isFishing = false;
    }
    private void fishingState(){
        System.out.println(fishingDuration);
        if(fishingDuration <= 0) isFishing = false;
        fishingDuration-= 1;
    }
    public static LogicController getInstance(){return instance;}

    public void addMovable(Movable m){movableEntities.add(m);}
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        if(isFishing) fishingState();
        else{
            for (Movable eM : movableEntities) {
                eM.move();
                eM.update();
            }
            currentMap.update();
        }
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

    public void setFishing(boolean fishing) {
        isFishing = fishing;
    }

    public void setFishingDuration(double fishingDuration) {
        this.fishingDuration = fishingDuration;
    }
}
