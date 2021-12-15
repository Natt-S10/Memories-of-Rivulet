package UIpanel.fishing;

import Logic.GameState;
import Logic.LogicController;
import Renderer.IRenderable;
import UIpanel.IPanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.invoke.LambdaConversionException;

public class FishingPanel implements IRenderable {
    //for Panel Background
    private static final int panelW = 640,
                            panelH =  360;
    private int anchorX, anchorY;
    private boolean isVisible;
    private Timer timer;
    private QuickTimeSign qtSign;

    public FishingPanel(int screenW, int screenH){
        this.anchorX = (screenW-panelW)/2;
        this.anchorY = (screenH-panelH)/2;
        isVisible = false;
        timer = new Timer(320,130);
        qtSign = new QuickTimeSign(25, 220);
    }

    @Override
    public int getLayer() {
        return 10;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(anchorX,anchorY,panelW, panelH, 6,6);
        gc.strokeRoundRect(anchorX,anchorY,panelW, panelH, 6,6);
        timer.draw(anchorX, anchorY, gc);
        qtSign.draw(anchorX, anchorY, gc);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return LogicController.getInstance().getGameState() == GameState.FISHING;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void update(){
        timer.update();

    }
}
