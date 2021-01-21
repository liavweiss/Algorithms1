package NumberGameProblem;


import java.util.Arrays;

/**
 * This class represents the problem of numbers game, where players are given an even array of numbers and in each turn
 * they can take either the outer left or the outer right, the winner is the one whose sum of numbers is the largest.
 * with algorithms dynamic with complexity of O(n^2).
 */
public class NumberGame_Dynamic {

    public static int[][] matrixBuilder(int[] game) {
        int n = game.length;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i][i] = game[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = Math.max(game[i] - matrix[i + 1][j], game[j] - matrix[i][j - 1]);
            }
        }
        return matrix;
    }

    public static void gameStrategy(int[] game) {
        int i = 0;
        int n = game.length;
        int j = n - 1;
        int first = 0;
        int second = 0;
        int firstSum = 0;
        int secondSum = 0;
        int[][] matrix = matrixBuilder(game);

        for (int k = 0; k < n / 2; k++) {
            if (game[i] - matrix[i + 1][j] > game[j] - matrix[i][j - 1]) {
                firstSum += game[i];
                first = i++;
            } else {
                firstSum += game[j];
                first = j--;
            }

            if (i != j) {
                if (game[i] - matrix[i + 1][j] > game[j] - matrix[i][j - 1]) {
                    secondSum += game[i];
                    second++;
                } else {
                    secondSum += game[j];
                    second = j--;
                }
            } else { //j=0 or i=n-1
                second = j;
                secondSum += game[j];
            }
            System.out.println("first: game[" + first + "] =" + game[first] + ", second: game[" + second + "] = " + game[second]);
        }
        System.out.println("firstSum= " + firstSum + ", secondSum =" + secondSum + ", the diff is: " + (firstSum - secondSum));
    }


    /**
     * Return the maximum diff from player 1 to player 2.
     */
    public static int maxDiff(int [] game){
        int [][] mat = matrixBuilder(game);
        return mat[0][mat.length-1];
    }


    public static void main(String[] args) {
        int[] arr = {5, 6, 10, 8};
        int[][] matrix = matrixBuilder(arr);
        System.out.println(Arrays.deepToString(matrix));
        gameStrategy(arr);
        System.out.println(maxDiff(arr));
    }
}


/**
 *הוכחת נכונות
 * תכנות דינאמי
 * אנחנו בונים מטריצת עזר שאיברי האלכסון שלה הם איברי המערך של המשחק
 * נרוץ מהפינה הימנית של המטריצה עד לפינה השמאלית העליונה כשר בכל תר יהיה את המקסימום רווח
 * לאחר בניית המטריצה נרות מהפינה הימנית העליונה של המטריצה בכמות של אורך המערך חלקי 2
 * כאשר:
 * I - אינדקס של איבר שמאלי שפנוי
 * J - אינדקס של איבר ימיני שפנוי
 *
 * הרווח המקסימאלי של שחקן במצב (I,J) יהיה:
 * F(I,J) = max(aI - F(I+1, J), F(aJ-F(I,J-1).
 */
