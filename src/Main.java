import Input.InputUtils;
import Logic.GameState;
import Logic.LogicController;
import Renderer.*;
import UIcontainer.UIcontainer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Map.GameMap;

import static Renderer.ResourcesLoader.sceneH;
import static Renderer.ResourcesLoader.sceneW;

public class Main extends Application {
    public static GameScreen mapCanvas;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene gameScene = new Scene(root, sceneW, sceneH);

        mapCanvas = new GameScreen(sceneW, sceneH);
        root.getChildren().add(mapCanvas);

        mapCanvas.requestFocus();

        GameMap demoGameMap = new GameMap();
        try {
            demoGameMap = new GameMap(ResourcesLoader.River_map);

        } catch (Exception e) {
            e.printStackTrace();
        }



        LogicController.getInstance().setMainChar(ResourcesLoader.mainChar);
        LogicController.getInstance().setCurrentMap(demoGameMap);
        LogicController.getInstance().setGameState(GameState.MENU);

        RenderableHolder.getInstance().add(demoGameMap);
        RenderableHolder.getInstance().add(ResourcesLoader.mainChar);
        UIcontainer container = new UIcontainer(root);


        stage.setTitle("Memories of Rivulet");
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.show();



        //Map finalDemoMap = demoMap;

        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //System.out.println(InputUtils.isLeftClickDown()+" "+InputUtils.mouseOnScreen);
                //System.out.println(1000000000.0/(lastFrameST-l)); lastFrameST = l;
                container.update();
                //Logic update
                LogicController.getInstance().update();
                //finalDemoMap.update();
                //render
                RenderableHolder.getInstance().update();
                mapCanvas.paintComponent();
                //Input reset
                InputUtils.updateInputState();
            }
        };
        animation.start();
    }


}
