import java.awt.*;
import java.util.ArrayList;

public class Player{
    Point point;
    double angle;
    PointerInfo a = MouseInfo.getPointerInfo();
    public int playerX = 5;
    public int playerY= 5;
    public int playerW = 35;
    public int playerH = 35;
    public int playerSpeedX = 4;
    public int playerSpeedY = 4;
    public int playerHealth = 100;
    public boolean canShoot = true;
    public int shotTimer = 0;
    ArrayList<Bullet> bullets = new ArrayList<>();
    public void shoot(){
        if (this.canShoot) {
            double xSpeed = Math.cos(angle) * 100;
            double ySpeed = Math.sin(angle) * 100;
            if (point.getX() < playerX) {
                xSpeed = -xSpeed;
                ySpeed = -ySpeed;
            }
            bullets.add(new Bullet(playerX + playerW / 2, playerY + playerH / 2, 20, 20, xSpeed, ySpeed));
            this.canShoot = false;
            this.shotTimer = 0;
        }
    }
    public Player(){

    }
}
