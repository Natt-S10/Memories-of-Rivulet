import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;
import sharedObject.MapCanvas;
import sharedObject.RenderableHolder;

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

        MapCanvas mapCanvas = new MapCanvas(sceneW, sceneH);
        root.getChildren().add(mapCanvas);
        mapCanvas.requestFocus();

        RenderableHolder.getInstance().add(new Map());

        stage.setTitle("About to be Game");
        stage.setScene(gameScene);
        stage.show();

        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                mapCanvas.paintComponent();
                RenderableHolder.getInstance().update();
            }
        };
        animation.start();
    }


}
