import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
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
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    PointerInfo a = MouseInfo.getPointerInfo();
    int testCounter = 0;
    public Canvas(){
        this.setTitle("Loot b-oot");
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
        enemies.add(new Enemy(300,600,40,40,0,"enemy 1", new int[]{30,600,600,500,200,20}));
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
            g2.rotate(angle, player.playerX + (double) player.playerW/2, player.playerY + (double) player.playerH/2);
            g2.draw(playerRect);
            g2.fill(playerRect);
            g2.setTransform(original);
            for (int x=0; x<player.bullets.size(); x++){
                g2.fillRect((int)player.bullets.get(x).xPosition, (int)player.bullets.get(x).yPosition, player.bullets.get(x).width, player.bullets.get(x).height);
            }
            for (int x=0; x<enemies.size(); x++){
                Enemy current = enemies.get(x);
                current.Patrol(player.playerX,player.playerY);
                if (collision(player.playerX,player.playerY,player.playerW,player.playerH,current.xPosition,current.yPosition,current.width,current.height,currentFloor,current.floor)){
                    player.playerHealth = 0;
                }
                /*testCounter++;
                if (testCounter >= 0.2){
                    current.angle = Math.toRadians(Math.toDegrees(current.angle)+ 1);
                    testCounter = 0;
                }*/
                    if (current.health <= 0){
                        enemies.remove(x);
                    }
                if (current.floor == currentFloor) {
                    current.arc = new Arc2D.Double((double)(current.xPosition - current.vDist/2 + current.width/2),(double)(current.yPosition - current.vDist/2 + current.height/2),(double)(current.vDist),(double)(current.vDist),(double)(Math.toDegrees(-current.angle)-current.fov/2),(double)(current.fov),Arc2D.PIE);
                    if (current.alert){
                        g2.setColor(Color.RED);
                    }
                    g2.draw(current.arc);
                    g2.setColor(Color.BLACK);
                    if (current.arc.intersects(playerRect)){
                        current.alert = true;
                    }
                    original = g2.getTransform();
                    g2.rotate(current.angle, current.xPosition + (double) current.width/2, current.yPosition + (double) current.height/2);
                    g2.fillRect(current.xPosition, current.yPosition, current.width, current.height);
                    g2.setColor(Color.black);
                    g2.drawString(current.name, current.xPosition, current.yPosition);
                    g2.drawString(Integer.toString(current.health), current.xPosition, current.yPosition-10);
                    g2.setTransform(original);
                }
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
        if (player.playerHealth != 0){
            movePlayer();
        }
        repaint();
        player.point = point;
        for (int x=0; x<player.bullets.size(); x++){
            player.bullets.get(x).moveBullet();
            for (int y=0; y<obstacles.size(); y++) {
                    try {
                        if (player.bullets.get(x).collisionBullet(obstacles.get(y).xPosition,obstacles.get(y).yPosition,obstacles.get(y).width,obstacles.get(y).height,currentFloor,obstacles.get(y).floor)) {
                            player.bullets.remove(x);
                        }
                    } catch(Exception e) {

                    }
                }
                for (int y=0; y<enemies.size(); y++) {
                    try {
                        if (player.bullets.get(x).collisionBullet(enemies.get(y).xPosition,enemies.get(y).yPosition,enemies.get(y).width,enemies.get(y).height,currentFloor,enemies.get(y).floor)) {
                            player.bullets.remove(x);
                            enemies.get(y).health --;
                        }
                    } catch(Exception e) {

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
            if (collision(player.playerX, player.playerY, player.playerW, player.playerH, current.xPosition, current.yPosition, current.width, current.height, current.floor, currentFloor)) {
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
    public static Boolean collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2, int floor1, int floor2){
        if (x1 + w1 >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2 && floor1 == floor2){
            return true;
        } else {
            return false;
        }
    }
}