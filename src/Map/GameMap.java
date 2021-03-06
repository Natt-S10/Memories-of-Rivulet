package Map;

import Input.InputUtils;
import Logic.LogicController;
import Renderer.GameScreen;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.Math.*;

public class GameMap implements IRenderable, java.io.Serializable {
    public static final int tileSize;
    //map info
    private MapName mapName;
    private int mapWidth; //pref 32
    private int mapHeight; //pref 18
    private int physicalWidth;
    private int physicalHeight;
    private int spriteCounter;
    private int spriteNum;

    private TileType[][] tileMatrix;

    static {
        tileSize = 80; // kuay
    }


    public GameMap(TileType[][] tileMatrix) {
        this.tileMatrix = tileMatrix;
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
        spriteCounter = 0;
        spriteNum = 1;
    }

    public GameMap() { //demo constructer
        this.tileMatrix = new TileType[][]
                {{TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT},
                        {TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER, TileType.WATER},
                        {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT}};

        mapName = MapName.DEMO_MAP;
        mapHeight = tileMatrix.length;
        mapWidth = tileMatrix[0].length;
        physicalWidth = mapWidth * tileSize;
        physicalHeight = mapHeight *tileSize;
        spriteCounter = 0;
        spriteNum = 1;
    }
    //hee
    public GameMap(String filePath) throws Exception{

        try{
            String row = "";
            BufferedReader csvReader = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    ClassLoader.getSystemResourceAsStream(filePath)),
                            StandardCharsets.UTF_8));
            ArrayList<String[]> preMap = new ArrayList<>();
            while ((row = csvReader.readLine()) != null && !row.isEmpty()) {
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
            spriteCounter = 0;
            spriteNum = 1;

            for (int i = 0 ; i < mapHeight; i++ ) {
                for( int j = 0 ; j < mapWidth; j++) {
                    tileMatrix[i][j] = TileType.valueOf(preMap.get(i)[j]);
                }
            }
        } catch ( FileNotFoundException e){
            throw new FileNotFoundException();
        }
//        catch (IOException e){
//            throw new IOException();
//        }
        String[] name = filePath.split("/");
        //System.out.println(name[1]);

        String[] nameOnly = name[1].split("\\.");

        //System.out.println(nameOnly[0]);
        mapName = MapName.valueOf(nameOnly[0].toUpperCase());

    }
    
    public void drawEveryTiles(GraphicsContext gc){
        WritableImage croppedTile = null;
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                switch (tileMatrix[i][j]) {

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
        //System.out.println(lowI*tileSize - anchorX);
        for(int j=max(lowJ,0); j< min(hiJ,mapHeight); j++){
            for(int i = max(lowI,0); i<min(hiI,mapWidth); i++){
                switch (tileMatrix[j][i]) {
                    case LOAD -> croppedTile = ResourcesLoader.load;
                    case GRASS -> croppedTile = ResourcesLoader.grass;
                    case DIRT -> croppedTile = ResourcesLoader.dirt16;
                    case WATER -> {
                        if( spriteNum <=8 ){
                            croppedTile = ResourcesLoader.water16;
                        } else if (spriteNum >=9 ){
                            croppedTile = ResourcesLoader.water16_2;
                        }
                    }
                    case SPAWN -> croppedTile = ResourcesLoader.spawn;
                    case STONE -> croppedTile = ResourcesLoader.stone;
                    case GRASS_WATER_UP -> croppedTile = ResourcesLoader.grass_water_up;
                    case GRASS_WATER_DOWN -> croppedTile = ResourcesLoader.grass_water_down;
                    case WOOD ->  croppedTile = ResourcesLoader.wood;
                    case SAND -> croppedTile = ResourcesLoader.sand;
                }
                gc.drawImage(croppedTile, i* tileSize - anchorX, j * tileSize - anchorY,tileSize,tileSize); //posx, posy
            }
        }
    }





    public boolean isCollidable(int x, int y) { //for charactor logic
        //System.out.println(x+ " " +y);
        int i = y / tileSize;
        int j = x / tileSize;

        if (i>= tileMatrix.length || j >= tileMatrix[i].length) return true;
        return switch (tileMatrix[i][j]) {
            case DIRT, GRASS,WOOD,SAND, STONE, SPAWN -> false;
            case WATER,GRASS_WATER_DOWN, GRASS_WATER_UP -> true;
            default -> true;
        };
    }
    // TODO: Render only visible tiles
    public void update(){
//        System.out.println(LogicController.getInstance().getGameState());
        double anchorX, anchorY;
        anchorX = LogicController.getInstance().getAnchorX();
        anchorY = LogicController.getInstance().getAnchorY();

//        System.out.println(spriteNum);
//        System.out.println(spriteCounter);
        switch (LogicController.getInstance().getGameState()){
            case WALK, BAITING, FISHING, AFTERFISHING, FISHRAISING -> waterTileUpdate();
        }

    }

    private void waterTileUpdate() {
        if(spriteCounter > 16){
            if(spriteNum == 16) {
                spriteNum = 1;
            }
            spriteNum++;
            spriteCounter = 0;
        }
        spriteCounter++;
    }

    private int snapToGrid(double pos){return (int)(pos/tileSize);}
    
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

    public int getTileSize(){
        return tileSize;
    }

    public MapName getMapName() {
        return mapName;
    }

    public void setMapList(MapName mapName) {
        this.mapName = mapName;
    }

    public TileType clickedTile(){
        int i = snapToGrid(InputUtils.mouseX + LogicController.getInstance().getAnchorX());
        int j = snapToGrid(InputUtils.mouseY + LogicController.getInstance().getAnchorY());
        return tileMatrix[j][i];
    }
}
