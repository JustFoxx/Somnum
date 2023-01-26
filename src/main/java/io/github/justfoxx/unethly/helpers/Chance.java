package io.github.justfoxx.unethly.helpers;

public class Chance {
    private int chanceNow;
    private final int rndInt;
    public Chance(int rndInt) {
        this.rndInt = rndInt;
    }
    public boolean chanceNext(int chance) {
        int chanceNext = chanceNow + chance;
        if(rndInt >= chanceNow && rndInt <= chanceNext) {
            chanceNow = chanceNext;
            return true;
        }
        chanceNow = chanceNext;
        return false;
    }

    public static int doubleToPercentInt(double by,double number) {
        return (int) (number/by);
    }

    public static int doubleToPercentInt(double number) {
        return doubleToPercentInt(.001, number);
    }
}
