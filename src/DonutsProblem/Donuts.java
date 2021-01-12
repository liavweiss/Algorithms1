package DonutsProblem;

public class Donuts {

    /**
     * This class represents the problem of donuts, how long does it take to cook many donuts on one pan with a certain capacity
     */
    private static final int time = 2;

    // Complexity: O(1)
    public static int getTime(int donutsNum,int capacity) {
        if(capacity >= donutsNum) return time;
        if((time*donutsNum)%capacity == 0) return (time*donutsNum)/capacity;
        return (time*donutsNum)/capacity + 1;
    }
}