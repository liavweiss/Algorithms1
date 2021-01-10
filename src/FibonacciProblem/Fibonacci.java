package FibonacciProblem;

/**
 * This class represents the way to solve Fibonacci problem with complexity of O(log n).
 */
public class Fibonacci {

    public static int FibonacciMatrix(int n){
        int fibo [][] = {{1,1},{1,0}};
        int other [][] = {{1,1},{1,0}};
        n-=2;

        while(n!=0){
            if(n%2==1){
                MultMatrix(fibo,other);
            }
            MultMatrix(other,other);
            n/=2;
        }
        return fibo[0][0];
    }

    static void MultMatrix(int fibo[][], int other[][]) {
        int first =  fibo[0][0]*other[0][0] + fibo[0][1]*other[1][0];
        int second =  fibo[0][0]*other[0][1] + fibo[0][1]*other[1][1];
        int third =  fibo[1][0]*other[0][0] + fibo[1][1]*other[1][0];
        int fourth =  fibo[1][0]*other[0][1] + fibo[1][1]*other[1][1];
        fibo[0][0] = first;
        fibo[0][1] = second;
        fibo[1][0] = third;
        fibo[1][1] = fourth;
    }


    public static void main(String[] args) {
        System.out.println(FibonacciMatrix(6));
    }
}
