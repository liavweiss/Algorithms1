package LIS;

public class Lis_Dynamic {

    /**
     * This class represents an algorithm for solving the LIS problem - finding the longest rising sub-series
     * (order is important, sequences are not important).
     */


    //O(n log n)
    public static int lis(int[] a) {
        int[] arr = new int[a.length];
        arr[0]=a[0];
        int lis = 1;
        int index;
        for (int i = 1; i < a.length; i++) {
            index= binarySearchLis(arr , a[i] , lis );
            arr[index]=a[i];
            if(index>lis) lis++;
        }
        return lis;
    }


    public static int binarySearchLis(int[] arr , int a , int lis) {
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


    public static void main(String[] args) {
        int [] arr ={8,4,12,2,3,10,14};
        System.out.println(lis(arr));
    }
}
