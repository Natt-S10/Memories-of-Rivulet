package UIcontainer.AudioController;

import Logic.GameState;
import Logic.LogicController;
import UIcontainer.MapChanger.ButtonList;
import UIcontainer.UIButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import map.Map;

public class VolumeButton extends UIButton {
    private boolean inRange;
    private boolean isRight;
    private boolean isMusic;

    public VolumeButton(Image image, int sizex, int sizey, boolean isSquare, boolean isRight, boolean isMusic) {
        super(image, sizex, sizey, isSquare);
        inRange = true;
        this.isRight = isRight;
        this.isMusic = isMusic;
    }

    @Override
    public void pressButton() {
        this.setOnMousePressed((MouseEvent event)->{
            if(event.getButton() == MouseButton.PRIMARY  && inRange){
                if(isRight){
                    if(isMusic){
                        LogicController.getInstance().setMusicVolume(LogicController.getMusicVol()+0.1);
                    } else{
                        LogicController.getInstance().setFXVolume(LogicController.getSFXVol()+0.1);

                    }

                } else {
                    if(isMusic){
                        LogicController.getInstance().setMusicVolume(LogicController.getMusicVol()-0.1);
                    } else{
                        LogicController.getInstance().setFXVolume(LogicController.getSFXVol()-0.1);

                    }

                }

                if(LogicController.getSFXVol() <=0||LogicController.getSFXVol()>=1 ||
                        LogicController.getMusicVol() <=0|| LogicController.getMusicVol() >=1){
                    inRange = false;
                }

                //System.out.println(inRange);
            }
        });
        //System.out.println(inRange);

        block();

    }

    public void block(){
        if(!inRange){
            setVisible(false);
        } else setVisible(true);
    }
}
