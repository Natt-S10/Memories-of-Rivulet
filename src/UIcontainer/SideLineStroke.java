package UIcontainer;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SideLineStroke {
    private  static Rectangle rect1;
    private  static Rectangle rect2;

    public SideLineStroke(StackPane root){
        rect1 = new Rectangle(400,900);
        rect2 = new Rectangle(400,900);

        rect1.setFill(Color.TAN);
        rect2.setFill(Color.TAN);
        root.getChildren().addAll(rect1,rect2);
        rect1.setTranslateX(-700);
        rect2.setTranslateX(700);
    }

    public static  void setVisible(boolean t){
        rect1.setVisible(t);
        rect2.setVisible(t);
    }

}
