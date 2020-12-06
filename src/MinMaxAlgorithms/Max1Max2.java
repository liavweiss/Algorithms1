package MinMaxAlgorithms;

/**
 * This class finds two maximum members in the array, using the fastest method (in pairs).
 */

public class Max1Max2 {

    public static void Max1_Max2(int[] arr) {
        int max1,max2;
        int comparisons=1;
        if(arr[0]>arr[1]) {
            max1=arr[0];
            max2=arr[1];
        }else {
            max1=arr[1];
            max2=arr[0];
        }
        for (int i = 2; i < arr.length-1; i=i+2) {
            comparisons++;
            if(arr[i+1]>arr[i]) {
                comparisons+=2;
                if(arr[i+1]>max1) {
                    max2=max1;
                    max1=arr[i+1];
                }
                if(arr[i]>max2) {
                    max2=arr[i];
                }
            }
            else {
                comparisons+=2;
                if(arr[i+1]<arr[i]) {
                    max2=max1;
                    max1=arr[i];
                }
                if(arr[i+1]>max2) {
                    max2=arr[i+1];
                }
            }
        }
        if(arr.length%2!=0) {
            comparisons++;
            if(arr[arr.length-1]>max1) {
                max2=max1;
                max1=arr[arr.length-1];
            }

            else if(arr[arr.length-1]>max2) {
                max2=arr[arr.length-1];
                comparisons++;
            }
        }
        System.out.println("max1 :"+max1 +" max2: "+max2+"comparisons:"+comparisons);
    }

    public static void main(String[] args) {
        int [] arr = {0,1,2,3,4,5,6,7,8,9,1,5,4,8,7,88,55,100,60,150};
        Max1_Max2(arr);
    }
}
