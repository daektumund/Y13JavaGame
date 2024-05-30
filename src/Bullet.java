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
    public boolean collisionBullet(double x,double y,int w,int h,int floor){
        if (collision((int)this.xPosition,(int)this.yPosition,this.width,this.height,(int)x,(int)y,w,h)){
            return true;
        }
        else return false;
    }
    public Boolean collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
        if (x1 + w1 >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2){
            return true;
        } else {
            return false;
        }
    }
}
