import Input.InputUtils;
import Logic.GameState;
import Logic.LogicController;
import Renderer.*;
import UIcontainer.MapChanger.*;
import UIcontainer.Menu.*;
import UIcontainer.Option.OptionMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;

import static Renderer.ResourcesLoader.sceneH;
import static Renderer.ResourcesLoader.sceneW;

public class Main extends Application {
    public static long lastFrameST = 0;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene gameScene = new Scene(root, sceneW, sceneH);

        GameScreen mapCanvas = new GameScreen(sceneW, sceneH);
        root.getChildren().add(mapCanvas);

        mapCanvas.requestFocus();

        Map demoMap = new Map();
        try {
            demoMap = new Map(ResourcesLoader.demo_map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ButtonList buttonlists = new ButtonList(root);
        MenuButtonList menuButtonList = new MenuButtonList(root);
        PauseButtonList pauseButtonList = new PauseButtonList(root);
        OptionMenu optionMenu = new OptionMenu(root);

        LogicController.getInstance().setMainChar(ResourcesLoader.mainChar);
        LogicController.getInstance().setCurrentMap(demoMap);
        LogicController.getInstance().setGameState(GameState.MENU);

        RenderableHolder.getInstance().add(demoMap);
        RenderableHolder.getInstance().add(ResourcesLoader.mainChar);


        stage.setTitle("Memories of Rivulet");
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.show();



        //Map finalDemoMap = demoMap;

        Map finalDemoMap = demoMap;

        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //System.out.println(InputUtils.isLeftClickDown()+" "+InputUtils.mouseOnScreen);
                //System.out.println(1000000000.0/(lastFrameST-l)); lastFrameST = l;
                buttonlists.update();
                menuButtonList.update();
                pauseButtonList.update();
                optionMenu.update();
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
