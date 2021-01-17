package Test_22_01_2018;

import java.util.LinkedList;

/**
 * classes for Q1a and Q1b
 */
class Node {

    public Node next;
    public Node prev;
    public char c;

    public Node(char c, Node previous, Node next) {
        this.c = c;
        this.prev = previous;
        this.next = next;

    }

}

class parking {
    public Node head, last;

    public parking() {
        this.head = null;
        this.last = null;
    }

    public void parkingBuilder(int size) {
        if (size == 0) {
            return;
        }
        this.head = new Node((char) ('a' + (int) (Math.random() * 24)), null, null);
        Node temp = this.head;
        for (int i = 1; i < size - 1; i++) {
            char c = (char) ('a' + (int) (Math.random() * 24));
            Node n = new Node(c, temp, null);
            temp.next = n;
            temp = n;
        }
        if (size == 1) {
            this.last = this.head;
            return;
        }

        this.last = new Node((char) ('a' + (int) (Math.random() * 24)), null, null);
        this.last.next = this.head;
        this.last.prev = temp;
        temp.next = this.last;
        this.head.prev = this.last;
    }
}

class Node_arm {
    public Node_arm prev, next;
    public int data;

    public Node_arm(int data, Node_arm prev, Node_arm next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

class parking_arm {
    public Node_arm head, last;
    public LinkedList<Node_arm> list;
    public int counter = 0;

    public parking_arm() {
        this.head = this.last = null;
    }

    public void parkingArmBuilder(int sizeOfArm, int sizeOfList) {
        if (sizeOfList == 0) {
            return;
        }
        if (sizeOfArm < 0 || sizeOfList < 0) {
            throw new RuntimeException("the number of the all list and the number of the arm must be positive");
        }
        if (sizeOfArm > sizeOfList) {
            throw new RuntimeException("the number of the all list must be bigger then the number of the arm");
        }
        if (sizeOfArm == sizeOfList) {
            throw new RuntimeException("there is no cycle in this list");
        }
        this.head = new Node_arm(counter++, null, null);
        Node_arm temp = this.head;
        for (int i = 0; i < sizeOfArm - 1; i++) {
            Node_arm n = new Node_arm(counter++, temp, null);
            if (i == 0) {
                this.head.next = n;
            }
            temp.next = n;
            temp = n;
        }
        if (sizeOfArm == sizeOfList) {
            this.last = new Node_arm(counter++, temp, null);
            return;
        }
        Node_arm temp2 = temp;
        for (int i = 0; i < sizeOfList - sizeOfArm - 1; i++) {
            Node_arm n = new Node_arm(counter++, temp, null);
            temp.next = n;
            temp = n;
        }
        if (sizeOfList == 1) {
            this.last = this.head;
            return;
        }
        this.last = new Node_arm(counter++, temp, temp2);
        temp.next = this.last;
        temp2.prev = last;
    }
}

/**
 * This class represents a solution for Q1 from the test date: 22.01.2018.
 */
public class Q1_parking {

    /**
     * Q1:
     */
    public static int calCar(parking park) {
        if (park.head == null) {
            return 0;
        }
        if (park.head == park.last) {
            return 1;
        }
        park.head.c = 'v';
        boolean flag = true;
        int counter = 1;
        Node temp = park.head.next;
        while (flag) {
            if (temp.c == 'v') {
                temp.c = 'w';
                int i = counter;
                while (i > 0) {
                    temp = temp.prev;
                    i--;
                }
                if (temp.c == 'w') {
                    flag = false;
                } else {
                    counter = 1;
                    temp = park.head.next;
                }
            } else {
                temp = temp.next;
                counter++;
            }
        }
        return counter;
    }

    /**
     * Q1:b:
     */
    public static int parkingArm(parking_arm p) {
        Node_arm turtle = p.head;
        Node_arm rabbit = p.head;
        boolean flag = true;
        boolean run = true;
        int ans = 0;

        while (flag) {
            if (turtle.next == null || rabbit.next == null || rabbit.next.next == null) {
                flag = false;
                run = false;
            }
            else{
                rabbit = rabbit.next.next;
                turtle = turtle.next;
                if(rabbit.data == turtle.data){
                    flag = false;
                    run = true;
                }
            }
        }
        rabbit = p.head;
        while(run){
            if(rabbit.data == turtle.data){
                run = false;
            }
            else{
                rabbit=rabbit.next;
                turtle = turtle.next;
            }
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("**********************1a********************");
        parking p = new parking();
        p.parkingBuilder(150);
        System.out.println(calCar(p));

        System.out.println("**********************1b********************");
        parking_arm pa = new parking_arm();
        pa.parkingArmBuilder(13,20);
        System.out.println(parkingArm(pa));
    }
}
