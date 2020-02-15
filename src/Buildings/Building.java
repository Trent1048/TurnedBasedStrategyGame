package Buildings;

import java.util.Objects;

// anything that extends this can
// be displayed on the game map
public abstract class Building implements Comparable<Building> {
    private int level;
    private Location location;
    private boolean friendly;
    private int hitPoints;
    private boolean destroyed;

    // TODO make turns not end until the user actually does something
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

    public abstract String getDisplayName(); // should be 2 chars long

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

    public int getLevel() {
        return level;
    }

    public boolean isFriendly() {
        return friendly;
    }

    // for compareTo
    protected abstract int getImportance();

    @Override
    public int compareTo(Building other) {
        return other.getImportance() - this.getImportance();
    }

    public Location getLocation() {
        return location;
    }

    // for storing the location of the building
    public static class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        // how far away the other Location is
        public int getDistance(Location other) {
            return (int)Math.sqrt(Math.pow(x-other.getX(), 2) + Math.pow(y-other.getY(), 2));
        }
    }
}