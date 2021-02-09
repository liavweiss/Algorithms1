package Test_15_10_2018;

import java.util.Arrays;

/**
 * This class represents a solution for Test_24_1_2021.Q1 from the test date: 15.10.2018.
 */
public class Q1_lcs_arrays_3 {
    public static int[][] matrixBuilder(int[] a, int[] b){
        int row= a.length +1;
        int col = b.length +1;
        int[][] mat = new int [row][col];

        for(int i=0; i<row; i++){
            mat[i][0] =0;
        }
        for(int i=1; i<col; i++){
            mat[0][i] =0;
        }
        for(int i=1; i<row; i++){
            for(int j=1;j<col; j++){
                if(a[i-1] == b[j-1]){
                    mat[i][j] = mat[i-1][j-1] +1;
                }
                else{
                    mat[i][j] = Math.max(mat[i][j-1], mat[i-1][j]);
                }
            }
        }
        return mat;
    }

    public static int[] lcs(int[] a, int[] b){
        int [][] mat = matrixBuilder(a,b);
        int row = mat.length;
        int col = mat[0].length;
        int size = mat[row-1][col-1];
        int [] ans = new int [size];
        int counter = size-1;
        int i=row-1;
        int j=col-1;
        int place = size-1;

        while(counter>=0){
            if(a[i-1] == b[j-1]){
                ans[place] = a[i-1];
                i--;
                j--;
                place--;
                counter--;
            }
            else if(mat[i][j] == mat[i][j-1]){
                j--;
            }
            else{
                i--;
            }
        }
        return ans;
    }

    public static int[] lcsThreeSubStringCommon(int[] a, int[] b, int[] c){
        int row = a.length + 1;
        int col = b.length + 1;
        int[][] mat  =new int [row][col];
        int countC = 0;
        for(int i=1; i<row; i++){
            for(int j =1; j<col; j++){
                if(a[i-1]==b[j-1]){
                    for(int k = countC; k<c.length;k++){
                        if(a[i-1]==c[k]){
                            mat[i][j] = mat[i-1][j-1] +1;
                            countC=k;
                            break;
                        }
                    }
                    if(mat[i][j] == 0){
                        mat[i][j] = Math.max(mat[i][j-1], mat[i-1][j]);
                    }
                }
                else{
                    mat[i][j] = Math.max(mat[i][j-1], mat[i-1][j]);
                }
            }
        }
        int [] ans = helpForLcsThreeSubStringCommon(a,b,mat);
        return ans;
    }

    public static int[] helpForLcsThreeSubStringCommon(int[] a, int[] b, int[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        int size = mat[row-1][col-1];
        int [] ans = new int [size];
        int counter = size-1;
        int i=row-1;
        int j=col-1;
        int place = size-1;

        while(counter>=0){
            if(a[i-1] == b[j-1]){
                ans[place] = a[i-1];
                i--;
                j--;
                place--;
                counter--;
            }
            else if(mat[i][j] == mat[i][j-1]){
                j--;
            }
            else{
                i--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("******************Q1a*****************");
        int [] arr = {1,2,3,4,5};
        int [] arr2 = {4,1,1,2,3};
        int [] ans = lcs(arr, arr2);
        System.out.println(Arrays.toString(ans));

        System.out.println("******************Q1b*****************");
        int [] a = {1,2,3,4,5};
        int [] b = {4,5,1,2,3};
        int [] c = {4,5,6,7,8};
        int [] lcs = lcsThreeSubStringCommon(a, b, c);
        System.out.println(Arrays.toString(lcs));
    }
}
