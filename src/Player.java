import java.util.ArrayList;

public class Player {
    public int playerX = 5;
    public int playerY= 5;
    public int playerW = 35;
    public int playerH = 35;
    public int playerSpeedX = 4;
    public int playerSpeedY = 4;
    public int playerLevel;
    KeyInput keyInput = new KeyInput();
    MouseInput mouseInput = new MouseInput();
    ArrayList<Bullet> bullets = new ArrayList<>();
    public void movePlayer(){
        if (keyInput.upMove){
            playerY -= playerSpeedY;
        }
        if (keyInput.downMove){
            playerY += playerSpeedY;
        }
        if (keyInput.leftMove){
            playerX -= playerSpeedX;
        }
        if (keyInput.rightMove){
            playerX += playerSpeedX;
        }
    }
    public void shoot(){
        bullets.add(new Bullet(playerX,playerY,5,5));
    }
    public Player(){

    }
}
