package ArmLengthProblem;

public class Node {
    private int data;
    private Node prev, next;

    public Node(int data, Node prev, Node next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    public Node(Node n){
        this.next=n.getNext();
        this.prev=n.getPrev();
        this.data=n.getData();
    }

    public String toString() {
        return "" + this.data;
    }

    public void setData(char c) {
        this.data = c;
    }

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setNext(Node n) {
        next = n;
    }

    public void setPrev(Node n) {
        prev = n;
    }
}
