package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;

import java.util.ArrayList;

public class Map implements IRenderable{
    public static final Image dirtRawImg;
    public static final Image waterRawImg;
    public static final int tileSize;
    //map info
    private int mapWidth; //pref 32
    private int mapHeight; //pref 18
    private TileType[][] tileMatrix;

    static{
        tileSize = 40;
        String dirtImgPath = "dirt.bmp";
        String waterImgPath = "water.bmp";
        dirtRawImg = new Image(ClassLoader.getSystemResource(dirtImgPath).toString());
        waterRawImg = new Image(ClassLoader.getSystemResource(waterImgPath).toString());
    }

    private WritableImage croppedTile;

    public Map(TileType[][] tileMatrix){
        this.tileMatrix = tileMatrix;
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
    }

    public Map() {
        this.tileMatrix = new TileType[][]
                {{TileType.DIRT, TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT},
                        {TileType.WATER,TileType.WATER,TileType.WATER,TileType.WATER,TileType.WATER,TileType.WATER},
                        {TileType.DIRT, TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT,TileType.DIRT}};
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
    }

    public boolean isCollidable(int x, int y){ //for charactor logic
        int i = x/tileSize;
        int j = y/tileSize;
        return switch (tileMatrix[i][j]){
            case DIRT -> false;
            case WATER -> true;
            default -> true;
        };
    }

    @Override
    public int getLayer() {
        return -99;
    }

    @Override
    public void draw(GraphicsContext gc) {
        WritableImage croppedTile = null;
        for(int i=0; i<mapHeight; i++){
            for(int j=0; j<mapWidth; j++){
                switch (tileMatrix[i][j]){
                    case DIRT -> croppedTile = new WritableImage(dirtRawImg.getPixelReader(),tileSize,tileSize);
                    case WATER -> croppedTile = new WritableImage(waterRawImg.getPixelReader(),tileSize,tileSize);
                }
                gc.drawImage(croppedTile, j*tileSize, i*tileSize );
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
