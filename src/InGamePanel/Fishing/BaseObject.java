package InGamePanel.Fishing;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {
    protected int refpointX, refpointY;

    public abstract void draw(int panelX, int panelY, GraphicsContext gc);
}
