// holds all the buildings in the game
public class GameMap {
    private int size;
    private Building[][] spaces;

    public GameMap(int size) {
        this.size = size;
        spaces = new Building[size][size];
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

    public void addBuilding(Building building, int row, int col) {
        spaces[row][col] = building;
    }
}