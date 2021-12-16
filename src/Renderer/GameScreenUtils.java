package Renderer;

import Input.InputUtils;
import Input.KeyMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class GameScreenUtils {
    public static void addListener(GameScreen gameScreen){
        gameScreen.setOnMousePressed((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY)
                InputUtils.mouseLeftDown();
        });

        //listener listen to all keys -> if for each keys;

        gameScreen.setOnKeyPressed((KeyEvent event) ->{
            if(event.getCode() == KeyCode.W){
                InputUtils.keyPressed(KeyMap.W);
            } else if(event.getCode() == KeyCode.A){
                InputUtils.keyPressed(KeyMap.A);
            } else if(event.getCode() == KeyCode.S){
                InputUtils.keyPressed(KeyMap.S);
            } else if(event.getCode() == KeyCode.D){
                InputUtils.keyPressed(KeyMap.D);
            } else if(event.getCode() == KeyCode.UP){
                InputUtils.keyPressed(KeyMap.UP);
            } else if(event.getCode() == KeyCode.LEFT){
                InputUtils.keyPressed(KeyMap.LEFT);
            } else if(event.getCode() == KeyCode.DOWN){
                InputUtils.keyPressed(KeyMap.DOWN);
            } else if(event.getCode() == KeyCode.RIGHT){
                InputUtils.keyPressed(KeyMap.RIGHT);
            } else if(event.getCode() == KeyCode.SPACE){
                InputUtils.keyPressed(KeyMap.SPACE);
            }



        });

        gameScreen.setOnKeyReleased((KeyEvent event) -> {
            if(event.getCode() == KeyCode.W){
                InputUtils.keyReleased(KeyMap.W);
            } else if(event.getCode() == KeyCode.A){
                InputUtils.keyReleased(KeyMap.A);
            } else if(event.getCode() == KeyCode.S){
                InputUtils.keyReleased(KeyMap.S);
            } else if(event.getCode() == KeyCode.D){
                InputUtils.keyReleased(KeyMap.D);
            } else if(event.getCode() == KeyCode.UP){
                InputUtils.keyReleased(KeyMap.UP);
            } else if(event.getCode() == KeyCode.LEFT){
                InputUtils.keyReleased(KeyMap.LEFT);
            } else if(event.getCode() == KeyCode.DOWN){
                InputUtils.keyReleased(KeyMap.DOWN);
            } else if(event.getCode() == KeyCode.RIGHT){
                InputUtils.keyReleased(KeyMap.RIGHT);
            } else if(event.getCode() == KeyCode.SPACE){
                InputUtils.keyReleased(KeyMap.SPACE);
            }
        });

        gameScreen.setOnMouseReleased((MouseEvent event) ->{
            if (event.getButton() == MouseButton.PRIMARY){
                InputUtils.mouseLeftRelease();
            }
        });

        gameScreen.setOnMouseEntered((MouseEvent event) -> {
            InputUtils.mouseOnScreen = true;
        });

        gameScreen.setOnMouseExited((MouseEvent event) -> {
            InputUtils.mouseOnScreen = false;
        });

        gameScreen.setOnMouseMoved((MouseEvent event) -> {

            if (InputUtils.mouseOnScreen) {
                InputUtils.mouseX = event.getX();
                InputUtils.mouseY = event.getY();

            }
        });
    }




}
