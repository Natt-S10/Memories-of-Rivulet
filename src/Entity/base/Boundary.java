package Entity.base;

public class Boundary implements  java.io.Serializable{
    private int posX, posY, width, height;

    public Boundary(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    public Boundary(int width, int height){
        this.width =  width;
        this.height = height;
        posY = posX = 0;
    }

    public int left() {
        return posX;
    }

    public int top() {
        return posY;
    }

    public int right() {
        return posX + width;
    }

    public int bottom() {
        return posY + height;
    }

    public boolean isOverlapsed(Boundary other) {
        return other.left() < right() && other.right() > left() &&
                other.top() < bottom() && other.bottom() > top();
    }


    public void setPosX(int posX) {
        this.posX = posX;
    }


    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setByCenterX(int pointX){
        posX = pointX-(width-1)/2;
    }
    public void setByCenterY(int pointY){
        posY = pointY-(height-1)/2;
    }
    public int getCenterX(){
        return posX + (width-1)/2;
    }
    public int getCenterY(){
        return posY + (height-1)/2;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
