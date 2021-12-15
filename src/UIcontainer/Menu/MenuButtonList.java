package UIcontainer.Menu;

import Logic.GameState;
import Renderer.ResourcesLoader;
import UIcontainer.MapChanger.MapChanger;
import javafx.scene.layout.StackPane;

public class MenuButtonList {


        private final StackPane root;
        private static MenuButton start;
        private static MenuButton load;
        private static MenuButton option;
        private static MenuButton exit;

        public MenuButtonList(StackPane root){
            this.root = root;

            start = new MenuButton(ResourcesLoader.button_play,223,108, GameState.NEW_GAME);
            option = new MenuButton(ResourcesLoader.button_option,223,108,GameState.OPTION);
            load =new MenuButton(ResourcesLoader.button_load,223,108,GameState.LOAD_GAME);
            exit = new MenuButton(ResourcesLoader.button_exit,223,108,GameState.EXIT);

            root.getChildren().addAll(start,option,load,exit);
            start.setTranslateY(75);
            start.setTranslateX(-150);


            option.setTranslateY(75);
            option.setTranslateX(150);


            load.setTranslateY(200);
            load.setTranslateX(-150);

            exit.setTranslateY(200);
            exit.setTranslateX(150);


        }

        public static void setVisible(boolean t){
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
