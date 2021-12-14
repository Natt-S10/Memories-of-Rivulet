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
    MapChanger logo_river;
    MapChanger logo_sea;
    MapChanger logo_waterfall;
    MenuButton logo_fire;

    public ButtonList(StackPane root){
        this.root = root;

        logo_river = new MapChanger(ResourcesLoader.logo_river,80, ResourcesLoader.River_map);
        logo_sea = new MapChanger(ResourcesLoader.logo_sea,80,ResourcesLoader.Beach_map);
        logo_waterfall =new MapChanger(ResourcesLoader.logo_waterfall,80,ResourcesLoader.Waterfall_map);
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

    }

    public void update(){
        logo_fire.pressButton();
        logo_river.pressButton();
        logo_sea.pressButton();
        logo_waterfall.pressButton();
    }





}
