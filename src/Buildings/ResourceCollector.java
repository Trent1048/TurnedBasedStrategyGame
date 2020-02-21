package Buildings;

import Locations.Location;

public class ResourceCollector extends Building {

    public ResourceCollector(Location location, boolean friendly) {
        super(location, friendly);
    }

    @Override
    public int turn(int resources) {
        int newResources = resources + getLevel();
        if(isFriendly()) {
            System.out.println("Resource Collector Turn\nGenerated " + getLevel() + " resource" + pluralize(getLevel()));
        }
        // produces an amount of resources
        // equal to it's level
        return newResources;
    }

    @Override
    public int getUpgradeCost() {
        return 3 * (int)Math.pow(getLevel() + 1, 2);
    }

    @Override
    public String toString() {
        return "Resource Collector at " + this.getLocation() + " level " + this.getLevel();
    }

    @Override
    public String getDisplayName() {
        return "RC";
    }

    @Override
    protected int getImportance() {
        return 4;
    }
}