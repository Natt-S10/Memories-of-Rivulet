package UIcontainer.Menu;

import Logic.GameState;
import Renderer.ResourcesLoader;
import UIcontainer.MapChanger.MapChanger;
import javafx.scene.layout.StackPane;

public class PauseButtonList {


    private final StackPane root;
    private static MenuButton continuePlay;
    private static MenuButton option;
    private static MenuButton menu;
    private static MenuButton exit;

    public PauseButtonList(StackPane root){
        this.root = root;

        continuePlay = new MenuButton(ResourcesLoader.button_resume,223,108, GameState.RESUME);
        option = new MenuButton(ResourcesLoader.button_option,223,108,GameState.OPTION);
        menu = new MenuButton(ResourcesLoader.button_menu,223,108,GameState.MENU);
        exit = new MenuButton(ResourcesLoader.button_exit,223,108,GameState.EXIT);

        root.getChildren().addAll(continuePlay,option,menu,exit);
        continuePlay.setTranslateY(-100);


        option.setTranslateY(0);
        menu.setTranslateY(100);
        exit.setTranslateY(200);
        continuePlay.setTranslateY(75);
        continuePlay.setTranslateX(-150);


        option.setTranslateY(75);
        option.setTranslateX(150);


        menu.setTranslateY(200);
        menu.setTranslateX(-150);

        exit.setTranslateY(200);
        exit.setTranslateX(150);
        setVisible(false);



    }

    public static void setVisible(boolean t){
        continuePlay.setVisible(t);
        option.setVisible(t);
        menu.setVisible(t);
        exit.setVisible(t);
    }



    public void update(){
        continuePlay.pressButton();
        option.pressButton();
        menu.pressButton();
        exit.pressButton();
    }







}
