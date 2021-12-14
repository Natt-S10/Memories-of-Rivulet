package UIcontainer;

import Logic.LogicController;
import Renderer.IRenderable;
import Renderer.RenderableHolder;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import map.Map;

import java.util.ArrayList;

public class MapChanger extends UIButton{
    String map;
    public MapChanger(Image image, int size, String map){
        super(image,size);
        this.map = map;
    }

    public void pressButton(){
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY){
                try {

                    LogicController.getInstance().setCurrentMap(new Map(map));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
