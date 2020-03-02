import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator implements Iterator {
    private Word_Map word_map;
    private Word_Map.Node current;
    public MyIterator(Word_Map word_map){
        this.word_map = word_map;
        current = word_map.head;
    }
    @Override
    public boolean hasNext() {
        return current != null;
    }
    @Override
    public Object next() {
        if (hasNext()) {
            Word_Map.Node temp = current;
            current = current.next;
            return temp;
        }

        throw new NoSuchElementException();
    }
    public void remove(){
        if(hasNext()) {
          word_map.remove(current);
        }
        current = current.next;
    }
}
