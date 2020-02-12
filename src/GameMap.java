import Buildings.*;
import java.util.ArrayList;

// holds all the buildings in the game and
// takes care of how many buildings of each
// type there can be based on the level of
// the HQ
public class GameMap {
    private int size;
    private int resources;
    private Headquarters hq;
    private ArrayList<Building> buildings;
    private Building[][] spaces;

    // must start with an HQ
    public GameMap(int size, Headquarters hq, int hqX, int hqY) {
        resources = 0;
        this.size = size;
        spaces = new Building[size][size];
        this.hq = hq;
        spaces[hqX][hqY] = hq;
        buildings = new ArrayList<Building>();
        buildings.add(hq);
    }

    // executes one turn in the game
    public void turn() {
        // goes through each type of building on the map and does it's turn
        for(Building building : buildings) {
            resources = building.turn(resources);
            System.out.println("Now at " + resources + " resources");
        }
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

    public void addBuilding(Building building, int row, int col) {
        // don't let the user place a building outside the map
        if(row > size || col > size) throw new IllegalArgumentException("The selected row or column is out of the map");
        // don't let a building overwrite an existing one
        if(spaces[row][col] != null) {
            throw new IllegalArgumentException("There is already a building in the selected location (" +
                    row + ", " + col + ")");
        }
        // makes sure there aren't too many buildings before adding it
        if(hq.getAllowedBuildings(building) > getBuildingOccurrences(building)) {
            buildings.add(building);
            spaces[row][col] = building;
        } else {
            throw new IllegalArgumentException("The max number of building type: " +
                    building.getClass() + " has been reached");
        }
    }

    public String toString() {
        StringBuilder display = new StringBuilder();
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                Building current = spaces[row][col];
                if(current != null) {
                    display.append(current.getDisplayName());
                    display.append("_");
                    display.append(current.getLevel());
                }
                else display.append("____");
                display.append(" ");
            }
            display.append("\n\n");
        }
        return display.toString();
    }

    public int getResources() {
        return resources;
    }
}