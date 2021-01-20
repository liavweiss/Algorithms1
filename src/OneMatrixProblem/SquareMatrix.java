package OneMatrixProblem;

/**
 * This class represents the largest square 1 matrix problem, with dynamic algorithms.
 * complexity of O(n^2).
 */

public class SquareMatrix {
    public static int getBiggestSubMatrixOf1(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] help = new int[n][m];
        int max = 0, imax = 0, jmax = 0;

        //fill the first row and the first col on help matrix
        for (int i = 0; i < n; i++) {
            help[i][0] = mat[i][0];
        }
        for (int i = 0; i < m; i++) {
            help[0][i] = mat[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1) {
                    help[i][j] = min(help[i][j - 1], help[i - 1][j], help[i - 1][j - 1]) + 1;
                    if (help[i][j] > max) {
                        max = help[i][j];
                        jmax = j - max + 1;
                        imax = i - max + 1;
                    }
                }
            }
        }
        if (max != 0) System.out.println("Max square length is - " + max + ", start at: (" + imax + "," + jmax + ")");
        return max;
    }

    public static int min(int a, int b, int c) {
        int min = a;
        if (min > b) {
            min = b;
        }
        if (min > c) {
            min = c;
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] arr =  {{1, 1, 1, 1},
                        {1, 1, 1, 0},
                        {1, 1, 1, 0},
                        {1, 0, 1, 0}};

        getBiggestSubMatrixOf1(arr);
    }
}

/**
 * הוכחת נכונות:
 * נבנה מטריצת עזר:
 * העמודה הראשונה והשורה הראשונה נמלא בדיוק כמו במטריצה המקורית
 * ואז נסתכל על הדבר הבא:
 * אם במטריצה המקורית יש 0 נשאיר במטריצת העזר 0
 * אם במטריצה המקורית יש 1 נמלא את התא במטריצת העזר לפי המינימום של התא מעליו משמאלו ובאלכסון שלו למעלה
 * כאשר התא שבו יש את הערך הכי גדול הוא יהיה הפינה הימנית התחתונה של תת המטריצה הריבועית של האחדות
 */
