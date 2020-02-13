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
        // TODO make this work
    }

    private void trainArtillery() {
        if(isFriendly()) {
            System.out.println("Artillery Trained");
        }
        // TODO make this work
    }

    @Override
    public int turn(int resources) {
        int newResources = resources;
        if(isFriendly()) {
            Scanner console = new Scanner(System.in);
            System.out.println("What do you want to train?\n" +
                    "\tA = Artillery\n" +
                    "\tU = Units\n" +
                    "\tN = Nothing");
            String answer = console.nextLine().toUpperCase();
            if (answer.startsWith("A") && newResources - 5 >= 0) {
                trainArtillery();
                newResources -= 5;
            } else if (answer.startsWith("U") && newResources - 1 >= 0) {
                trainUnits();
                newResources -= 1;
            }
        } else {
            // TODO make a bot that makes good decisions here
        }
        return newResources;
    }

    @Override
    public int getUpgradeCost() {
        return 10 * getLevel();
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
