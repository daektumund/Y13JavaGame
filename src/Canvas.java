import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Canvas extends JFrame{
    KeyInput keyInput = new KeyInput();
    int playerX = 5;
    int playerY= 5;
    int playerSpeedX = 2;
    int playerSpeedY = 2;
    int playerLevel;
    boolean playerMoving = false;
    public Canvas(){
        this.setTitle("Poot Poot");
        this.getContentPane().setPreferredSize(new Dimension(1000,980));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setContentPane(new panel());
        this.pack();
        this.setContentPane(new Panel());
        this.addKeyListener(keyInput);
    }
    class Panel extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.fillRect(playerX, playerY, 50, 50);
        }
    }
    public void movePlayer(){
        if (keyInput.upMove){
            playerY -= playerSpeedY;
        }
        if (keyInput.downMove){
            playerY += playerSpeedY;
        }
        if (keyInput.leftMove){
            playerX -= playerSpeedX;
        }
        if (keyInput.rightMove){
            playerX += playerSpeedX;
        }
    }
    public void loop(){
        movePlayer();
        repaint();
        if (keyInput.diveRoll){
            playerSpeedX = 10;
            playerSpeedY = 10;
        } else {
            playerSpeedX = 2;
            playerSpeedY = 2;
        }
    }
}