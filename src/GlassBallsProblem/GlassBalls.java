package GlassBallsProblem;

/**
 * This class represents the problem of glass balls, by implementation the algorithm for triangular numbers.
 */
public class GlassBalls {

    /**
     * using 2 balls
     */
    public static void useTrianglesNumber(int a , int [] arr){
        int numOfCheck = 0;
        int floor = 1;  //represent p from the problem.
        int numOfFloors = arr.length;

        while(floor*(floor+1)/2 < numOfFloors){  //represent the size of p from the problem.
           floor++;
        }
        int currentFloor = floor;  //the floor we are on.
        int jump = floor-1; //the amount of floors we will jump every time the ball is not broken

        while(arr[currentFloor]<a){  //search when the ball will broke, until that we will jump to the next floor (p+(p-1)).
            currentFloor = currentFloor + jump;
            jump = jump -1;
            numOfCheck++;
        }
        System.out.println("The first ball brakes at floor: "+currentFloor);

        currentFloor = currentFloor - (jump+1);  //return to the last floor we were on.
        while(arr[currentFloor]<a){
            currentFloor++;
            numOfCheck++;
        }
        System.out.println("The second ball brakes at floor: "+currentFloor);
        System.out.println("the number of checks is: "+numOfCheck);
    }




    public static void main(String[] args) {
        useTrianglesNumber(10, new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21});
        useTrianglesNumber(55, new int[] {10,20,30,40,50,60,70});
    }
}
