import Buildings.*;

public class Game {
    public static void main(String[] args) {
        Headquarters hq = new Headquarters();
        GameMap map = new GameMap(10, hq, 5, 3);
        House house = new House();
        MilitaryBase base = new MilitaryBase();
        ResourceCollector collector = new ResourceCollector();
        map.addBuilding(house, 5, 4);
        map.addBuilding(base, 6, 4);
        map.addBuilding(collector, 7, 3);
        hq.upgrade();
        hq.upgrade();
        base.upgrade();
        System.out.println(map);
    }
}