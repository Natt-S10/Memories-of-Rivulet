package UIcontainer.Menu;

import Logic.GameState;
import Logic.LogicController;
import UIcontainer.UIButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class HomeButton extends UIButton {
    public HomeButton(Image image, int size) {
        super(image, size,size,true);
    }

    @Override
    public void pressButton() {
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY){
                LogicController.getInstance().setGameState(GameState.PAUSE);

            }
        });
    }
}
