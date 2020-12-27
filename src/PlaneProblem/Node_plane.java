package PlaneProblem;

/**
 * This class represents the problem of the plane, the plane must reach from
 * point (0,0) to point (m, n) at the minimum cost, while the rest of the plane
 * is allowed to move down and to the right.
 */

import java.util.Arrays;

class Node {

    public int xPrice;
    public int yPrice;
    public int price;
    public int nPath;
    public int counter = 0;


    public Node(int x, int y) {
        this.xPrice = x;
        this.yPrice = y;
        this.price = 0;
        this.nPath = 0;
    }

    @Override
    public String toString() {
        return counter++ + "{" + "price:" + price + " xPrice=" + xPrice + " yPrice=" + yPrice + '}';
    }

}

class planeProblem {

    public static Node[][] node_plane_mat;


    public planeProblem() {
        node_plane_mat = init_matrix(10, 10);
    }


    public static Node[][] init_matrix(int row, int col) {
        Node matrix[][] = new Node[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = new Node((int) (Math.random() * 10), (int) (Math.random() * 10));
            }
        }
        return matrix;
    }

    //O(n+m): n-row , m-col.
    public static void pricePathMatrix_builder(Node[][] mat) {
        for (int j = 1; j < mat[0].length; j++) {
            mat[0][j].price = mat[0][j - 1].price + mat[0][j - 1].xPrice;
            mat[0][j].nPath = 1;
        }
        for (int i = 1; i < mat.length; i++) {
            mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].yPrice;
            mat[i][0].nPath = 1;
        }
        int a, b;
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                a = mat[i - 1][j].price + mat[i - 1][j].yPrice; // from above
                b = mat[i][j - 1].price + mat[i][j - 1].xPrice; // from left
                if (a < b) {// from above
                    mat[i][j].price = a;
                    mat[i][j].nPath = mat[i - 1][j].nPath;
                } else if (a > b) {// from left
                    mat[i][j].price = b;
                    mat[i][j].nPath = mat[i][j - 1].nPath;
                } else {// a == b
                    mat[i][j].price = a;
                    mat[i][j].nPath = mat[i - 1][j].nPath + mat[i][j - 1].nPath;
                }
            }
        }
    }

    ////O(n+m): n=row , m=col.
    public static String getBestPath_1() {
        String path = "";
        int i = node_plane_mat.length - 1;
        int j = node_plane_mat[0].length - 1;

        while (i > 0 && j > 0) {
            int x = node_plane_mat[i][j - 1].price + node_plane_mat[i][j - 1].xPrice;
            int y = node_plane_mat[i - 1][j].price + node_plane_mat[i - 1][j].yPrice;

            if (y <= x) {
                path = "up, " + path;
                i--;
            } else {                        // if (y >= x)
                path = "right, " + path;
                j--;
            }
        }
        while (j > 0) {
            path = "right, " + path;
            j--;
        }
        while (i > 0) {
            path = "up, " + path;
            i--;
        }
        return path;
    }


    //  O(n+m): n=row , m=col.
    // return the path on recursion.
    public static void getBestTrack_1_Rec(String path, int i, int j) {

        if (i > 0 && j > 0) {
            int x = node_plane_mat[i][j-1]. price + node_plane_mat[i][j-1].xPrice;
            int y = node_plane_mat[i-1][j].price + node_plane_mat[i-1][j].yPrice;

            if (y <= x)
                getBestTrack_1_Rec("up, "+ path, i-1, j);
            if (y > x)
                getBestTrack_1_Rec("right, "+ path, i, j-1);
        }
        if (i == 0 && j > 0)
            getBestTrack_1_Rec("right, "+ path, i, j-1);
        if (i > 0 && j == 0)
            getBestTrack_1_Rec("up, "+ path, i-1, j);
        if (i == 0 && j == 0)			//  i == 0 && j == 0
            System.out.println("path : " + path);
    }

    //  O(n*m): n=row , m=col.
    // return all paths recursive
    public static void getAllBestTracks(String path, int i, int j) {

        if (i > 0 && j > 0) {
            int x = node_plane_mat[i][j-1]. price + node_plane_mat[i][j-1].xPrice;
            int y = node_plane_mat[i-1][j].price + node_plane_mat[i-1][j].yPrice;

            if (y < x)
                getAllBestTracks("up, "+ path, i-1, j);
            if (y > x)
                getAllBestTracks("right, "+ path, i, j-1);
            if (y == x) {
                getAllBestTracks("up, "+ path, i-1, j);
                getAllBestTracks("right, " + path, i, j-1);
            }
        }
        if (i == 0 && j > 0)
            getAllBestTracks("right, "+ path, i, j-1);
        if (i > 0 && j == 0)
            getAllBestTracks("up, "+ path, i-1, j);
        if (i == 0 && j == 0)			//  i == 0 && j == 0
            System.out.println("path : " + path);
    }


    public static void main(String[] args) {
        Node[][] matrix = init_matrix(2, 2);
        System.out.println(Arrays.deepToString(matrix));
        pricePathMatrix_builder(matrix);
        System.out.println(Arrays.deepToString(matrix));

    }

}
