import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        // setup the board
        GameMap map = new GameMap(10, 5, 3);
        // loop through the turns
        while(true) {
            System.out.println(map);
            map.turn();
            System.out.print("Press Enter to continue");
            console.nextLine();
        }
    }
}