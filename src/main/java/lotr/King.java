package lotr;

import lotr.kick.PowerToHpKick;

public class King extends Character {
    public King() {
        super(5, 15, 5, 15, new PowerToHpKick());
    }
}

