import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        for (String arg : args) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(arg));
            } catch (IOException ignored) {
                System.out.println("File is Error!");
            }
            assert img != null;
            int width = img.getWidth();
            int height = img.getHeight();
            Color[][] arr = new Color[width][height];
            int b = img.getRGB(0, 0);
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    Color c = new Color(img.getRGB(i, j));
                    arr[i][j] = c;
                }
            }
            //My Thread1 is my main.
            PriorityQueue PQEUC = new PriorityQueue(new EucComparator());
            PriorityQueue PQLEX = new PriorityQueue(new LexComparator());
            PriorityQueue PQBMX = new PriorityQueue(new BmxComparator());
            int count = 1;
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    System.out.printf("Thread 1: [%d,%d,%d]\n", arr[i][j].getRed(), arr[i][j].getGreen(), arr[i][j].getBlue());
                    count++;
                    PQEUC.offer(arr[i][j]);
                    PQLEX.offer(arr[i][j]);
                    PQBMX.offer(arr[i][j]);
                    if (count > 100) { //After count is 100,create and start other threads.
                        Thread2 thread2 = new Thread2(PQLEX);
                        Thread3 thread3 = new Thread3(PQEUC);
                        Thread4 thread4 = new Thread4(PQBMX);
                        thread2.start();
                        thread3.start();
                        thread4.start();
                        thread2.join();
                        thread3.join();
                        thread4.join();
                    }

                }
            }
        }
    }
}
