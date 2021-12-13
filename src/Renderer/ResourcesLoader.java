package Renderer;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ResourcesLoader {


    public static final Image ballsri;
    public static WritableImage dirt16;
    public static WritableImage water16;
    public static Image rpg;
   //public static final Image w1,w2,w3,w4,w5,w6,w7,w8;
    public static Image w1160;//,w2160,w3160,w4160,w5160,w6160,w7160,w8160;




    static {

        //String dirtImgPath = "dirt.bmp";
        //String waterImgPath = "water.bmp";
        String mainCharImgPath = "sth.bmp";
        String ballsriPath = "Ball.png";
        String rpgPath = "rpg.png";
        //String water16Path = "Ball.png";
        int size = 160;

//        String walk1 = "/player/Frame_1.png";
        String walk1160 = "player/Frame_1_160.png";
//        String walk2 = "/player/Frame_2.png";
//        String walk3 = "/player/Frame_3.png";
//        String walk4 = "/player/Frame_4.png";
//        String walk5 = "/player/Frame_5.png";
//        String walk6 = "/player/Frame_6.png";
//        String walk7 = "/player/Frame_7.png";
//        String walk8 = "/player/Frame_8.png";
        w1160 = new Image((ClassLoader.getSystemResource(walk1160).toString()));
//        w1 = new Image((ClassLoader.getSystemResource(walk1).toString()));
//        w2 = new Image((ClassLoader.getSystemResource(walk2).toString()));
//        w3 = new Image((ClassLoader.getSystemResource(walk3).toString()));
//        w4 = new Image((ClassLoader.getSystemResource(walk4).toString()));
//        w5 = new Image((ClassLoader.getSystemResource(walk5).toString()));
//        w6 = new Image((ClassLoader.getSystemResource(walk6).toString()));
//        w7 = new Image((ClassLoader.getSystemResource(walk7).toString()));
//        w8 = new Image((ClassLoader.getSystemResource(walk8).toString()));




        //dirt = new Image(ClassLoader.getSystemResource(dirtImgPath).toString());
        //water = new Image(ClassLoader.getSystemResource(waterImgPath).toString());
        //mainCharacter = new Image(ClassLoader.getSystemResource(mainCharImgPath).toString());
        ballsri = new Image(ClassLoader.getSystemResource(ballsriPath).toString());

        rpg = new Image(ClassLoader.getSystemResource(rpgPath).toString());
        dirt16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),6 * (size+10),0, size, size);
        water16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),0,0, size, size);










    }
}
