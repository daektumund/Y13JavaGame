import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
public class MouseInput implements MouseListener {
    public boolean clicking = false;

    public void mouseClicked(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){
        clicking = true;
    }
    public void mouseReleased(MouseEvent e){
        clicking = false;
    }
}
