package Buildings;

import java.util.*;

// the main thing where everything happens
public class Headquarters extends Building {

    // stores the location of every building on all
    // HQs to make sure none are at the same spot
    private static HashSet<Location> buildingLocations;
    private ArrayList<Building> buildings;
    private int size;
    private int resources;
    private static HashMap<String, Class> buildingTypeMap;

    static {
        buildingLocations = new HashSet<>();

        buildingTypeMap = new HashMap<>();
        buildingTypeMap.put("H", House.class);
        buildingTypeMap.put("R", ResourceCollector.class);
        buildingTypeMap.put("M", MilitaryBase.class);
    }

    public Headquarters(Location location, boolean friendly, int boardSize) {
        super(location, friendly);
        this.size = boardSize;
        resources = 5; // start with 5 resources
        buildings = new ArrayList<>();
        buildings.add(this);
        buildingLocations.add(location);
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
                        buildingOccurrenceFraction(House.class) + " Houses, " +
                        buildingOccurrenceFraction(ResourceCollector.class) + " Resource Collectors, and " +
                        buildingOccurrenceFraction(MilitaryBase.class) + " Military Bases.\n" +
                        "You have " + resources + " resources\n" +
                        "What type of building would you like to buy?\n" +
                        "H = House (1 resource)\n" +
                        "R = Resource Collector (2 resources)\n" +
                        "M = Military Base (3 resources)\n" +
                        "N = Nothing");
                input = console.nextLine().toUpperCase().substring(0,1);
                Class buildingType = buildingTypeMap.get(input);

                if(buildingType != null) {
                    // sets up the location
                    System.out.print("X: ");
                    int x = console.nextInt();
                    System.out.print("Y: ");
                    int y = console.nextInt();
                    Location buildingLoc = new Location(x, y);

                    // make sure the location is open
                    if(buildingLocations.contains(buildingLoc)) {
                        System.out.println("There is already a building in that location");
                        return newResources;
                    } else {
                        buildingLocations.add(buildingLoc);
                    }

                    // don't add if there are too many already
                    if(buildingAdditionIsAllowed(buildingType)) {
                        // don't add if can't afford
                        if(getBuildingPrice(buildingType) <= newResources) {
                            newResources -= getBuildingPrice(buildingType);
                            switch (input) {
                                case "H":
                                    buildings.add(new House(buildingLoc, isFriendly()));
                                    break;
                                case "R":
                                    buildings.add(new ResourceCollector(buildingLoc, isFriendly()));
                                    break;
                                case "M":
                                    buildings.add(new MilitaryBase(buildingLoc, isFriendly()));
                                    break;
                            }
                        } else {
                            System.out.println("You cannot afford that building");
                        }
                    } else {
                        System.out.println("You already own the max number of that building");
                    }
                }
            }
        } else {
            // TODO make a bot to make good decisions
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

    // for displaying how many buildings the player
    // owns out of the max number they can own
    private String buildingOccurrenceFraction(Class c) {
        return getBuildingOccurrences(c) + " / " + getNumOfAllowedBuildings(c);
    }

    // stores how much it costs to purchase a building
    private static int getBuildingPrice(Class c) {
        if(c.equals(House.class)) {
            return 1;
        } else if(c.equals(ResourceCollector.class)) {
            return 2;
        } else if(c.equals(MilitaryBase.class)) {
            return 3;
        }
        return 0;
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
