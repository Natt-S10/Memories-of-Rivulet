package entity;

import Input.InputUtils;
import Logic.GameState;
import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import UIcontainer.MapChanger.MapChanger;
import entity.base.Boundary;
import entity.base.Collidable;
import entity.base.Direction;
import entity.base.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import map.Map;
import map.MapName;
import map.TileType;

public class Character extends Entity implements IRenderable, Movable, Collidable, java.io.Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private Boundary collisionBoundary;
    private Direction facing;
    private double baitX, baitY, baitProgress;
    private boolean isRight;
    private double speed, reachingDist;
    boolean isColliding;
    private int spriteCounter;
    private int spriteNum;
    public final int screenX;
    public final int screenY;
    public Map playerCurMap;





    public boolean updateVisualBoundary(){
        boolean a = posX <= screenX/2;
        boolean b = posX >= LogicController.getInstance().getCurrentMap().getPhysicalWidth() - (screenX/2);
        boolean c = posY <= screenY/2;
        boolean d = posY >= LogicController.getInstance().getCurrentMap().getPhysicalHeight() - (screenY/2);
        if(a) visualBoundary.setByCenterX((int)posX);
        else if(b)
            visualBoundary.setByCenterX((int)(posX-LogicController.getInstance().getCurrentMap().getPhysicalWidth()+screenX));
        if(c) visualBoundary.setByCenterY((int)posY);
        else if(d)
            visualBoundary.setByCenterY((int)(posY-LogicController.getInstance().getCurrentMap().getPhysicalHeight()+screenY));

        return a||b||c||d;
    }

    public Character(String name, int posX, int posY, int width, int height, double speed, double reachingDist) {
        super(name, posX, posY, width, height);
        collisionBoundary = new Boundary(posX -(width/6),posY+(height/11),width/3,height/4);
        facing = Direction.STABLE;
        isRight = true;
        spriteCounter = 0;
        spriteNum = 1;
        isColliding = false;
        this.speed = speed;
        this.reachingDist = reachingDist;
        screenX = GameScreen.screenWidth;
        screenY = GameScreen.screenHeight;
        visualBoundary.setByCenterX(screenX/2);
        visualBoundary.setByCenterY(screenY/2);


    }

    @Override
    public void update() {
        if(InputUtils.mouseOnScreen && InputUtils.isLeftClickTriggered() &&
                isReachable(InputUtils.mouseX,InputUtils.mouseY)) {
            if(LogicController.getInstance().getCurrentMap().clickedTile() == TileType.WATER)
                LogicController.getInstance().startBaiting();
        }
        //System.out.println(isWarpable());
            //System.out.println(playerCurMap.getMapName());


//        System.out.println("POSX = " + posX);
//        System.out.println("POSY = " + posY);
//        System.out.println("vPOSX = " + visualBoundary.getCenterX());
//        System.out.println("vPOSY = " + visualBoundary.getCenterY());
//        System.out.println("cPOSX = " + collisionBoundary.getCenterX());
//        System.out.println("cPOSY = " + collisionBoundary.getCenterY());
        facing = Movable.directionByKeyboard();
        collisionBoundary.setPosX((int)posX -this.getWidth()/6);
        collisionBoundary.setPosY((int)posY + (this.getHeight()/11));



        if(spriteCounter > 3){
            if(spriteNum == 16) {
                spriteNum = 1;
            }
            spriteNum++;
            spriteCounter = 0;
        }
        spriteCounter++;
    }


    public int[] checkTile(double calcPosX, double calcPosY){
        double newPosX = calcPosX;
        double newPosY = calcPosY;
        int playerLeftX = collisionBoundary.left();
        int playerRightX = collisionBoundary.right();
        int playerTopY = collisionBoundary.top();
        int playerBottomY = collisionBoundary.bottom();

        int t = LogicController.getInstance().getCurrentMap().getTileSize();
        int i = 0;


        int xplus = -1;
        int yplus = 0;
        if(facing == Direction.E || facing == Direction.NE || facing == Direction.SE){
            xplus = 1;
        }
        if(facing == Direction.S || facing == Direction.SE || facing == Direction.SW){
            yplus = 1;
        }

        boolean lrup = LogicController.getInstance().getCurrentMap().isCollidable((int)calcPosX+xplus*collisionBoundary.getWidth()/2, playerTopY);
        boolean lrdown = LogicController.getInstance().getCurrentMap().isCollidable((int)calcPosX+xplus*collisionBoundary.getWidth()/2, playerBottomY);
        boolean updownl = LogicController.getInstance().getCurrentMap().isCollidable(playerLeftX, (int)calcPosY + yplus*collisionBoundary.getHeight()+getHeight()/11);
        boolean updownr = LogicController.getInstance().getCurrentMap().isCollidable(playerRightX, (int)calcPosY+ yplus*collisionBoundary.getHeight()+getHeight()/11);
        //System.out.println(lrup);
        if(lrup){
            i = 1;
            newPosX = posX;
        } else if(lrdown){
            i = 1;
            newPosX= posX;
        }
        if(updownl){
            i = 1;
            newPosY = posY;
        } else if (updownr){
            i = 1;
            newPosY = posY;
        }


        return new int[]{i, (int)newPosX, (int) newPosY};
    }

    @Override
    public void move() {
        double calcPosX = posX + Movable.deltaX(speed,facing);
        double calcPosY = posY + Movable.deltaY(speed,facing);
        int[] collideChecker = checkTile(calcPosX,calcPosY);


        if(calcPosX < collisionBoundary.getWidth()/2) calcPosX = collisionBoundary.getWidth()/2;
        else if(calcPosX > LogicController.getInstance().getCurrentMap().getPhysicalWidth()- collisionBoundary.getWidth()/2)
            calcPosX = LogicController.getInstance().getCurrentMap().getPhysicalWidth()- collisionBoundary.getWidth()/2;
        if(calcPosY < collisionBoundary.getHeight()/2) calcPosY = collisionBoundary.getHeight()/2;
        else if(calcPosY > LogicController.getInstance().getCurrentMap().getPhysicalHeight()- collisionBoundary.getHeight()/2)
            calcPosY = LogicController.getInstance().getCurrentMap().getPhysicalHeight()- collisionBoundary.getHeight()/2;

        if(collideChecker[0] == 1){
            isColliding = true;
            calcPosX = collideChecker[1];
            calcPosY = collideChecker[2];

        }else isColliding = false;
        posX = calcPosX;
        posY = calcPosY;
        if(!updateVisualBoundary()){
            visualBoundary.setByCenterX(screenX/2);
            visualBoundary.setByCenterY(screenY/2);
        };


    }
    @Override
    public int getLayer() {
        return 5;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        switch (LogicController.getInstance().getGameState()){
            case LOADING -> {return false;}
            case WALK ,AFTERFISHING, BAITING , FISHING, LOADED-> {return true;}
        }
        return true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(LogicController.getInstance().getGameState() == GameState.BAITING ||
            LogicController.getInstance().getGameState() == GameState.FISHING){
            baitProgress = Math.min(baitProgress, 1.0);
            gc.setLineWidth(2.0);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(visualBoundary.getCenterX(),visualBoundary.getCenterY(),
                    visualBoundary.getCenterX()*(1-baitProgress)+baitX*baitProgress,
                    visualBoundary.getCenterY()*(1-baitProgress)+baitY*baitProgress);
            if(baitProgress==1.0){
                gc.setStroke(Color.BLACK);
                gc.setFill(Color.RED);
                gc.fillOval(baitX-5,baitY-5,10,10);
                gc.strokeOval(baitX-5,baitY-5,10,10);
            }
            baitProgress+=0.035;
        }
        switch(facing){
            case STABLE -> {
                if(isRight){
                    gc.drawImage(ResourcesLoader.w4,visualBoundary.left(),visualBoundary.top(),visualBoundary.getWidth(), visualBoundary.getHeight());
                } else{
                    gc.drawImage(ResourcesLoader.w4,visualBoundary.left()+160,visualBoundary.top(),-visualBoundary.getWidth(), visualBoundary.getHeight());
                }
            }
            case N, S ->{
                //gc.drawImage(ResourcesLoader.w1,visualBoundary.left(),visualBoundary.top(),visualBoundary.getWidth(), visualBoundary.getHeight());
                drawSP(gc,spriteCounter,spriteNum,isRight);
            }
            case NE, SE, E -> {
                isRight = true;
                drawSP(gc,spriteCounter,spriteNum,isRight);
            }
            case NW, SW , W-> {
                isRight = false;
                drawSP(gc,spriteCounter,spriteNum,isRight);
            }
        }
    }

    public void drawSP(GraphicsContext gc, int spriteCounter, int spriteNum, boolean isRight){
        Image image = null;
        if(spriteNum == 1 || spriteNum == 16){
            image = ResourcesLoader.w1;
        } else if(spriteNum == 2|| spriteNum == 15){
            image = ResourcesLoader.w2;
        } else if(spriteNum == 3 || spriteNum == 14){
            image = ResourcesLoader.w3;
        } else if(spriteNum == 4 || spriteNum == 13){
            image = ResourcesLoader.w4;
        } else if(spriteNum == 5 || spriteNum == 12){
            image = ResourcesLoader.w5;
        } else if(spriteNum == 6 || spriteNum == 11){
            image = ResourcesLoader.w6;
        } else if(spriteNum == 7 || spriteNum == 10){
            image = ResourcesLoader.w7;
        } else if(spriteNum == 8 || spriteNum == 9){
            image = ResourcesLoader.w8;
        }
        int resize = isRight ? 0 : 160;
        int rotate = isRight ? 1 : -1;

        gc.drawImage(image,visualBoundary.left()+resize,visualBoundary.top(),rotate*visualBoundary.getWidth(), visualBoundary.getHeight());
    }

    public boolean isReachable(double pointX, double pointY){
        double deltaX,deltaY;
        deltaX = LogicController.getInstance().getAnchorX()+pointX-posX;
        deltaY = LogicController.getInstance().getAnchorY()+pointY-posY;
        return (Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2)) < reachingDist);
    }

    public boolean isWarpable(){
        double pointX = MapName.getValidX(LogicController.getInstance().getCurrentMap()),pointY = MapName.getValidY(LogicController.getInstance().getCurrentMap());
        double deltaX,deltaY;
        deltaX = posX/LogicController.getInstance().getCurrentMap().getTileSize()-pointX;
        deltaY = posY/LogicController.getInstance().getCurrentMap().getTileSize()-pointY;
        //System.out.println((Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2))));
//        System.out.println(deltaX);
//        System.out.println(deltaY);
        return (Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2)) < LogicController.getInstance().getWarpDist()/LogicController.getInstance().getCurrentMap().getTileSize());
    }

    public void setValidPOS(Map map){
        setPosX(MapName.getValidX(map)*map.getTileSize());
        setPosY(MapName.getValidY(map)*map.getTileSize());

    }

    @Override
    public Boundary getCollisionBoundary() {
        return collisionBoundary;
    }


    @Override
    public Direction getDirection() {
        return facing;
    }

    @Override
    public void setDirection(Direction d) {
        this.facing = d;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setBaitX(double baitX) {
        this.baitX = baitX;
    }

    public void setBaitY(double baitY) {
        this.baitY = baitY;
    }

    public void setBaitProgress(double baitProgress) {
        this.baitProgress = baitProgress;
    }

}
