import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Canvas extends JFrame implements KeyListener {
    int playerX = 5;
    int playerY= 5;
    int playerLevel;
    public Canvas(){
        this.setTitle("Poot Poot");
        this.getContentPane().setPreferredSize(new Dimension(1000,980));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setContentPane(new panel());
        this.pack();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(playerX,playerY,50,50);
    }
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case (KeyEvent.VK_W):
                playerY -= 5;
                break;
            case (KeyEvent.VK_A):
                playerX -= 5;
                break;
            case (KeyEvent.VK_S):
                playerY += 5;
                break;
            case (KeyEvent.VK_D):
                playerX += 5;
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        System.out.println("Released");
    }
    public void keyTyped(KeyEvent e){
        System.out.println("Typed");
    }
}