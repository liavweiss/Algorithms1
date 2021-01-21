package Test_17_09_2018;

/**
 * This class represents a solution for Q4 from the test date: 17.09.2018.
 */
public class Q4_power_fibonacci {

    /**
     *power on complexity of O(log n)
     */
    public static int power(int n, int x) {
        int results = 1;
        while (n != 0) {
            if(n%2==1){
                results *= x;
            }
            x *= x;
            n /= 2;
        }
        return results;
    }


    /**
     * fibonacci  on complexity of O(log n).
     */
    public static int fibonacci(int n){
        int [][] fibonacci = {{1,1},{1,0}};
        int [][] other = {{1,1},{1,0}};
        n = n - 2;
        while(n!=0){
            if(n%2 == 1){
                fibonacci = multMatrix(fibonacci, other);
            }
            other=multMatrix(other, other);
            n /= 2;
        }
        return fibonacci[0][0];
    }

    public static int[][] multMatrix(int[][] fibo, int[][] other){
        int [][] answer = new int [2][2];
        answer[0][0] = fibo[0][0] * other[0][0] + fibo[0][1] * other[1][0];
        answer[0][1] = fibo[0][0] * other[0][1] + fibo[0][1] * other[1][1];
        answer[1][0] = fibo[1][0] * other[0][0] + fibo[1][1] * other[1][0];
        answer[1][1] = fibo[1][0] * other[0][1] + fibo[1][1] * other[1][1];

        return answer;
    }


    public static void main(String[] args) {
        System.out.println(power(2,4));
        System.out.println(fibonacci(6));
    }
}
