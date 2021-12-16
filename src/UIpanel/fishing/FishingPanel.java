package UIpanel.fishing;

import Logic.GameState;
import Logic.LogicController;
import Renderer.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.lang.invoke.LambdaConversionException;

public class FishingPanel implements IRenderable, Serializable {
    //for Panel Background
    private static final int panelW = 640,
                            panelH =  360;
    private int anchorX, anchorY;
    private boolean isVisible;
    private Timer timer;
    private QuickTimeSign qtSign;
    private SpinningRod spinningRod;

    public FishingPanel(int screenW, int screenH){
        this.anchorX = (screenW-panelW)/2;
        this.anchorY = (screenH-panelH)/2;
        isVisible = false;
        timer = new Timer(428,120);
        qtSign = new QuickTimeSign(380, 180);
        spinningRod = new SpinningRod(37,-75);
    }

    @Override
    public int getLayer() {
        return 10;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(anchorX,anchorY,panelW, panelH, 30,30);
        gc.strokeRoundRect(anchorX,anchorY,panelW, panelH, 30,30);
        timer.draw(anchorX, anchorY, gc);
        qtSign.draw(anchorX, anchorY, gc);
        spinningRod.draw(anchorX,anchorX,gc);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        boolean isVisible;
        switch (LogicController.getInstance().getGameState()){
            case FISHING, AFTERFISHING -> isVisible = true;
            default -> isVisible = false;
        }
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}
