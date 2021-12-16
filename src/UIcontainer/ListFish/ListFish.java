package UIcontainer.ListFish;


import Items.Fish.FishSpecies;
import Items.Fish.FishUtils;
import Logic.LogicController;
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
        this.setPadding(new Insets(10,10,10,10));
        int count = 0;
        for(Image e: FishUtils.fishImage){
            listF[count] = new FishPicture(FishSpecies.values()[count].toString().replace("_"," "),e,false );
            count++;
        }
        int j = 0;
        int k = 0;
        for(int i = 0; i< FishUtils.speciesCount;i++){
            j = i%8;
            this.add(listF[i],j,k);
            if(j == 7) k++;
//            root.getChildren().add(listF[i]);
//            listF[i].setTranslateY(k+50);
//            listF[i].setTranslateX(j+40);
        }

        this.setVgap(20);
        this.setHgap(30);




    }
    public void checkFound(){

        boolean[] achieve = LogicController.getInstance().getFishAchievement();
        int i = 0;
        for(FishPicture e: listF){

                e.setFound(achieve[i]);


                i++;
        }

    }

    public static void setOn(boolean t){
        UIcontainer.listFish.setVisible(t);
    }

    public void update(){
        checkFound();
    }









}
