package Test_17_09_2018;


/**
 * This class represents a solution for Q1 from the test date: 17.09.2018.
 */
public class Q3_galssBall {
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

    public static void main(String[] args) {
        System.out.println(numOfCheckingFor2Ball(10));
        System.out.println(numOfCheckingFor3Ball(10));
    }
}
