package Test_22_01_2018;

import java.util.Arrays;

/**
 * This class represents a solution for Q4 from the test date: 22.01.2018.
 */

public class Q4 {
    public static String subStringWithOutContinue(String x){
        String temp = "";
        String max = "";
        int i=0;
        int j=0;
        int[] arr = new int [26];
        while(i<x.length() && j<x.length()){
            if(arr[x.charAt(i)-'a'] == 0){
                temp+=x.charAt(i);
                arr[x.charAt(i)-'a']++;
                i++;
            }
            else{
                if(temp.length()>max.length()){
                    max=temp;
                }
                arr = new int[26];
                temp="";
                j++;
                i=j;
            }
        }
        if(temp.length()>max.length()){
            return temp;
        }
        return max;
    }


    public static void main(String[] args) {
        String x = "abcabcabcd";
        System.out.println(subStringWithOutContinue(x));
    }
}
