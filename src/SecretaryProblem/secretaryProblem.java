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




/**
 * נכונות האלגוריתם:
 * נמיין את זמני התורים מהקטן לגדול וכך נקבל את הזמן שהות המינימאלי
 * הוכחה:
 * AVG = (T1+T2+T3+...+Tn)/n.
 * נסצכל קודם כל על הזמן ההמתנה הכולל כאשר נפרק אותו לגורמים:
 * sumAVG=T1+T2+T3+...+Ti+Ti+1+...+Tn == t1+...+(t1+...+ti-1)+(t1+...+ti-1+ti)+(t1+...+ti-1+ti+ti+1)+...+(t1+...+tn).
 * נניח בשלילה שזמן ההמתנה ti גדול יותר מהאיבר הבא אחריו ti+1 (כל זה במערך של זמני תורים לא ממויינים) אם נחליף בינהם נקבל את הדבר הבא:
 * sumAVGImproved=T1+T2+T3+...+Ti+1+Ti+...+Tn == t1+...+(t1+...+ti-1)+(t1+...+ti-1+ti+1)+(t1+...+ti-1+ti+1+ti)+...+(t1+...+tn).
 *
 * אם ניקח את sumAVG ונחסיר ממנו את sumAVGImproved
 * נקבל:
 * ti-ti+1>0
 * כלומר הראנו שאם נמיין את הזמנים הזמן הממוצע הכללי הסופי יהיה  קטן יותר.
 */
