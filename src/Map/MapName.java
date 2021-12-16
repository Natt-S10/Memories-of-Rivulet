package Map;

import Renderer.GameScreen;

public enum MapName {
    RIVER_MAP, BEACH_MAP, WATERFALL_MAP, LOADING_MAP, DEMO_MAP, DEMO_MAP1;

    public static int getValidX(GameMap m){
        switch (m.getMapName()){
            case DEMO_MAP,DEMO_MAP1,BEACH_MAP -> {
                return 12;
            }
            case  RIVER_MAP ->{
                return 15;
            }
            case WATERFALL_MAP -> {return 12;}

        }
        return GameScreen.screenWidth/2;

    }
    public static int getValidY(GameMap m){
        switch (m.getMapName()){
            case DEMO_MAP,DEMO_MAP1,BEACH_MAP -> {
                return 12;
            }
            case RIVER_MAP -> {
                return 25;
            }
            case WATERFALL_MAP -> {return 15;}

        }
        return GameScreen.screenHeight/2;

    }
}
