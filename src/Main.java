public class Main extends Thread{
    Canvas canvas = new Canvas();
    public static void main(String[] args) {
        Main thread = new Main();
        thread.start();
    }
    public void run(){
        try {
            while(true){
                Thread.sleep(1000/60);
                if (canvas.started){
                    canvas.loop();
                }
                if (canvas.keyInput.diveRoll){
                    canvas.keyInput.drt++;
                } else {
                    canvas.keyInput.drot++;
                }
                if (canvas.keyInput.drt == 10){
                    canvas.keyInput.diveRoll = false;
                    canvas.keyInput.canDiveRoll = false;
                    canvas.keyInput.drt = 0;
                    canvas.keyInput.drot = 0;
                }
                if (canvas.keyInput.drot == 80){
                    canvas.keyInput.canDiveRoll = true;
                }
                if (!canvas.player.canShoot){
                    canvas.player.shotTimer++;
                }
                if (canvas.player.shotTimer >= 100){
                    canvas.player.canShoot = true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error!");
        }
    }
}