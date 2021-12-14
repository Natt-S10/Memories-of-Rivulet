package UIpanel.fishing;

import javafx.scene.canvas.GraphicsContext;

public abstract class BaseObject {
    protected int refpointX, refpointY;

    public abstract void draw(int refX, int refY, GraphicsContext gc);
    public abstract void update();
}
