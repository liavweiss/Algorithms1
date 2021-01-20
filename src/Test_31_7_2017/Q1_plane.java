package Test_31_7_2017;

/**
 * This class represents a solution for Q1 from the test date: 31.07.2017.
 */

class Node {
    int right, up, data,numOfPaths, priceFromTheEnd;

    public Node(int right, int up) {
        this.right = right;
        this.up = up;
        data = 0;
        priceFromTheEnd = 0;
        numOfPaths = 1;
    }
}

public class Q1_plane {
    int cheapestPrice, numOfPaths;
    private Node[][] mat;

    public Q1_plane(Node[][] mat) {
        this.mat = mat;
        cheapestPrice = 0;
        numOfPaths = 0;
        buildMatrix();
    }

    /**
     * build the matrix contains the price to get each point (from (0,0))
     * and the number of shortest path until each point
     * Complexity: O(n*m)
     */
    private void buildMatrix() {
        int row = mat.length, col = mat[0].length;
        for (int i = 1; i < row; i++) {
            mat[i][0].data = mat[i - 1][0].up + mat[i - 1][0].data;
            mat[i][0].numOfPaths = 1;
        }
        for (int j = 1; j < col; j++) {
            mat[0][j].data = mat[0][j - 1].data + mat[0][j - 1].right;
            mat[0][j].numOfPaths = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                int y = mat[i - 1][j].data + mat[i - 1][j].up;
                int x = mat[i][j - 1].data + mat[i][j - 1].right;
                mat[i][j].data = x <= y ? x : y;
                if (y < x) mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths;
                else if (y > x) mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths;
                else mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths + mat[i - 1][j].numOfPaths;
            }
        }
        numOfPaths = mat[row - 1][col - 1].numOfPaths;
        cheapestPrice = mat[row - 1][col - 1].data;
    }

    /**
     * build the matrix contains the price to get each point (from (0,0))
     * and the number of shortest path until each point, for expensive path.
     * Complexity: O(n*m)
     */
    private void buildMatrixForExpensivePath() {
        int row = mat.length, col = mat[0].length;
        for (int i = 1; i < row; i++) {
            mat[i][0].data = mat[i - 1][0].up + mat[i - 1][0].data;
            mat[i][0].numOfPaths = 1;
        }
        for (int j = 1; j < col; j++) {
            mat[0][j].data = mat[0][j - 1].data + mat[0][j - 1].right;
            mat[0][j].numOfPaths = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                int y = mat[i - 1][j].data + mat[i - 1][j].up;
                int x = mat[i][j - 1].data + mat[i][j - 1].right;
                mat[i][j].data = x <= y ? y : x;
                if (x < y) mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths;
                else if (x > y) mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths;
                else mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths + mat[i - 1][j].numOfPaths;
            }
        }
        numOfPaths = mat[row - 1][col - 1].numOfPaths;
        cheapestPrice = mat[row - 1][col - 1].data;
    }

    /**
     * return the difference from the expensive path to the cheapest path.
     */
    public int diffLargestShortestPat() {
        buildMatrix();
        int cheapest = cheapestPrice;
        buildMatrixForExpensivePath();
        int expensive = mat[mat.length - 1][mat[0].length - 1].data;
        return expensive - cheapest;
    }


    public static void main(String[] args) {
        Node[][] mat = new Node[4][4];
        mat[0][0] = new Node(1, 2);
        mat[0][1] = new Node(1, 1);
        mat[0][2] = new Node(1, 3);
        mat[0][3] = new Node(0, 1);
        mat[1][0] = new Node(2, 3);
        mat[1][1] = new Node(5, 1);
        mat[1][2] = new Node(6, 3);
        mat[1][3] = new Node(0, 1);
        mat[2][0] = new Node(2, 4);
        mat[2][1] = new Node(7, 1);
        mat[2][2] = new Node(2, 3);
        mat[2][3] = new Node(0, 1);
        mat[3][0] = new Node(2, 0);
        mat[3][1] = new Node(1, 0);
        mat[3][2] = new Node(1, 0);
        mat[3][3] = new Node(0, 0);
        Q1_plane bp = new Q1_plane(mat);
        System.out.println(bp.cheapestPrice);
        System.out.println(bp.diffLargestShortestPat());
    }
}
