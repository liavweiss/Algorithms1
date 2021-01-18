package Test_18_02_2018;


import java.util.Arrays;

/**
 * This class represents a solution for Q1 from the test date: 18.02.2018.
 */
class Program {
    public String name;
    public double len;
    public double probability;

    public Program(String name, double len, double prob){
        this.name=name;
        this.len=len;
        this.probability=prob;
    }

}
public class Q1_compiler {
    public static double[] compilerSameProbability(Program[] programs){
        double[] finalProgram = new double[programs.length];
        for(int i=0; i<programs.length;i++){
            finalProgram[i]=programs[i].len;
        }
        Arrays.sort(finalProgram);
        return finalProgram;
    }

    public static double []compilerSameLen(Program[] programs){
        double[] finalProgram = new double[programs.length];
        for(int i=0; i<programs.length;i++){
          finalProgram[i]=programs[i].probability;
        }
        Arrays.sort(finalProgram);
        double[] finalProgramReverse = new double[programs.length];
        for(int i=programs.length-1; i>=0;i--){
            finalProgramReverse[programs.length-i-1] = finalProgram[i];
        }

        return finalProgramReverse;
    }


    public static void compilerOptimalOrder(Program[] programs) {
        mergeSort(programs, 0, programs.length);
        int totalTime = 0;int totalLen = 0;
        for (int i = 0; i < programs.length; i++) {
           // System.out.println(programs[i]);
            totalTime += (totalLen+programs[i].len)*programs[i].probability;
            totalLen += programs[i].len;
        }
        System.out.println("Total time: " + totalTime);
    }

    private static void mergeSort(Program[] p, int low, int high) {
        int n = high - low;
        if (n <= 1) return;
        int mid = (low + high) / 2;
        mergeSort(p, low, mid);
        mergeSort(p, mid, high);
        int i = low, j = mid, k = 0;
        Program[] temp = new Program[n];
        while (i < mid && j < high) {
            double t1 = (double) p[i].len / p[i].probability;
            double t2 = (double) p[j].len / p[j].probability;
            if (t1 < t2) temp[k++] = p[i++];
            else temp[k++] = p[j++];
        }
        while (i < mid) temp[k++] = p[i++];
        while (j < high) temp[k++] = p[j++];
        for (int l = 0; l < n; l++) {
            p[low + l] = temp[l];
        }
    }

    public static void main(String[] args) {
        Program p1 = new Program("a1",5,5);
        Program p2 = new Program("a2",5,6);
        Program p3 = new Program("a3",5,50);
        Program p4 = new Program("a4",5,5);
        Program p5 = new Program("a5",5,10);
        Program[] prog = {p1,p2,p3,p4,p5};
        double[] arr = compilerSameLen(prog);
        double avg = 0;
        int totalLen=0;
        for(int i=0;i<arr.length;i++){
            avg+=(totalLen+5)*arr[i];
            totalLen+=5;
        }
        compilerOptimalOrder(prog);

        System.out.println(avg);
    }

}
