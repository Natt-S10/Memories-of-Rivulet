package UIcontainer.Menu;

import Logic.GameState;
import Renderer.ResourcesLoader;
import UIcontainer.MapChanger.MapChanger;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MenuButtonList {


        private final StackPane root;
        private static MenuButton start;
        private static MenuButton load;
        private static MenuButton option;
        private static MenuButton exit;
        private static ImageView logo;


        public MenuButtonList(StackPane root){
            this.root = root;

            logo = new ImageView(ResourcesLoader.logo);
            logo.setFitWidth(400);
            logo.setFitHeight(400);

            start = new MenuButton(ResourcesLoader.button_play,223,108, GameState.NEW_GAME);
            option = new MenuButton(ResourcesLoader.button_option,223,108,GameState.OPTIONM);
            load =new MenuButton(ResourcesLoader.button_load,223,108,GameState.LOAD_GAME);
            exit = new MenuButton(ResourcesLoader.button_exit,223,108,GameState.EXIT);

            root.getChildren().addAll(logo,start,option,load,exit);
            start.setTranslateY(75);
            start.setTranslateX(-150);


            logo.setTranslateY(-180);


            option.setTranslateY(75);
            option.setTranslateX(150);


            load.setTranslateY(200);
            load.setTranslateX(-150);

            exit.setTranslateY(200);
            exit.setTranslateX(150);


        }

        public static void setVisible(boolean t){

            logo.setVisible(t);
            start.setVisible(t);
            option.setVisible(t);
            load.setVisible(t);
            exit.setVisible(t);
        }



        public void update(){
            start.pressButton();
            option.pressButton();
            load.pressButton();
            exit.pressButton();

        }







}
