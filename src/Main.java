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
                canvas.loop();
                if (canvas.keyInput.diveRoll){
                    canvas.keyInput.drt++;
                    System.out.println("diveroll");
                }
                if (canvas.keyInput.drt == 30){
                    canvas.keyInput.diveRoll = false;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
        System.out.println("Loop!");
    }
}