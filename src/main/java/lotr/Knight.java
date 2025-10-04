package lotr;

import lotr.kick.PowerToHpKick;

public class Knight extends Character {
    public Knight() {
        super(2, 12, 2, 12, new PowerToHpKick());
    }
}

