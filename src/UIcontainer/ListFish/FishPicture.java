package UIcontainer.ListFish;

import Renderer.ResourcesLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FishPicture extends VBox {
    private String name;
    private Image image;
    private ImageView fish;
    private boolean isFound;
    private final int sizex = 100;
    private final int sizey = 80;

    public FishPicture(String name, Image image, boolean isFound){
        this.image = image;
        this.fish = new ImageView();
        if(isFound) this.fish.setImage(image);
        else this.fish.setImage(ResourcesLoader.block);
        fish.setFitWidth(sizex);
        fish.setFitHeight(sizey);
        Text below = new Text(name);
        below.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 14));
        this.isFound = isFound;
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(sizey);
        this.setPrefWidth(sizex);
        this.setPadding(new Insets(5,5,5,5));
        this.getChildren().addAll(fish,below);

        this.setVisible(false);


    }

    public void setFound(boolean t){
        isFound = t;
        if(isFound) this.fish.setImage(image);
        else this.fish.setImage(ResourcesLoader.block);
    }




}
