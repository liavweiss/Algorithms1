package ParkingProblem;


/**
 * This class solves the parking problem using a LinkedList.
 */
class Node {
    public char data;
    public Node prev, next;

    public Node(char data, Node prev, Node next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    public Node(Node n){
        this.next=n.next;
        this.prev=n.prev;
        this.data=n.data;
    }

    public String toString() {
        return "" + this.data;
    }

}

public class ParkingProblem_LinkedList {
    public Node head, last;
    final char v = 'v', w = 'w';


    /**
     * Default constructor
     */
    public ParkingProblem_LinkedList(int size) {
        this.head = this.last = null;
        ParkingProblem_builder(size);
    }


    /**
     * LinkedList builder for the problem.
     *
     * @param size - the size of the car.
     */
    public void ParkingProblem_builder(int size) {
        if (size == 0) {
            return;
        }
        this.head = new Node((char) ('a' + (int) (Math.random() * 18)), null, null);
        Node temp = this.head;
        for (int i = 1; i < size - 1; i++) {
            char c = (char) ('a' + (int) (Math.random() * 18));
            Node n = new Node(c, temp, null);
            if (i == 0) {
                this.head.next = n;
            }
            temp.next = n;
            temp = n;
        }
        if (size == 1) {
            this.last = this.head;
            return;
        }
        this.last = new Node((char) ('a' + (int) (Math.random() * 18)), temp, this.head);
        temp.next = this.last;
        this.head.prev = last;
    }

    @Override
    public String toString() {
        String s = "";
        s += "ParkingProblem_LinkedList{";
        if (this.head == null && this.last == null) {
            return s += "}";
        }
        if (this.head == this.last) {
            return s += this.head.data + "}";
        }

        Node temp = new Node(this.head);
        while (temp != this.head) {
            s += temp.data;
            s += ",";
            temp = temp.next;
        }
        s += '}';
        return s;
    }

    /**
     * Function that solves the problem
     *
     * @return - the number of the car
     */
    public int calCars() {
        if (this.head == null) {
            return 0;
        }
        if (this.head == this.last) {
            return 1;
        }
        this.head.data = v;
        Node t = this.head.next;
        boolean flag = true;
        int counter = 1;

        while (flag) {
            if (t.data == v) {
                t.data = w;
                int i = counter;

                while (i > 0) {
                    t = t.prev;
                    i--;
                }
                if (t.data == w) {
                    flag = false;
                } else {
                    counter = 1;
                    t = this.head.next;
                }
            } else {
                t = t.next;
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        ParkingProblem_LinkedList problem = new ParkingProblem_LinkedList(20);
        System.out.println(problem.toString());
        System.out.println(problem.calCars());
    }

}


/**
 * הוכחת סיבוכיות:
 * במקרה הטוב יש רק סימן V אחד ולכן נבצע רק 2n צעדים
 * במקרה הגרוע כל רכב מסומן בV לכן נצארך לבצע n^2 צעדים
 */
