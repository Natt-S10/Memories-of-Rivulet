package UIcontainer;

import Input.InputUtils;
import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class UIButton extends ImageView  {

    private boolean isVisible;
    private int size;

    public UIButton(Image image, int size){
        this.setImage( image);
        this.setPreserveRatio(true);
        this.setFitWidth(size);

    }

    public abstract void pressButton();


}
