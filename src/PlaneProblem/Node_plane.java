package PlaneProblem;

/**
 * This class represents the problem of the plane, the plane must reach from
 * point (0,0) to point (m, n) at the minimum cost, while the rest of the plane
 * is allowed to move down and to the right.
 */

import java.util.Arrays;

public class Node_plane {
    public int xPrice;
    public int yPrice;
    public int price;
    public int nPath;
    public int counter=0;


    public Node_plane(int x , int y){
        this.xPrice=x;
        this.yPrice=y;
        this.price=0;
        this.nPath=0;
    }

    @Override
    public String toString() {
        return counter+++"{" +"price:"+ price + " xPrice=" + xPrice + " yPrice=" + yPrice + '}';
    }

    public static Node_plane [][] init_matrix(int row , int col){
        Node_plane matrix [] [] = new Node_plane[row][col];

        for(int i=0;i<row;i++){
            for(int j=0; j<col; j++){
                matrix[i][j] = new Node_plane((int)(Math.random()*10) , (int)(Math.random()*10));
            }
        }
        return matrix;
    }

    //O(n+m): n-row , m-col.
    public static void pricePathMatrix_builder(Node_plane [][] mat) {
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


    public static void main(String[] args) {
       Node_plane [] [] matrix = init_matrix(2,2);
        System.out.println(Arrays.deepToString(matrix));
        pricePathMatrix_builder(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
