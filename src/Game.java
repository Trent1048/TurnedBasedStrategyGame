import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        // introduce the game
        System.out.println("Welcome to my turn based strategy game\n\n" + // TODO come up with a better name
                "There are four types of buildings in the game:\n" +
                "\t- Headquarters: This is your main base, the thing you are " +
                "trying to protect. If it is destroyed, the game is over. Upgrading the " +
                "Headquarters gives access to more buildings and levels that increase their efficiency.\n" +
                "\t- Resource Collector: These produce resources every turn, upgrading them grants more" +
                "resources for each turn.\n" +
                "\t- House: These increase your territory by one space and boost the health points of each building." +
                "Upgrading increases the amount of health boost the Houses provide\n" +
                "\t- Military Base: These are used to train Military Units and Artillery. They can only train " +
                "one thing at a time, upgrading them strengthens the Units and Artillery they train.\n\n" +
                "The Board:\n" +
                "\t- The board is a grid represented with X, Y coordinates that hold a building in each space.\n" +
                "\t- The numbers on the bottom of the board represent the X coordinate and numbers on the right" +
                "side represent the Y axis.\n" +
                "\t- All friendly buildings are colored blue while enemy ones are colored red.\n\n" +
                "Gameplay:\n" +
                "\t- Turns rotate back and forth between you and the enemy.\n" +
                "\t- On your turn, you will be prompted to do certain actions like training Units and Artillery or " +
                "upgrading and purchasing buildings.\n" +
                "\t- These actions cost resources so make sure to get your Resource Collectors up and running so you" +
                "don't run out of resources.\n" +
                "\t- Buildings can only be placed within your territory (the blue area). Purple spaces are shared " +
                "territory between both sides\n");

        System.out.print("Press Enter to continue");
        console.nextLine();

        // setup the board and enemy base
        int boardSize = 10;
        GameMap map = new GameMap(boardSize);
        map.addHQ(random.nextInt(boardSize), random.nextInt(boardSize), false);

        // let the user pick a starting location
        System.out.println(map);

        int x, y;
        while (true) {
            try {
                System.out.println("Where do you want to put your Headquarters?");
                System.out.print("X: ");
                x = console.nextInt();
                System.out.print("Y: ");
                y = console.nextInt();
                map.addHQ(x, y, true);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid location");
            }
        }

        // loop through the turns
        while(true) {
            System.out.println(map);
            map.turn();
            System.out.print("Press Enter to continue");
            console.nextLine();
        }
    }
}