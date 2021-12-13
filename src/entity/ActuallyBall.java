package entity;

import Input.InputUtils;
import Input.KeyMap;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import entity.base.Collidable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
//import map.Seasons;
import entity.base.Boundary;


//PNG

public class ActuallyBall extends Entity implements Collidable, IRenderable {
    private boolean isNight;
    //private Seasons season;
    private Boundary collisionBoundary;
    private int z;


    public ActuallyBall(String name, int posX, int posY, int width, int height, boolean isNight){//, Seasons season){
        super(name, posX, posY, width, height);
        setCollisionBoundary(new Boundary(posX, posY, width, height));
        setNight(isNight);
        //setSeason(season);
        
    }

    public void update(){

        getVisualBoundary().setByCenterX((int)InputUtils.mouseX);
        getVisualBoundary().setByCenterY((int)InputUtils.mouseY);

    }


    @Override
    public int getLayer() {
        return 5;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(new WritableImage(ResourcesLoader.ballsri.getPixelReader(),
                (int)ResourcesLoader.ballsri.getWidth(),
                (int)ResourcesLoader.ballsri.getHeight()),
                getVisualBoundary().left(), getVisualBoundary().top());
       // gc.translate(InputUtils.mouseX,InputUtils.mouseY);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }


    public void setCollisionBoundary(Boundary a){
        this.collisionBoundary = a;
    }

    public Boundary getCollisionBoundary(){
        return collisionBoundary;
    }

    public void setNight(boolean isNight){
        this.isNight = isNight;
    }

    public boolean getNight(){
        return isNight;
    }

//    public void setSeason(Seasons season){
//        this.season = season;
//    }
//
//    public Seasons getSeason(){
//        return season;
//    }


}
