import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    public boolean upMove, downMove, leftMove, rightMove, diveRoll, canDiveRoll, interact;
    public int drt;
    public int drot;
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT && !diveRoll && canDiveRoll){
            diveRoll = true;
            drt = 0;
        }
        switch (e.getKeyChar()) {
            case ('w'):
            case ('W'):
                upMove = true;
                break;
            case ('s'):
            case ('S'):
                downMove = true;
                break;
            case ('a'):
            case ('A'):
                leftMove = true;
                break;
            case ('d'):
            case ('D'):
                rightMove = true;
                break;
            case ('e'):
            case ('E'):
                interact = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyChar()) {
            case ('w'):
            case ('W'):
                upMove = false;
                break;
            case ('s'):
            case ('S'):
                downMove = false;
                break;
            case ('a'):
            case ('A'):
                leftMove = false;
                break;
            case ('d'):
            case ('D'):
                rightMove = false;
                break;
            case ('e'):
            case ('E'):
                interact = false;
                break;
        }
    }
    public void keyTyped(KeyEvent e){

    }
}
