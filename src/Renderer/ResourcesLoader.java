package Renderer;

import javafx.scene.image.Image;

public class ResourcesLoader {
    public static final Image dirt;
    public static final Image water;
    public static final Image ballsri;
    //public static final Image mainCharacter;

    static {
        String dirtImgPath = "dirt.bmp";
        String waterImgPath = "water.bmp";
        String mainCharImgPath = "sth.bmp";
        String ballsriPath = "Ball.png";

        dirt = new Image(ClassLoader.getSystemResource(dirtImgPath).toString());
        water = new Image(ClassLoader.getSystemResource(waterImgPath).toString());
        //mainCharacter = new Image(ClassLoader.getSystemResource(mainCharImgPath).toString());
        ballsri = new Image(ClassLoader.getSystemResource(ballsriPath).toString());
    }
}
