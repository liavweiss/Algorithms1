package CompilerProblem;

import java.util.Arrays;

/**
 * This class represents the compiler problem, its goal is to achieve the smallest time average.
 * complexities of O(n) + O(n log n) = O(n log n).
 */
class Program {
    String name;
    int len, probability;

    public Program(String name, int len, int prob) {
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

    public static void getOptimalOrder(Program[] programs) {
        mergeSort(programs, 0, programs.length);
        int totalTime = 0;int totalLen = 0;
        for (int i = 0; i < programs.length; i++) {
            System.out.println(programs[i]);
            totalTime += (totalLen+programs[i].len)*programs[i].probability;
            totalLen += programs[i].len;
        }
        System.out.println("Total time: " + totalTime);
    }

    private static void mergeSort(Program[] p, int low, int high) {
        int n = high - low;
        if(n <= 1) return;
        int mid = (low + high)/2;
        mergeSort(p,low,mid);
        mergeSort(p,mid,high);
        int i = low, j = mid, k = 0;
        Program[] temp = new Program[n];
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

    public static void main(String[] args) {
        Program p1 = new Program("a1",5,5);
        Program p2 = new Program("a2",6,6);
        Program p3 = new Program("a3",10,50);
        Program p4 = new Program("a4",5,5);
        Program p5 = new Program("a5",6,10);
        Program [] prog = {p1,p2,p3,p4,p5};

        getOptimalOrder(prog);
    }
}
