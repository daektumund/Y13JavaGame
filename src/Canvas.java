import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
public class Canvas extends JFrame{
    KeyInput keyInput = new KeyInput();
    MouseInput mouseInput = new MouseInput();
    Player player = new Player();
    public int currentFloor = 0;
    boolean playerMoving = false;
    Point point;
    double angle = 0;
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    PointerInfo a = MouseInfo.getPointerInfo();
    public Canvas(){
        this.setTitle("Poot Boot");
        this.getContentPane().setPreferredSize(new Dimension(1000,980));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setContentPane(new Panel());
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        obstacles.add(new Obstacle(600,60,100,100,0,0,"staircaseup"));
        obstacles.add(new Obstacle(600,60,100,100,1,0,"staircaseup"));
        obstacles.add(new Obstacle(600,60,100,100,-1,0,"staircaseup"));
        obstacles.add(new Obstacle(600,300,100,100,0,0,"staircasedown"));
        obstacles.add(new Obstacle(600,60,100,100,2,0,"staircasedown"));
        obstacles.add(new Obstacle(600,900,100,100,1,0,"staircasedown"));
        obstacles.add(new Obstacle(600,600,100,100,0,0,"table"));
    }
    class Panel extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
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
            g2.setColor(Color.black);
            Rectangle playerRect = new Rectangle(player.playerX, player.playerY, player.playerW, player.playerH);
            AffineTransform original = new AffineTransform();
            original = g2.getTransform();
            g2.rotate(angle, player.playerX + (double) player.playerW /2, player.playerY + (double) player.playerH /2);
            g2.draw(playerRect);
            g2.fill(playerRect);
            g2.setTransform(original);
            for (int x=0; x<player.bullets.size(); x++){
                g2.fillRect((int)player.bullets.get(x).xPosition, (int)player.bullets.get(x).yPosition, player.bullets.get(x).width, player.bullets.get(x).height);
            }
        }
    }
    public void movePlayer(){
        if (keyInput.upMove){
            player.playerY -= player.playerSpeedY;
        }
        if (keyInput.downMove){
            player.playerY += player.playerSpeedY;
        }
        if (keyInput.leftMove){
            player.playerX -= player.playerSpeedX;
        }
        if (keyInput.rightMove){
            player.playerX += player.playerSpeedX;
        }
    }
    public void loop(){
        movePlayer();
        repaint();
        player.point = point;
        for (int x=0; x<player.bullets.size(); x++){
            player.bullets.get(x).moveBullet();
            for (int y=0; y<obstacles.size(); y++) {
                if (player.bullets.get(x).collisionBullet(obstacles.get(y).xPosition,obstacles.get(y).yPosition,obstacles.get(y).width,obstacles.get(y).height,currentFloor)) {
                    player.bullets.remove(x);
                }
            }
        }
        if (mouseInput.clicking){
            player.shoot();
        }
        point = MouseInfo.getPointerInfo().getLocation();
        angle = (Math.atan((player.playerY - point.getY())/(player.playerX - point.getX())));
        player.angle = angle;
        if (keyInput.diveRoll){
            player.playerSpeedX = 12;
            player.playerSpeedY = 12;
        } else {
            player.playerSpeedX = 4;
            player.playerSpeedY = 4;
        }
        for (int x=0; x<obstacles.size(); x++) {
            Obstacle current = obstacles.get(x);
            if (collision(player.playerX, player.playerY, player.playerW, player.playerH, current.xPosition, current.yPosition, current.width, current.height, current.floor)) {
                current.touched = true;
                if (keyInput.interact) {
                    switch (current.type) {
                        case ("staircaseup"):
                            currentFloor++;
                            break;
                        case ("staircasedown"):
                            currentFloor--;
                            break;
                        case ("table"):
                            break;
                    }
                    keyInput.interact = false;
                }
            } else {
                current.touched = false;
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