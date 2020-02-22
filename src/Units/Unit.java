package Units;

import Locations.*;

public abstract class Unit implements Locatable {

    private Location location;
    private boolean friendly;
    private int level;

    public Unit(Location location, boolean friendly, int level) {
        this.location = location;
        this.friendly = friendly;
        this.level = level;
    }

    // the range the unit can attack from
    public abstract int getRange();

    // the distance the unit is allowed to move on a turn
    public abstract int getMoveDist();

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isFriendly() {
        return friendly;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public String toString() {
        return "Unit at " + this.getLocation() + " level " + this.getLevel();
    }
}
