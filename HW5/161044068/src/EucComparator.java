import java.awt.*;
import java.util.Comparator;

public class EucComparator implements Comparator<Color> {
    public int compare(Color o1, Color o2) {
        //each color's red,blue and green components multiply its own and collect them then take root.
        int firstEuc = (int) Math.sqrt(o1.getRed()*o1.getRed()+o1.getGreen()*o1.getGreen()+o1.getBlue()*o1.getBlue());
        int secondEuc = (int) Math.sqrt(o2.getRed()*o2.getRed()+o2.getGreen()*o2.getGreen()+o2.getBlue()*o2.getBlue());

        if(firstEuc > secondEuc) return 1; // if firstEuc is bigger than secondEuc,o1 is bigger than o2.
        else if (firstEuc < secondEuc) return -1; //if firstEuc is smaller than secondEuc,o1 is smaller than o2.
        else return 0;//if firstEuc equal secondEuc,o1 equal o2.
    }
}
