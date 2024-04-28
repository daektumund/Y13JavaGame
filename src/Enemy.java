public class Enemy {
    private float xPosition;
    private float yPosition;
    private float level;
    private boolean alert;
    public Enemy(float xPos, float yPos, float level){
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.level = level;
    }
    public float[] getPosition(){
        float[] pos = new float[3];
        pos[0] = this.xPosition;
        pos[1] = this.yPosition;
        pos[2] = this.level;
        return pos;
    }
}