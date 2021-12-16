package UIcontainer.MapChanger;

import Logic.GameState;
import Logic.LogicController;
import Renderer.AudioAsset;
import UIcontainer.UIButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import map.Map;

public class MapChanger extends UIButton {
    private Image image;
    private  Image blockedImage;
    private String map;
    public boolean isBlocked;
    public boolean isAvalaible;
    public MapChanger(Image image,Image imageb, int size, String map){
        super(image,size,size,true);
        this.image = image;
        this.blockedImage = imageb;
        this.map = map;
        isBlocked = false;
        isAvalaible = false;
    }

    public void pressButton(){
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY && !isBlocked && isAvalaible){
                try {
                    AudioAsset.click.play(LogicController.getSFXVol());
                    LogicController.getInstance().setMapLoadingT(240);
                    LogicController.getInstance().setGameState(GameState.LOADING);
                    LogicController.getInstance().setNextMap(new Map(map));
                    LogicController.getInstance().setButtonTriggered(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ButtonList.setAllValid();
                setBlocked(true);
            }
        });
    }

    public void setAvalaible(boolean x){
        isAvalaible = x;
        setImage(x&&!isBlocked ? image :blockedImage);
    }

    public void setBlocked(boolean x){
        isBlocked = x;
        setImage(x&&isAvalaible ? blockedImage:image);
    }

    public void setValid(){
        isBlocked = false;
        isAvalaible = true;
        setImage(image);
    }
}
