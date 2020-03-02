import java.util.*;

public class Word_Map<INITCAP> implements Map, Iterable
{
    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    int size = 0; //I have for total size
    final static float LOADFACT = 0.75f;
    private Node table[];
    public Node head = null; //begin link

    public Word_Map() {
        this.table = new Node[CURRCAP];
        for(int i=0; i<CURRCAP; ++i)
            table[i] = new Node(null,null);
    }
    @Override
    public Iterator iterator() {
        MyIterator itr = new MyIterator(this);
        return itr;
    }

    static class Node {
        String word; //Key of my hashmap is word.
        File_Map value;//Value of my hashmap is value.
        Node next; //I define to link nodes.
        Node(Object key, Object value) { //Constructors of my Node class
            this.word = (String) word; this.value = (File_Map) value;
        }
        Node(Object word, Object value,Node next){
            this.word = (String) word;
            this.value = (File_Map) value;
            this.next = next;
        }
        //Getter and Setter of my Node class
        String getWord(){return word;}
        File_Map getValue(){return value;}
        Node getNext(){return next;}
        void setWord(String word){this.word = word;}
        void setValue(File_Map value){this.value = value;}
        void setNext(Node next){this.next = next;}
        //Print Node
        void print(){
            System.out.println(word+" "+value.occurances);
        }

    }
    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
        if (containsKey(key)) { //If put same key,new position insert key
            setValue(key, value);
        }
        else {
            Node temp = head;
            while (temp != null && temp.next !=null) {
                temp = temp.next;
            }
            int tmp = hash((String) key); //Take hash index
            if (tmp < 0)
                tmp = tmp + CURRCAP;
            int i = tmp;
            do {
                if (size + 1 == CURRCAP) {//If array is filled,extend capacity.
                    resize();
                }
                if (table[i].word == null) { //Id array[i] is empty,fill with new node.
                    table[i].word = (String) key;
                    table[i].value = (File_Map) value;
                    table[i].next = null;
                    if(head ==null) //Do link
                        head = table[i];
                    else
                        temp.next = table[i];
                    size++;
                    return key;
                }
                i = (i + 1) % CURRCAP;
            } while (i != tmp);
        }
        return key;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) throws IllegalArgumentException{
        if (key == null) throw new IllegalArgumentException("argument is null");
        return get(key) != null; //If array has not key,return false.Otherwise return true.
    }
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) throws IllegalArgumentException{
        if (value == null) throw new IllegalArgumentException("argument is null");
        Node temp = head;
        while (temp.next != null){//If find value with link,return true.Otherwise return false.
            if(temp.value == value) return true;
            temp = temp.next;
        }
        return false;
    }
    public void setValue(Object key,Object value){//A new position is added to an element already in the array
        ((File_Map) this.get(key)).put(((File_Map) value).fnames.get(0),((File_Map) value).occurances.get(0));
    }
    private void resize() {
        Node[] temp = new Node[(int) (CURRCAP+LOADFACT * CURRCAP)];//Extend capacity with Load Factor.
        for(int i=0; i<temp.length; ++i)
            temp[i] = new Node(null,null);
        for (int i = 0; i < CURRCAP; i++) {
            if (table[i].word != null) {
                temp[i] = table[i];
            }
        }
        table = temp;
        CURRCAP = (int) (CURRCAP+LOADFACT * CURRCAP);
    }
    @Override
    public Object get(Object key) { //Look elements with iterator and if you find key,return its value.
        MyIterator itr = (MyIterator) iterator();
        while(itr.hasNext()){
            Node temp = (Node) itr.next();
            if(temp.word.equals(key))
                return temp.value;
        }
        return null;
    }
    private int hash(String key)//Find index using hashCode
    {
        return key.hashCode() % INITCAP;
    }
    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        return null;
    }
    @Override
    public void putAll(Map m) throws NullPointerException{
        if (m == null) throw new NullPointerException();
        Node temp = ((Word_Map)m).head;
        while (temp.next != null){
            put(temp.word,temp.value);
            temp = temp.next;
        }
    }
    @Override
    public void clear() {
        for(int i = 0; i<table.length; ++i)
            table[i] = null;
    }
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        Node temp = head;
        Set set = new HashSet();
        while(temp.next != null){//Add to set keys in map.
            set.add(temp.word);
            temp = temp.next;
        }
        return set;
    }
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Node temp = head;
        Collection set = new HashSet();
        while(temp.next != null){//Add to collection values in map.
            set.add(temp.value);
            temp = temp.next;
        }
        return set;
    }
    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }
}
