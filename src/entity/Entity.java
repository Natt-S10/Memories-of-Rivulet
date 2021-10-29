package entity;

import entity.base.Boundary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity { // sort by greater Y then Smaller X
    private String name;
    private Boundary visualBoundary;


    public Entity(String name, int posX, int posY, int width, int height) {
        this.name = name;
        this.visualBoundary = new Boundary(posX,posY,width,height);

    }

    public abstract void draw(GraphicsContext gc);

    //general getter&setter
    public Boundary getVisualBoundary(){
        return visualBoundary;
    }
    //setters

}
