package Buildings;

import java.util.Objects;

// anything that extends this can
// be displayed on the game map
public abstract class Building implements Comparable<Building> {
    private int level;
    private Location location;
    private boolean friendly;

    public Building(Location location, boolean friendly) {
        level = 1;
        this.location = location;
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
    }
}