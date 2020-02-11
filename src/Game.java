import Buildings.*;

public class Game {
    public static void main(String[] args) {
        // make the buildings
        Headquarters hq = new Headquarters();
        GameMap map = new GameMap(10, hq, 5, 3);
        House house = new House();
        House house2 = new House();
        House house3 = new House();
        MilitaryBase base = new MilitaryBase();
        ResourceCollector collector = new ResourceCollector();
        // upgrade them
        hq.upgrade();
        hq.upgrade();
        base.upgrade();
        house.upgrade();
        // add them to the map
        map.addBuilding(house, 5, 4);
        map.addBuilding(house2, 4, 4);
        map.addBuilding(house3, 3, 4);
        map.addBuilding(base, 6, 4);
        map.addBuilding(collector, 7, 3);
        System.out.println(map);
    }
}