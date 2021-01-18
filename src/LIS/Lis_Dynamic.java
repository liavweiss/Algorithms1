package LIS;

import java.util.Arrays;

public class Lis_Dynamic {

    /**
     * This class represents an algorithm for solving the LIS problem - finding the longest rising sub-series
     * (order is important, sequences are not important).
     * by using with dynamic algorithms.
     */


    //O(n log n)
    //return the length of the sub-series.
    public static int LISLength(int[] a) {
        int[] arr = new int[a.length];
        arr[0]=a[0];
        int lis = 1;
        int index;
        for (int i = 1; i < a.length; i++) {
            index= binarySearchForLength(arr , a[i] , lis );
            arr[index]=a[i];
            if(index>lis) lis++;
        }
        return lis;
    }


    public static int binarySearchForLength(int[] arr , int a , int lis) {
        if(a<arr[0]) return 0;
        if(a>arr[lis]) return lis+1;
        int start = 0;
        while(start<=lis) {
            int middle = (start + lis) /2;
            if(start == lis) return start;
            else {
                if(arr[middle]==a) return middle;
                else if(a<arr[middle]) lis=middle;
                else start = middle+1;
            }
        }
        return -1;
    }



    public static int[] LIS(int[] arr) {
        int n = arr.length;
        int[][] mat = new int[n][n];
        mat[0][0] = arr[0];
        int end = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearchAllSubSeries(mat,end,arr[i]);
            mat[index][index] = arr[i];
            if(index == end) end++;
            copy(mat,index);
        }
        int[] ans = new int[end];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = mat[end-1][i];
        }
        return ans;
    }

    private static int binarySearchAllSubSeries(int[][] mat, int end, int v) {
        if(v > mat[end-1][end-1]) return end;
        if(v < mat[0][0]) return 0;
        int high = end, low = 0;
        while(low <= high) {
            if(low == high)return low;
            int mid = (low + high)/2;
            if(mat[mid][mid] == v) return mid;
            if(mat[mid][mid] > v) high = mid;
            else low = mid+1;
        }
        return -1;
    }


    private static void copy(int[][] mat, int index) {
        for (int i = 0; i < index; i++) {
            mat[index][i] = mat[index-1][i];
        }
    }



    public static void main(String[] args) {
        int [] arr ={8,4,12,2,3,10,14};
        System.out.println(LISLength(arr));
        System.out.println(Arrays.toString(LIS(arr)));
    }
}


/**
 *נכונות האלגוריתם:
 * דבר ראשון ניצור מטריצה שבה כל שורה i תייצג את את תת הסידרה העולה הארוכה ביותר
 * עבורכל איבר שנעבור עליו במערך נבדוק : אם האיבר גדול מכל איברי האלכסון סימן שאנחנו יכולים להוסיף איבר לסידרה הארוכה ביותר, נעתיק את השורה האחרונה לשורה מתחת ונוסיף את האיבר החדש.
 * אם האיבר לא יותר גדול מכל איבר האלכסון אז נחליף את האיבר הראשון שהוא גדןל ממנו בו.
 *
 * דוגמא:
 *{1,5,6,3}
 * 1 ייכנס בשורה ה 1
 * 5 גדול מ 1 ולכן הוא ייכנס בשורה ה 1 – ואז נעתיק את 1 לשורה של 5
 * 6 גדול מכל הקודמים ולכן הוא ייכנס בשורה 2 ונעתיק את האיברים משורה 1 לשורה של 6
 * 3 אינו גדול מכולם, מקומו בין 1 ל 5 ולכן הוא יחליף את 5 בשורה 1 ונעתיק את כל האיברים בשורה הקודמת לשורה 1 .
 *
 * הסבר על סיבוכיות:
 * עבור כל איבר אנו מבצעים חיפוש בינארי כדי לשים אותו במקום המתאים ובנוסף לזה אנחנו גם מעתיקים את השורה לשורה חדשה ולכן הסיבוכיות יוצאת:
 * O(n*(log n + n) = O(n^2).
 **/