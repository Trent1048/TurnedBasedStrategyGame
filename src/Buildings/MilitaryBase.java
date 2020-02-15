package Buildings;

import java.util.Scanner;

public class MilitaryBase extends Building {

    public MilitaryBase(Location location, boolean friendly) {
        super(location, friendly);
    }

    private void trainUnits() {
        if(isFriendly()) {
            System.out.println("Units trained");
        }
    }

    private void trainArtillery() {
        if(isFriendly()) {
            System.out.println("Artillery Trained");
        }
    }

    @Override
    public int turn(int resources) {
        int newResources = resources;
        if(isFriendly()) {
            boolean turnIsOver = false;
            while(!turnIsOver) {
                Scanner console = new Scanner(System.in);
                System.out.println("What do you want to train?\n" +
                        "\tA = Artillery\n" +
                        "\tU = Units\n" +
                        "\tN = Nothing");
                String answer = console.nextLine().toUpperCase();
                // TODO make a system for training troops that takes a certain number of turns
                if (answer.startsWith("A") && newResources - 5 >= 0) {
                    trainArtillery();
                    newResources -= 5;
                    turnIsOver = true;
                } else if (answer.startsWith("U") && newResources - 1 >= 0) {
                    trainUnits();
                    newResources -= 1;
                    turnIsOver = true;
                } else if (answer.startsWith("N")) turnIsOver = true;
            }
        } else {
            // TODO make a bot that makes good decisions here
        }
        return newResources;
    }

    @Override
    public int getUpgradeCost() {
        return 5 * (int)Math.pow(getLevel() + 1, 2);
    }

    @Override
    public String toString() {
        return "Military Base at " + this.getLocation() + " level " + this.getLevel();
    }

    @Override
    public String getDisplayName() {
        return "MB";
    }

    @Override
    protected int getImportance() {
        return 2;
    }
}
