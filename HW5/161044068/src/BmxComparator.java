import java.awt.*;
import java.util.Comparator;

public class BmxComparator implements Comparator<Color> {
    @Override
    public int compare(Color o1, Color o2) {
        int[] o1RedArr = convertToByte(IntToByte(o1.getRed()));
        int[] o1GreenArr = convertToByte(IntToByte(o1.getGreen()));
        int[] o1BlueArr = convertToByte(IntToByte(o1.getBlue()));
        int[] o2RedArr = convertToByte(IntToByte(o2.getRed()));
        int[] o2GreenArr = convertToByte(IntToByte(o2.getGreen()));
        int[] o2BlueArr = convertToByte(IntToByte(o2.getBlue()));
        int o1Total = CollectBit(o1RedArr,o1GreenArr,o1BlueArr);
        int o2Total = CollectBit(o2RedArr,o2GreenArr,o2BlueArr);
        //if o1's bits'collection is bigger than o2,o1 is big.
        if(o1Total > o2Total) return 1;//if o1's bits'collection is smaller than o2,o1 is small.
        else if(o1Total < o2Total) return -1;
        else return 0; //else o1 and o2 equal.
    }
    //Convert int to bits and return a array
    private int[] convertToByte(int num){
        int binary[] = {0,0,0,0,0,0,0,0};
        int index = 7;
        while(num > 0){
            binary[index--] = num%2;
            num = num/2;
        }
        return binary;
    }
    //Convert int to byte.If number is negative,number is added 256.
    private byte IntToByte(int num){
        return (byte) (num < 0 ? 256 + num : num);
    }
    //Collect bits that have same index and return total number.
    private int CollectBit(int[] redArr,int[] greenArr,int[] blueArr){
        int total = 0;
        for(int i=0; i<8; i++){
            total = total + redArr[i] + greenArr[i] + blueArr[i];
        }
        return total;
    }
}
