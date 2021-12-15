package entity;

import entity.base.Boundary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class Entity implements Serializable { // sort by greater Y then Smaller X
    protected String name;
    protected Boundary visualBoundary;
    protected double posX, posY;
    protected int width;
    protected int height;

    public Entity(String name, double posX, double posY, int width, int height) {
        this.name = name;
        this.visualBoundary = new Boundary(width,height);
        setHeight(height);
        setWidth(width);
        this.posX = posX;
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void draw(GraphicsContext gc);

    //general getter&setter
    public Boundary getVisualBoundary(){
        return visualBoundary;
    }
    //setters

    public String getName() {
        return name;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
