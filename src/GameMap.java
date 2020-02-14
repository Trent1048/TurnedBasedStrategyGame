import Buildings.*;

import java.util.ArrayList;

// stores HQs, lets them do their turns,
// and displays the board
public class GameMap {
    private int size;
    // for looping through both HQs
    private ArrayList<Headquarters> hQs;
    private Headquarters friendlyHQ;
    private Headquarters enemyHQ;

    // must start with an HQ
    public GameMap(int size) {
        this.size = size;
        hQs = new ArrayList<>();
    }

    public void addHQ(int x, int y, boolean friendly) {
        // don't let building go out of the map
        if(x >= size || y >= size) throw new IllegalArgumentException("Selected location is outside the map");

        Headquarters newHQ = new Headquarters(new Building.Location(x, y), friendly, size);
        // only allow one HQ to be controlled by the player (AKA friendly)
        if(friendly) {
            // don't allow one HQ to go on top of another one
            if(enemyHQ != null && newHQ.getLocation().equals(enemyHQ.getLocation())) {
                throw new IllegalArgumentException("There is already another HQ in that spot");
            }

            if(friendlyHQ != null) throw new IllegalArgumentException("There is already an existing friendly HQ");
            friendlyHQ = newHQ;
        } else {
            // don't allow one HQ to go on top of another one
            if(friendlyHQ != null && newHQ.getLocation().equals(friendlyHQ.getLocation())) {
                throw new IllegalArgumentException("There is already another HQ in that spot");
            }

            if(enemyHQ != null) throw new IllegalArgumentException("There is already an existing enemy HQ");
            enemyHQ = newHQ;
        }
        hQs.add(newHQ);
    }

    public void turn() {
        for(Headquarters hq : hQs) {
            hq.doTurns();
        }
    }

    public String toString() {
        // makes a 2d array of Buildings and puts the
        // Buildings in their correct location
        Building[][] spaces = new Building[size][size];
        for(Headquarters hq : hQs) {
            if(hq.getBuildings() != null) {
                for (Building building : hq.getBuildings()) {
                    int x = building.getLocation().getX();
                    int y = building.getLocation().getY();
                    spaces[x][y] = building;
                }
            }
        }

        StringBuilder display = new StringBuilder();
        // the loop is funky so it will print like a normal graph
        // with x horizontal, y vertical, and (0, 0) in the bottom left
        for(int row = size - 1; row >= 0; row--) {
            display.append("\n");
            for(int col = 0; col < size; col++) {
                Building current = spaces[col][row];
                if(current != null) {
                    // make it blue or red depending on if it's friendly
                    if(current.isFriendly()) display.append("\u001B[34m");
                    else display.append("\u001B[31m");

                    display.append(current.getDisplayName());
                    display.append("_");
                    display.append(current.getLevel());

                    // stop the color
                    display.append("\u001B[0m");
                } else {
                    Building.Location currentLocation = new Building.Location(col, row);

                    boolean withinFriendlyTerritory = false;
                    if(friendlyHQ != null) withinFriendlyTerritory = friendlyHQ.isWithinTerritory(currentLocation);
                    boolean withinEnemyTerritory = false;
                    if(enemyHQ != null) withinEnemyTerritory = enemyHQ.isWithinTerritory(currentLocation);

                    // decides if it should add a color stop symbol or not
                    boolean addedColor = false;
                    if(withinEnemyTerritory || withinFriendlyTerritory) {
                        // make it blue if it is just in friendly territory
                        if(!withinEnemyTerritory) display.append("\u001B[34m");
                        // make it red if it is just in enemy territory
                        else if(!withinFriendlyTerritory) display.append("\u001B[31m");
                        // make it purple if it is in both territories
                        else display.append("\u001B[35m");
                        addedColor = true;
                    }
                    display.append("____");
                    if(addedColor) display.append("\u001B[0m");
                }
                display.append(" ");
            }
            display.append(row);
            display.append("\n");
        }

        // adds numbers to the bottom row
        for(int i = 0; i < size; i++) {
            display.append(i);
            display.append("    ");
        }
        display.append("\n");

        return display.toString();
    }
}