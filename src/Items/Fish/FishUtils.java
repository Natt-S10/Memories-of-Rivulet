package Items.Fish;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FishUtils {
    public static final Map<FishSpecies,Object[]> fishStatMap = loadFishStat("res/fish_stat.csv");
    // FishSpecies -> [int idx, double baseLength, double baseWeight]
    public static final int imgW = 200, imgH = 150;
    public static final int speciesCount = FishSpecies.values().length;
    public static final Image[] fishImage = new Image[speciesCount];
    public static final Random rand = new Random();

    static {
        for(FishSpecies species: FishSpecies.values()){
            fishImage[fishIndex(species)] = new Image(ClassLoader.getSystemResource("fish/"+species.toString()+".png").toString());
        }
    }

    public static int fishIndex(FishSpecies species){
        return (int)fishStatMap.get(species)[0];
    }

    public static double fishBaseLength(FishSpecies species){
        return (double) fishStatMap.get(species)[1];
    }

    public static double fishBaseWeight(FishSpecies species){
        return (double) fishStatMap.get(species)[2];
    }

    public static Image fishImg(FishSpecies species){
        return fishImage[fishIndex(species)];
    }

    public static FishSpecies randomFish(){
        return FishSpecies.values()[(int)(Math.random()*speciesCount)];
    }
    public static double randomFishWeight(FishSpecies species){
        return (double)Math.round(fishBaseWeight(species)*(1.0+ randNormRange(-1,1)*0.2)*100) /100;
    }

    public static double randomFishLength(FishSpecies species){
        return (double)Math.round(fishBaseLength(species)*(1.0+ randNormRange(-1,1)*0.2)*100) /100;
    }

    private static Map loadFishStat(String filePath){
        Map<FishSpecies, Object[] > fishStatMap = new HashMap();
        try{
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            while ((row = csvReader.readLine()) != null){
                String[] data = row.split(",");
                Object [] stats = {Integer.valueOf(data[1]),
                    Double.valueOf(data[2]),
                    Double.valueOf(data[3])};
                fishStatMap.put(FishSpecies.valueOf(data[0]),stats);
            }
            csvReader.close();
        }catch (Exception e){ e.printStackTrace();}
        return fishStatMap;
    }

    private static double randNormRange(double rMin, double rMax){
        double r = Math.max(rMin, rand.nextGaussian());
        r = Math.min(rMax, r);
        return r;
    }



}
