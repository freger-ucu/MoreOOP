package lotr;

public class GameManager {
    public void fight(Character c1, Character c2) {
        System.out.println("Fight starts: " + c1 + " vs " + c2);
        int rounds = 0;
        int noProgressRounds = 0;
        int lastScore = score(c1, c2);
        while (c1.isAlive() && c2.isAlive() && rounds < 1000) {
            rounds++;
            if ((rounds % 2) == 1) {
                System.out.println("Round " + rounds + ": " + c1.getClass().getSimpleName() + " attacks");
                c1.kick(c2);
            } else {
                System.out.println("Round " + rounds + ": " + c2.getClass().getSimpleName() + " attacks");
                c2.kick(c1);
            }
            System.out.println("Status: " + c1 + " | " + c2);
            int currScore = score(c1, c2);
            if (currScore == lastScore) {
                noProgressRounds++;
                if (noProgressRounds >= 50) {
                    System.out.println("No progress in fight for too long. Declared draw.");
                    break;
                }
            } else {
                noProgressRounds = 0;
                lastScore = currScore;
            }
        }
        if (c1.isAlive() && !c2.isAlive()) {
            System.out.println(c1.getClass().getSimpleName() + " wins");
        } else if (!c1.isAlive() && c2.isAlive()) {
            System.out.println(c2.getClass().getSimpleName() + " wins");
        } else {
            System.out.println("Draw");
        }
    }

    private int score(Character c1, Character c2) {
        return c1.getHp() + c2.getHp() + c1.getPower() + c2.getPower();
    }
}

