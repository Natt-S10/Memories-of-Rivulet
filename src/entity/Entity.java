package entity;

import entity.base.Boundary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity { // sort by greater Y then Smaller X
    protected String name;
    protected Boundary visualBoundary;
    protected double posX, posY;

    public Entity(String name, double posX, double posY, int width, int height) {
        this.name = name;
        this.visualBoundary = new Boundary(width,height);
        this.posX = posX;
        this.posY = posY;

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
}
