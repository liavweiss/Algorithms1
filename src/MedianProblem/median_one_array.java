package MedianProblem;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This class solves the median problem by taking the 64 license organs so that the probability of one of
 * them being greater than the median is one hundred percent.
 * (The number 64 does not matter, but the number must be large enough to reach 0).
 */
public class median_one_array {

    /**
     * run time:O(1).
     * @param a - the array.
     * @return max from the median
     */
    public static int medianMax(int [] a){
        int max = a[0];

        for(int i=1; i<a.length-1 && i<64-1; i=i+2){
           if(a[i]>a[i+1]) {
               if (max < a[i]) {
                   max = a[i];
               }
           }
           else{
               if(max<a[i+1]) { max = a[i+1];}
           }
        }
        return max;
    }


    public static void main(String[] args) {
        int [] a =new int [1000];
        for(int i=0; i<a.length; i++){
            a[i] = (int)(Math.random()*1000);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(medianMax(a));
    }
}
