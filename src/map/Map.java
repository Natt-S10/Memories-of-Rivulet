package map;

import Input.InputUtils;
import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Map implements IRenderable {
    public static final int tileSize;
    //map info
    private int mapWidth; //pref 32
    private int mapHeight; //pref 18
    private int physicalWidth, physicalHeight;
    private TileType[][] tileMatrix;

    static {
        tileSize = 80; // kuay
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
        physicalWidth = mapWidth * tileSize;
        physicalHeight = mapHeight *tileSize;
    }
    //hee
    public Map(String filePath) throws Exception{

        try{
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            ArrayList<String[]> preMap = new ArrayList<>();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                preMap.add(data);
            }
            csvReader.close();

            mapHeight = preMap.size();
            mapWidth = preMap.get(0).length;
            physicalWidth = mapWidth * tileSize;
            physicalHeight = mapHeight *tileSize;
            tileMatrix = new TileType[mapHeight][mapWidth]; //Y-axis then X-axis

            for (int i = 0 ; i < mapHeight; i++ ) {
                for( int j = 0 ; j < mapWidth; j++) {
                    tileMatrix[i][j] = TileType.valueOf(preMap.get(i)[j]);
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
//                    case DIRT -> croppedTile = new WritableImage(ResourcesLoader.dirt.getPixelReader(), tileSize, tileSize);
//                    case WATER -> croppedTile = new WritableImage(ResourcesLoader.water.getPixelReader(), tileSize, tileSize);
                    case DIRT -> croppedTile = ResourcesLoader.dirt16;
                    case WATER -> croppedTile = ResourcesLoader.water16;
                }
                gc.drawImage(croppedTile, j * tileSize, i * tileSize); //posx, posy
            }
        }
    }

    public void drawCamView(GraphicsContext gc){
        WritableImage croppedTile = null;
        double anchorX, anchorY;
        anchorX = LogicController.getInstance().getAnchorX();
        anchorY = LogicController.getInstance().getAnchorY();

        int lowI = (int)floor(anchorX/tileSize);
        int hiI = (int) ceil((anchorX+GameScreen.screenWidth)/tileSize);
        int lowJ = (int) floor(anchorY/tileSize);
        int hiJ = (int) ceil((anchorY+GameScreen.screenHeight)/tileSize);
        System.out.println(lowI*tileSize - anchorX);
        for(int j=max(lowJ,0); j< min(hiJ,mapHeight); j++){
            for(int i = max(lowI,0); i<min(hiI,mapWidth); i++){
                switch (tileMatrix[j][i]) {
//                    case DIRT -> croppedTile = new WritableImage(ResourcesLoader.dirt.getPixelReader(), tileSize, tileSize);
//                    case WATER -> croppedTile = new WritableImage(ResourcesLoader.water.getPixelReader(), tileSize, tileSize);
                    case DIRT -> croppedTile = ResourcesLoader.dirt16;
                    case WATER -> croppedTile = ResourcesLoader.water16;
                }
                gc.drawImage(croppedTile, i* tileSize - anchorX, j * tileSize - anchorY); //posx, posy
            }
        }
    }



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
        drawCamView(gc);
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

    public int getPhysicalWidth() {
        return physicalWidth;
    }

    public int getPhysicalHeight() {
        return physicalHeight;
    }
}
