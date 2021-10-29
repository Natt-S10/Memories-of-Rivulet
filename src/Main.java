import Input.InputUtils;
import Renderer.*;
import entity.ActuallyBall;
import entity.Entity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;
import map.Seasons;

public class Main extends Application {
    public static final int sceneW = 1280;
    public static final int sceneH = 720;


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

        Map demoMap = null;
        try {
            demoMap = new Map("res/demoMap.csv");
            //demoMap = new Map();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActuallyBall ball = new ActuallyBall("Ball",demoMap.getMapWidth()/2,demoMap.getMapHeight()/2,75,49, false, Seasons.SUMMER);

        RenderableHolder.getInstance().add(demoMap);
        RenderableHolder.getInstance().add(ball);

        stage.setTitle("Memories of Rivulet");
        stage.setScene(gameScene);
        stage.show();

        Map finalDemoMap = demoMap;

        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //System.out.println(InputUtils.isLeftClickDown()+" "+InputUtils.mouseOnScreen);
                assert finalDemoMap != null;
                mapCanvas.paintComponent();
                finalDemoMap.update();
                ball.update();
                RenderableHolder.getInstance().update();
                InputUtils.updateInputState();
            }
        };
        animation.start();
    }


}
