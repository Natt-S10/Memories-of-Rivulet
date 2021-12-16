package UIcontainer.Option;

import Logic.GameState;
import Logic.LogicController;
import Renderer.ResourcesLoader;
import UIcontainer.AudioController.VolumeControl;
import UIcontainer.Menu.MenuButton;
import javafx.scene.layout.StackPane;

public class OptionMenu {


    private final StackPane root;
    private static MenuButton menu;
    private static VolumeControl v;


    public OptionMenu(StackPane root){
        this.root = root;


        menu = new MenuButton(ResourcesLoader.button_menu,223,108, GameState.MENU);
        v = new VolumeControl();
        menu.setTranslateY(180);
        v.setTranslateY(-75);


        root.getChildren().addAll(v,menu);
        menu.setVisible(false);
        v.setVisible(false);

    }

    public static void setVisible(boolean t){
        menu.setVisible(t);
        v.setOn(t);
    }



    public void update(){
        menu.pressButton();
        v.update();
    }


}
