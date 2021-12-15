package map;

import Renderer.GameScreen;

public enum MapName {
    RIVER_MAP, BEACH_MAP, WATERFALL_MAP, LOADING_MAP, DEMO_MAP, DEMO_MAP1;

    public static int getValidX(Map m){
        switch (m.getMapName()){
            case DEMO_MAP,DEMO_MAP1,BEACH_MAP -> {
                return 12;
            }
            case  RIVER_MAP ->{
                return 15;
            }

        }
        return GameScreen.screenWidth/2;

    }
    public static int getValidY(Map m){
        switch (m.getMapName()){
            case DEMO_MAP,DEMO_MAP1,BEACH_MAP -> {
                return 12;
            }
            case RIVER_MAP -> {
                return 25;
            }

        }
        return GameScreen.screenHeight/2;

    }
}