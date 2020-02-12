package Buildings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// the main thing where everything happens
public class Headquarters extends Building {

    private ArrayList<Building> buildings;
    private int size;
    private int resources;

    public Headquarters(int x, int y, boolean friendly, int boardSize) {
        super(x, y, friendly);
        this.size = boardSize;
        resources = 5; // start with 5 resources
        buildings = new ArrayList<>();
        buildings.add(this);
    }

    // executes one turn for every building associated with this HQ
    // including a turn from the HQ
    public void doTurns() {
        Collections.sort(buildings);
        // goes through each type of building on the map and does it's turn
        for(Building building : buildings) {
            resources = building.turn(resources);
            if(isFriendly()) {
                System.out.println("Now at " + resources + " resources");
            }
            // since the HQ always runs last, it won't try to
            // group newly purchased buildings into a turn
            if (building == this) return;
        }
    }

    @Override
    public int turn(int resources) {
        int newResources = resources;
        if(isFriendly()) {
            Scanner console = new Scanner(System.in);
            System.out.println("Headquarters Turn\n" +
                    "U = Upgrade a building\n" +
                    "P = Purchase a new building\n" +
                    "N = Nothing");
            String input = console.nextLine().toUpperCase();
            if(input.startsWith("U")) {

            } else if(input.startsWith("P")) {
                System.out.println("You own " +
                        getBuildingOccurrences(House.class) + " / " +
                        getNumOfAllowedBuildings(House.class) + " Houses, " +
                        getBuildingOccurrences(ResourceCollector.class) + " / " +
                        getNumOfAllowedBuildings(ResourceCollector.class) + " Resource Collectors, and " +
                        getBuildingOccurrences(MilitaryBase.class) + " / " +
                        getNumOfAllowedBuildings(MilitaryBase.class) + " Military Bases.\n" +
                        "You have " + resources + " resources\n" +
                        "What type of building would you like to buy?\n" +
                        "H = House (1 resource)\n" +
                        "R = Resource Collector (2 resources)\n" +
                        "M = Military Base (3 resources)\n" +
                        "N = Nothing");
                String buildingType = console.nextLine().toUpperCase();
                // sets up the location
                if(!buildingType.startsWith("N")) {

                    System.out.println("What is the desired x position of this building?");
                    int x = console.nextInt();
                    System.out.println("What is the desired y position of this building?");
                    int y = console.nextInt();

                    try {
                        if (buildingType.startsWith("H") && buildingAdditionIsAllowed(House.class)) {
                            if(resources >= 1) {
                                addBuilding(new House(x, y, isFriendly()));
                                newResources -= 1;
                            }
                        } else if (buildingType.startsWith("R") && buildingAdditionIsAllowed(ResourceCollector.class)) {
                            if(resources >= 2) {
                                addBuilding(new ResourceCollector(x, y, isFriendly()));
                                newResources -= 2;
                            }
                        } else if (buildingType.startsWith("M") && buildingAdditionIsAllowed(MilitaryBase.class)) {
                            if(resources >= 3) {
                                addBuilding(new MilitaryBase(x, y, isFriendly()));
                                newResources -= 3;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("That location is already taken");
                    }
                }
            }
        }
        return newResources;
    }

    // how many buildings of a specific type allowed
    public int getNumOfAllowedBuildings(Class c) {
        // can have 1 house for each level
        if(c.equals(House.class)) return getLevel();
        else if(c.equals(MilitaryBase.class)) {
            // level 1-3 = 1 allowed
            // level 4-6 = 2 allowed
            // level 7+ = 3 allowed
            if(getLevel() <= 9) {
                return 1 + (getLevel() - 1) / 3;
            }
            // 3 is the max
            return 3;
        } else if(c.equals(ResourceCollector.class)) {
            // get an extra collector every other level, starting with 1
            return getLevel() / 2 + 1;
        }
        // if it isn't recognized or is another Headquarters
        // don't allow it to be placed
        return 0;
    }

    // returns how many instances of a building class there are
    private int getBuildingOccurrences(Class c) {
        int occurrences = 0;
        for(Building building : buildings) {
            // counts each time it is the same class
            if(building.getClass().equals(c)) occurrences++;
        }
        return occurrences;
    }

    // if the allowed buildings is higher
    // than the current amount of occurrences
    private boolean buildingAdditionIsAllowed(Class c) {
        return getBuildingOccurrences(c) < getNumOfAllowedBuildings(c);
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
        if(getNumOfAllowedBuildings(building.getClass()) > getBuildingOccurrences(building.getClass())) {
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
    public String getDisplayName() {
        return "HQ";
    }

    @Override
    protected int getImportance() {
        return 1;
    }
}
