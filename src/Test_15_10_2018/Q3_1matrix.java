package Test_15_10_2018;

/**
 * This class represents a solution for Q3 from the test date: 15.10.2018.
 */
public class Q3_1matrix {

    public static int matrix_1(int[][] a){
        int n=a.length;
        int[][] arr = new int[n][n];
        int max=0;
        for(int i=0; i<n; i++){
            arr[i][0] = a[i][0];
            arr[0][i] = a[0][i];
        }

        for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                if(a[i][j] == 1){
                    arr[i][j] = min3(arr[i-1][j], arr[i][j-1], arr[i-1][j-1]) +1;
                    if(arr[i][j]>max){
                        max = arr[i][j];
                    }
                }
            }
        }
        return max;
    }


    public static int min3(int a, int b, int c){
        int min =a;
        if(min>b){
            min = b;
        }
        if(min>c){
            min = c;
        }
        return min;
    }

    public static int matrix_1_kxk(int[][] a, int k){
        int n=a.length;
        int[][] arr = new int[n][n];
        int max=0;
        for(int i=0; i<n; i++){
            arr[i][0] = a[i][0];
            arr[0][i] = a[0][i];
        }

        for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                if(a[i][j] == 1){
                    arr[i][j] = min3(arr[i-1][j], arr[i][j-1], arr[i-1][j-1]) +1;
                    if(arr[i][j]>=k){
                        max++;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] arr =  {{1, 1, 1, 1},
                        {1, 1, 1, 0},
                        {1, 1, 1, 0},
                        {1, 1, 0, 0}};

        int max = matrix_1(arr);
        System.out.println(max);
        int max2 = matrix_1_kxk(arr, 2);
        System.out.println(max2);
    }
}
