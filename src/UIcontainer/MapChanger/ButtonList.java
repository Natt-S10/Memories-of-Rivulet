package UIcontainer.MapChanger;

import Logic.LogicController;
import Renderer.ResourcesLoader;
import UIcontainer.Menu.HomeButton;
import javafx.scene.layout.*;

public class ButtonList  {

    private final StackPane root;
    private static MapChanger logo_river;
    private static MapChanger logo_sea;
    private static MapChanger logo_waterfall;
    private static HomeButton logo_fire;

    public ButtonList(StackPane root){
        this.root = root;

        logo_river = new MapChanger(ResourcesLoader.logo_river,ResourcesLoader.logo_river_B,80, ResourcesLoader.River_map);
        logo_sea = new MapChanger(ResourcesLoader.logo_sea,ResourcesLoader.logo_sea_B,80,ResourcesLoader.Beach_map);
        logo_waterfall =new MapChanger(ResourcesLoader.logo_waterfall,ResourcesLoader.logo_waterfall_B,80,ResourcesLoader.Waterfall_map);
        logo_fire = new HomeButton(ResourcesLoader.logo_fire,80);

        root.getChildren().addAll(logo_river,logo_sea,logo_waterfall,logo_fire);
        logo_river.setTranslateX(root.getWidth()/3+150);
        logo_river.setTranslateY(-200);

        logo_sea.setTranslateX(root.getWidth()/3+150);
        logo_sea.setTranslateY(-100);

        logo_waterfall.setTranslateX(root.getWidth()/3+150);
        logo_waterfall.setTranslateY(0);

        logo_fire.setTranslateX(root.getWidth()/3+150);
        logo_fire.setTranslateY(-300);

        //logo_river.setBlocked();



    }

    public static void setVisible(boolean t){
        logo_river.setVisible(t);
        logo_sea.setVisible(t);
        logo_waterfall.setVisible(t);
        logo_fire.setVisible(t);
    }

    public static void setAllValid(){
        logo_river.setValid();
        logo_sea.setValid();
        logo_waterfall.setValid();
    }

    public static void checkDistace(){
        if(LogicController.getInstance().getMainChar().isWarpable()){
            logo_river.setAvalaible(true);
            logo_sea.setAvalaible(true);
            logo_waterfall.setAvalaible(true);
        }else {
            logo_river.setAvalaible(false);
            logo_sea.setAvalaible(false);
            logo_waterfall.setAvalaible(false);
        }
    }

    public void CheckMap(){
        switch(LogicController.getInstance().getCurrentMap().getMapName()){
            case RIVER_MAP -> {
                logo_river.setBlocked(true);
                logo_waterfall.setBlocked(false);
                logo_sea.setBlocked(false);
            }
            case BEACH_MAP -> {
                logo_river.setBlocked(false);
                logo_waterfall.setBlocked(false);
                logo_sea.setBlocked(true);

            }
            case WATERFALL_MAP -> {
                logo_river.setBlocked(false);
                logo_waterfall.setBlocked(true);
                logo_sea.setBlocked(false);
            }
        }

    }

    public void update(){
        CheckMap();
        checkDistace();
        logo_fire.pressButton();
        logo_river.pressButton();
        logo_sea.pressButton();
        logo_waterfall.pressButton();
    }





}
