package UIpanel.VisualFX;

import Logic.GameState;
import Logic.LogicController;
import Renderer.IRenderable;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class FishCaughtFX implements IRenderable, Serializable {
    private boolean isVisible;
    private Twinkle[] twinkles;

    public FishCaughtFX(){
        int[] pXs = {279,604,373,876,1011};
        int[] pYs = {187,132,615,543,206};
        twinkles = new Twinkle[5];
        for(int i=0; i<pXs.length; i++){
            twinkles[i] = new Twinkle(pXs[i],pYs[i],100,0);
        }
        randomWink();
    }

    private void randomWink(){
        for(Twinkle each:twinkles){
            each.setSpriteNum((int)(Math.random()*30));
        }
    }


    @Override
    public int getLayer() {
        return 11;
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Twinkle eachWink:twinkles){
            eachWink.draw(gc);
        }
    }

    public void update(){
        if(LogicController.getInstance().getGameState() == GameState.AFTERFISHING &&
            !isVisible){
            randomWink();
        }
        isVisible = LogicController.getInstance().getGameState() == GameState.AFTERFISHING;
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
