package DuelThreeProblem;

/**
 * This class represents the duel problem between three players with certain hit percentages
 */
 class Player {
    String name;
    boolean isAlive = true;
    double isChance;
    public Player(String name, double chance) {
        this.name = name;
        this.isChance = chance;
    }

}
public class duelThree {

     //Duel between two soldiers each one have his strategy.
    public static void duelOfTwoPlayers(Player p1, Player p2, int steps) {
        int numOfCase = 1;
        while (p1.isAlive && p2.isAlive) {
            if (steps > 200) {
                System.out.println("The time is over - and we have no winner");
                return;
            }
            steps++;
            switch (numOfCase) {
                case 1: //player 1 play
                    if (Math.random() < p1.isChance) {
                        p2.isAlive = false;
                        System.out.println(p2.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2: //player 2 play
                    if (Math.random() < p2.isChance) {
                        p1.isAlive = false;
                        System.out.println(p1.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
            }
            numOfCase = 3 - numOfCase;
        }
        System.out.println("The winner is: " + (p1.isAlive ? p1.name : p2.name));
        System.out.println("Steps = " + steps);
    }

   //Duel between three soldiers each one have his strategy
    public static void duel3d(Player p1, Player p2, Player p3) {
        int numOfCase = (int) (Math.random() * 3 + 1);
        int steps = 0;
        Player target;
        while (p1.isAlive && p2.isAlive && p3.isAlive) {
            if (steps > 200) {
                System.out.println("The time is over - and we have no winner");
                return;
            }
            steps++;
            numOfCase++;
            if (numOfCase > 3) numOfCase = 1;
            switch (numOfCase) {
                case 1://player 1 play
                    target = strategy(p1, p2, p3);
                    if (target != null && Math.random() < p1.isChance) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2://player 2 play
                    target = strategy(p2, p1, p3);
                    if (target != null && Math.random() < p2.isChance) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
                case 3://player 3 play
                    target = strategy(p3, p1, p2);
                    if (target != null && Math.random() < p3.isChance) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p3.name);
                    } else {
                        System.out.println(p3.name + " missed");
                    }
                    break;
            }
        }
        if (!p1.isAlive) {
            if (numOfCase == 2) duelOfTwoPlayers(p3, p2, steps);
            else duelOfTwoPlayers(p2, p3, steps);
        } else if (!p2.isAlive) {
            if (numOfCase == 3) duelOfTwoPlayers(p1, p3, steps);
            else duelOfTwoPlayers(p3, p1, steps);
        } else if (!p3.isAlive) {
            if (numOfCase == 2) duelOfTwoPlayers(p1, p2, steps);
            else duelOfTwoPlayers(p2, p1, steps);
        }
    }

    private static Player strategy(Player p1, Player p2, Player p3) {

        // If P1's chance of hitting is lower than everyone else his goal is to miss so the other two kill each other
        if (p1.isChance < p2.isChance && p1.isChance < p3.isChance) {
            return null;
        }
        return p2.isChance > p3.isChance ? p2 : p3;
    }

    public static void main(String[] args) {
        duel3d(new Player("A", 1), new Player("B", 0.5), new Player("C", 0.8));
    }
}

/**
 * הסבר נכונות האלגוריתם :
 * בשלב הראשון נבחר את סדר הקרב כלומר מי יורה את הץ ראשון ומי אחריו
 * נגדיר את שמם של הנלחמים בקרב כ - A,B,C
 * האסטרטגיה של האלגוריתם היא כזאת:
 * אם אתה ב100 אחוז פגיעה (או בעצם עם האחוז פגיעה הכי גבוהה) אתה תנסה לפגוע קודם כל במי שבאחוז פגיעה הכי קרוב אליך
 * אם אתה מבחינת הסתברות פגיעה במקום השני אז אתה תנסה לפגוע במי שהאחוז פגיעה שלו הכי גבוהה
 * אם אתה מבחינת אחוז פגיעה במקום האחרון(אחוז פגיעה הכי נמוך) אתה תרצה להחטיא את המטרה בכדי שהשתיים עם האחוז הגבוהה ינסו להרוג את עצמם
 * נעשה סימולציה:
 * A-100%
 * B-80%
 * C-30%
 *
 * אם A מתחיל הוא ירצה תחילה לפגוע בB, אם B מתחיל הוא ירצה לפגוע תחילה בA, אם C מתחיל הוא ירצה לפספס בכדי שA וB יהרגו אחד את השני
 */

