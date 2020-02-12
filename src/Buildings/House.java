package Buildings;

public class House extends Building {

    @Override
    public int turn(int resources) {
        System.out.println("House Turn");
        return super.turn(resources);
    }

    @Override
    public String getDisplayName() {
        return "H_";
    }
}
