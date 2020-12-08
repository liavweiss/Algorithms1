package Lcs;

/**
 * This class represents an algorithm for solving the LCS problem - finding the largest common string
 * (order is important, sequences are not important).
 */
public class Lcs {

    public static int[][] matrixBuilder(String x, String y) {
        int row = x.length() + 1;
        int col = y.length() + 1;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < col; j++) {
            matrix[0][j] = 0;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix;
    }

    public static int lsc(String x, String y) {
        int[][] matrix = matrixBuilder(x, y);
        int row = matrix.length;
        int col = matrix[0].length;
        int seqLength = matrix[row - 1][col - 1];
        String result = "";
        int i = row-1;
        int j = col-1;
        int counter = seqLength - 1;

        while (counter >= 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                result = x.charAt(i - 1) + result;
                i--;
                j--;
                counter--;
            } else if (matrix[i][j] == matrix[i][j - 1]) {
                j--;
            } else {
                i--;
            }
        }
        return result.length();
    }

    public static void main(String[] args) {
        String s1 = "abcdefgh";
        String s2 = "abjhcfdabcdelf";

        System.out.println(lsc(s1,s2));
    }
}
