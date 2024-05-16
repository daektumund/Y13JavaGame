import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Canvas extends JFrame{
    KeyInput keyInput = new KeyInput();
    int playerX = 5;
    int playerY= 5;
    int playerW = 35;
    int playerH = 35;
    int playerSpeedX = 4;
    int playerSpeedY = 4;
    int playerLevel;
    int currentFloor = 0;
    boolean playerMoving = false;
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    public Canvas(){
        this.setTitle("Poot Poot");
        this.getContentPane().setPreferredSize(new Dimension(1000,980));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setContentPane(new Panel());
        this.addKeyListener(keyInput);
        obstacles.add(new Obstacle(600,60,100,100,0,0,"staircaseup"));
        obstacles.add(new Obstacle(400,60,100,100,1,0,"staircaseup"));
        obstacles.add(new Obstacle(600,60,100,100,-1,0,"staircaseup"));
        obstacles.add(new Obstacle(600,300,100,100,0,0,"staircasedown"));
        obstacles.add(new Obstacle(600,900,100,100,1,0,"staircasedown"));
        obstacles.add(new Obstacle(600,600,100,100,0,0,"table"));
    }
    class Panel extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.fillRect(playerX, playerY, playerW, playerH);
            g2.drawString(Integer.toString(currentFloor), 10, 10);
            g2.setColor(Color.blue);
            for (int x=0; x<obstacles.size(); x++){
                Obstacle current = obstacles.get(x);
                if (current.floor == currentFloor) {
                    g2.fillRect(current.xPosition, current.yPosition, current.width, current.height);
                    g2.setColor(Color.black);
                    switch (current.type) {
                        case ("staircaseup"):
                            g2.drawString("stairsup", current.xPosition, current.yPosition);
                            break;
                        case ("staircasedown"):
                            g2.drawString("stairsdown", current.xPosition, current.yPosition);
                            break;
                        case ("table"):
                            g2.drawString("table", current.xPosition, current.yPosition);
                            break;
                    }
                }
            }
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
            playerSpeedX = 12;
            playerSpeedY = 12;
        } else {
            playerSpeedX = 4;
            playerSpeedY = 4;
        }
        for (int x=0; x<obstacles.size(); x++) {
            Obstacle current = obstacles.get(x);
            if (collision(playerX, playerY, playerW, playerH, current.xPosition, current.yPosition, current.width, current.height, current.floor)) {
                switch(current.type) {
                    case("staircaseup"):
                        currentFloor++;
                        break;
                    case("staircasedown"):
                        currentFloor--;
                        break;
                    case("table"):
                        break;
                }
            }
        }
    }
    public Boolean collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int floor){
        if (x1 + w1 >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2 && floor == currentFloor){
            return true;
        } else {
            return false;
        }
    }
}