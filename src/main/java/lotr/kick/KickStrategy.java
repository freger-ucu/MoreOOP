package lotr.kick;

import lotr.Character;

public interface KickStrategy {
    void kick(Character attacker, Character defender);
}

