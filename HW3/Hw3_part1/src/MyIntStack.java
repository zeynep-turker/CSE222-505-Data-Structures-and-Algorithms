public class MyIntStack {
    private int size = 0;
    private MyIntNode head;
    public static class MyIntNode {
        int i,j;
        char compo;
        MyIntNode next;
        public MyIntNode(int i, int j,char compo){
            this.i = i;
            this.j = j;
            this.compo = compo;
        }
        public MyIntNode track(MyIntNode top){ return top;}
        public void printNode(){
            System.out.println("i : "+i+" j : "+j+" compo : "+compo);
        }
    }
    public MyIntStack() {
        head = null;
    }
    public boolean empty(){
        if(size == 0)
            return true;
        return false;
    }
    public int size() {
        return size;
    }
    public void push(int i,int j,char compo) {
        MyIntNode newNode = new MyIntNode(i,j,compo);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    public MyIntNode pop() {
        if (head == null){
            System.out.println("Stack is empty");
            return null;
        }
        MyIntNode temp = head;
        head = head.next;
        size--;

        return temp;
    }
    public MyIntNode peek() {
        if (head == null){
            return null;
        }
        return head;
    }
    public void printStack(){ //print all nodes.
        MyIntNode temp = this.peek();
        if(temp == null){
            System.out.println("**MY STACK IS EMPTY**");
            return;
        }
        System.out.println("**MY STACK**");
        while (temp != null){
            System.out.println("i : "+temp.i);
            System.out.println("j : "+temp.j);
            System.out.println("compo : "+temp.compo);
            temp = temp.next;
        }
    }
}
