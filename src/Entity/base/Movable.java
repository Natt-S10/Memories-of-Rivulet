package Entity.base;

import Input.InputUtils;
import Input.KeyMap;

/**
 * Hi above
 */
public interface Movable {
    /* hi below */
    public static final double sqrt2 = Math.sqrt(2);
    public void update();
    public void move();
    public Direction getDirection();
    public void setDirection(Direction d);
    public double getSpeed();
    public void setSpeed(double speed);
    public static Direction toDirection(int d){
        switch (d){
            case -4 -> {return Direction.SW;}
            case -3 -> {return Direction.W;}
            case -2 -> {return Direction.NW;}
            case -1 -> {return Direction.S;}
            case 1 -> {return Direction.N;}
            case 2 -> {return Direction.SE;}
            case 3 -> {return Direction.E;}
            case 4 -> {return Direction.NE;}
            default -> {return Direction.STABLE;}
        }
    }
    public static Direction directionByKeyboard(){
        int d = 0;
        if(InputUtils.isKeyDown(KeyMap.W)) d+=1;
        if(InputUtils.isKeyDown(KeyMap.A)) d-=3;
        if(InputUtils.isKeyDown(KeyMap.S)) d-=1;
        if(InputUtils.isKeyDown(KeyMap.D)) d+=3;
        return toDirection(d);
    }
    public static double deltaX(double speed, Direction d){
        switch (d){
            case E -> {return speed;}
            case NE,SE -> {return speed/sqrt2;}
            case NW,SW -> {return -speed/sqrt2;}
            case W -> {return -speed;}
            default -> {return 0.0;}
        }
    }
    public static double deltaY(double speed, Direction d){
        switch (d){
            case N -> {return -speed;}
            case NE, NW -> {return -speed/sqrt2;}
            case SE, SW -> {return  speed/sqrt2;}
            case S -> {return speed;}
            default -> {return 0.0;}
        }
    }

}
