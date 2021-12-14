package UIcontainer;

import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class UIButton  implements IRenderable {
    private Image image;
    private boolean isVisible;
    private int z;
    private double posX;
    private double posY;
    private int size;



    public UIButton(Image image, int size){
        this.image = image;
        this.z = 200;
        this.isVisible = true;
        this.posX =  GameScreen.screenWidth - 60;
        this.posY = 60;
        this.size = size;
    }

    public void pressButton(){

    }


    @Override
    public int getLayer() {
        return z;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(isVisible){
            gc.drawImage(image,posX,posY,size,size);
        } else{
            gc.drawImage(image,posX,posY,size,size);
        }

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }
}
