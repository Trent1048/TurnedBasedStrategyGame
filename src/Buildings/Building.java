package Buildings;

// anything that extends this can
// be displayed on the game map
public abstract class Building {
    protected int level;

    public Building() {
        level = 1;
    }

    public void upgrade() {
        level++;
    }

    public abstract String getDisplayName(); // should be 2 chars long

    public int getLevel() {
        return level;
    }
}