package Logic;

import entity.base.Collidable;
import entity.base.Movable;
import entity.Character;
import java.util.ArrayList;

public class LogicController {
    private static final LogicController instance = new LogicController();
    private final ArrayList<Movable> movableEntities;


    private final ArrayList<Collidable> collidableEntities;
    private Character mainChar;
    //private static final Character mainChar;
    private LogicController(){
        movableEntities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
    }
    public static LogicController getInstance(){return instance;}

    public void addMovable(Movable m){movableEntities.add(m);}
    public void addCollidable(Collidable c){collidableEntities.add(c);}

    public void update(){
        for(Movable eM: movableEntities){
            eM.move();
            eM.update();
        }
    }

    public ArrayList<Movable> getMovableEntities() {
        return movableEntities;
    }

    public ArrayList<Collidable> getCollidableEntities() {
        return collidableEntities;
    }
    public Character getMainChar() {
        return mainChar;
    }

    public void setMainChar(Character mainChar) {
        if(this.mainChar == null) {
            this.mainChar = mainChar;
            addMovable(mainChar);
            return;
        }
        int id = movableEntities.indexOf(mainChar);
        if(id == -1){
            addMovable(mainChar);
            this.mainChar = mainChar;
            return;
        }
        movableEntities.set(id, (Movable) mainChar);
        this.mainChar = mainChar;
    }
}
