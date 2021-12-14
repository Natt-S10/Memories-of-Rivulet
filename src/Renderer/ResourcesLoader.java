package Renderer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourcesLoader {
    public static final String demo_map;
    public static final String River_map;
    public static final String Beach_map;
    public static final String Waterfall_map;

    public static Image ballsri;
    public static WritableImage dirt16;
    public static WritableImage water16;
    public static WritableImage water16_2;
    public static Image rpg;
    public static final Image w1,w2,w3,w4,w5,w6,w7,w8;

    static {
        demo_map = "res/demoMap.csv";
        River_map = "";
        Beach_map = "";
        Waterfall_map = "";




        String ballsriPath = "Ball.png";
        String rpgPath = "rpg.png";
        String rpg80 = "roguelikeSheetx5.png";
        int size = 160;

        String walk1 = "player/Frame_1.png";
        String walk2 = "player/Frame_2.png";
        String walk3 = "player/Frame_3.png";
        String walk4 = "player/Frame_4.png";
        String walk5 = "player/Frame_5.png";
        String walk6 = "player/Frame_6.png";
        String walk7 = "player/Frame_7.png";
        String walk8 = "player/Frame_8.png";



        w1 = new Image((ClassLoader.getSystemResource(walk1).toString()));
        w2 = new Image((ClassLoader.getSystemResource(walk2).toString()));
        w3 = new Image((ClassLoader.getSystemResource(walk3).toString()));
        w4 = new Image((ClassLoader.getSystemResource(walk4).toString()));
        w5 = new Image((ClassLoader.getSystemResource(walk5).toString()));
        w6 = new Image((ClassLoader.getSystemResource(walk6).toString()));
        w7 = new Image((ClassLoader.getSystemResource(walk7).toString()));
        w8 = new Image((ClassLoader.getSystemResource(walk8).toString()));

        try{
            rpg = new Image(ClassLoader.getSystemResource(rpgPath).toString());
            dirt16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),6 * (size+10),0, size, size);
            water16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),0,0, size, size);
            water16_2 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),size+10,0, size, size);
            ballsri = new Image(ClassLoader.getSystemResource(ballsriPath).toString());
        } catch(Exception e){

        }



    }
}
