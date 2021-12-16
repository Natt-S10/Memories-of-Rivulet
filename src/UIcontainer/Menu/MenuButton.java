package UIcontainer.Menu;

import Logic.GameState;
import Logic.LogicController;
import Main.Main;
import Renderer.GameScreenUtils;
import UIcontainer.MapChanger.ButtonList;
import UIcontainer.UIButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import map.Map;

public class MenuButton extends UIButton {
    GameState state;

    public MenuButton(Image image, int sizex, int sizey, GameState state) {
        super(image, sizex, sizey,false);
        this.state = state;
    }

    @Override
    public void pressButton() {
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY && isVisible()){
                if(state == GameState.MENU){ //reset all bad status
                    ButtonList.setAllValid();
                }
                //GameScreenUtils.addListener(Main.mapCanvas);
                LogicController.getInstance().setGameState(state);

                MenuButtonList.setVisible(false);
                PauseButtonList.setVisible(false);
                LogicController.getInstance().setSetup(false);

            }
        });

    }
}
