package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;

public class Tile {
    private static final Image tileRawImage = new Image(ClassLoader.getSystemResource("").toString());
    public static final WritableImage tileImage = new WritableImage(tileRawImage.getPixelReader(), 0,0);
    protected TileType type;

}
