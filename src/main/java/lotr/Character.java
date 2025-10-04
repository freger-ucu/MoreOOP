package lotr;

import lotr.kick.KickStrategy;

public abstract class Character {
    private int power;
    private int hp;
    private KickStrategy kickStrategy;

    protected Character(int hp, int power, KickStrategy strategy) {
        this.hp = Math.max(0, hp);
        this.power = Math.max(0, power);
        this.kickStrategy = strategy;
    }

    protected Character(int hpMin, int hpMax, int powerMin, int powerMax, KickStrategy strategy) {
        this(randomInRange(hpMin, hpMax), randomInRange(powerMin, powerMax), strategy);
    }

    public void kick(Character c) {
        if (kickStrategy == null) {
            return;
        }
        kickStrategy.kick(this, c);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max(0, power);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        // Clamp to zero when below 0
        this.hp = Math.max(0, hp);
    }

    protected void setKickStrategy(KickStrategy kickStrategy) {
        this.kickStrategy = kickStrategy;
    }

    protected static int randomInRange(int minInclusive, int maxInclusive) {
        if (minInclusive > maxInclusive) {
            int t = minInclusive; minInclusive = maxInclusive; maxInclusive = t;
        }
        return minInclusive + (int) Math.floor(Math.random() * (maxInclusive - minInclusive + 1));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{hp=" + hp + ", power=" + power + "}";
    }
}

