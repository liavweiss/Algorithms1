package PrisonersProblem;

/**
 * This class represents the solution to the prisoners' problem, if the light is on and if not.
 */
public class prisonersProblem {

    public static int prisonersLampOn(int n) {
        boolean [] enter=new boolean[n];
        for(int i=0; i<n;i++){
            enter[i]=false;
        }

        boolean lamp = true;
        int count=0,steps=0;

        while(count<n) {
            steps++;
            int p=(int)(Math.random()*n);
            if(p==0)//the counter enter
            {
                if(enter[p]==false) {
                    enter[p]=true;
                    count++;
                    System.out.println(p+" "+lamp+" "+ count +" ");
                }
                if(!lamp) {
                    lamp=true;
                    count++;
                    System.out.println("Power On "+p+" "+ count);
                }
            }else {
                if(enter[p]==false && lamp==true) {
                    lamp=false;
                    enter[p]=true;
                    System.out.println("Power Off "+p);
                }
            }
        }
        boolean ansAllEntered = true;

        for(int i=0; i<n; i++){ // for checks if all the prisoners entered into the room
            if(!enter[i]){
                ansAllEntered=false;
            }
        }
        System.out.println("All the prisoners entered: "+ansAllEntered);


        return steps;
    }
    public static int prisonersLampUnknown(int n){
        boolean lamp = ((int)(Math.random()*2)==0) ? false : true;
        System.out.println("lamp = "+lamp);
        while (!lamp) {
            int p = (int)(Math.random()*n);
            if (p == 0)
                lamp = true;
        }
        System.out.println("lamp = "+lamp);
        int steps = prisonersLampOn(n);
        return steps;
    }
    public static void main(String[] args) {
        System.out.println(prisonersLampOn(10));
        System.out.println(prisonersLampUnknown(10));
    }
}
