import Buildings.*;

public class Game {
    public static void main(String[] args) {
        // make the buildings
        GameMap map = new GameMap(10);
        map.addBuilding(new House(5, 4, true));
        map.addBuilding(new House(4, 4, true));
        map.addBuilding(new House(3, 4, true));
        map.addBuilding(new MilitaryBase(6, 4, true));
        map.addBuilding(new ResourceCollector(7, 3, true));

        // print the map
        System.out.println(map);
        map.turn();
    }
}