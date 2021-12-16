package Input;

public class InputUtils {
    private static boolean isLeftClickedLastTick = false;
    private static boolean[] keyPressed = new boolean[KeyMap.keyAmount];
    private static boolean[] keyTriggered = new boolean[KeyMap.keyAmount];
    public static double mouseX, mouseY;
    public static boolean mouseOnScreen;
    private static boolean isLeftDown = false;


    //EventListener Interface
    public static void mouseLeftDown(){
        isLeftDown = true;
        isLeftClickedLastTick = true;
    }
    public static void mouseLeftRelease(){
        isLeftDown = false;
    }
    public static void keyPressed(int keyNumber){
        keyPressed[keyNumber] = true;
        keyTriggered[keyNumber] = true;
//        System.out.print("PRESSED : ");
//        for(boolean e : keyPressed) System.out.print(e + " ");
//        System.out.println();
    }
    public static void keyReleased(int keyNumber){
        keyPressed[keyNumber] = false;
        //System.out.print("RELEASE : ");
        //for(boolean e : keyPressed) System.out.print(e + " ");
        //System.out.println();
        }
    //Logic & Game Elements Interface
    public static boolean isLeftClickDown(){
        return isLeftDown;
    }
    public static boolean isLeftClickTriggered(){
        return isLeftClickedLastTick;
    }
    public static boolean isKeyDown(int keyNumber){
        return keyPressed[keyNumber];
    }
    public static boolean isKeyTriggered(int keyNumber){
        return keyTriggered[keyNumber];
    }

    public static void updateInputState(){
        isLeftClickedLastTick = false;
        keyTriggered = new boolean[KeyMap.keyAmount]; //may have problem but might not need to optimize
        //for(boolean e: keyTriggered) e = false;
    }


}
