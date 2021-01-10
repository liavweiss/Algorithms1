package Recursion_Loop;

public class RecursionOrLoop {

    //loop is better: loop:O(n) , recursion:O(2^n)
    public static int [] fibonacci(int n){
        int [] a = new int [n];
        a[0]=a[1]=1;
        for(int i=2; i<n; i++){
            a[i]=a[i-1]+a[i-2];
        }
        return a;
    }

    //loop is better:because the memory. loop:O(n) , recursion:O(n)
    public static int factorialIterative(int n){
       int sum=1;
        for(int i=1; i<n; i++){
            sum*=i;
        }
        return sum;
    }

    public static int factorialRrecursion(int n){
       if(n==1) {return 1;}
       return n*factorialRrecursion(n-1);
    }


    //recursion is better:because it is a readable code. run time:O(n log n).
    public static int [] mergeSort (int [] a , int low , int high){
        if(low<high){
            int mid=(low+high)/2;
            mergeSort(a,low,mid);
            mergeSort(a,mid+1,high);
          //  merge(a[low,mid] , a[mid,high]); //need the merge function.
        }
        return a;
    }

}


