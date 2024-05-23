public class Bullet extends Player {
    public int xPosition;
    public int yPosition;
    public int width;
    public int height;
    public Bullet(int x, int y, int w, int h){
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
    }
    public void moveBullet(){
        for (int x=0; x<bullets.size(); x++){
            bullets.get(x).xPosition += 0.1;
        }
    }
}
