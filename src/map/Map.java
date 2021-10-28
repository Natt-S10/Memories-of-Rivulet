package map;

import Input.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import Renderer.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map implements IRenderable {
    public static final Image dirtRawImg;
    public static final Image waterRawImg;
    public static final int tileSize;
    //map info
    private int mapWidth; //pref 32
    private int mapHeight; //pref 18
    private TileType[][] tileMatrix;

    static {
        tileSize = 40;
        String dirtImgPath = "dirt.bmp";
        String waterImgPath = "water.bmp";
        dirtRawImg = new Image(ClassLoader.getSystemResource(dirtImgPath).toString());
        waterRawImg = new Image(ClassLoader.getSystemResource(waterImgPath).toString());
    }

    private WritableImage croppedTile;

    public Map(TileType[][] tileMatrix) {
        this.tileMatrix = tileMatrix;
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
    }

    public Map() { //demo constructer
        this.tileMatrix = new TileType[][]
                {{TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT}};
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
    }
    //hee
    public Map(String filePath) throws Exception{

        try{
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            ArrayList<String[]> Hee = new ArrayList<>();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                Hee.add(data);
            }
            csvReader.close();

            mapHeight = Hee.size();
            mapWidth = Hee.get(0).length;
            tileMatrix = new TileType[mapHeight][mapWidth]; //Y-axis then X-axis

            for (int i = 0 ; i < mapHeight; i++ ) {
                for( int j = 0 ; j < mapWidth; j++) {
                    tileMatrix[i][j] = TileType.valueOf(Hee.get(i)[j]);
                }
            }
        } catch ( FileNotFoundException e){
            throw new FileNotFoundException();
        } catch (IOException e){
            throw new IOException();
        }
    }



    public boolean isCollidable(int x, int y) { //for charactor logic
        int i = y / tileSize;
        int j = x / tileSize;
        return switch (tileMatrix[i][j]) {
            case DIRT -> false;
            case WATER -> true;
            default -> true;
        };
    }

    public void update(){
        if(InputUtils.mouseOnScreen && InputUtils.isLeftClickTriggered()){
            int i = (int) (InputUtils.mouseY / tileSize);
            int j = (int) (InputUtils.mouseX / tileSize);
            if(0<=i && i<mapHeight && 0<=j && j<mapWidth)
                System.out.println(tileMatrix[i][j].toString());
        }
    }

    @Override
    public int getLayer() {
        return -99;
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedTile = null;
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                switch (tileMatrix[i][j]) {
                    case DIRT -> croppedTile = new WritableImage(dirtRawImg.getPixelReader(), tileSize, tileSize);
                    case WATER -> croppedTile = new WritableImage(waterRawImg.getPixelReader(), tileSize, tileSize);
                }
                gc.drawImage(croppedTile, j * tileSize, i * tileSize);
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
