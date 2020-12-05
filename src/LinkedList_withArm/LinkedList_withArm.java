package LinkedList_withArm;



import java.util.LinkedList;

/**
 * This class solves the problem of a list associated with an arm
 */
public class LinkedList_withArm {

        public LinkedList<Node> list;
        public Node head,last;
        public int counter;

        /**
         * Default constructor
         */
        public LinkedList_withArm(){
            list=new LinkedList<>();
            this.head=this.last=null;
            this.counter=0;
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
                if(i==0){this.head.setNext(n);}
                temp.setNext(n);
                temp = n;
            }
            if(sizeOfArm==sizeOfList){
                this.last = new Node(counter++, temp, null);
                return;
            }
            Node temp2=temp;
            for (int i =0; i < sizeOfList-sizeOfArm-1; i++) {
                Node n = new Node(counter++, temp, null);
                temp.setNext(n);
                temp = n;
            }
            if(sizeOfList==1){
                this.last=this.head;
                return;
            }
            this.last = new Node(counter++, temp, temp2);
            temp.setNext(this.last);
            temp2.setPrev(last);
        }

        public int ArmLength() {
            int ans = 0;
            boolean run = true;
            boolean flag = true;
            Node tertel = this.head;
            Node rabbit = this.head;

            while (flag) {
                if (tertel.getNext() == null || rabbit.getNext() == null || rabbit.getNext().getNext() == null) {
                    flag = false;
                    run = false;
                } else {
                    tertel = tertel.getNext();
                    rabbit = rabbit.getNext().getNext();
                    if (tertel.getData() == rabbit.getData()) {
                        flag = false;
                        run = true;
                    }
                }
            }
            rabbit = this.head;
            if (tertel.getData() == rabbit.getData()){ run=false;}
            while (run) {
                if (tertel.getData() == rabbit.getData()) {
                    run = false;
                } else {
                    tertel = tertel.getNext();
                    rabbit = rabbit.getNext();
                }
                ans++;
            }
            return ans;
        }

    public static void main(String[] args) {
        LinkedList_withArm problem = new LinkedList_withArm();
        problem.ListWithArm_builder(5,8);
        System.out.println(problem.ArmLength());
    }
}
