public class Obstacle {
    public int xPosition;
    public int yPosition;
    public int height;
    public int width;
    public int floor;
    public int radius;
    public String type;
    public boolean touched;
    public Obstacle(int x, int y, int l, int w, int f, int r, String t){
        this.xPosition = x;
        this.yPosition = y;
        this.height = l;
        this.width = w;
        this.floor = f;
        this.radius = r;
        this.type = t;
        this.touched = false;
    }
}
