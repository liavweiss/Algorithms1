package MedianProblem;

import java.util.Arrays;

/**
 * This class represents an algorithm for solving the median problem where we get two sorted arrays,
 * by returning a third array of all the numbers greater than the median, we do this by parallel
 * algorithms where we make comparisons between the first limb of the array and the last limb of the
 * second array and by these comparisons we know how Arrange the third array.
 */
public class median_two_array {

    public static int [] medianArray(int [] a , int [] b) {
        int n = a.length-1; // a.length=b.length
        int[] c = new int[n+1];
        int i = 0, j = n, k = n;
        c[i] = Math.max(a[i], b[n -1]);
        while (k >= 0) {
            if (a[i] >= b[j]) {
                c[k--] = a[i++];
            } else {
                c[k--] = b[j--];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int [] a =new int [5];
        int [] b= new int [5];
        for(int i=0; i<a.length; i++){
            a[i] = (int)(Math.random()*10);
            b[i] = (int)(Math.random()*10);
        }
        int [] c = medianArray(a,b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }
}
