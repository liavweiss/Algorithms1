package LIS;

import java.util.Arrays;
import java.util.Stack;

/**
 * This class represents an algorithm for solving the LIS problem - finding the longest rising sub-series
 * (order is important, sequences are not important).
 * by using with greedy algorithms.
 */
public class Lis_Greedy {


    // O(n) - the least good algorithm of greedy.
    public static Stack<Integer> greedyLIS(int[] arr,int index) {
       Stack<Integer> ans = new Stack<Integer>();
        ans.push(arr[index]);
        int max = arr[index];
        for (int i = index+1; i < arr.length; i++) {
            if(arr[i] > max) {
                ans.push(arr[i]);
                max = arr[i];
            }
        }
        return ans;
    }
    // O(n^2) - the better algorithm of greedy.(but also does not give the final correct answer).
    public static Stack<Integer> greedyLISImproved(int[] arr) {
        Stack<Integer> ans = new Stack<Integer>();
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            Stack<Integer> temp = greedyLIS(arr,i);
            if(temp.size() > maxLen) {
                maxLen = temp.size();
                ans = temp;
            }
        }
        return ans;
    }

    //Lis by Lcs.
    // O(n^2) - the better algorithm of greedy.(give yoe the final correct answer)
    public static int[] LIS_usingLCS(int[] arr) {
        int[] s_arr = new int[arr.length];
        for (int i = 0; i < s_arr.length; i++) {
            s_arr[i] = arr[i];
        }
        Arrays.sort(s_arr);
        return lcs(arr,s_arr);
    }
    private static int[] lcs(int[] X,int[] Y){
        int mat[][] = buildMatrix(X,Y);
        int i = mat.length-1;
        int j = mat[0].length-1;
        int end = mat[i][j];
        int[] ans = new int[end];
        int start=0;
        while (start<end) {
            if (X[i-1]==Y[j-1]) {
                ans[end-start-1] = X[i-1];
                i--; j--; start++;
            }
            else if (mat[i-1][j]>=mat[i][j-1]) i--;
            else j--;
        }
        return ans;
    }

    private static int[][] buildMatrix(int[] X,int[] Y) {
        int row=X.length+1;
        int col=Y.length+1;
        int mat[][] = new int[row][col];
        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (X[i-1]==Y[j-1]) mat[i][j] = mat[i-1][j-1] + 1;
                else mat[i][j] = Math.max(mat[i-1][j], mat[i][j-1]);
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int [] arr ={1,100,101,2,3,4,5,6,7};
        System.out.println(Arrays.toString(LIS_usingLCS(arr)));
        System.out.println((greedyLIS(arr,0)));
        System.out.println((greedyLISImproved(arr)));
    }
}
