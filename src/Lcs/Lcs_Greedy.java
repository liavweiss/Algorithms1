package Lcs;

/**
 * This class represents an algorithm for solving the LCS problem - finding the largest common string
 * (order is important, sequences are not important).
 * algorithms greedy complexity of O(n*m) and O(m+n) - |X| = n , |Y| = m
 */
public class Lcs_Greedy {
    // Complexity: O(n*m) - |X| = n , |Y| = m
    public static String greedyLCS(String X, String Y){
        String ans = "";
        int start = 0;
        for (int i=0; i<X.length(); i++){
            int index = Y.indexOf(X.charAt(i), start);
            if (index != -1){
                ans = ans + X.charAt(i);
                start = index+1;
            }
        }
        return ans;
    }


    // Complexity: O(n+m) - |X| = n , |Y| = m
    public static String greedyLCSImproved(String X, String Y){
        int freq[] = new int[26];
        for (int i=0; i<X.length(); i++){
            int place = (X.charAt(i)-'a');
            freq[place]++;
        }
        String ans = "";
        int start = 0;
        for (int i=0; i<Y.length(); i++){
            int place = (Y.charAt(i)-'a');
            if (freq[place] > 0){
                int index = X.indexOf(Y.charAt(i), start);
                if (index != -1){
                    ans = ans + Y.charAt(i);
                    start = index+1;
                    freq[place]--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str1 = "acxfcdf";
        String str2 = "acxehfcdfd";
        System.out.println(greedyLCS(str1,str2));
        System.out.println(greedyLCSImproved(str1,str2));
    }
}
