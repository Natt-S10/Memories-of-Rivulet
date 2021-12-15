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
    private int sizex;
    private int sizey;
    private boolean isSquare;

    public UIButton(Image image, int sizex, int sizey, boolean isSquare){
        this.setImage( image);
        this.isSquare = isSquare;
        if(isSquare){
            this.setPreserveRatio(true);
            this.setFitWidth(sizex);

        } else{
            this.setFitWidth(sizex);
            this.setFitHeight(sizey);
        }

    }

    public abstract void pressButton();


}
