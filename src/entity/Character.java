package entity;

import Logic.GameState;
import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import entity.base.Boundary;
import entity.base.Collidable;
import entity.base.Direction;
import entity.base.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Character extends Entity implements IRenderable, Movable, Collidable {
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
    private void updateVisualBoundary(){
        if(posX <= screenX/2) visualBoundary.setByCenterX((int)posX);
        else if(posX >= LogicController.getInstance().getCurrentMap().getPhysicalWidth() - (screenX/2))
            visualBoundary.setByCenterX((int)(posX-LogicController.getInstance().getCurrentMap().getPhysicalWidth()+screenX));
        if(posY <= screenY/2) visualBoundary.setByCenterY((int)posY);
        else if(posY >= LogicController.getInstance().getCurrentMap().getPhysicalHeight() - (screenY/2))
            visualBoundary.setByCenterY((int)(posY-LogicController.getInstance().getCurrentMap().getPhysicalHeight()+screenY));
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
        facing = Movable.directionByKeyboard();
        checkCollide();
        collisionBoundary.setPosX((int)posX  -this.getWidth()/6);
        collisionBoundary.setPosY((int)posY+(this.getHeight()/11));
        //System.out.println(height);
        //System.out.println(isColliding);
        if(spriteCounter > 3){
            if(spriteNum == 16) {
                spriteNum = 1;
            }
            spriteNum++;
            spriteCounter = 0;
        }
        spriteCounter++;

    }
    //check objects and check tiles
    public void checkCollide(){

        double posx = 0; double posx2 = 0; double posx3 = 0;
        double posy = 0; double posy2 = 0; double posy3 = 0;
        boolean sCase = false;
        switch(facing){
            case N-> {
                posx += collisionBoundary.left();
                posx2 += collisionBoundary.right();
                posy += collisionBoundary.top();
                posy2 = posy;
            }
            case NE -> {
                sCase= true;
                posx += collisionBoundary.left();
                posx2 += collisionBoundary.right();
                posy += collisionBoundary.top();
                posy2 = posy;
                posx3 = posx2;
                posy3 = collisionBoundary.bottom();

            }
            case NW -> {
                sCase = true;
                posx += collisionBoundary.left();
                posx2 += collisionBoundary.left();
                posy += collisionBoundary.top();
                posy2 = posy;
                posx3 = posx2;
                posy3 = collisionBoundary.bottom();
            }
            case E -> {

                posx = collisionBoundary.right();
                posx2 = posx;
                posy = collisionBoundary.top();
                posy2 = collisionBoundary.bottom();

            }
            case W -> {

                posx = collisionBoundary.left();
                posx2 = posx;
                posy = collisionBoundary.top();
                posy2 = collisionBoundary.bottom();

            }
            case S -> {
                posx += collisionBoundary.left();
                posx2 = collisionBoundary.right();
                posy += collisionBoundary.bottom();
                posy2 = posy;

            }
            case SW -> {
                sCase = true;
                posx += collisionBoundary.left();
                posx2 += collisionBoundary.right();
                posy += collisionBoundary.bottom();
                posy2 = posy;
                posx3 = posx;
                posy3 += collisionBoundary.top();
            }
            case SE -> {
                sCase = true;
                posx += collisionBoundary.right();
                posx2 += collisionBoundary.left();
                posy += collisionBoundary.bottom();
                posy2 = posy;
                posx3 = posx;
                posy3 = collisionBoundary.top();
            }

        }
//        System.out.println("X = " +posx );
//        System.out.println("Y = " +posy );
        if(LogicController.getInstance().getCurrentMap().isCollidable((int)posx, (int)posy) || LogicController.getInstance().getCurrentMap().isCollidable((int)posx2, (int)posy2)){
            if(sCase){
                if (LogicController.getInstance().getCurrentMap().isCollidable((int)posx3, (int)posy3)){
                    isColliding = true;
                    return;
                }
            }
            isColliding = true;
            return;
        }


        isColliding = false;
        return;


    }

    @Override
    public void move() {
        if(!isColliding){
            double calcPosX = posX + Movable.deltaX(speed,facing);
            double calcPosY = posY + Movable.deltaY(speed,facing);

            if(calcPosX < visualBoundary.getWidth()/2) calcPosX = visualBoundary.getWidth()/2;
            else if(calcPosX > LogicController.getInstance().getCurrentMap().getPhysicalWidth()- visualBoundary.getWidth()/2)
                calcPosX = LogicController.getInstance().getCurrentMap().getPhysicalWidth()- visualBoundary.getWidth()/2;
            if(calcPosY < visualBoundary.getHeight()/2) calcPosY = visualBoundary.getHeight()/2;
            else if(calcPosY > LogicController.getInstance().getCurrentMap().getPhysicalHeight()- visualBoundary.getHeight()/2)
                calcPosY = LogicController.getInstance().getCurrentMap().getPhysicalHeight()- visualBoundary.getHeight()/2;
            posX = calcPosX;
            posY = calcPosY;
            updateVisualBoundary();
        }

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
                    gc.drawImage(ResourcesLoader.w1,visualBoundary.left(),visualBoundary.top(),visualBoundary.getWidth(), visualBoundary.getHeight());
                } else{
                    gc.drawImage(ResourcesLoader.w1,visualBoundary.left()+160,visualBoundary.top(),-visualBoundary.getWidth(), visualBoundary.getHeight());
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
