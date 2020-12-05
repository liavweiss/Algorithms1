package MinMaxAlgorithms;

import java.util.Arrays;

public class MaxAndMinSearch {

    public static int[] maxAndMinSearchOnArray(int[] arr) {

        //the first is the max the second is thr min ant the third is the number of the comparisons.
        int[] a = new int[3];
        int min, max, comparisons;
        min = arr[0];
        max = arr[1];
        comparisons = 1;
        if(arr[0]>arr[1]){
            max=arr[0];
            min=arr[1];
        }
        for (int i = 2; i < arr.length-1; i=i+2) {
            comparisons++;
            if (arr[i] < arr[i + 1]) {
                comparisons+=2;
                if(arr[i]<min){
                    min=arr[i];
                }
                if(arr[i+1]>max){
                    max=arr[i+1];
                }
            }
            else{
                comparisons+=2;
                if(arr[i+1]<min){
                    min=arr[i+1];
                }
                if(arr[i]>max){
                    max=arr[i];
                }
            }
            if(arr.length%2!=0){
                comparisons++;
                if(arr[arr.length-1]>max){
                    max=arr[arr.length-1];
                }
                else{
                    comparisons++;
                    if(arr[arr.length-1]<min){
                        min=arr[arr.length-1];
                    }
                }
            }
        }
        a[0]=min;
        a[1]=max;
        a[2]=comparisons;
        return a;
    }

    public static void main(String[] args) {
        int [] arr ={0,4,5,8,7,5,8,4,7,55,8,7,4,22,5,8};
        int [] a = maxAndMinSearchOnArray(arr);
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);


    }
}
