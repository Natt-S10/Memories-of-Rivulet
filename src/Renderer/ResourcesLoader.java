package Renderer;

import Logic.LogicController;
import InGamePanel.MoneyStatus.MoneyBar;
import InGamePanel.VisualFX.FishCaughtFX;
import InGamePanel.Fishing.FishingPanel;
import Entity.Character;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

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
    public static WritableImage stone;
    public static WritableImage spawn;

    public static Image logo_river;
    public static Image logo_sea;
    public static Image logo_waterfall;
    public static Image logo_fire;
    public static Image logo_river_B;
    public static Image logo_sea_B;
    public static Image logo_waterfall_B;
    public static Image logo_fire_B;
    public static Image rpg;
    public static Image button_play;
    public static Image button_load;
    public static Image button_option;
    public static Image button_resume;
    public static Image button_exit;
    public static Image button_menu;
    public static Image button_fish;
    public static Image block;
    public static Image moneyBarImg;
    public static Image larrow;
    public static Image rarrow;
    public static final Image w1,w2,w3,w4,w5,w6,w7,w8, wShow;

    public static final Image fireworkSP;
    public static final Image loadingSP;
    public static final Image logo;
    public static final WritableImage[] fireworks;
    public static final WritableImage[] fishingRod;
    public static final WritableImage[] spin_rod;
    public static final WritableImage[] loadings;

    //TODO: Game Objects
    public static LogicController saveLogic;
    public static LogicController defaultLogic;
    public static FishingPanel fishingPanel;
    public static FishCaughtFX fishCaughtFX;
    public static Character mainChar;
    public static MoneyBar moneyBar;

    public static String saveData;
    public static String newsaveData;
    public static final int sceneW = 1280;
    public static final int sceneH = 720;


    static {



        saveData = "";
        newsaveData = "gamesave.sav";

        String blockf = "block.png";
        demo_map =  River_map = "map/River_Map.csv";
        Beach_map = "map/Beach_Map.csv";
        Waterfall_map = "map/waterfall_Map.csv";
        Loading_map = "map/loading_Map.csv";


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
        String logoPath = "logo/logo.png";
        String loadingPath = "heart.png";
        String moneyBarPath = "moneyBar.png";
        String r_arrow = "rarrow.png";
        String l_arrow = "larrow.png";

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

        String bplay = "button/play.png";
        String bload = "button/load.png";
        String boption = "button/option.png";
        String bexit = "button/quit.png";
        String bresume = "button/resume.png";
        String bmenu = "button/menu.png";
        String bfish = "button/fish.png";

        rarrow = new Image((ClassLoader.getSystemResource(r_arrow).toString()));
        larrow = new Image((ClassLoader.getSystemResource(l_arrow).toString()));

        w1 = new Image((ClassLoader.getSystemResource(walk1).toString()));
        w2 = new Image((ClassLoader.getSystemResource(walk2).toString()));
        w3 = new Image((ClassLoader.getSystemResource(walk3).toString()));
        w4 = new Image((ClassLoader.getSystemResource(walk4).toString()));
        w5 = new Image((ClassLoader.getSystemResource(walk5).toString()));
        w6 = new Image((ClassLoader.getSystemResource(walk6).toString()));
        w7 = new Image((ClassLoader.getSystemResource(walk7).toString()));
        w8 = new Image((ClassLoader.getSystemResource(walk8).toString()));
        wShow = new Image((ClassLoader.getSystemResource(showFish).toString()));

        logo = new Image((ClassLoader.getSystemResource(logoPath).toString()));
        button_play = new Image((ClassLoader.getSystemResource(bplay).toString()));
        button_load = new Image((ClassLoader.getSystemResource(bload).toString()));
        button_option = new Image((ClassLoader.getSystemResource(boption).toString()));
        button_resume = new Image((ClassLoader.getSystemResource(bresume).toString()));
        button_exit = new Image((ClassLoader.getSystemResource(bexit).toString()));
        button_menu = new Image((ClassLoader.getSystemResource(bmenu).toString()));
        button_fish = new Image((ClassLoader.getSystemResource(bfish).toString()));
        block = new Image((ClassLoader.getSystemResource(blockf).toString()));
        moneyBarImg = new Image(ClassLoader.getSystemResource(moneyBarPath).toString());

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
            grass_water_down = new WritableImage(ResourcesLoader.rpg.getPixelReader(),3*(size+10),2*(size+10), size, size);
            load = new WritableImage(ResourcesLoader.rpg.getPixelReader(),18*(size+10),15*(size+10), size, size);
            stone = new WritableImage(ResourcesLoader.rpg.getPixelReader(),7*(size+10),1*(size+10), size, size);
            spawn = new WritableImage(ResourcesLoader.rpg.getPixelReader(),3*(size+10),7*(size+10), size, size);






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

        //Last thing to do
        loadGameObjects();

    }

    private static void loadGameObjects() {
        fishingPanel = new FishingPanel(GameScreen.screenWidth,GameScreen.screenHeight);
        fishCaughtFX = new FishCaughtFX();
        mainChar = new Character("Steve",
                sceneW/2, sceneH/2,160,220, 7,270);
        moneyBar = new MoneyBar(15,15,-10,10);
        defaultLogic = new LogicController();
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
