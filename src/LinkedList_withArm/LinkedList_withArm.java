package LinkedList_withArm;



import java.util.LinkedList;

/**
 * This class solves the problem of a list associated with an arm
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

public class LinkedList_withArm {

        public LinkedList<Node> list;
        public Node head,last;
        public int counter;

        /**
         * Default constructor
         */
        public LinkedList_withArm(int sizeOfArm, int sizeOfList){
            list=new LinkedList<>();
            this.head=this.last=null;
            this.counter=0;
            ListWithArm_builder(sizeOfArm, sizeOfList);
        }

        /**
         * LinkedList builder for the problem.
         *
         * @param sizeOfArm - the size of the arm .
         * @param sizeOfList -  the size of the all list .
         */
        public void ListWithArm_builder(int sizeOfArm , int sizeOfList){

            if(sizeOfList==0){return;}
            if(sizeOfArm<0 || sizeOfList<0){ throw new RuntimeException("the number of the all list and the number of the arm must be positive");}
            if(sizeOfArm>sizeOfList){ throw new RuntimeException("the number of the all list must be bigger then the number of the arm");}
            if(sizeOfArm==sizeOfList){ throw new RuntimeException("there is no cycle in this list");}
            this.head = new Node(counter++,null,null);
            Node temp=this.head;
            for (int i =0; i < sizeOfArm-1 ; i++) {
                Node n = new Node(counter++, temp, null);
                if(i==0){this.head.next = n;}
                temp.next = n;
                temp = n;
            }
            if(sizeOfArm==sizeOfList){
                this.last = new Node(counter++, temp, null);
                return;
            }
            Node temp2=temp;
            for (int i =0; i < sizeOfList-sizeOfArm-1; i++) {
                Node n = new Node(counter++, temp, null);
                temp.next = n;
                temp = n;
            }
            if(sizeOfList==1){
                this.last=this.head;
                return;
            }
            this.last = new Node(counter++, temp, temp2);
            temp.next = this.last;
            temp2.prev = last;
        }

        public int ArmLength() {
            int ans = 0;
            boolean run = true;
            boolean flag = true;
            Node tertel = this.head;
            Node rabbit = this.head;

            while (flag) {
                if (tertel.next == null || rabbit.next == null || rabbit.next.next == null) {
                    flag = false;
                    run = false;
                } else {
                    tertel = tertel.next;
                    rabbit = rabbit.next.next;
                    if (tertel.data == rabbit.data) {
                        flag = false;
                        run = true;
                    }
                }
            }
            rabbit = this.head;

            while (run) {
                if (tertel.data == rabbit.data) {
                    run = false;
                } else {
                    tertel = tertel.next;
                    rabbit = rabbit.next;
                }
                ans++;
            }
            return ans;
        }

    public static void main(String[] args) {
        LinkedList_withArm problem = new LinkedList_withArm(5,8);
        System.out.println(problem.ArmLength());
    }
}



/**
 * הוכחת שהם נפגשים:
 * N - מספר האיברים ברשימה
 * V - אורך הזרוע
 * R - מספר האיברים מנקודת תחילת המפגש
 * M - מספר סיבובי הצב
 * Q - מספר סיבובי האנרב
 * J - מספר צעדים שעשה הצב
 * J*2 - מספר צעדים שעשה הארנב
 *
 * J=NM+R+V
 * 2*J=NQ+R+V
 *
 * נכפיל את המשוואה הראשונה ב2 ונחסר בין שני המשוואות ונקבל:
 * R=ׂN(Q-2M)-V
 *כלומר אנו רואים שבעצם שR=N-V
 * (בלי קשר למספר הסיבובים של הצב והארנב)
 */
