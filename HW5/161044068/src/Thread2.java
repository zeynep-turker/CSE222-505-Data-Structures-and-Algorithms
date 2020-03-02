import java.awt.*;

public class Thread2 extends Thread {
    private PriorityQueue PQLEX;
    public Thread2(PriorityQueue PQLEX){
        this.PQLEX = PQLEX;
    }
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < PQLEX.size(); i++) {
                Color removed = PQLEX.poll();
                System.out.printf("Thread2-PQLEX: [%d,%d,%d]\n",removed.getRed(), removed.getGreen(), removed.getBlue());
            }
        }
    }
    public void start(){
        this.run();
    }
}
