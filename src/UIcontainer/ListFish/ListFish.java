package UIcontainer.ListFish;


import Items.Fish.FishSpecies;
import Items.Fish.FishUtils;
import Renderer.GameScreen;
import UIcontainer.UIcontainer;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ListFish extends GridPane {
    private static FishPicture[] listF = new FishPicture[FishUtils.speciesCount];

    public ListFish(){

        this.setHeight(GameScreen.screenHeight-120);
        this.setWidth(GameScreen.screenWidth);
        this.setPadding(new Insets(50,50,40,40));
        int count = 0;
        for(Image e: FishUtils.fishImage){
            listF[count] = new FishPicture(FishSpecies.values()[count].toString().replace("_"," "),e,true );
            count++;
        }
        int j = 0;
        int k = 0;
        for(int i = 0; i< FishUtils.speciesCount;i++){
            j = i%8;
            if(j == 7) k++;
            this.add(listF[i],j,k);
//            root.getChildren().add(listF[i]);
//            listF[i].setTranslateY(k+50);
//            listF[i].setTranslateX(j+40);
        }

        this.setVgap(50);
        this.setHgap(40);




    }
    public void checkFound(){
        boolean t = false;
        for(FishPicture e: listF){
            e.setFound(t);
        }

    }

    public static void setOn(boolean t){
        UIcontainer.listFish.setVisible(t);
    }









}
