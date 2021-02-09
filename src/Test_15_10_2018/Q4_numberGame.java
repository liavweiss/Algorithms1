package Test_15_10_2018;

/**
 * This class represents a solution for Test_24_1_2021.Q1 from the test date: 15.10.2018.
 */
public class Q4_numberGame {

    public static int[][] matrixBuilder(int[] game) {
        int n = game.length;
        int[][] mat = new int[n][n];

        for (int i = 0; i < n; i++) {
            mat[i][i] = game[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                mat[i][j] = Math.max(game[i] - mat[i + 1][j], game[j] - mat[i][j - 1]);
            }
        }
        return mat;
    }

    public static int maximumProfitNumberGame(int[] game) {
        int[][] mat = matrixBuilder(game);
        int n = game.length;
        int firstSum = 0;
        int secondSum = 0;
        int i = 0;
        int j = n - 1;


        for (int k = 0; k < n / 2; k++) {
            if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {
                firstSum += game[i];
                i++;
            } else {
                firstSum += game[j];
                j--;
            }
            if (i != j) {
                if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {
                    secondSum += game[i];
                    i++;
                } else {
                    secondSum += game[j];
                    j--;
                }
            } else {
                secondSum += game[i];
            }
        }
        return firstSum - secondSum;
    }

    public static void allGameNumber(int[] game) {
        int[][] mat = matrixBuilder(game);
        int n = game.length;
        int firstSum = 0;
        int secondSum = 0;
        int i = 0;
        int j = n - 1;
        int first = 0;
        int second = 0;

        for (int k = 0; k < n / 2; k++) {
            if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {
                firstSum += game[i];
                first = i++;
            } else {
                firstSum = game[j];
                first = j--;
            }
            if (i != j) {
                if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {
                    secondSum += game[i];
                    second = i++;
                } else {
                    secondSum += game[j];
                    second = j--;
                }
            } else {
                secondSum += game[i];
                second = i;
            }
            System.out.println("first: game[" + first + "], second: game[" + second + "]");
        }
        System.out.println("the diff from player one to player to is: " + (firstSum - secondSum));
    }


    public static void main(String[] args) {
        System.out.println("*********************Q4a*****************");
        int[] game = {2, 8, 7, 4};
        int max = maximumProfitNumberGame(game);
        System.out.println(max);

        System.out.println("*********************Q4b*****************");
        allGameNumber(game);
    }
}
