package UIcontainer.AudioController;

import Logic.LogicController;
import Renderer.AudioAsset;
import UIcontainer.UIButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
                AudioAsset.click.play(LogicController.getSFXVol());
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




                System.out.println(inRange);
            }
        });
        //System.out.println(inRange);

        block();

    }

    public void block(){
        setVisible(inRange);

    }

    public void update(){
        if (isMusic) {
            if(isRight){
                if( (Math.round(LogicController.getMusicVol()*10.0)) >=10) {
                    inRange = false;
                }else inRange = true;


            } else{
                if(Math.round(LogicController.getMusicVol()*10.0)<=0){
                    inRange = false;
                }else inRange = true;



            }


        } else {
            if(isRight){
                if(Math.round(LogicController.getSFXVol()*10.0)>=10){
                    inRange = false;
                } else inRange = true;

            } else{
                if(Math.round(LogicController.getSFXVol()*10.0) <=0){
                    inRange = false;
                } else inRange = true;
            }



        }

    }


}
