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
    public Shape arc;
    public boolean alert;
    public int[] patrol;
    public int currentPatrol = 0;
    public int destinationX;
    public int destinationY;
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
        } else {
            destinationX = x;
            destinationY = y;
        }
        if (this.xPosition != destinationX || this.yPosition != destinationY) {
            if (this.xPosition != destinationX && this.yPosition != destinationY) {
                this.angle = (Math.atan((double) (this.yPosition - destinationY) / (this.xPosition - destinationX)));
            }
            if (destinationX > this.xPosition) {
                this.xPosition+= speed;
            } else if (destinationX < this.xPosition) {
                this.angle += Math.PI;
                this.xPosition-= speed;
            }
            if (destinationY > this.yPosition) {
                this.yPosition+= speed;
            } else if (destinationY < this.yPosition) {
                this.yPosition-= speed;
            }
        } else if (currentPatrol == patrol.length-2){
            currentPatrol = 0;
        } else {
            currentPatrol += 2;
        }
    }
}