package UIpanel.fishing;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {
    protected int refpointX, refpointY;

    public abstract void draw(int refX, int refY, GraphicsContext gc);
    public abstract void update();
}
