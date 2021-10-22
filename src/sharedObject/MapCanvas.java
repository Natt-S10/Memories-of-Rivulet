package sharedObject;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapCanvas extends Canvas {
    public MapCanvas (double w, double h){
        super(w,h);
        this.setVisible(true);
    }

    public void paintComponent(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for(IRenderable entity: RenderableHolder.getInstance().getEntities()){
            if(entity.isVisible() && !entity.isDestroyed()){
                entity.draw(gc);
            }
        }
    }
}
