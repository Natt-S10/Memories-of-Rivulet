import Input.InputUtils;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;
import Renderer.GameScreen;
import Renderer.GameScreenUtils;
import Renderer.RenderableHolder;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        RenderableHolder.getInstance().add(demoMap);

        stage.setTitle("About to be Game");
        stage.setScene(gameScene);
        stage.show();

        Map finalDemoMap = demoMap;
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                mapCanvas.paintComponent();
                assert finalDemoMap != null;
                finalDemoMap.update();
                InputUtils.updateInputState();
                RenderableHolder.getInstance().update();
            }
        };
        animation.start();
    }


}
