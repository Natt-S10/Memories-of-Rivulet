package UIcontainer.AudioController;

import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.ResourcesLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VolumeControl extends VBox {
//    private static Sliders music;
//    private static Sliders fx;
    private static Text tmusic;
    private static Text tfx;
    private static VolumeButton musicr;
    private static  VolumeButton fxr;
    private static VolumeButton musicl;
    private static  VolumeButton fxl;

    private static Text mvol;
    private static  Text fvol;

    public VolumeControl(){

//        music = new Sliders(true);
//        fx = new Sliders(false);

        musicr = new VolumeButton(ResourcesLoader.block,223,108,false,true,true);
        musicl = new VolumeButton(ResourcesLoader.block,223,108,false,false,true);
        fxr = new VolumeButton(ResourcesLoader.block,223,108,false,true,false);
        fxl = new VolumeButton(ResourcesLoader.block,223,108,false,false,false);
        mvol = new Text(Integer.toString((int)(LogicController.getMusicVol()*100)));
        fvol = new Text(Integer.toString((int)(LogicController.getSFXVol()*100)));


        mvol.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 35));
        fvol.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 35));
        mvol.setTranslateY(30);
        fvol.setTranslateY(30);


        tmusic = new Text("MUSIC");
        tmusic.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 35));

        tfx = new Text("SOUND FX");
        tfx.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 35));


        HBox m = new HBox();
        m.setSpacing(30);
        m.getChildren().addAll(musicl,mvol,musicr);
        HBox f = new HBox();
        f.setSpacing(30);
        f.getChildren().addAll(fxl,fvol ,fxr);

        m.setTranslateX(GameScreen.screenWidth/4+38);
        f.setTranslateX(GameScreen.screenWidth/4+38);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10,10,10,10));

        this.getChildren().addAll(tmusic,m,tfx,f);

    }
    public  void setOn(boolean t){

        musicl.setVisible(t);
        musicr.setVisible(t);
        mvol.setVisible(t);
        fxl.setVisible(t);
        fxr.setVisible(t);
        fvol.setVisible(t);

        tmusic.setVisible(t);

        tfx.setVisible(t);

        this.setVisible(t);


    }

    public void update(){

        mvol.setText(Integer.toString((int)(LogicController.getMusicVol()*10)));
        fvol.setText(Integer.toString((int)(LogicController.getSFXVol()*10)));

        musicr.pressButton();
        musicl.pressButton();
        fxl.pressButton();
        fxr.pressButton();
    }






}
