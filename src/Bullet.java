public class Bullet extends Player {
    public double xPosition;
    public double yPosition;
    public int width;
    public int height;
    public double xSpeed;
    public double ySpeed;
    public Bullet(int x, int y, int w, int h, double xs, double ys){
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
        this.xSpeed = xs;
        this.ySpeed = ys;
    }
    public void moveBullet(){
        this.xPosition += xSpeed;
        this.yPosition += ySpeed;
    }
}
