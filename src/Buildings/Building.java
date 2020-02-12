package Buildings;

// anything that extends this can
// be displayed on the game map
public abstract class Building implements Comparable<Building> {
    private int level;

    public Building() {
        level = 1;
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

    @Override
    public int compareTo(Building other) {
        return other.getImportance() - this.getImportance();
    }
}