package Test_22_01_2018;

import java.util.Arrays;

/**
 * This class represents a solution for Q3 from the test date: 22.01.2018.
 */
public class Q3_game_number {

    /**
     *Q3:a:
     */
    public static int[][] matrixBuilder(int[] arr) {
        int n = arr.length;
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = arr[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                mat[i][j] = Math.max(arr[i] - mat[i + 1][j], arr[j] - mat[i][j - 1]);
            }
        }
        return mat;
    }

    public static void numberGame(int[] arr) {
        int[][] game = matrixBuilder(arr);
        int n = arr.length;
        int i=0;
        int j=n-1;
        int firstSum=0;
        int secondSum=0;
        int first=0;
        int second=0;

        for(int k=0; k<n/2; k++){
            if(arr[i]-game[i+1][j]>arr[j] - game[i][j-1]){
                firstSum+=arr[i];
                first=i++;
            }
            else{
                firstSum+=arr[j];
                first=j--;
            }
            if(i!=j){
                if(arr[i]-game[i+1][j]>arr[j] - game[i][j-1]){
                    secondSum+=arr[i];
                    second=i++;
                }
                else{
                    secondSum+=arr[j];
                    second=j--;
                }
            }
            else{
                secondSum+=arr[j];
                second=j;
            }
            System.out.println("first game: arr["+first+"] = "+arr[first]+", second game: arr["+second+"] = "+arr[second]);
        }
        System.out.println("first sum = "+firstSum+", second sum = "+secondSum+"--> different = "+(firstSum-secondSum));
    }

    /**
     * Q3:b:
     */
    public static void numberGameCircle(int[] arr) {
        int max = 0;
        int index=-1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index=i;
            }
        }

        int [] tempGame = new int[arr.length-1];
        for(int i=0;i<tempGame.length; i++){
            if(index+1 >= arr.length) index = 0;
            tempGame[i]=arr[++index];
        }
        System.out.println(Arrays.toString(tempGame));
        int[][] game = matrixBuilder(tempGame);
        int n = tempGame.length;
        int i=0;
        int j=n-1;
        int firstSum=0;
        int secondSum=max;

        for(int k=0; k<n/2; k++){
            if(arr[i]-game[i+1][j]>arr[j] - game[i][j-1]){
                firstSum+=arr[i];
                i++;
            }
            else{
                firstSum+=arr[j];
                j--;
            }
            if(i!=j){
                if(arr[i]-game[i+1][j]>arr[j] - game[i][j-1]){
                    secondSum+=arr[i];
                    i++;
                }
                else{
                    secondSum+=arr[j];
                    j--;
                }
            }
            else{
                secondSum+=arr[j];
            }

        }
        System.out.println("first sum = "+secondSum+", second sum = "+firstSum+"--> different = "+(secondSum-firstSum));

    }


    public static void main(String[] args) {
        System.out.println("**********************3a********************");
        int[] game = {5,6,10,11};
        numberGame(game);

        System.out.println("**********************3b********************");
        int[] arr2 = {12,5,6,10,11};
        numberGameCircle(arr2);
    }
}

