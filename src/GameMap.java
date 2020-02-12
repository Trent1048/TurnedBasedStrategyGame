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
        friendlyHQ = new Headquarters(x, y, true, size);
        Headquarters enemyHQ = new Headquarters(size - 1, size - 1, false, size);
        hQs = new Headquarters[]{friendlyHQ, enemyHQ};
    }

    // TODO make the HQ add it's own buildings
    public void addBuilding(Building building) {
        friendlyHQ.getBuildings().add(building);
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
}