package Test_31_10_2017;

/**
 * This class represents a solution for Test_24_1_2021.Q2 from the test date: 31.10.2017.
 */
public class Q2_ballGlass {
    public static int glassBall2(int[] floors, int ball) {
        int n = floors.length;
        int step = 1;
        while((step*(step+1))/2 < n) {
            step++;
        }
        int currentFloor = step;
        boolean isBreak = false;
        while(!isBreak) {
            if(floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while(!isBreak) {
                    if(floors[currentFloor] > ball) {
                        return currentFloor+1;
                    }
                    currentFloor++;
                }
            }
            if(currentFloor == n-1) break;
            step--;
            currentFloor += step;
            if(currentFloor > n-1) currentFloor = n-1;
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(glassBall2(new int[] {10,20,30,40,50,60,70},55));
        System.out.println(glassBall2(new int[] {10,11,12,13,14,15,16,17,18,19,20,21},23));
    }
}

