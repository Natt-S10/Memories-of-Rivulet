package InGamePanel.Fishing;

import Logic.LogicController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.Serializable;

public class Timer extends BaseObject implements Serializable {
    private static final int DIAMETER = 90;

    public Timer(int centerX,int centerY){
        refpointX = centerX-DIAMETER/2;
        refpointY = centerY-DIAMETER/2;
    }


    @Override
    public void draw(int panelX, int panelY, GraphicsContext gc) {
        int arcAngle = (int)(LogicController.getInstance().getFishingTimeRatio()*360);
        gc.setFill(Color.RED);
        gc.fillArc(panelX + refpointX, panelY + refpointY,DIAMETER, DIAMETER,90,arcAngle, ArcType.ROUND);
        gc.strokeArc(panelX + refpointX, panelY + refpointY,DIAMETER, DIAMETER,90,arcAngle, ArcType.ROUND);
    }

}
