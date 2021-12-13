package Renderer;

import entity.Character;
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
    public static WritableImage grass;
    public static WritableImage grass_water_up;
    public static WritableImage grass_waterdown;
    public static Image logo_river;
    public static Image logo_sea;
    public static Image logo_waterfall;
    public static Image logo_fire;
    public static Image rpg;
    public static Image logo;
    public static final Image w1,w2,w3,w4,w5,w6,w7,w8;
    public static final Image fireworkSP;
    public static final WritableImage[] fireworks;

    public static  Character mainChar;

    public static final int sceneW = 1280;
    public static final int sceneH = 720;

    static {
        demo_map = "res/demoMap1.csv";
        River_map = "res/demoMap1.csv";
        Beach_map = "res/demoMap.csv";
        Waterfall_map = "";

        mainChar = new Character("Steve",
                sceneW/2, sceneH/2,160,220, 7,5);




        String ballsriPath = "Ball.png";
        String rpgPath = "rpg.png";
        String rpg80 = "roguelikeSheetx5.png";
        String logoRPath = "logo_river.png";
        String logoSPath = "logo_sea.png";
        String logoWPath = "logo_waterfall.png";
        String logoFPath = "logo_fire.png";
        int size = 160;
        String fireworkPath = "Firework.png";
        int logoW = 328;
        int logoH = 302;

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

        fireworkSP = new Image(ClassLoader.getSystemResource(fireworkPath).toString());
        fireworks = loadFireworks();
        try{
            rpg = new Image(ClassLoader.getSystemResource(rpgPath).toString());

            logo_river = new Image(ClassLoader.getSystemResource(logoRPath).toString());
            logo_sea = new Image(ClassLoader.getSystemResource(logoSPath).toString());
            logo_waterfall = new Image(ClassLoader.getSystemResource(logoWPath).toString());
            logo_fire = new Image(ClassLoader.getSystemResource(logoFPath).toString());

            dirt16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),6 * (size+10),0, size, size);
            water16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),0,0, size, size);
            water16_2 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),size+10,0, size, size);
            grass = new WritableImage(ResourcesLoader.rpg.getPixelReader(),5*(size+10),0, size, size);
            ballsri = new Image(ClassLoader.getSystemResource(ballsriPath).toString());
        } catch(Exception e){

        }



    }

    private static WritableImage[] loadFireworks(){
        WritableImage[] fw = new WritableImage[30];
        int counter=0;
        for(int i=0; i<6;i++){
            for(int j=0; j<5; j++){
                fw[counter++] = new WritableImage(fireworkSP.getPixelReader(),256*i,256*j,256,256);
            }
        }
        return fw;
    }
}
