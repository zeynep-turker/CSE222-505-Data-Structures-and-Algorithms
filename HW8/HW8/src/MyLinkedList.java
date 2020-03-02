public class MyLinkedList {
    Node head = null;
    Node tail = null;

    class Node{
        Edge edge;
        Node next;

        public Node(int src, int dest){
            edge = new Edge(src,dest);
        }
        public void setData(int src,int dest){
            edge.setSrc(src);
            edge.setDest(dest);
        }
        public Edge getEdge(){
            return edge;
        }
    }

    public void addNode(int src, int dest){
        if(head==null){
            Node temp = new Node(src,dest);
            head = temp;
            tail = temp;
        }else{
            tail.next = new Node(src,dest);
            tail = tail.next;
        }
    }

    public void addNodeAtStart(int src, int dest){
        Node newNode = new Node(src,dest);
        if(head==null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }

    public void removeAtEnd(){
        Node temp = head;
        while(temp.next!=null && temp.next.next!=null){
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
    }
    public void removeAtStart(){
        head = head.next;
    }

    public Node removeWithIndex(int index) {
        Node node= head;
        for (int i = 0; i < index; i++) {
            node= node.next;
        }
        node.next = node.next.next;
        return node;
    }

    public Node remove(int dest)throws NullPointerException{
        Node temp = head;
        if(temp.edge.getDest() == dest){
            head = head.next;
            return temp;
        }
        while (temp !=null && temp.next.edge.getDest() !=dest)
            temp = temp.next;
        if(temp == null)
            throw new NullPointerException();
        Node returnNode = temp.next;
        temp.next=temp.next.next;
        return returnNode;
    }

    public void print(){
        Node temp = head;
        while(temp!=null){
            System.out.printf("%d ",temp.edge.getDest());
            temp = temp.next;
        }
        System.out.println();
    }

    public int size(){
        Node temp = head;
        int count=0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Node get(int index){
        Node temp = head;
        int count = 0;
        while(temp!=null && count!=index) {
            count++;
            temp = temp.next;
        }
        return temp;
    }

    public boolean contains(int dest){
        Node temp = head;
        while (temp != null){
            if(temp.edge.getDest() == dest)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public Edge findDest(int dest){
        Node temp = head;
        while (temp != null){
            if (temp.getEdge().getDest() == dest)
                return temp.getEdge();
            temp = temp.next;
        }
        return null;
    }
}
