package Rabbit_Tertel;

import java.util.LinkedList;

/**
 * This class solves the problem of turtle and the rabbit.
 */
public class isCycle {
    public LinkedList<Node> list;
    public Node head,last;
    public int counter;

    /**
     * Default constructor
     */
    public isCycle(){
        list=new LinkedList<>();
        this.head=this.last=null;
        this.counter=0;
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
            if(i==0){this.head.setNext(n);}
            temp.setNext(n);
            temp = n;
        }
        if(size==1){
            this.last=this.head;
            return;
        }
        this.last = new Node(counter++, temp, this.head);
        temp.setNext(this.last);
        this.head.setPrev(last);
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
            if(tertel.getNext()==null || rabbit.getNext()==null || rabbit.getNext().getNext()==null){
                flag=false;
                ans=false;
            }
            else{
                tertel=tertel.getNext();
                rabbit=rabbit.getNext().getNext();
                if(tertel.getData()==rabbit.getData()){
                   flag=false;
                    ans=true;
                }
            }
        }
       return ans;
    }

    public static void main(String[] args) {
        isCycle problem = new isCycle();
        problem.isCycle_builder(15);
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
