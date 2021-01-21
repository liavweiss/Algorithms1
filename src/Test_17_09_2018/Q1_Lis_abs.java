package Test_17_09_2018;

import java.util.Arrays;

/**
 * This class represents a solution for Q3 from the test date: 17.09.2018.
 */
public class Q1_Lis_abs {

    /**
     *Lis length - O(nlog n)
     */
    public static int LISLength(int[] a) {
        int[] arr = new int[a.length];
        arr[0]=a[0];
        int lis = 0;
        int index;
        for (int i = 1; i < a.length; i++) {
            index= binarySearchForLength(arr , a[i] , lis );
            arr[index]=a[i];
            if(index>lis) lis++;
        }
        return lis+1;
    }

    public static int binarySearchForLength(int[] arr , int a , int lis) {
        if(a<arr[0]) return 0;
        if(a>arr[lis]) return lis+1;
        int start = 0;
        while(start<=lis) {
            int middle = (start + lis) /2;
            if(start == lis) return start;
            else {
                if(arr[middle]==a) return middle;
                else if(a<arr[middle]) lis=middle;
                else start = middle+1;
            }
        }
        return -1;
    }

    /**
     *Return the length of lis with diff abs between the adjacent of k.
     */
    public static int LISLength_abs(int[] a, int k) {
        int n = a.length;
        int[] arrOfAbs = new int[n];
        arrOfAbs[0]=0;
        int lis = 0;
        int index=0;
        int sum=0;
        for (int i = 1; i < arrOfAbs.length; i++) {
            arrOfAbs[i] = Math.abs(a[i]-a[i-1]);
        }
        for (int i = 1; i < a.length; i++) {
            if(arrOfAbs[i] == k){
                lis++;
                index=i;
                sum=0;
            }
            else if(lis == 0 && a[i] == k){
                lis++;
                index=i;
                sum=0;
            }
            else{
                sum+=arrOfAbs[i];
                if(sum == k){
                    sum=0;
                    lis++;
                    index=i;
                }
                else if(sum>k){
                    if(Math.abs(a[index] - a[i]) == k){
                        sum=0;
                        lis++;
                        index = i;
                    }
                }
            }
        }
        return lis;
    }

    public static void main(String[] args) {
        int[] arr = {1,11,2,10,4,5,2,1};
        System.out.println(LISLength(arr));
        System.out.println(LISLength_abs(arr,1));
    }
}
