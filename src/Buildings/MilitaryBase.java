package Buildings;

import java.util.Scanner;

public class MilitaryBase extends Building {

    public MilitaryBase(int x, int y, boolean friendly) {
        super(x, y, friendly);
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
                    "A = Artillery\n" +
                    "U = Units\n" +
                    "N = Nothing");
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
    public String getDisplayName() {
        return "MB";
    }

    @Override
    protected int getImportance() {
        return 2;
    }
}
