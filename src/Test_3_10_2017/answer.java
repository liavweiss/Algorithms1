package Test_3_10_2017;

import java.util.Arrays;

/**
 * This class represents a solution for the test from: 3.10.2017.
 */
public class answer {
    /**
     * Test_24_1_2021.Q1:a:
     */
    public static int oneMatrix(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int max = 0;

        int help[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            help[i][0] = arr[i][0];
        }
        for (int i = 0; i < m; i++) {
            help[0][i] = arr[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 1) {
                    help[i][j] = min(help[i - 1][j], help[i][j - 1], help[i - 1][j - 1]) + 1;
                    if (help[i][j] > max) {
                        max = help[i][j];
                    }
                }
            }
        }
        return max * max;
    }

    public static int min(int a, int b, int c) {
        int min = a;
        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }
        return min;
    }

    /**
     * Test_24_1_2021.Q1:b:
     */
    public static int oneMatrix2x2(int arr[][]) {
        int n = arr.length;
        int m = arr[0].length;
        int counter = 0;

        int help[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            help[i][0] = arr[i][0];
        }
        for (int i = 0; i < m; i++) {
            help[0][i] = arr[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 1) {
                    help[i][j] = min(help[i - 1][j], help[i][j - 1], help[i - 1][j - 1]) + 1;
                    if (help[i][j] >= 2) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * Test_24_1_2021.Q2:a:
     */
    public static int[] LISCircle(int [] a)
    {
        int [][] mat = new int[a.length][a.length];
        int index,count=1,max=0,i_i;
        mat[0][0] = a[0];
        for (int i = 0; i <a.length ; i++) {
            mat[0][0] = a[i];
            count = 1;
            if(i+1==a.length)
                i_i = 0;
            else
                i_i = i+1;
            for (int j = 1; j < a.length; j++) {

                index = binarySearchBetween(a[i_i], count, mat);
                mat[index][index] = a[i_i];
                if (count == index)
                    count++;
                copy(mat, index);
                i_i++;
                if (i_i == a.length )
                    i_i = 0;
            }
            if (count>max)
                max = count;
        }
        int [] arr = new int[max];
        for (int j = 0; j < max; j++) {
            arr[j] = mat[max-1][j];
        }
        return arr;
    }
    public static void copy(int [][] mat,int size)
    {
        for (int i = 0; i < size; i++) {
            mat[size][i] = mat[size-1][i];
        }
    }
    public static int binarySearchBetween(int num,int size,int[][] mat)
    {
        int low,high,mid;
        if(num<mat[0][0])
            return 0;
        if(num>mat[size-1][size-1])
            return size;
        low = 0;
        high = size;
        while(low<=high)
        {
            if(low == high)
                return low;
            mid = (high+low)/2;
            if(mat[mid][mid]>num)
            {
                high = mid;
            }
            else if(mat[mid][mid] < num)
                low = mid+1;
            else
                return mid;
        }
        return -1;
    }


    private static int binarySearchBetween(int[][] mat, int end, int v) {
        if(v < mat[0][0]) return 0;
        if(v > mat[end-1][end-1]) return end;
        int high = end, low = 0;
        while(low <= high) {
            if(low == high)return low;
            int mid = (low + high)/2;
            if(mat[mid][mid] == v) return mid;
            if(mat[mid][mid] > v) high = mid;
            else low = mid+1;
        }
        return -1;
    }


    /**
     * Q3:a: (b --> proof)
     */
    public static int fibonacci(int n) {
        int[][] fibo = {{1, 1}, {1, 0}};
        int[][] other = {{1, 1}, {1, 0}};
        while (n > 0) {
            if (n % 2 == 1) {
                fibo = multMatrix(fibo, other);
            }
            other = multMatrix(other, other);
            n = n / 2;
        }
        return fibo[1][1];
    }

    public static int[][] multMatrix(int[][] a, int[][] b) {
        int[][] matrix = new int[2][2];
        matrix[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        matrix[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        matrix[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        matrix[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];

        return matrix;
    }

    /**
     * Q4:a:
     */
    public static String lcsNoCommon(String x, String y) {
        if (x.length() > y.length()) {
            return x;
        } else if (y.contains(x)) {
            return null;
        }
        return x;
    }

    /**
     * Q4:b:
     */
    public static String lcsNoCommon2(String x, String y) {
        String ans = "";
        if (x.length() > y.length()) {
            return x;
        } else if (x.length() < y.length()) {
            return y;
        }
        if (x.contains(y)) {
            return null;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println("**********************1********************");
        int[][] arr = {{1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0}};
        System.out.println(oneMatrix(arr));
        System.out.println(oneMatrix2x2(arr));

        System.out.println("**********************2********************");
        int[] arr1 = {3,4,1,2,100};
        System.out.println(Arrays.toString(LISCircle(arr1)));

        System.out.println("**********************3********************");
        System.out.println(fibonacci(10));

        System.out.println("**********************4********************");
        String x = "abf";
        String y = "abfg";
        System.out.println(lcsNoCommon(x, y));
        System.out.println(lcsNoCommon2(x, y));
    }
}
