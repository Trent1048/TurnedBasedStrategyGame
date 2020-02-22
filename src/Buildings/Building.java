package Buildings;

import Locations.*;

// anything that extends this can
// be displayed on the game map
public abstract class Building implements Comparable<Building>, Locatable {
    private int level;
    private Location location;
    private boolean friendly;
    private int hitPoints;
    private boolean destroyed;

    public Building(Location location, boolean friendly) {
        hitPoints = 100;
        level = 1;
        this.location = location;
        this.friendly = friendly;
    }

    protected void upgrade() {
        level++;
    }

    public abstract int getUpgradeCost();

    // does it's turn then returns what
    // the resource count should be afterwards
    public int turn(int resources) {
        return resources;
    }

    public String toString() {
        return "Building at " + this.getLocation() + " level " + this.getLevel();
    }

    public void damage(int damageAmount) {
        hitPoints -= damageAmount;
        if(hitPoints <= 0) destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public boolean isFriendly() {
        return friendly;
    }

    // for compareTo
    protected abstract int getImportance();

    @Override
    public int compareTo(Building other) {
        return other.getImportance() - this.getImportance();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    // if the word should be plural, return "s", if the inputted num is 1, return ""
    public static String pluralize(int num) {
        if(num == 1) return "";
        return "s";
    }
}