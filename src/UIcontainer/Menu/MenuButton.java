package UIcontainer.Menu;

import Logic.GameState;
import Logic.LogicController;
import Renderer.AudioAsset;
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
                AudioAsset.click.play(LogicController.getSFXVol());
                if(state == GameState.MENU){ //reset all bad status
                    ButtonList.setAllValid();
                }

                LogicController.getInstance().setGameState(state);

                MenuButtonList.setVisible(false);
                PauseButtonList.setVisible(false);
                LogicController.getInstance().setSetup(false);

            }
        });

    }
}
