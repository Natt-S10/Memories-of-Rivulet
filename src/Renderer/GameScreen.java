package Renderer;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class GameScreen extends Canvas {
    public GameScreen(double w, double h) {
        super(w, h);
        this.setVisible(true);
        GameScreenUtils.addListener(this);
    }

    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (IRenderable element : RenderableHolder.getInstance().getElements()) {
            if (element.isVisible() && !element.isDestroyed()) {
                element.draw(gc);
            }
        }
    }
}
