package InGamePanel.MoneyStatus;

import Logic.LogicController;
import Renderer.IRenderable;
import Renderer.ResourcesLoader;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MoneyBar implements IRenderable {
    private int posX, posY, height, width, textShiftX, textShiftY;
    private String padLeftSpaces(String input, int length) {
        if (input.length() >= length) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - input.length()) {
            sb.append(' ');
        }
        sb.append(input);

        return sb.toString();
    }

    public MoneyBar(int posX, int posY, int textShiftX, int textShiftY) {
        this.posX = posX;
        this.posY = posY;
        this.textShiftX = textShiftX;
        this.textShiftY = textShiftY;
        width = (int)ResourcesLoader.moneyBarImg.getWidth();
        height = (int)ResourcesLoader.moneyBarImg.getHeight();
    }

    @Override
    public int getLayer() {
        return 10;
    }
    @Override
    public void draw(GraphicsContext gc) {
        String text = (LogicController.getInstance().getMoney()>= 5000)?"WIN" :
                padLeftSpaces(Integer.toString(LogicController.getInstance().getMoney()),4);
        gc.drawImage(ResourcesLoader.moneyBarImg,posX,posY);
        gc.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 34));
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.RIGHT);

        gc.setTextBaseline(VPos.TOP);
        gc.fillText(text, posX+width+textShiftX, posY+textShiftY);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.BASELINE);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        boolean isVisible;
        switch (LogicController.getInstance().getGameState()){
            case WALK, BAITING, FISHING,
                    FISHRAISING,AFTERFISHING -> isVisible = true;
            default -> isVisible = false;
        }
        return isVisible;
    }

}
