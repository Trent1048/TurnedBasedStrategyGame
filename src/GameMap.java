import Buildings.*;

// stores HQs, lets them do their turns,
// and displays the board
public class GameMap {
    private int size;
    // for looping through both HQs
    private Headquarters[] hQs;
    private Headquarters friendlyHQ;

    // must start with an HQ
    public GameMap(int size, int x, int y) {
        this.size = size;
        // TODO randomize starting locations
        friendlyHQ = new Headquarters(new Building.Location(x, y), true, size);
        Headquarters enemyHQ = new Headquarters(new Building.Location(0, 0), false, size);
        hQs = new Headquarters[]{friendlyHQ, enemyHQ};
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
            display.append(row + "\n");
        }

        // adds numbers to the bottom row
        for(int i = 0; i < size; i++) {
            display.append(i + "    ");
        }
        display.append("\n");

        return display.toString();
    }
}