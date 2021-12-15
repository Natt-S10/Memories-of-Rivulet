package UIcontainer;

import Renderer.GameScreen;
import Renderer.RenderableHolder;
import Renderer.ResourcesLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ButtonList  {

    private final StackPane root;
    private static MapChanger logo_river;
    private static MapChanger logo_sea;
    private static MapChanger logo_waterfall;
    private static MenuButton logo_fire;

    public ButtonList(StackPane root){
        this.root = root;

        logo_river = new MapChanger(ResourcesLoader.logo_river,ResourcesLoader.logo_river_B,80, ResourcesLoader.River_map);
        logo_sea = new MapChanger(ResourcesLoader.logo_sea,ResourcesLoader.logo_sea_B,80,ResourcesLoader.Beach_map);
        logo_waterfall =new MapChanger(ResourcesLoader.logo_waterfall,ResourcesLoader.logo_waterfall_B,80,ResourcesLoader.Waterfall_map);
        logo_fire = new MenuButton(ResourcesLoader.logo_fire,80);

        root.getChildren().addAll(logo_river,logo_sea,logo_waterfall,logo_fire);
        logo_river.setTranslateX(root.getWidth()/3+150);
        logo_river.setTranslateY(-200);

        logo_sea.setTranslateX(root.getWidth()/3+150);
        logo_sea.setTranslateY(-100);

        logo_waterfall.setTranslateX(root.getWidth()/3+150);
        logo_waterfall.setTranslateY(0);

        logo_fire.setTranslateX(root.getWidth()/3+150);
        logo_fire.setTranslateY(-300);

        logo_river.setBlocked();



    }

    public static void setAllValid(){
        logo_river.setValid();
        logo_sea.setValid();
        logo_waterfall.setValid();
    }

    public void update(){
        logo_fire.pressButton();
        logo_river.pressButton();
        logo_sea.pressButton();
        logo_waterfall.pressButton();
    }





}
