package lotr.kick;

import lotr.Character;

public class CryKick implements KickStrategy {
    @Override
    public void kick(Character attacker, Character defender) {
        System.out.println(attacker.getClass().getSimpleName() + " cries and does nothing.");
    }
}

