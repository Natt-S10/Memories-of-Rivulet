import Input.InputUtils;
import Logic.LogicController;
import Renderer.*;
import entity.ActuallyBall;
import entity.Character;
import entity.Entity;
import entity.base.Movable;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;
import map.Seasons;

public class Main extends Application {
    public static final int sceneW = 1280;
    public static final int sceneH = 720;
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
            demoMap = new Map("res/demoMap.csv");
            //demoMap = new Map();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ActuallyBall ball = new ActuallyBall("Ball",demoMap.getMapWidth()/2,demoMap.getMapHeight()/2,75,49, false, Seasons.SUMMER);
        Character mainChar = new Character("Steve",
                sceneW/2, sceneH/2,80,160, 8);
        //demoMap = new Map();
        RenderableHolder.getInstance().add(demoMap);
        RenderableHolder.getInstance().add(mainChar);

        LogicController.getInstance().setMainChar(mainChar);

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
                System.out.println(1000000000.0/(lastFrameST-l)); lastFrameST = l;
                //Logic update
                LogicController.getInstance().update();
                finalDemoMap.update();
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
