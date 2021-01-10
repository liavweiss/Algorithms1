package PowerProblem;

/**
 * This class represents the way to compute a power with complexity of O(log n).
 */
public class Power {


    public static int PowerByLoop(int x, int n) {
        int result = 1;

        while (n != 0) {

            if (n % 2 == 1) {
                result *= x;
            }
            x *= x;
            n /= 2;
        }
        return result;
    }


    public static int PowerByRecursion(int x, int n) {
        if (n == 0) return 1;

        if (n % 2 == 0) return PowerByRecursion(x * x, n / 2);

        return x * PowerByRecursion(x * x, (n - 1) / 2);
    }

    public static void main(String[] args) {
        System.out.println(PowerByLoop(2, 8));
        System.out.println(PowerByRecursion(2,8));

        System.out.println(PowerByLoop(3, 3));
        System.out.println(PowerByRecursion(3,3));
    }

}
