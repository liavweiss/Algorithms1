package PizzaProblem;

/**
 * This class presents an algorithm for solving the pizza problem.
 * The pizza problem: Two brothers ordered a family pizza, one brother eating X times faster than the other brother eating.
 * The two must not reach the last triangle at the same time, this algorithm offers the optimal pizza division.
 */
public class pizzaProblem {

    public static int pizza(double x , int n){
            int k = (int)x+1;
            int p = n/(k+1);
            int r = n%(k+1);
            int ans = -1;
            if(r != 1) {
                double t = (x*p+r-1)/((x+1)*p+r);
                if(t < x/(x+1)) ans = 1;
                else ans = 0;
            }
            return ans;
    }

    // practice from gil levi marathon.
    public static int getNumberOfPieces(double k) {
        if(k == (int)k) return (int)k+1;
        return (int)k+2;
    }

    public static void main(String[] args) {
        System.out.println(pizza(1,3));
        System.out.println(getNumberOfPieces(200));
    }
}
