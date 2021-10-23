package sharedObject;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
    public int getLayer();

    public void draw(GraphicsContext gc);

    public boolean isDestroyed();

    public boolean isVisible();
}
