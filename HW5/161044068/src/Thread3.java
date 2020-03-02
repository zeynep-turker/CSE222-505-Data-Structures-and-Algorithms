import java.awt.*;

public class Thread3 extends Thread {
    private PriorityQueue PQEUC;
    public Thread3(PriorityQueue PQEUC){
        this.PQEUC = PQEUC;
    }
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < PQEUC.size(); i++) {
                Color removed = PQEUC.poll();
                System.out.printf("Thread3-PQEUC: [%d,%d,%d]\n",removed.getRed(), removed.getGreen(), removed.getBlue());
            }
        }
    }
    public void start(){
        this.run();
    }
}
