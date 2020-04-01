package Buildings;

import java.util.ArrayList;
import java.util.Scanner;
import Locations.Location;
import Units.*;

public class MilitaryBase extends Building {

    private int unitTrainTime;
    private UnitType unitTraining;
    private ArrayList<Unit> units;

    public MilitaryBase(Location location, boolean friendly) {
        super(location, friendly);
        unitTraining = null;
        unitTrainTime = 0;
        units = new ArrayList<>();
    }

    private enum UnitType {
        SOLDIER,
        ARTILLERY
    }

    private void updateUnitTraining() {
        if(isFriendly()) System.out.println("Units training");

        unitTrainTime--;

        if(unitTrainTime == 0) {
            // add a unit to the units list and reset the variables associated with it
            if(unitTraining == UnitType.SOLDIER) {
                units.add(new Soldier(this.getLocation(), this.isFriendly(), this.getLevel()));
                if(isFriendly()) System.out.println("Soldier trained");
            } else if(unitTraining == UnitType.ARTILLERY) {
                units.add(new Artillery(this.getLocation(), this.isFriendly(), this.getLevel()));
                if(isFriendly()) System.out.println("Artillery trained");
            }
        }
    }

    @Override
    public int turn(int resources) {
        if (isFriendly()) System.out.println("Military Base Turn");
        int newResources = resources;
        // if there are units training, don't do anything
        // except wait for them to continue training
        if(unitTrainTime == 0) {
            if (isFriendly()) {
                boolean turnIsOver = false;
                while (!turnIsOver) {
                    Scanner console = new Scanner(System.in);
                    System.out.println("What do you want to train?\n" +
                            "\tA = Artillery (5 resources, 3 turns)\n" +
                            "\tS = Soldiers (3 resources, 1 turn)\n" +
                            "\tN = Nothing");
                    String answer = console.nextLine().toUpperCase();
                    if (answer.startsWith("A") && newResources - 5 >= 0) {
                        // set up the multi turn unit training system
                        unitTrainTime = 3;
                        unitTraining = UnitType.ARTILLERY;

                        newResources -= 5;
                        turnIsOver = true;
                    } else if (answer.startsWith("S") && newResources - 3 >= 0) {
                        // set up the multi turn unit training system
                        unitTrainTime = 1;
                        unitTraining = UnitType.SOLDIER;

                        newResources -= 3;
                        turnIsOver = true;
                    } else if (answer.startsWith("N")) turnIsOver = true;
                }
            } else {
                // TODO make a bot that makes good decisions here
            }
        } else {
            updateUnitTraining();
        }
        return newResources;
    }

    public ArrayList<Unit> getUnits() {
        return units;
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
