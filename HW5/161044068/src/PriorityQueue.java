import java.awt.*;
import java.util.Comparator;


//PriorityQueue class implemented with the binary heap.
public class PriorityQueue {
    private BinaryHeap heap; //The heap
    private Comparator cmp;

    //Construct an empty PriorityQueue with a specified comparator.
    public PriorityQueue(Comparator c) {
        cmp = c;
        heap = new BinaryHeap(cmp);
    }
    //insert a element in queue using heap method.
    public void offer(Color item) {
        heap.insert(item);
    }
    //return first element in queue usinh heap method.
    public Color peek(){
        return heap.peek();
    }
    //print queue's all elements usinh heap method.
    public void print() {
        heap.print();
    }
    // return elemet size in queue using heap method.
    public int size(){ return heap.size(); }

    //Make queue empty using heap method.
    public void clear( )
    {
        heap.clear();
    }
    //control heap whether or not is empty
    public boolean isEmpty(){ return heap.isEmpty(); }
    //remove a element in queue heap method.
    public Color poll() {
        return heap.deleteMax();
    }

}

