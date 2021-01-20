package PlaneProblem;

import java.util.Vector;

/**
 * class Node:
 * right,up - the price for get up (up) or right (right)
 * data - the best price from (0,0) to this node
 * numOfPaths - number of shortest paths until this node
 * priceFromTheEnd - same as price, but from the end to (0,0)
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

public class Plane {
    int cheapestPrice, numOfPaths;
    private Node[][] mat;

    public Plane(Node[][] mat) {
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

    public void printDataOnMatrix() {
        System.out.println("\nmatrix of prices in right direction\n");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j].data + " ");
            }
            System.out.println();
        }
    }

    public void printNumberOfPathsOnMatrix() {
        System.out.println("\nthe matrix of numbers of the cheapest paths \n");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j].numOfPaths + " ");
            }
            System.out.println();
        }
    }

    /**
     * returns one of shortest path - recursion
     * Complexity: O(n+m) - but need to build the matrix first in O(n*m)
     */
    public String getOneMinPathRec() {
        return getOneMinPathRec(mat.length - 1, mat[0].length - 1);
    }

    private String getOneMinPathRec(int i, int j) {
        if (i == 0 && j == 0) return "";
        if (i > 0 && j == 0) return getOneMinPathRec(i - 1, 0) + "up ";
        if (i == 0 && j > 0) return getOneMinPathRec(0, j - 1) + "right ";
        if (mat[i][j].data == mat[i - 1][j].data + mat[i - 1][j].up) {
            return getOneMinPathRec(i - 1, j) + "up ";
        } else {
            return getOneMinPathRec(i, j - 1) + "right ";
        }
    }

    /**
     * returns one of shortest path - induction
     * Complexity: O(n+m) - but need to build the matrix first in O(n*m)
     */
    public String getOneMinPath() {
        String ans = "";
        int i = mat.length - 1, j = mat[0].length - 1;
        while (i > 0 && j > 0) {
            int a = mat[i - 1][j].data + mat[i - 1][j].up;
            int b = mat[i][j - 1].data + mat[i][j - 1].right;
            if (a <= b) {
                ans = "up " + ans;
                i--;
            } else {
                ans = "right " + ans;
                j--;
            }
        }
        while (j > 0) {
            ans = "right " + ans;
            j--;
        }
        while (i > 0) {
            ans = "up " + ans;
            i--;
        }
        return ans;
    }

    /**
     * get all shortest paths
     * complexity: O((n+m)choose(n))
     */
    public Vector<String> allPathsRec() {
        Vector<String> ans = new Vector<String>();
        allPathsRec("", mat.length - 1, mat[0].length - 1, ans);
        return ans;
    }

    private void allPathsRec(String temp, int i, int j, Vector<String> ans) {
        if (i == 0 && j == 0) {
            ans.add(temp);
            return;
        } else if (i > 0 && j == 0) {
            allPathsRec("up " + temp, i - 1, 0, ans);
        } else if (i == 0 && j > 0) {
            allPathsRec("right " + temp, 0, j - 1, ans);
        } else {
            int a = mat[i - 1][j].data + mat[i - 1][j].up;
            int b = mat[i][j - 1].data + mat[i][j - 1].right;
            if (a < b) {
                allPathsRec("up " + temp, i - 1, j, ans);
            } else if (b < a) {
                allPathsRec("right " + temp, i, j - 1, ans);
            } else {
                allPathsRec("up " + temp, i - 1, j, ans);
                allPathsRec("right " + temp, i, j - 1, ans);
            }
        }
    }

    /**
     * returns true if the given point is on one of the shortest paths
     * Complexity: O(n*m) but if we call buildMatrixFromTheEnd() first and then call isOnBestPath
     * the answer is in O(1)
     */
    public boolean isOnMinPath(int i, int j) {
        int startSumPath = minPathFromTo(0,0,i,j);
        int secondSumPath = minPathFromTo(i,j,mat.length-1,mat[0].length-1);
        return startSumPath+secondSumPath == cheapestPrice;
    }

    /**
     * the same like build matrix but now we build it from the end to (0,0)
     * Complexity: O(n*m)
     */
//    private void buildMatrixFromTheEnd() {
//        int n = mat.length - 1, m = mat[0].length - 1;
//        for (int i = n - 1; i >= 0; i--) {
//            mat[i][m].priceFromTheEnd = mat[i + 1][m].priceFromTheEnd + mat[i][m].up;
//        }
//        for (int j = m - 1; j >= 0; j--) {
//            mat[n][j].priceFromTheEnd = mat[n][j + 1].priceFromTheEnd + mat[n][j].right;
//        }
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = m - 1; j >= 0; j--) {
//                int y = mat[i + 1][j].priceFromTheEnd + mat[i][j].up;
//                int x = mat[i][j + 1].priceFromTheEnd + mat[i][j].right;
//                mat[i][j].priceFromTheEnd = x <= y ? x : y;
//            }
//        }
//    }

    /**
     *checks if two point are on the same shortest path.
     */
    public boolean onTheSamePath(int x1, int y1, int x2, int y2){
        if(x1<x2&&y1>y2 || x1>x2&&y1<y2){
            return false;
        }
        int firstPath = minPathFromTo(0,0 , x1 , y1);
        int secondPath = minPathFromTo(x1,y1,x2,y2);
        int thirdPath = minPathFromTo(x2,y2,mat.length-1,mat[0].length-1);
        if(firstPath+secondPath+thirdPath == cheapestPrice){
            return true;
        }
        return false;
    }
    /**
     * return the minimal price from point (x1,y1) to (x2,y2)
     * Complexity: O(n*m)
     */
    private int minPathFromTo(int x1, int y1, int x2, int y2) {
        // By the question I assume the input is correct
        Node[][] temp = new Node[x2-x1 + 1][y2-y1 + 1];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = new Node(0, 0);
            }
        }
        for (int i = 1; i < x2-x1 + 1; i++) {
            temp[i][0].data =temp[i - 1][0].data + mat[i - 1 + x1][y1].up ;
        }
        for (int j = 1; j < y2-y1 + 1; j++) {
            temp[0][j].data = temp[0][j - 1].data + mat[x1][j - 1 + y1].right;
        }
        for (int i = 1; i < x2-x1 + 1; i++) {
            for (int j = 1; j < y2-y1 + 1; j++) {
                int x = temp[i - 1][j].data + mat[i - 1 + x1][j + y1].up;
                int y = temp[i][j - 1].data + mat[i + x1][j - 1 + y1].right;
                temp[i][j].data = x <= y ? x : y;

            }
        }
        return temp[x2-x1][y2-y1].data;
    }

//    /**
//     * return true if all the points in p is on the same shortest path
//     * sorting the array first (using counting sort: O(k)) by x and then by y
//     * Complexity: O(n*m*k) k = |p|
//     */
//    public boolean isOnBestPath(Node[] p) {
//        sort(p);
//        int sum = mat[p[0].up][p[0].right].data;
//        for (int i = 1; i < p.length; i++) {
//            if(p[i].up < p[i-1].up) return false;
//            sum += bestPathFromTo(p[i-1].right,p[i-1].up,p[i].right,p[i].up);
//        }
//        sum += bestPathFromTo(p[p.length-1].right,p[p.length-1].up,mat[0].length-1,mat.length-1);
//        return sum == cheapestPrice;
//    }

//    private void sort(Node[] p) {
//        @SuppressWarnings("unchecked")
//        ArrayList<Node>[] freqy = new ArrayList[mat.length];
//        for (int i = 0; i < freqy.length; i++) {
//            freqy[i] = new ArrayList<Node>();
//        }
//        Node[] temp = new Node[p.length];
//        for (int i = 0; i < p.length; i++) {
//            freqy[p[i].up].add(p[i]);
//        }
//        int k = 0;
//        for (int i = 0; i < freqy.length; i++) {
//            for (int j = 0; j < freqy[i].size(); j++) {
//                temp[k++] = freqy[i].get(j);
//            }
//        }
//        @SuppressWarnings("unchecked")
//        ArrayList<Node>[] freqx = new ArrayList[mat[0].length];
//        for (int i = 0; i < freqx.length; i++) {
//            freqx[i] = new ArrayList<Node>();
//        }
//        Node[] temp2 = new Node[p.length];
//        for (int i = 0; i < temp.length; i++) {
//            freqx[temp[i].right].add(temp[i]);
//        }
//        k = 0;
//        for (int i = 0; i < freqx.length; i++) {
//            for (int j = 0; j < freqx[i].size(); j++) {
//                temp2[k++] = freqx[i].get(j);
//            }
//        }
//        for (int i = 0; i < temp2.length; i++) {
//            p[i] = temp2[i];
//        }
//    }

    /**
     * returns the shortest path with minimal turnings
     * Complexity: O((n+m)choose(n)*(m+n))
     */
    public String pathWithOutTurnings() {
        Vector<String> paths = allPathsRec();
        String ans = "";
        int min = Integer.MAX_VALUE;
        for (String path : paths) {
            int turning = 0;
            for (int i = 1; i < path.length(); i++) {
                if (path.charAt(i) != path.charAt(i - 1)) turning++;
            }
            if (turning < min) {
                ans = path;
                min = turning;
            }
        }
        return ans;
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
        buildMatrix(); // return to the normal matrix
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
        Plane bp = new Plane(mat);
        System.out.println(bp.minPathFromTo(1,1,3,2));
        System.out.println(bp.cheapestPrice);
        System.out.println(bp.diffLargestShortestPat());
        System.out.println(bp.numOfPaths);
        System.out.println(bp.allPathsRec());
        System.out.println(bp.onTheSamePath(1,1,3,2));
        bp.printDataOnMatrix();
        System.out.println(bp.isOnMinPath(1,0));
        System.out.println(bp.pathWithOutTurnings());
        bp.printNumberOfPathsOnMatrix();



//        Node[][] mat2 = new Node[6][6];
//        for (int i = 0; i < mat2.length; i++) {
//            for (int j = 0; j < mat2.length; j++) {
//                mat2[i][j] = new Node(j == 4 ? 0 : 1,i == 4 ? 0 : 1);
//            }
//        }
//        Plane b = new Plane(mat2);
//        System.out.println(b.getAllPathsRec().size());
    }
}
