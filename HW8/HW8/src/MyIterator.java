public class MyIterator {
    MyLinkedList list;
    MyLinkedList.Node current;

    MyIterator(MyLinkedList list){
        this.list = list;
        current = list.get(0);
    }

    public boolean hasNext(){
        if(current != null)
            return true;
        return false;
    }

    public Edge next(){
        MyLinkedList.Node temp = current;
        if(hasNext()){
            current = current.next;
        }
        return temp.getEdge();
    }

    public void remove(){
        if(current != null)
            list.remove(current.getEdge().getDest());
    }
}
