package Test_31_10_2017;

/**
 * This class represents a solution for Q2 from the test date: 31.10.2017.
 */
public class Q4_lcs_insert_delete {

    /**
     * O(m*n)
     */
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

    /**
     * O(m+n)
     */
    public static String lcs(String x, String y) {
        int[][] matrix = matrixBuilder(x, y);
        int row = matrix.length;
        int col = matrix[0].length;
        int seqLength = matrix[row - 1][col - 1];
        String result = "";
        int i = row - 1;
        int j = col - 1;
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

        return result;
    }

    /**
     * count hoh many time we delete or insert for change x to be y.
     * O(n*m) - because lcs.
     */
    public static void lcsInsertDelete(String x, String y) {
        String lcs = lcs(x, y);
        System.out.println(lcs);
        int n = lcs.length();

        int delete = x.length() - n;
        int insert = y.length() - n;

        System.out.println("The number of delete is: "+delete);
        System.out.println("The number of insert is: "+insert);
    }


    public static void main(String[] args) {
        String s1 = "abcdefgh";
        String s2 = "abjhcfdabcdelfg";
        System.out.println(lcs(s1,s2));

        String x = "abcdefklk";
        String y = "brcdeal";
        lcsInsertDelete(x,y);

    }
}
