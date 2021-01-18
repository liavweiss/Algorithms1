package PizzaProblem;

/**
 * This class presents an algorithm for solving the pizza problem.
 * The pizza problem: Two brothers ordered a family pizza, one brother eating X times faster than the other brother eating.
 * The two must not reach the last triangle at the same time, this algorithm offers the optimal pizza division.
 */
public class pizzaProblem {

    public static int pizza(double x , int n){
            int k = (int)x+1;
            int p = n/(k+1);
            int r = n%(k+1);
            int ans = -1;
            if(r != 1) {
                double t = (x*p+r-1)/((x+1)*p+r);
                if(t < x/(x+1)) ans = 1;
                else ans = 0;
            }
            return ans;
    }

    // practice from gil levi marathon.
    public static int getNumberOfPieces(double k) {
        if(k == (int)k) return (int)k+1;
        return (int)k+2;
    }

    public static void main(String[] args) {
        System.out.println(pizza(1,3));
        System.out.println(getNumberOfPieces(200));
    }
}


/**
 * נגדיר N=מספר החלוקה
 *  הוחכה שהחלוקה האופטימאלית תהיה כפולה של (X+1):
 *
 * דבר ראשון נניח שהחלוקה האופטימאלית היא לX חלקים כלומר N=X, כמהירות האכילה של האח המהיר:
 * אזי האח המהיר יאכל :N-1 חלקי N חלקים והאח האיטי יאכל 1 חלקי N חלקים והם לא יסיימו בו זמנית
 *
 * אם נחלק לX+1 חלקים כלומר N=X+1 נקבל:
 * האח המהיר יאכל N חלקי N+1 חלקים, והאח האיטי יאכל 1 חלקי N חלקים ואז הם יסיימו בו זמנית.
 * כדי לדעת איזה חלוקה עדיפה באמת נוכיח את הדבר הבא:
 * N/(N+1)>(N-1)/N  --> (N-1)*(N+1)<N^2  --> N^2-1<N^2
 * ואכן אנחנו רואים שהאי שיוויון מתקיים.כלומר חלוקה עדיפה יותר היא לX+1.
 *
 * נגדיר את X+1 להיות מספר המשולשים שהם סיימו לאכול בו זמנית ונגדיר את P להיות מספר הפעמים (הכפולה של X+1) שחזרו על עצמם.
 * קל לראות שN חייב להיות שונה מ- 1+ (X+1)*P מכייון שאם זה יהיה כך הם יגיעו למשולש האחרון ביחד מה שאסור להם.
 *נגדיר r להיות השארית כך ש:
 * 2<=r<=X
 * נוכיח שהחלוקה N = (X+1)*P+r היא הנוסחא הנכונה:
 * האח המהיר יאכל: (X*P+1+r)/(X*P+r-1) כאשר האח האיטי יאכל: (X*P+1+r)/(P+1).
 *
 * אנחנו צריכים להוכיח את האי שוויון הבא:
 * (X*P+1+r)/(X*P+r-1)<X/(X+1)
 * מאי שוויון זה נקבל:
 * X^2*P+XP+Xr+r-X-1 < X^2*P+XP+Xr  ---> r<X+1
 * קיבלנו שהשארית באמת קטנה מהחלוקה ובגלל זה הם לכולם לא יגיעו לאחרון בו זמנית(כי האח המהיר יאכל את השאירת לנפני האח האיטי)
 * ולכן N=X+1 היא החלוקה האופטימאלית.
 *
 */
