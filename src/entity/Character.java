package entity;

import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import entity.base.Boundary;
import entity.base.Collidable;
import entity.base.Direction;
import entity.base.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Character extends Entity implements IRenderable, Movable, Collidable {
    private Boundary collisionBoundary;
    private Direction facing;
    private double speed;
    public final int screenX;
    public final int screenY;

    public Character(String name, int posX, int posY, int width, int height, double speed) {
        super(name, posX, posY, width, height);
        collisionBoundary = new Boundary(posX+(width/4),posY+(height/2),width/2,height/2);
        facing = Direction.STABLE;
        this.speed = speed;
        screenX = GameScreen.screenWidth;
        screenY = GameScreen.screenHeight;
        visualBoundary.setByCenterX(screenX/2);
        visualBoundary.setByCenterY(screenY/2);
    }

    @Override
    public void update() {
        facing = Movable.directionByKeyboard();
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
       //gc.setFill(Color.BLACK);
        //gc.fillRect(visualBoundary.left(),visualBoundary.top()
                //,visualBoundary.getWidth(), visualBoundary.getHeight());
        gc.drawImage(ResourcesLoader.w1160,visualBoundary.left(),visualBoundary.top(),visualBoundary.getWidth(), visualBoundary.getHeight());
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
