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
    public int vdist = 300;
    public double angle = 0;
    public Shape arc;
    public boolean alert;
    public Enemy(int xPos, int yPos, int width, int height, int level, String name){
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.width = width;
        this.height = height;
        this.floor = level;
        this.name = name;
    }
}