package Items.Fish;

import Items.itemBase.ItemBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Fish extends ItemBase implements Serializable {
    private FishSpecies species;
    private double fishLength;
    private double fishWeight;


    public Fish(){
        species = FishUtils.randomFish();
        fishLength = FishUtils.randomFishLength(species);
        fishWeight = FishUtils.randomFishWeight(species);
    }

    @Override
    public Image getImage() {
        return FishUtils.fishImg(species);
    }

    public int getFishPrice(){
        return (int)(FishUtils.fishBasePrice(species) * fishWeight / FishUtils.fishBaseWeight(species));
    }

    public FishSpecies getSpecies() {
        return species;
    }

    public double getFishLength() {
        return fishLength;
    }

    public double getFishWeight() {
        return fishWeight;
    }

    @Override
    public String toString() {
        return species.toString().replace("_"," ");
    }
}
