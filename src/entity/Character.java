package entity;

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
    private boolean isRight;
    private double speed;
    private int spriteCounter;
    private int spriteNum;
    public final int screenX;
    public final int screenY;

    public Character(String name, int posX, int posY, int width, int height, double speed) {
        super(name, posX, posY, width, height);
        collisionBoundary = new Boundary(posX+(width/4),posY+(height/2),width/2,height/2);
        facing = Direction.STABLE;
        isRight = true;
        spriteCounter = 0;
        spriteNum = 1;
        this.speed = speed;
        screenX = GameScreen.screenWidth;
        screenY = GameScreen.screenHeight;
        visualBoundary.setByCenterX(screenX/2);
        visualBoundary.setByCenterY(screenY/2);
    }

    @Override
    public void update() {
        facing = Movable.directionByKeyboard();

        if(spriteCounter > 4){
            if(spriteNum == 16) {
                spriteNum = 1;
            }
            spriteNum++;
            spriteCounter = 0;
        }
        spriteCounter++;

    }

    @Override
    public void move() {
        double calcPosX = posX + Movable.deltaX(speed,facing);
        double calcPosY = posY + Movable.deltaY(speed,facing);
        posX = calcPosX;
        posY = calcPosY;

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
}
