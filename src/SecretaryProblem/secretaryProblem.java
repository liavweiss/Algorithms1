package SecretaryProblem;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This department represents the problem of the secretary, the problem is: a certain office gives service
 * to n clients, the purpose of the secretary is to reduce as much as possible the average time that the clients
 * are in the office.
 * We see that the average time is smaller when the other adjacent treatment times are in ascending order.
 */
public class secretaryProblem {

    public static Double getAvarageTime(int [] times){
        Arrays.sort(times);
        double avg = 0;//[2,3,4,6]
        for(int i : times){
            avg=avg+avg+i;
        }
        return (avg/times.length);
    }

    public static void main(String[] args) {
        int [] time = {3,1,2,4};
        System.out.println(getAvarageTime(time));
        System.out.println(Arrays.toString(time));
    }
}
