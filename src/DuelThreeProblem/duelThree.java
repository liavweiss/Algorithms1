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
            if (steps > 100) {
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
            else duelOfTwoPlayers(p3, p2, steps);
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
        duel3d(new Player("A", 0.6), new Player("B", 0.8), new Player("C", 0.5));
    }
}

