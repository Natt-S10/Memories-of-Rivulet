package UIcontainer.Option;



import Logic.GameState;
import Logic.LogicController;
import Renderer.ResourcesLoader;
import UIcontainer.Menu.MenuButton;
import javafx.scene.layout.StackPane;

public class OptionFish {


    private final StackPane root;
    private static MenuButton menu;


    public OptionFish(StackPane root){
        this.root = root;


        menu = new MenuButton(ResourcesLoader.button_menu,223,108, GameState.PAUSE);

        menu.setTranslateY(250);


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
