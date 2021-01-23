package Rabbit_Tertel;

import java.util.LinkedList;

/**
 * This class solves the problem of turtle and the rabbit.
 * complexity of O(n).
 */
class Node {
    public int data;
    public Node prev, next;

    public Node(int data, Node prev, Node next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public String toString() {
        return "" + this.data;
    }

}


public class isCycle {
    public LinkedList<Node> list;
    public Node head,last;
    public int counter;

    /**
     * Default constructor
     */
    public isCycle(int size){
        list=new LinkedList<>();
        this.head=this.last=null;
        this.counter=0;
        isCycle_builder(size);
    }

    /**
     * LinkedList builder for the problem.
     *
     * @param size - the size of the list .
     */
    public void isCycle_builder(int size){

        if(size==0){return;}
        this.head = new Node(counter++,null,null);
        Node temp=this.head;
        for (int i =1; i < size-1 ; i++) {
            Node n = new Node(counter++, temp, null);
            if(i==0){this.head.next = n;}
            temp.next = n;
            temp = n;
        }
        if(size==1){
            this.last=this.head;
            return;
        }
        this.last = new Node(counter++, temp, this.head);
        temp.next = this.last;
        this.head.prev = last;
    }

    /**
     * Function that solves the problem
     *
     * @return - the number of the car
     */
    public boolean ifCycle(){
        boolean flag=true;
        boolean ans=false;
        int counter=0;

        Node tertel = this.head;
        Node rabbit = this.head;

        while(flag){
            counter++;
            if(tertel.next==null || rabbit.next==null || rabbit.next.next==null){
                flag=false;
                ans=false;
            }
            else{
                tertel=tertel.next;
                rabbit=rabbit.next.next;
                if(tertel.data==rabbit.data){
                   flag=false;
                    ans=true;
                }
            }
        }
       return ans;
    }

    public static void main(String[] args) {
        isCycle problem = new isCycle(15);
        System.out.println(problem.ifCycle());
    }
}


/**
 * הוכחת סיבוכיות:
 * N - מספר האיברים ברשימה
 * R - מספר האיברים מנקודת תחילת המפגש
 * M - מספר סיבובי הצב
 * Q - מספר סיבובי האנרב
 * J - מספר צעדים שעשה הצב
 * J*2 - מספר צעדים שעשה הארנב
 *
 * J=NM+R
 * 2*J=NQ+R
 *
 * נכפיל את המשוואה הראשונה ב2 ונחסר בין שני המשוואות ונקבל:
 * R=ׂN(Q-2M)
 * ןלכן אנו מבינים שR היא כפולה של N
 * נשים לב שמספר הסיבובים המינימאלי המינימאלי שמקיים את המשוואה הוא שR=0
 */
