package UIpanel.fishing;

import Logic.LogicController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.Serializable;
import java.lang.invoke.LambdaConversionException;

public class QuickTimeSign extends BaseObject implements Serializable {
   private final String[] buttonSign = {"W","A","S","D"};
   private final int spacing = 3, rectSize = 30, radius = 3;

   public QuickTimeSign(int anchorX, int anchorY){
      this.refpointX = anchorX;
      this.refpointY = anchorY;
   }

   @Override
   public void draw(int panelX, int panelY, GraphicsContext gc) {
      int textShift = 7,maxWidth = 20;
      gc.setStroke(Color.BLACK);
      gc.setLineWidth(1.5);
      gc.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 20));
      int pX = refpointX + panelX, pY = refpointY + panelY;
      for(int i=0; i<buttonSign.length; i++){
         // TODO: move signState to LC
         gc.setFill((LogicController.getInstance().getQtState()[i])? Color.LIME:Color.GRAY);
         if(i==0){
            gc.fillRoundRect(pX+spacing+rectSize,pY, rectSize, rectSize, radius, radius);
            gc.strokeRoundRect(pX+spacing+rectSize,pY, rectSize, rectSize, radius, radius);
            gc.setFill(Color.BLACK);
            gc.fillText(buttonSign[i],pX+spacing+rectSize+textShift, pY+rectSize-textShift, maxWidth);
         }
         else{
            var v = pX + spacing * (i - 1) + rectSize * (i - 1);
            gc.fillRoundRect(v,pY+spacing+rectSize, rectSize, rectSize, radius, radius);
            gc.strokeRoundRect(v,pY+spacing+rectSize, rectSize, rectSize, radius, radius);
            gc.setFill(Color.BLACK);
            gc.fillText(buttonSign[i],v+textShift, pY+spacing+rectSize*2-textShift, maxWidth);
         }
      }
   }

}
