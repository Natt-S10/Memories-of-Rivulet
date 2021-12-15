package UIpanel.fishing;

import Logic.LogicController;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;

public class SpinningRod extends BaseObject{
    int spriteNum, animationCounter;

    public SpinningRod(int anchorX, int anchorY){
        spriteNum = animationCounter =0;
        this.refpointX = anchorX;
        this.refpointY = anchorY;
    }



    @Override
    public void draw(int panelX, int panelY, GraphicsContext gc) {
        if(animationCounter > 3){
            spriteNum = (spriteNum+1)%13;
            animationCounter =0;
        }
        animationCounter++;
        gc.drawImage(ResourcesLoader.spin_rod[
                (LogicController.getInstance().isFishCaught())?(13+spriteNum/7):spriteNum],
                panelX +refpointX, panelY +refpointY,256,256);
    }
}
