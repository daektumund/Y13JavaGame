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
        if (this.xPosition < 0 || this.yPosition < 0 || this.xPosition > 1000 || this.yPosition > 980){
            bullets.remove(this);
        }
    }
    public boolean collisionBullet(double x,double y,int w,int h,int floor1,int floor2){
        return Canvas.collision((int) this.xPosition, (int) this.yPosition, this.width, this.height, (int) x, (int) y, w, h, floor1, floor2);
    }
}
