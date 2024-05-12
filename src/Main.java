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
                } else {
                    canvas.keyInput.drot++;
                }
                if (canvas.keyInput.drt == 10){
                    canvas.keyInput.diveRoll = false;
                }
                if (canvas.keyInput.drot == 10){
                    canvas.keyInput.canDiveRoll = true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
        System.out.println("Loop!");
    }
}