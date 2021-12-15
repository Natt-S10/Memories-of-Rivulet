package UIcontainer.Option;

import Logic.GameState;
import Logic.LogicController;
import Renderer.ResourcesLoader;
import UIcontainer.Menu.MenuButton;
import javafx.scene.layout.StackPane;

public class OptionMenu {


    private final StackPane root;
    private static MenuButton menu;


    public OptionMenu(StackPane root){
        this.root = root;


        menu = new MenuButton(ResourcesLoader.button_menu,223,108, GameState.MENU);

        menu.setTranslateY(75);


        root.getChildren().addAll(menu);
        menu.setVisible(false);

    }

    public static void setVisible(boolean t){
        menu.setVisible(t);
    }



    public void update(){
        menu.pressButton();
    }


}
