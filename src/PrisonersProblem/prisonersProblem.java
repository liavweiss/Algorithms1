package PrisonersProblem;

/**
 * This class represents the solution to the prisoners' problem, if the light is on and if not.
 */
public class prisonersProblem {

    public static int prisonersLampOn(int n) {
        boolean [] enter=new boolean[n];
        for(int i=0; i<n;i++){
            enter[i]=false;
        }

        boolean lamp = true;
        int count=0,steps=0;

        while(count<n) {
            steps++;
            int p=(int)(Math.random()*n);
            if(p==0)//the counter enter
            {
                if(enter[p]==false) {
                    enter[p]=true;
                    count++;
                    System.out.println("For the first time the counter enter to the room");
                }
                if(!lamp) {
                    lamp=true;
                    count++;
                    System.out.println("The counter turn on the lamp and the count is: "+ count);
                }
            }else {
                if(enter[p]==false && lamp==true) {
                    lamp=false;
                    enter[p]=true;
                    System.out.println("Power Off by the prisoner number: "+p);
                }
            }
        }
        boolean ansAllEntered = true;

        for(int i=0; i<n; i++){ // for checks if all the prisoners entered into the room
            if(!enter[i]){
                ansAllEntered=false;
            }
        }
        System.out.println("All the prisoners entered: "+ansAllEntered);

        System.out.println("The number of steps is: ");
        return steps;
    }
    public static int prisonersLampUnknown(int n){
        boolean lamp = ((int)(Math.random()*2)==0) ? false : true;
        System.out.println("The status of the lamp = "+lamp);
        while (!lamp) {
            int p = (int)(Math.random()*n);
            if (p == 0)
                lamp = true;
        }
        System.out.println("And now the status of the lamp = "+lamp);
        int steps = prisonersLampOn(n);
        return steps;
    }
    public static void main(String[] args) {
        System.out.println(prisonersLampOn(10));
        System.out.println("************************************************************************\n");
        System.out.println(prisonersLampUnknown(10));
    }
}

/**
 * הוכחת נכונות:
 * נתחיל במצב בו הנורה דולקת:
 * האלגוריתם שלנו עובד כך שברגע שהנורה דולקת ויש אסיר שעדיין לא נכנס פנימה אז הוא יכנס ויכבה אותה
 * אם הנורה דולקת רק לסופר מותר להיכנס לחדר לכבוד אותה ולעלות את הספירה באחד
 * מה שיוביל אותנו בסופו של דבר שאחרי שהסופר ידליק את האור כמספר האסירים הוא ידע שכולם היו
 *
 * מצב בו הנורה לא ידועה:
 * נחכה שהסופר יכנס לחדר ברגע שהוא נכנס הוא ישנה את מצב הנורה (אם פועלת ישאיר אותה פעולת אם לא הוא ידליק אותה)
 * ואז האלגוריתם עובד בדיוק כמו האלגוריתם כאשר מצב הנורה ידוע והיא דולקת
 */
