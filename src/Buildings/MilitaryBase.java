package Buildings;

import java.util.Scanner;

public class MilitaryBase extends Building {

    private void trainUnits() {
        System.out.println("Units trained");
        // TODO make this work
    }

    private void trainArtillery() {
        System.out.println("Artillery Trained");
        // TODO make this work
    }

    @Override
    public int turn(int resources) {
        int newResources = resources;
        Scanner console = new Scanner(System.in);
        System.out.println("Military Base Turn\nWould you like to train anything? ");
        String answer = console.nextLine();
        if(answer.toUpperCase().startsWith("Y")) {
            System.out.println("What do you want to train?\n" +
                    "A = Artillery\n" +
                    "U = Units");
            answer = console.nextLine();
            if(answer.toUpperCase().startsWith("A") && newResources - 5 >= 0) {
                trainArtillery();
                newResources -= 5;
            } else if(answer.toUpperCase().startsWith("U") && newResources - 1 >= 0){
                trainUnits();
                newResources -= 1;
            }
        }
        console.close();
        return newResources;
    }

    @Override
    public String getDisplayName() {
        return "MB";
    }
}
