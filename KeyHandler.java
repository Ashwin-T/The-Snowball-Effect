import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener{

    public static boolean upPress, downPress, leftPress, rightPress, spacePress, enterPress, escPress;
    
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            enterPress = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            escPress = true;
        }
        //r
        if(e.getKeyCode() == KeyEvent.VK_R){
            System.out.println("R pressed");
            Game.reset();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            enterPress = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            escPress = false;
        }
    }

    public boolean isUpPress() {
        return upPress;
    }

    public boolean isDownPress() {
        return downPress;
    }

    public boolean isLeftPress() {
        return leftPress;
    }

    public boolean isRightPress() {
        return rightPress;
    }
    public boolean isEscPress() {
        return escPress;
    }

    public boolean isEnterPress() {
        return enterPress;
    }


    public boolean isKeyPressed(){
        return upPress || downPress || leftPress || rightPress || spacePress || enterPress || escPress;
    }

    public boolean isMovementKeyPressed(){
        return upPress || downPress || leftPress || rightPress;
    }



}