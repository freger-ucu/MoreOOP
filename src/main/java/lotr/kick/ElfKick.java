package lotr.kick;

import lotr.Character;

public class ElfKick implements KickStrategy {
    @Override
    public void kick(Character attacker, Character defender) {
        if (attacker.getPower() > defender.getPower()) {
            System.out.println("Elf instantly kills the weaker " + defender.getClass().getSimpleName());
            defender.setHp(0);
        } else {
            System.out.println("Elf weakens the foe's power by 1");
            defender.setPower(defender.getPower() - 1);
        }
    }
}

