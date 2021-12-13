package map;

import Input.InputUtils;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Map implements IRenderable {
    public static final int tileSize;
    //map info
    private int mapWidth; //pref 32
    private int mapHeight; //pref 18
    private boolean drawn;
    private TileType[][] tileMatrix;

    static {
        tileSize = 80; // kuay
    }

    private WritableImage croppedTile;

    public Map(TileType[][] tileMatrix) {
        this.tileMatrix = tileMatrix;
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
        drawn = false;
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
        drawn = false;
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
            drawn = false;

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
    
    public void drawEveryTiles(GraphicsContext gc){
        WritableImage croppedTile = null;
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                switch (tileMatrix[i][j]) {
                    case DIRT -> croppedTile = ResourcesLoader.dirt16;
                    case WATER -> croppedTile = ResourcesLoader.water16;
                }
                gc.drawImage(croppedTile, j * tileSize, i * tileSize);
            }
        }
    }
    
//    public void drawAroundPoint(GraphicsContext gc, int x, int y){
//        WritableImage croppedTile = null;
//        int snappedX = snapToGrid(x);
//        int snappedY = snapToGrid(y);
//        for (int i = max(0,snappedY-3); i < min(snappedY+4,mapHeight); i++) {
//            for (int j = max(0,snappedX-3); j < min(snappedX+4,mapWidth); j++) {
//                switch (tileMatrix[i][j]) {
//                    case DIRT -> croppedTile = new WritableImage(ResourcesLoader.dirt.getPixelReader(), tileSize, tileSize);
//                    case WATER -> croppedTile = new WritableImage(ResourcesLoader.water.getPixelReader(), tileSize, tileSize);
//                }
//                gc.drawImage(croppedTile, j * tileSize, i * tileSize);
//            }
//        }
//    }

    public void viewAroundCharacter(GraphicsContext gc){
        //TODO getCharPos -> Calc i j -> render map within (i,j) to (i2,j2)
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
    // TODO: Render only visible tiles
    public void update(){
        if(InputUtils.mouseOnScreen && InputUtils.isLeftClickTriggered()){
            int i = snapToGrid((int)InputUtils.mouseX);
            int j = snapToGrid((int)InputUtils.mouseY);
            if(0<=i && i<mapHeight && 0<=j && j<mapWidth)
                System.out.println(tileMatrix[i][j].toString());
        }
        if(!InputUtils.mouseOnScreen){drawn = false;}
    }

    private int snapToGrid(int pos){return (int)(pos/tileSize);}
    
    @Override
    public int getLayer() {
        return -99;
    }

    @Override
    public void draw(GraphicsContext gc) {
//        if(!drawn){ drawEveryTiles(gc); drawn=true;}
//        else{drawAroundPoint(gc,(int) InputUtils.mouseX,(int)InputUtils.mouseY);}
        drawEveryTiles(gc);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
