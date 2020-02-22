package Units;

import Locations.Location;

public class Artillery extends Unit {

    public Artillery(Location location, boolean friendly, int level) {
        super(location, friendly, level);
    }

    public int getRange() {
        return 3;
    }

    public int getMoveDist() {
        // artillery is stationary and can't move
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "A_";
    }

}