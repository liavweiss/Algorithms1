package Test_24_1_2021;

public class Q1 {

    /**
     * Test_24_1_2021.Q1)a)
     */
    public static int lcs(int[] X, int[] Y) {
        return matrixBuilder(X,Y);
    }

    private static int matrixBuilder(int[] X, int[] Y){
        int row = X.length + 1;
        int col = Y.length + 1;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < col; j++) {
            matrix[0][j] = 0;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[row-1][col-1];
    }


    /**
     *Test_24_1_2021.Q1)b)
     */
    public static int lcs3(int[] X, int[] Y, int[] Z){
        int row = X.length + 1;
        int col = Y.length + 1;
        int counterSize=0;
        int count = 0;
        for(int i=1; i<row; i++){
            for(int j =1; j<col; j++){
                if(X[i-1]==Y[j-1]){
                    for(int k = count; k<Z.length;k++){
                        if(X[i-1]==Z[k]){
                            count=k;
                            counterSize++;
                            break;
                        }
                    }
                }
            }
        }
        return counterSize;
    }
}
