package Test_15_10_2018;

/**
 * This class represents a solution for Test_24_1_2021.Q2 from the test date: 15.10.2018.
 */
public class Q2_prisoners {

    public static void prisonersLightOn(int prisoners) {
        boolean[] visited = new boolean[prisoners];
        for (int i = 0; i < prisoners; i++) {
            visited[i] = false;
        }
        int counter = 0;
        int steps=0;
        boolean light = true;
        while (counter < prisoners) {
            steps++;
            int p = (int) (Math.random() * prisoners);
            if (p == 0) {
                if (visited[0] == false) {
                    counter++;
                    visited[0] = true;
                    System.out.println("the counter enter for the first time after "+steps+"steps");
                }
                if(!light){
                    counter++;
                    light = true;
                }
            }
            else{
                if(visited[p]==false && light){
                    visited[p] = true;
                    light = false;
                    System.out.println("the prisoners number: "+p+" turn the light");
                }
            }
        }
        System.out.println("number of steps: "+steps);

    }

    public static void prisonersLightUnKnown(int prisoners){
        boolean light = ((int)(Math.random()*2)==0) ? false : true;
        while(!light){
            int p = (int)(Math.random()*prisoners);
            if(p==0){
                light=true;
            }
        }
        prisonersLightOn(prisoners);
    }

    public static void main(String[] args) {
        prisonersLightOn(10);
        System.out.println("*************************");
        prisonersLightUnKnown(10);
    }
}
