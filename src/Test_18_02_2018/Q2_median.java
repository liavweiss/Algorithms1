package Test_18_02_2018;

import java.util.Arrays;

/**
 * This class represents a solution for Q2 from the test date: 18.02.2018.
 */
public class Q2_median {

    public static int biggestFromMedian(int[] arr){
        int median = arr[0];
        for(int i=0; i<arr.length && i<64; i+=2){
            if(arr[i]>arr[i+1]){
                if(median<arr[i]){
                    median = arr[i];
                }
            }
            else{
                if(median<arr[i+1]){
                    median = arr[i+1];
                }
            }
        }
        return median;
    }

    public static int[] medianArray(int[] a, int [] b){
        int n = a.length-1;
        int [] c = new int[n+1];
        int i=n;
        int j=n;
        int k=n;
        while(k>=0){
            if(a[i]>b[j]){
                c[k--] = a[i--];
            }
            else{
                c[k--] = b[j--];
            }
        }
        return c;
    }


    public static void main(String[] args) {
        System.out.println("********************Q2a**************");
        int[] arr = new int [1000];
        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*1000);
        }
        System.out.println(biggestFromMedian(arr));

        System.out.println("********************Q2b**************");
        int [] b ={4,50,100,120,200};
        int [] a= {45,51,78,91,102};

        int [] c = medianArray(a,b);
        System.out.println(Arrays.toString(c));
    }
}
