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
    public static final String Loading_map;

    public static Image ballsri;
    public static Image fishing_rodSP;
    public static Image spin_rodSP;
    public static WritableImage wood;
    public static WritableImage dirt16;
    public static WritableImage water16;
    public static WritableImage water16_2;
    public static WritableImage grass;
    public static WritableImage grass_water_up;
    public static WritableImage grass_water_down;
    public static WritableImage sand;
    public static WritableImage load;

    public static Image logo_river;
    public static Image logo_sea;
    public static Image logo_waterfall;
    public static Image logo_fire;
    public static Image logo_river_B;
    public static Image logo_sea_B;
    public static Image logo_waterfall_B;
    public static Image logo_fire_B;
    public static Image rpg;
    public static Image logo;

    public static final Image w1,w2,w3,w4,w5,w6,w7,w8,wShow;
    public static final Image fireworkSP;
    public static final Image loadingSP;
    public static final WritableImage[] fireworks;
    public static final WritableImage[] fishingRod;
    public static final WritableImage[] spin_rod;
    public static final WritableImage[] loadings;

    public static  Character mainChar;

    public static final int sceneW = 1280;
    public static final int sceneH = 720;



    static {
        demo_map = "res/demoMap.csv";
        River_map = "res/River_Map.csv";
        Beach_map = "res/Beach_Map.csv";
        Waterfall_map = "res/demoMap1.csv";
        Loading_map = "res/loadingMap.csv";

        mainChar = new Character("Steve",
                sceneW/2, sceneH/2,160,220, 7,270);




        String ballsriPath = "Ball.png";
        String fishing_rodPath = "fishing_rod.png";
        String rpgPath = "rpg.png";
        String rpg80 = "roguelikeSheetx5.png";
        String logoRPath = "logo/logo_river.png";
        String logoSPath = "logo/logo_sea.png";
        String logoWPath = "logo/logo_waterfall.png";
        String logoFPath = "logo/logo_fire.png";
        String logoRBPath = "logo/logo_river_BLOCK.png";
        String logoSBPath = "logo/logo_sea_BLOCK.png";
        String logoWBPath = "logo/logo_waterfall_BLOCK.png";
        String logoFBPath = "logo/logo_fire_BLOCK.png";
        String loadingPath = "heart.png";

        int size = 160;
        int logoW = 328;
        int logoH = 302;

        String fireworkPath = "Firework.png";
        String spin_rodPath = "spin_rod.png";

        String walk1 = "player/Frame_1.png";
        String walk2 = "player/Frame_2.png";
        String walk3 = "player/Frame_3.png";
        String walk4 = "player/Frame_4.png";
        String walk5 = "player/Frame_5.png";
        String walk6 = "player/Frame_6.png";
        String walk7 = "player/Frame_7.png";
        String walk8 = "player/Frame_8.png";
        String showFish = "player/Show-fish.png";



        w1 = new Image((ClassLoader.getSystemResource(walk1).toString()));
        w2 = new Image((ClassLoader.getSystemResource(walk2).toString()));
        w3 = new Image((ClassLoader.getSystemResource(walk3).toString()));
        w4 = new Image((ClassLoader.getSystemResource(walk4).toString()));
        w5 = new Image((ClassLoader.getSystemResource(walk5).toString()));
        w6 = new Image((ClassLoader.getSystemResource(walk6).toString()));
        w7 = new Image((ClassLoader.getSystemResource(walk7).toString()));
        w8 = new Image((ClassLoader.getSystemResource(walk8).toString()));
        wShow = new Image((ClassLoader.getSystemResource(showFish).toString()));

        fireworkSP = new Image(ClassLoader.getSystemResource(fireworkPath).toString());
        fireworks = loadFireworks();

        fishing_rodSP = new Image(ClassLoader.getSystemResource(fishing_rodPath).toString());
        fishingRod = loadFishing_rod();

        spin_rodSP = new Image(ClassLoader.getSystemResource(spin_rodPath).toString());
        spin_rod = loadSpinRod();

        loadingSP = new Image(ClassLoader.getSystemResource(loadingPath).toString());
        loadings = new WritableImage[6];
        try{
            rpg = new Image(ClassLoader.getSystemResource(rpgPath).toString());

            logo_river = new Image(ClassLoader.getSystemResource(logoRPath).toString());
            logo_sea = new Image(ClassLoader.getSystemResource(logoSPath).toString());
            logo_waterfall = new Image(ClassLoader.getSystemResource(logoWPath).toString());
            logo_fire = new Image(ClassLoader.getSystemResource(logoFPath).toString());
            logo_river_B = new Image(ClassLoader.getSystemResource(logoRBPath).toString());
            logo_sea_B = new Image(ClassLoader.getSystemResource(logoSBPath).toString());
            logo_waterfall_B = new Image(ClassLoader.getSystemResource(logoWBPath).toString());
            logo_fire_B = new Image(ClassLoader.getSystemResource(logoFBPath).toString());

            dirt16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),6 * (size+10),0, size, size);
            water16 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),0,0, size, size);
            water16_2 = new WritableImage(ResourcesLoader.rpg.getPixelReader(),size+10,0, size, size);
            grass = new WritableImage(ResourcesLoader.rpg.getPixelReader(),5*(size+10),0, size, size);
            wood = new WritableImage(ResourcesLoader.rpg.getPixelReader(),35 * (size+10),17 * (size+10), size, size);
            sand = new WritableImage(ResourcesLoader.rpg.getPixelReader(),8 * (size+10),0, size, size);
            grass_water_up = new WritableImage(ResourcesLoader.rpg.getPixelReader(),3*(size+10),0, size, size);
            grass_water_down = new WritableImage(ResourcesLoader.rpg.getPixelReader(),3*(size+10),2*(size+15), size, size);
            load = new WritableImage(ResourcesLoader.rpg.getPixelReader(),7*(size+10),27*(size+10), size, size);






            loadings[0] = new WritableImage(loadingSP.getPixelReader(),34,34+0*101,196,74);
            loadings[1] = new WritableImage(loadingSP.getPixelReader(),34,34+1*101,196,74);
            loadings[2] = new WritableImage(loadingSP.getPixelReader(),34,34+2*101,196,74);
            loadings[3] = new WritableImage(loadingSP.getPixelReader(),263,34+0*101,196,74);
            loadings[4] = new WritableImage(loadingSP.getPixelReader(),263,34+1*101,196,74);
            loadings[5] = new WritableImage(loadingSP.getPixelReader(),263,34+2*101,196,74);
            ballsri = new Image(ClassLoader.getSystemResource(ballsriPath).toString());
        } catch(Exception e){
            e.printStackTrace();
        }



    }

    private static WritableImage[] loadFishing_rod(){
        WritableImage[] frameArray = new WritableImage[3];
        int counter =0;
        for(int i=0; i<3;i++){
            frameArray[counter++] = new WritableImage(fishing_rodSP.getPixelReader(),64*i,0, 64,64);
        }
        return frameArray;
    }

    private static WritableImage[] loadFireworks(){
        WritableImage[] frameArray = new WritableImage[30];
        int counter=0;
        for(int i=0; i<6;i++){
            for(int j=0; j<5; j++){
                frameArray[counter++] = new WritableImage(fireworkSP.getPixelReader(),256*i,256*j,256,256);
            }
        }
        return frameArray;
    }

    private static WritableImage[] loadingLogo(){
        WritableImage[] loading = new WritableImage[6];

        for(int i = 0; i < 6; i++){
            if(i < 3){
                loading[i] = new WritableImage(loadingSP.getPixelReader(),34,34+i*101,74,196);
            } else{
                loading[i] = new WritableImage(loadingSP.getPixelReader(),263,34+(i-3)*101,74,196);
            }
        }
        return loading;
    }

    private static WritableImage[] loadSpinRod(){
        WritableImage[] frameArray = new WritableImage[15];
        int size= 256;
        int counter=0;
        for(int i=0; i<frameArray.length; i++){
            frameArray[counter++] = new WritableImage(spin_rodSP.getPixelReader(),size*i,0, size,size);
        }
        return frameArray;
    }
}
