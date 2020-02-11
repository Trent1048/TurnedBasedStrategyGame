import Buildings.*;

public class Game {
    public static void main(String[] args) {
        GameMap map = new GameMap(10);
        Headquarters hq = new Headquarters();
        House house = new House();
        MilitaryBase base = new MilitaryBase();
        ResourceCollector collector = new ResourceCollector();
        map.addBuilding(hq, 5, 3);
        map.addBuilding(house, 5, 4);
        map.addBuilding(base, 6, 4);
        map.addBuilding(collector, 7, 3);
        hq.upgrade();
        hq.upgrade();
        base.upgrade();
        System.out.println(map);
    }
}