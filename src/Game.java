import Buildings.*;

public class Game {
    public static void main(String[] args) {
        // make the buildings
        GameMap map = new GameMap(10, 5, 3);
        // print the map
        while(true) {
            System.out.println(map);
            map.turn();
        }
    }
}