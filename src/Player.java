import java.security.Key;

public class Player {
    public int playerX = 5;
    public int playerY= 5;
    public int playerW = 35;
    public int playerH = 35;
    public int playerSpeedX = 4;
    public int playerSpeedY = 4;
    public int playerLevel;
    KeyInput keyInput = new KeyInput();
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
    public Player(){

    }
}
