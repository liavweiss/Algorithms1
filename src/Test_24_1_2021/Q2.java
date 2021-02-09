package Test_24_1_2021;

public class Q2 {

    /**
     * Test_24_1_2021.Q2)a)
     */
    public static int longestOfOne(int[] arr) {
        int max = 0;
        int[] arr2 = new int[arr.length];
        if (arr[0] == 1) {
            arr2[0] = 1;
            max = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr2[i] = arr2[i - 1] + 1;
                if (max < arr2[i]) max = arr2[i];
            }
        }
        return max;
    }


    /**
     * Test_24_1_2021.Q2)b)
     */
    public static int imaginaryPlus(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] mat1 = new int[row][col];
        int[][] mat2 = new int[row][col];
        int[][] mat3 = new int[row][col];
        int[][] mat4 = new int[row][col];
        for (int i = 0; i < col; i++) {
            mat1[0][i] = arr[0][i];
        }
        for (int i = 0; i < col; i++) {
            mat2[row - 1][i] = arr[row - 1][i];
        }
        for (int i = 0; i < row; i++) {
            mat3[i][0] = arr[i][0];
        }
        for (int i = 0; i < row; i++) {
            mat4[i][col - 1] = arr[i][col - 1];
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] != 0) {
                    if (i != 0) {
                        mat1[i][j] = 1 + mat1[i - 1][j];
                    }
                    if (j != 0) {
                        mat3[i][j] = 1 + mat3[i][j - 1];
                    }
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (arr[i][j] != 0) {
                    if (i != row - 1) {
                        mat2[i][j] = 1 + mat2[i + 1][j];
                    }
                    if (j != col - 1) {
                        mat4[i][j] = 1 + mat4[i][j + 1];
                    }
                }
            }
        }
        int maxPlus = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maxPlus < Math.min(Math.min(mat1[i][j], mat2[i][j]), Math.min(mat3[i][j], mat4[i][j]))) {
                    maxPlus = Math.min(Math.min(mat1[i][j], mat2[i][j]), Math.min(mat3[i][j], mat4[i][j]));
                }
            }
        }
        return (maxPlus - 1) * 4 + 1;

    }

}
