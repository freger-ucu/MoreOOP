package lotr.kick;

import lotr.Character;

public class PowerToHpKick implements KickStrategy {
    @Override
    public void kick(Character attacker, Character defender) {
        int damage = (int) Math.floor(Math.random() * (attacker.getPower() + 1));
        System.out.println(attacker.getClass().getSimpleName() + " hits for " + damage + " damage");
        defender.setHp(defender.getHp() - damage);
    }
}

