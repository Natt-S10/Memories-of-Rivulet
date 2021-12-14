package UIpanel.fishing;

import Logic.LogicController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Timer extends BaseObject{
    private static final int DIAMETER = 70;
    private int arcAngle;

    public Timer(int centerX,int centerY){
        refpointX = centerX-DIAMETER/2;
        refpointY = centerY-DIAMETER/2;
        arcAngle = 360;
    }


    @Override
    public void draw(int refX,int refY,GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillArc(refX+ refpointX, refY+ refpointY,DIAMETER, DIAMETER,90,arcAngle, ArcType.ROUND);
        gc.strokeArc(refX+ refpointX, refY+ refpointY,DIAMETER, DIAMETER,90,arcAngle, ArcType.ROUND);
    }

    @Override
    public void update() {
        arcAngle = (int)(LogicController.getInstance().getFishingTimeRatio()*360);
    }
}
