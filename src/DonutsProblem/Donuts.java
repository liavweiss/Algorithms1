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

    public static void main(String[] args) {
        System.out.println(getTime(10,6));
    }
}

/**
 * נניח קודם את הטענה הבאה:
 * הכנת M סופגניות לוקחת M דקות
 * נחלק את הוכחת הנכונות לכמה חלקים:
 * 1) אם M הוא מספר זוגי:
 * אז בעצם אפשר להגיד את הדבר הבא: M=2*N כאשר N הוא מספר זוגות הסופגניות הנמצאות במחבת,
 * ולכן במקרה הזה מכינים את הסופגניות בזוגות ולכן יקח 2 דקות הכנה עבור כל זוג של סופגניות כלומר M דקות
 * 2) אם M הוא מספר אי-זוגי:
 *  אז בעצם אפשר להגיד את הדבר הבא: M=2*N +1 כאשר N הוא מספר הסופגניות שנמצאות במחבת
 *  במקרה הזה אנו רואים שיש N-1 זוגות שבשבילם יהיה 2 דקות הכנה והזוג האחרון יטוגן עם הסופגניה הבודדת מה שיקח 3 דקות
 *  לכן נקבל : M=2*(N-1) +3
 *   שזה בעצם: M=2*N +1
 *
 **/