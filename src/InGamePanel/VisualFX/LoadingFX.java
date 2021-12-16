package InGamePanel.VisualFX;

import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;

public class LoadingFX implements   IRenderable {
    private final int posX;
    private final int posY;
    private int animateCounter, spriteNum;
    private int z;
    private  boolean isVisible;
    public LoadingFX() {
        this.posX = GameScreen.screenWidth/2;
        this.posY = GameScreen.screenHeight/2;
        this.spriteNum = 0;
        animateCounter =0;
        z= 100;
        isVisible = true;
    }

    @Override
    public int getLayer() {
        return z;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(ResourcesLoader.loadings[spriteNum],ResourcesLoader.sceneW/2-98, ResourcesLoader.sceneH/2-37, 196, 74);
        update();
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    private void update(){
        animateCounter++;
        if(animateCounter > 12) {
            animateCounter = 0;
            spriteNum++;
            if(spriteNum == 6) spriteNum =0;

        }
    }




}
