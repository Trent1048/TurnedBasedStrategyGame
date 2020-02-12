package Buildings;

import java.util.Objects;

// anything that extends this can
// be displayed on the game map
public abstract class Building implements Comparable<Building> {
    private int level;
    private Location location;
    private boolean friendly;

    public Building(int x, int y, boolean friendly) {
        level = 1;
        location = new Location(x, y);
        this.friendly = friendly;
    }

    public void upgrade() {
        level++;
    }

    // does it's turn then returns what
    // the resource count should be afterwards
    public int turn(int resources) {
        return resources;
    }

    public abstract String getDisplayName(); // should be 2 chars long

    public int getLevel() {
        return level;
    }

    // for compareTo
    protected abstract int getImportance();

    public boolean isFriendly() {
        return friendly;
    }

    @Override
    public int compareTo(Building other) {
        return other.getImportance() - this.getImportance();
    }

    public Location getLocation() {
        return location;
    }

    // tests if two buildings are in the same location
    public boolean hasSameLocation(Building other) {
        return this.location.x == other.getLocation().x &&
                this.location.y == other.getLocation().y;
    }

    // for storing the location of the building
    public static class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
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
    }
}