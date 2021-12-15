package Items.Fish;

import Items.itemBase.ItemBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fish extends ItemBase {
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
        return 0;
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
}
