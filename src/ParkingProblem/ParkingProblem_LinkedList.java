package ParkingProblem;


/**
 * This class solves the parking problem using a LinkedList.
 */
public class ParkingProblem_LinkedList {
    public Node head,last;
    final char v = 'v', w = 'w';


    /**
     * Default constructor
     */
    public ParkingProblem_LinkedList() {
        this.head = this.last = null;
    }



    /**
     * LinkedList builder for the problem.
     *
     * @param size - the size of the car.
     */
    public void ParkingProblem_builder(int size) {
        if(size==0){return;}
        this.head = new Node((char) ('a' + (int) (Math.random() * 18)),null,null);
        Node temp=this.head;
        for (int i =1; i < size-1 ; i++) {
            char c = (char) ('a' + (int) (Math.random() * 18));
            Node n = new Node(c, temp, null);
            if(i==0){this.head.setNext(n);}
            temp.setNext(n);
            temp = n;
        }
        if(size==1){
            this.last=this.head;
            return;
        }
        this.last = new Node((char) ('a' + (int) (Math.random() * 18)), temp, this.head);
        temp.setNext(this.last);
        this.head.setPrev(last);
    }

    @Override
    public String toString() {
        String s = "";
        s += "ParkingProblem_LinkedList{" ;
        if(this.head==null&&this.last==null){return s+="}";}
        if(this.head==this.last){return s+=this.head.getData()+"}";}

                Node temp=new Node(this.head);
                while(temp!=this.head){
                    s+=temp.getData();
                    s+=",";
                    temp=temp.getNext();
                }
                s+='}';
                return s;
    }

    /**
     * Function that solves the problem
     *
     * @return - the number of the car
     */
    public int calCars() {
        if(this.head==null){return 0;}
        if(this.head==this.last){return 1;}
        this.head.setData(v);
        Node t = this.head.getNext();
        boolean flag = true;
        int counter = 1;

        while (flag) {
            if (t.getData() == v) {
                t.setData(w);
                int i = counter;

                while (i > 0) {
                    t = t.getPrev();
                    i--;
                }
                if (t.getData() == w) {
                    flag = false;
                } else {
                    counter = 1;
                    t = this.head.getNext();
                }
            } else {
                t = t.getNext();
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        ParkingProblem_LinkedList problem = new ParkingProblem_LinkedList();
        problem.ParkingProblem_builder(20);
        System.out.println(problem.toString());
        System.out.println(problem.calCars());
    }

}
