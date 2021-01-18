package Test_18_02_2018;

/**
 * This class represents a solution for Q4 from the test date: 18.02.2018.
 * Receives an array and returns the indexes where the array difference is the maximum.
 */
public class Q4 {

    public static void arrayDiff(int[] arr) {
        int min, max;
        min = arr[0];
        max = arr[1];

        if (arr[0] > arr[1]) {
            max = arr[0];
            min = arr[1];
        }
        for (int i = 2; i < arr.length - 1; i = i + 2) {

            if (arr[i] < arr[i + 1]) {

                if (arr[i] < min) {
                    min = arr[i];
                }
                if (arr[i + 1] > max) {
                    max = arr[i + 1];
                }
            } else {

                if (arr[i + 1] < min) {
                    min = arr[i + 1];
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            if (arr.length % 2 != 0) {

                if (arr[arr.length - 1] > max) {
                    max = arr[arr.length - 1];
                } else {

                    if (arr[arr.length - 1] < min) {
                        min = arr[arr.length - 1];
                    }
                }
            }
        }
        System.out.println("the diff is: "+(max-min));
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 7, 3, 9, 5};
        arrayDiff(arr);
    }
}
