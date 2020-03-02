import java.awt.*;

public class Thread4 extends Thread{
    private PriorityQueue PQBMX;
    public Thread4(PriorityQueue PQBMX) {
        this.PQBMX = PQBMX;
    }
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < PQBMX.size(); i++) {
                Color removed = PQBMX.poll();
                System.out.printf("Thread4-PQBMX: [%d,%d,%d]\n", removed.getRed(), removed.getGreen(), removed.getBlue());
            }
        }
    }

    public void start() {
        this.run();
    }

}
