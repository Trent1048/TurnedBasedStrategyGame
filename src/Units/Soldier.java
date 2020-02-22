package Units;

import Locations.Location;

public class Soldier extends Unit {

    public Soldier(Location location, boolean friendly, int level) {
        super(location, friendly, level);
    }

    public int getRange() {
        return 1;
    }

    public int getMoveDist() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "S_";
    }
}
