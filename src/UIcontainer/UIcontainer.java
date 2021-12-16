package UIcontainer;

import UIcontainer.ListFish.ListFish;
import UIcontainer.MapChanger.ButtonList;
import UIcontainer.Menu.MenuButtonList;
import UIcontainer.Menu.PauseButtonList;
import UIcontainer.Option.OptionFish;
import UIcontainer.Option.OptionMenu;
import UIcontainer.Option.OptionPuss;
import javafx.scene.layout.StackPane;

public class UIcontainer {
    public static ListFish listFish;
    public ButtonList buttonlists;
    public MenuButtonList menuButtonList;
    public PauseButtonList pauseButtonList;
    public OptionMenu optionMenu;
    public OptionPuss optionPuss;
    public OptionFish optionFish;

    public UIcontainer(StackPane root){
        listFish = new ListFish();
        root.getChildren().add(listFish);
        buttonlists = new ButtonList(root);
        menuButtonList = new MenuButtonList(root);
        pauseButtonList = new PauseButtonList(root);
        optionMenu = new OptionMenu(root);
        optionPuss = new OptionPuss(root);
        optionFish = new OptionFish(root);
    }

    public void update(){
        buttonlists.update();
        menuButtonList.update();
        pauseButtonList.update();
        optionMenu.update();
        optionPuss.update();
        optionFish.update();
        listFish.update();
    }

}
