package GlassBallsProblem;

import java.util.Arrays;

/**
 * This class represents the problem of glass balls, by implementation the algorithm for triangular numbers.
 */
public class GlassBalls {

    /**
     * O(sqrt(n))
     */
    public static int numOfFloorBrokenBall(int a, int[] building) {
        int numOfChecks = 0;
        int numOfFloors = building.length;
        int floor = 1;
        while ((floor * (floor + 1)) / 2 < numOfFloors) {
            floor++;
        }
        int currentFloor = floor;
        boolean ball = true;   // the ball is not broken yet
        while (ball) {
            numOfChecks++;
            if (building[currentFloor] > a) {
                currentFloor = currentFloor - floor + 1;
                while (ball) {
                    numOfChecks++;
                    if (building[currentFloor] > a) {
                        System.out.println("num of checks is: " + numOfChecks);
                        System.out.print("the floor is: ");
                        return currentFloor+1;
                    }
                    currentFloor++;
                }
            }
            if (currentFloor == numOfFloors - 1) break;
            floor = floor - 1;
            currentFloor += floor;
            if (currentFloor > numOfFloors - 1) currentFloor = numOfFloors - 1;
        }
        System.out.println("num of checks is: " + numOfChecks);
        System.out.print("the ball never broke ");
        //if the ball never broke we will return
        return -1;
    }

    /**
     * Return the number of checking to find the number of the floor broken ball.
     * checking with 2 balls.
     */
    public static int numOfCheckingFor2Ball(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        int min, temp;
        for (int i = 3; i < arr.length; i++) {
            min = n;
            for (int j = 1; j < i + 1; j++) {
                temp = Math.max(j, arr[i - j] + 1);
                if (temp < min) {
                    min = temp;
                }
            }
            arr[i] = min;
        }
        return arr[n];
    }


    /**
     * Return the number of checking to find the number of the floor broken ball.
     * checking with 3 balls.
     */
    public static int numOfCheckingFor3Ball(int n) {
        int[] arr3 = new int[n + 1];
        int[] arr2 = new int[n + 1];
        if (n == 1) return 1;
        if (n == 2 || n == 3) return 2;

        for (int i = 1; i < arr2.length; i++) {
            arr2[i] = numOfCheckingFor2Ball(i);
        }
        arr3[0] = 0;
        arr3[1] = 1;
        arr3[2] = 2;
        arr3[3] = 2;
        int min, temp;

        for (int i = 4; i < arr3.length; i++) {
            min = n;
            for (int j = 1; j < i - 1; j++) {
                temp = Math.max(arr2[j - 1] + 1, arr3[i - j] + 1);
                if (temp < min) {
                    min = temp;
                }
            }
            arr3[i] = min;
        }

        return arr3[n];
    }

    /**
     * Return the number of checking to find the number of the floor broken ball.
     * checking by K balls.
     */
    public static int numOfCheckingForKBall(int balls, int n) {
        int[][] matChecks = new int[balls + 1][n + 1];
        int min;

        //init the one ball number of checking for each floor.(the zero ball init to 0 automatic.
        for (int i = 1; i < n + 1; i++) {
            matChecks[1][i] = i;
        }
        for (int i = 1; i < balls+1;  i++) {

            matChecks[i][1] = 1;
        }
        for (int i = 2; i < balls+1; i++) {
            for (int j = 2; j < n+1; j++) {
                matChecks[i][j] = n+1;
                for (int k = 1; k < j; k++) {
                    min = Math.max(matChecks[i - 1][k-1], matChecks[i][j - k]) + 1;
                    if (min < matChecks[i][j]) {
                        matChecks[i][j] = min;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(matChecks));
        return matChecks[balls][n];
    }

    public static void main(String[] args) {

        System.out.println(numOfFloorBrokenBall(78,  new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}));
        //useTrianglesNumber(55, new int[] {10,20,30,40,50,60,70});
        System.out.println(numOfCheckingFor2Ball(10));
        System.out.println(numOfCheckingFor3Ball(10));
        System.out.println(numOfCheckingForKBall(4, 105));
    }
}


//    /**
//     * using 2 balls
//     *O(sqrt(n))
//     */
//    public static void useTrianglesNumber(int a , int [] arr){
//        int numOfCheck = 0;
//        int floor = 1;  //represent p from the problem.
//        int numOfFloors = arr.length;
//
//        while(floor*(floor+1)/2 < numOfFloors){  //represent the size of p from the problem.
//           floor++;
//        }
//        int currentFloor = floor;  //the floor we are on.
//        int jump = floor-1; //the amount of floors we will jump every time the ball is not broken
//
//        while(arr[currentFloor]<a){  //search when the ball will broke, until that we will jump to the next floor (p+(p-1)).
//            currentFloor = currentFloor + jump;
//            jump = jump -1;
//            numOfCheck++;
//        }
//        System.out.println("The first ball brakes at floor: "+currentFloor);
//
//        currentFloor = currentFloor - (jump+1);  //return to the last floor we were on.
//        while(arr[currentFloor]<a){
//            currentFloor++;
//            numOfCheck++;
//        }
//        System.out.println("The second ball brakes at floor: "+currentFloor);
//        System.out.println("the number of checks is: "+numOfCheck);
//    }