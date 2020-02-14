import Buildings.*;

import java.util.ArrayList;

// stores HQs, lets them do their turns,
// and displays the board
public class GameMap {
    private int size;
    // for looping through both HQs
    private ArrayList<Headquarters> hQs;
    private Headquarters friendlyHQ;

    // must start with an HQ
    public GameMap(int size) {
        this.size = size;
        hQs = new ArrayList<>();
    }

    public void addHQ(int x, int y, boolean friendly) {
        Headquarters newHQ = new Headquarters(new Building.Location(x, y), friendly, size);
        // only allow one HQ to be controlled by the player (AKA friendly)
        if(friendly) {
            if(friendlyHQ != null) throw new IllegalArgumentException("There is already an existing friendly HQ");
            friendlyHQ = newHQ;
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
                }
                else display.append("____");
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