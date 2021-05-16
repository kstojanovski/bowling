package org.acme.process;

public class BowlingHitsGenerator {

    private static final int PRIMARY_PITS = 10;
    private static int SECONDARY_PITS;

    public int firstTry() {
        int firstTry = (int)Math.round(Math.random() * PRIMARY_PITS);
        this.SECONDARY_PITS = PRIMARY_PITS - firstTry;
        return firstTry;
    }

    public int getSecondTry() {
        return (int)Math.round(Math.random() * SECONDARY_PITS);
    }
}
