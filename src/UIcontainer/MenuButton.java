package UIcontainer;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MenuButton extends UIButton{
    public MenuButton(Image image, int size) {
        super(image, size);
    }

    @Override
    public void pressButton() {
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY){
                System.out.println("HELLO THIS IS A MENU");
            }
        });
    }
}
