import java.awt.*;
import java.awt.geom.Arc2D;

public class Enemy {
    public int xPosition;
    public int yPosition;
    public int width;
    public int height;
    public int floor;
    public String name;
    public int health = 100;
    public int fov = 50;
    public int vDist = 500;
    public double angle = 0;
    public int speed = 5;
    public double xSpeed = 5;
    public double ySpeed = 5;
    public Shape arc;
    public boolean alert;
    public int[] patrol;
    public int currentPatrol = 0;
    public int destinationX;
    public int destinationY;
    public boolean beenShot = false;
    public Enemy(int xPos, int yPos, int width, int height, int level, String name, int[] patrolPoints){
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.width = width;
        this.height = height;
        this.floor = level;
        this.name = name;
        this.patrol = patrolPoints;
    }
    public void Patrol(int x,int y){
        if (!this.alert){
            destinationX = patrol[currentPatrol];
            destinationY = patrol[currentPatrol+1];
            this.speed = 4;
        } else {
            destinationX = x;
            destinationY = y;
            this.speed = 5;
        }
        if (this.xPosition != destinationX || this.yPosition != destinationY) {
            int diffX = this.xPosition - destinationX;
            int diffY = this.yPosition - destinationY;
            if (Math.abs(diffX) <= 5 && Math.abs(diffY) <= 5){
                this.xPosition = destinationX;
                this.yPosition = destinationY;
                this.xSpeed = 0;
                this.ySpeed = 0;
            }
            if (this.xPosition != destinationX && this.yPosition != destinationY) {
                this.angle = (Math.atan((double) (diffY) / (diffX)));
                this.xSpeed = Math.cos(this.angle) * this.speed;
                this.ySpeed = Math.sin(this.angle) * this.speed;
                if (destinationX < this.xPosition) {
                    this.xSpeed = -this.xSpeed;
                    this.ySpeed = -this.ySpeed;
                    this.angle += Math.PI;
                }
            }
            this.xPosition += this.xSpeed;
            this.yPosition += this.ySpeed;
        } else if (currentPatrol == this.patrol.length-2){
            currentPatrol = 0;
        } else {
            currentPatrol += 2;
        }
    }
}