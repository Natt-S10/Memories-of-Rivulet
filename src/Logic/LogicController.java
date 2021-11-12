package Logic;

import entity.base.Collidable;
import entity.base.Movable;

import java.util.ArrayList;

public class LogicController {
    private static final LogicController instance = new LogicController();
    private final ArrayList<Movable> movableEntities;
    private final ArrayList<Collidable> collidableEntities;
    private static final Character mainChar;
    private LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
    }
    public static LogicController getInstance(){return instance;}

    public void addMovable(Movable m){movableEntities.add(m);}
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        for(Movable eM: movableEntities){
            eM.update();
            eM.move();
        }
    }

    public ArrayList<Movable> getMovableEntities() {
        return movableEntities;
    }

    public ArrayList<Collidable> getCollidableEntities() {
        return collidableEntities;
    }
}
