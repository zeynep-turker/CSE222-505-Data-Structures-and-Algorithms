import java.awt.*;
import java.util.Comparator;

public class LexComparator implements Comparator<Color> {
    public int compare(Color o1, Color o2) {
        //if o1's components are bigger than o2's components,o1 is bigger than o2.
        if((o1.getRed() > o2.getRed()) || (o1.getRed() == o2.getRed() && o1.getGreen() > o2.getGreen()) ||
                (o1.getRed() == o2.getRed() && o1.getGreen() == o2.getGreen() && o1.getBlue() > o2.getBlue()))
            return 1;
        //if o1's components are smaller than o2's components,o1 is smaller than o2.
        else if((o2.getRed() > o1.getRed()) || ((o1.getRed() == o2.getRed()) && (o2.getGreen() > o1.getGreen())) ||
                (o1.getRed() == o2.getRed() && o1.getGreen() == o2.getGreen() && o2.getBlue() > o1.getBlue()))
            return -1;
        //if o1 and o2 's each components equal,they are same.
        else if(o1.getRed() == o2.getRed() && o1.getGreen() == o2.getGreen() && o1.getBlue() == o2.getBlue())
            return 0;
        else return -2;
    }
}
