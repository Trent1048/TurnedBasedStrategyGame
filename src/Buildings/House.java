package Buildings;

import Locations.Location;

public class House extends Building {

    public House(Location location, boolean friendly) {
        super(location, friendly);
    }

    @Override
    public int turn(int resources) {
        if(isFriendly()) {
            System.out.println("House Turn");
        }
        return super.turn(resources);
    }

    @Override
    public int getUpgradeCost() {
        return (int)Math.pow(getLevel() + 1, 2);
    }

    @Override
    public String toString() {
        return "House at " + this.getLocation() + " level " + this.getLevel();
    }

    @Override
    public String getDisplayName() {
        return "H_";
    }

    @Override
    protected int getImportance() {
        return 3;
    }
}
