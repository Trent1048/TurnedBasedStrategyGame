package Buildings;

import java.util.ArrayList;

// the main thing where everything happens
public class Headquarters extends Building {

    private ArrayList<Building> buildings;
    private int size;
    private int resources;

    public Headquarters(int x, int y, boolean friendly, int boardSize) {
        super(x, y, friendly);
        this.size = boardSize;
        resources = 0;
        buildings = new ArrayList<>();
        buildings.add(this);
    }

    // executes one turn for every building associated with this HQ
    // including a turn from the HQ
    public void doTurns() {
        // goes through each type of building on the map and does it's turn
        for(Building building : buildings) {
            resources = building.turn(resources);
            System.out.println("Now at " + resources + " resources");
        }
    }

    // how many buildings of a specific type allowed
    public int getAllowedBuildings(Building building) {
        // can have 1 house for each level
        if(building.getClass().equals(House.class)) return getLevel();
        else if(building.getClass().equals(MilitaryBase.class)) {
            // level 1-3 = 1 allowed
            // level 4-6 = 2 allowed
            // level 7+ = 3 allowed
            if(getLevel() <= 9) {
                return 1 + (getLevel() - 1) / 3;
            }
            // 3 is the max
            return 3;
        } else if(building.getClass().equals(ResourceCollector.class)) {
            // get an extra collector every other level, starting with 1
            return getLevel() / 2 + 1;
        }
        // if it isn't recognized or is another Headquarters
        // don't allow it to be placed
        return 0;
    }

    // returns how many times a type of building is on the map
    private int getBuildingOccurrences(Building b) {
        int occurrences = 0;
        for(Building building : buildings) {
            // counts each time it is the same class
            if(building.getClass().equals(b.getClass())) occurrences++;
            // if it is the same exact object, don't allow the user to add it
            if(building == b) throw new IllegalArgumentException("Building " + b + " has already been added");
        }
        return occurrences;
    }

    public void addBuilding(Building building) {
        int x = building.getLocation().getX();
        int y = building.getLocation().getY();

        // don't let the user place a building outside the map
        if(x > size || y > size) throw new IllegalArgumentException("The selected row or column is out of the map");
        for(Building b : buildings) {
            // don't let a building overwrite an existing one
            if (building.hasSameLocation(b)) {
                throw new IllegalArgumentException("There is already a building in the selected location "
                        + building.getLocation());
            }
        }
        // makes sure there aren't too many buildings before adding it
        if(getAllowedBuildings(building) > getBuildingOccurrences(building)) {
            buildings.add(building);
        } else {
            throw new IllegalArgumentException("The max number of building type: " +
                    building.getClass() + " has been reached");
        }
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    @Override
    public int turn(int resources) {
        System.out.println("Headquarters Turn");
        return super.turn(resources);
    }

    @Override
    public String getDisplayName() {
        return "HQ";
    }

    @Override
    protected int getImportance() {
        return 1;
    }
}
