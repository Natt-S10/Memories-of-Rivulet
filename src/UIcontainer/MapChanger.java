package UIcontainer;

import Logic.GameState;
import Logic.LogicController;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import map.Map;

public class MapChanger extends UIButton{
    private Image image;
    private  Image blockedImage;
    private String map;
    public boolean isBlocked;
    public MapChanger(Image image,Image imageb, int size, String map){
        super(image,size);
        this.image = image;
        this.blockedImage = imageb;
        this.map = map;
        isBlocked = false;
    }

    public void pressButton(){
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY && !isBlocked){
                try {

                    LogicController.getInstance().setMapLoadingT(240);
                    LogicController.getInstance().setGameState(GameState.LOADING);

                    LogicController.getInstance().setNextMap(new Map(map));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ButtonList.setAllValid();
                setBlocked();
            }
        });
    }

    public void setBlocked(){
        isBlocked = true;
        setImage(blockedImage);
    }

    public void setValid(){
        isBlocked = false;
        setImage(image);
    }
}
