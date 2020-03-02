public class MyStrStack {
    private int size = 0;
    private MyNode head;
    public static class MyNode {
        String data;
        MyNode next;
        public MyNode(String data) {
            this.data = data;
        }
        public MyNode track(MyNode top){ return top;}
    }
    public MyStrStack() {
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
    public void push(String data) {
        MyNode newNode = new MyNode(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    public String pop() {
        if (head == null){
            System.out.println("Stack is empty");
            return null;
        }
        MyNode temp = head;
        head = head.next;
        size--;

        return temp.data;
    }
    public MyNode peek() {
        if (head == null){
            return null;
        }
        return head;
    }
    public void printStack(){
        MyNode temp = this.peek();
        if(temp == null){
            System.out.println("**MY STACK IS EMPTY**");
            return;
        }
        System.out.println("**MY STACK**");
        while (temp != null){
            System.out.println("stack " +temp.data);
            temp = temp.next;
        }
    }
}
