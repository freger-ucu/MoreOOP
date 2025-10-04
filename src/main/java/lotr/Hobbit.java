package lotr;

import lotr.kick.CryKick;

public class Hobbit extends Character {
    public Hobbit() {
        super(3, 0, new CryKick());
    }

    private void toCry() {
        System.out.println("Hobbit cries: 'Waah!' ");
    }

    @Override
    public void kick(Character c) {
        toCry();
        super.kick(c);
    }
}

