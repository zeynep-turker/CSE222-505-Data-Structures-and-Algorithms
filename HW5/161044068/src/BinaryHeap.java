import java.awt.*;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap {
    private Color[] heap;
    private int capacity;
    private Comparator cmp;
    private int size = 0;

    public BinaryHeap(Comparator c) {
        capacity=500;
        heap = new Color[capacity];
        cmp = c;
    }
    public void insert(Color item) {
        if (size + 1 == capacity) //if array full,array's capacity will increase.
            increaseCapacity();
        heap[size++] = item;
        ArrangeAfterInsert(); //edit parent-child relationship.
    }
    public Color deleteMax() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {

            return heap[--size];
        }
        Color removed = heap[0]; //hold removed element.
        heap[0] = heap[size-1]; // root is array's end.
        size--;
        ArrangeAfterDelete(); //edit parent-child relationship.
        return removed;
    }
    private void increaseCapacity() { //create new Array that has 2*capacity.Then put it class's array's elements.
        Color[] newArray;
        capacity = capacity*2;
        newArray = new Color[capacity];

        for (int i = 0; i < size; i++)
            newArray[i] = heap[i];
        heap = newArray; //No longer my Array point new Array.
    }
    private void ArrangeAfterInsert() {
        int c = size - 1;  //new added element's index
        while (c > 0) {
            int p = (c - 1) / 2; //new added element's parent's index
            Color item = heap[c];
            Color parent = heap[p];
            if (cmp.compare(item, parent) > 0) { // if parent is smaller than child they are swapped
                // swap
                heap[c] = parent;
                heap[p] = item;
                // move up one level
                c = p;
            } else {
                break;
            }
        }
    }
    private void ArrangeAfterDelete() {
        int i = 0;
        int j = 2 * i + 1;
        while (j < size) {
            int max = j, r = j + 1;
            if (r < size) {
                // there is a right child
                if (cmp.compare(heap[r], heap[j]) > 0) {
                    max++;
                }
            }
            if (cmp.compare(heap[i], heap[max]) < 0) {
                // switch
                Color temp = heap[i];
                heap[i] = heap[max];
                heap[max] = temp;
                i = max;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
    }
    //return array's first element
    public Color peek(){
        return heap[0];
    }
    //No longer array be empty
    public void clear(){
        size = 0;
        heap = null;
    }
    //control array whether or not is empty
    public boolean isEmpty(){
        return size == 0;
    }
    //return array's size
    public int size() {
        return size;
    }
    //print array elements
    public void print() {
        for (int i = 0; i < size; ++i)
            System.out.printf("%d %d %d \n", heap[i].getRed(), heap[i].getGreen(), heap[i].getBlue());
    }
}

