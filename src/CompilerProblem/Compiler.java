package CompilerProblem;

import java.util.Arrays;

/**
 * This class represents the compiler problem, its goal is to achieve the smallest time average.
 * complexities of O(n) + O(n log n) = O(n log n).
 */
class program {
    String name;
    int len, probability;

    public program(String name, int len, int prob) {
        this.name = name;
        this.len = len;
        this.probability = prob;

    }
    @Override
    public String toString() {
        return "[" + name + " ," + len + " ," + probability + "]";
    }
}

public  class Compiler {

    public static void getOptimalOrder(program[] programs) {
        mergeSort(programs, 0, programs.length);
        int totalTime = 0;int totalLen = 0;
        for (int i = 0; i < programs.length; i++) {
            System.out.println(programs[i]);
            totalTime += (totalLen+programs[i].len)*programs[i].probability;
            totalLen += programs[i].len;
        }
        System.out.println("Total time: " + totalTime);
    }

    private static void mergeSort(program[] p, int low, int high) {
        int n = high - low;
        if(n <= 1) return;
        int mid = (low + high)/2;
        mergeSort(p,low,mid);
        mergeSort(p,mid,high);
        int i = low, j = mid, k = 0;
        program[] temp = new program[n];
        while(i<mid && j<high) {
            double t1 = (double)p[i].len/p[i].probability;
            double t2 = (double)p[j].len/p[j].probability;
            if(t1 < t2) temp[k++] = p[i++];
            else temp[k++] = p[j++];
        }
        while(i<mid) temp[k++] = p[i++];
        while(j<high) temp[k++] = p[j++];
        for (int l = 0; l < n; l++) {
            p[low + l] = temp[l];
        }
    }

    public static double compilerSameLen(program[] programs){
        double[] finalProgram = new double[programs.length];
        for(int i=0; i<programs.length;i++){
            finalProgram[i]=programs[i].probability/programs[i].len;
        }
        Arrays.sort(finalProgram);
        double[] finalProgramReverse = new double[programs.length];
        for(int i=programs.length-1; i>=0;i--){
            finalProgramReverse[programs.length-i-1] = finalProgram[i];
        }
        double avg = 0;
        for(int i=0; i<programs.length;i++){
            avg+=avg+finalProgramReverse[i];
        }
        return avg;
    }

    public static void main(String[] args) {
        program p1 = new program("a1",5,5);
        program p2 = new program("a2",6,6);
        program p3 = new program("a3",10,50);
        program p4 = new program("a4",5,5);
        program p5 = new program("a5",6,10);
        program[] prog = {p1,p2,p3,p4,p5};

        getOptimalOrder(prog);
        System.out.println(compilerSameLen(prog));
    }
}

/**
 *מקרה א': כל ההסתברויות שוות - הוכחה דומה בדיור לבעיית המזכירה (ההוכחה נמצאת בבעית המזכירה)
 * מקרה ב' : כל האורכים שווים - נמיין בסד יורד כך שההסתברות הגבוהה תקבל הכי פחות משמעות וההסתברות הגדולה תקבל את הכי הרבה משמעות.
 *
 * מקרה כללי: שבו גם ההסתבריות שונות וגם האורכים שונים
 * נראה כהוכחה פרטנית אם ניקח שלוש תוכנות P1,P2,P3 כאשר הגודל שלהם הוא לפי סידרם, נראה את הדבר הבא:
 *sum = P1+2P2+3P3    , sumImproved = P1+2P3+3P2   ----> sum-sumImproved = -P2+P3
 * הגדרנו את P3 להיות גדול יותר מP2 ולכן:
 * -P2+P3>0
 *
 * כלומר ננסה לקחת את כל הזמנים ולהחליף בין שתי תוכנות צמודות ונקבל את הדבר הבא:
 * sum=l1p1+....+(l1+l2+l3+...+li)*pi +(l1+l2+l3+...+li+li+1)*pi +1+...+ (l1+l2+...+ln)*pn
 * sumImproved=l1p1+....+(l1+l2+l3+...+li+li+1)*pi+1 +(l1+l2+l3+...+li)*pi +...+ (l1+l2+...+ln)*pn
 * sum-sumImproved = pi+1*li - pi*li+1
 * הראנו במקרה הפרטי שהתוצאה גדולה מאפס כאשר המערך ממויין ומחליפים בין 2 תוכנות, לכן גם כאן נקבל שהתוצאה גדולה מאפס ולכן:
 * pi+1*li - pi*li+1 > 0  ---> (pi+1)/(li+1) > pi/li
 * מכאן אפשר להסיק שכדי לקבל זמן מינימאלי נמיין לא את המערך הרגיל אלא נמיין את המערך של היחסים בין ההסתברות של התוכנה לגודל שלה.
 * (כמובן שבסדר יורד כמו שהראנו בתחיל ההוכחה)
 *
 **/