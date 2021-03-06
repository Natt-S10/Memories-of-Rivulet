package InGamePanel.VisualFX;

import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class Twinkle implements Serializable {
    private int posX, posY, size;
    private int animateCounter, spriteNum;

    public Twinkle(int posX, int posY,int size,int spriteNum) {
        this.posX = posX-size/2;
        this.posY = posY-size/2;
        this.size = size;
        this.spriteNum = spriteNum;
        animateCounter =0;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(ResourcesLoader.fireworks[spriteNum],posX, posY, size, size);

        update();
    }

    private void update(){
        animateCounter++;
        if(animateCounter > 4) {
            animateCounter = 0;
            spriteNum++;
            if (spriteNum == 30) spriteNum = 0;
        }
    }

    public void setSpriteNum(int sN){
        animateCounter = 0;
        sN = Math.max(0,sN);
        sN = Math.min(29,sN);
        spriteNum = sN;
    }

}
