package Lcs;

import java.util.Arrays;

/**
 * This class represents an algorithm for solving the LCS problem - finding the largest common string
 * (order is important, sequences are not important).
 * algorithms dynamic, complexity: O(m*n) -to build a matrix|X| = n , |Y| = m . O(m+n) - get the string.
 */
public class Lcs_Dynamic {

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

        return result;
    }


    /**
     * return the minimum sub string length that x,y contains on her.
     * its not work yet.
     */
    public static String minLongestContainsTwoStrings(String x, String y){
        String results = "";
        int i=0;
        int j=0;
        int k=0;
        String lcs = lcs(x,y);

        while(k<lcs.length() && i<x.length() && j<y.length()){
            if(x.charAt(i) == y.charAt(j) && y.charAt(j) == lcs.charAt(k)){
                results+=x.charAt(i);
                i++;
                k++;
                j++;
            }
            else{
                if(x.charAt(i) == lcs.charAt(k)){
                    results+=y.charAt(j);
                    j++;
                }
                else {
                    if (y.charAt(j) == lcs.charAt(k)) {
                        results += x.charAt(i);
                        i++;
                    } else {
                        results += x.charAt(i);
                        results += y.charAt(j);
                        i++;
                        j++;
                    }
                }
            }
        }
        while(i<x.length()){
            results += x.charAt(i++);
        }
        while(j<y.length()){
            results += y.charAt(j++);
        }
        return results;
    }

    /**
     * return the minimum longest sub string length that x,y contains on her.
     */
    public static int minLongestContainsTwoStringsLength(String x, String y){
        int ans =0;
        String  lcs = lcs(x, y);
        ans = x.length() + y.length() - lcs.length();
        return ans;
    }

    /**
     *return the one of sub-strings that calculate from start to end, and from end to start.
     */
    public static String palindroms(String s){
        String ans = "";
        String ansReverse = "";
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)!=' '){
                ans+=s.charAt(i);
                ansReverse = s.charAt(i) + ansReverse;
            }
        }
        return lcs(ans,ansReverse);
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
        String x = "abcbdab";
        String y = "bdcaba";
        System.out.println(minLongestContainsTwoStrings(x,y));
        System.out.println(minLongestContainsTwoStringsLength(x,y));

        String palindrom = "a bcd  fklr dc b a";
        System.out.println(palindroms(palindrom));

        String x2 = "abcdefklk";
        String y2 = "brcdeal";
        lcsInsertDelete(x2,y2);
    }
}



/**
 * הוכחת נכונות:
 * דבר ראשון אנחנו בונים מטריצת עזר שהשורה הראשונה והעמודה הראשונה הם שתי המחרוזות
 * ועבור כל תר במטריצה נבדוק אם האם האותיות הנמאצות באותם אינדקס שוות
 * במידה והם שוות:
 * נשים בתא זה את מה שיש באלכסון העליון +1
 * במידה והאיתיות לא שוות:
 * ניקח את המקסימום בין התא העליון לתא השמאלי
 *
 *
 *נסמן ב-F(X,Y) את האורך המקסימאלי של תת מחרוזת משותפת בין X,Y
 * *)אם המחרוזות מכילות אות אחת בלבד:
 *  F(X,Y)=1 כאשר X=Y
 * F(X,Y)=0 כאשר X!=Y
 *
 * *)אם נוסיף למחרוזת אותיות נקבל:
 * F(Xa,Yb) = F(X,Y)+1 כאשר a=b
 * max(F(Xa,Y),F(X,Yb) כאשר a!=b
 *
 * ((((צריך למצוא את המקסימום כאשר a!=b משום שאו a או b יכולים להאריך אץ המחרוזת המשותפת הארוכה ביותר אבל ביחד הם לא יכולים כי הם שונים אחד מהשני)))
 * (((במקרה שa שווה לb אז כמובן שזה מוסיף לתת מחרוזת הארוכה ביותר 1)))
 *
 */
