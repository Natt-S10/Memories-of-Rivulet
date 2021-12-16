package Entity.base;

public interface Collidable {
    public Boundary getCollisionBoundary();
    public static boolean isCollide(Collidable o1, Collidable o2){
        return o1.getCollisionBoundary().isOverlapsed(o2.getCollisionBoundary());
    }
}
