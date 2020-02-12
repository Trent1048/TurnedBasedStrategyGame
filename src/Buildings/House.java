package Buildings;

public class House extends Building {

    public House(Location location, boolean friendly) {
        super(location, friendly);
    }

    @Override
    public int turn(int resources) {
        if(isFriendly()) {
            System.out.println("House Turn");
        }
        return super.turn(resources);
    }

    @Override
    public String getDisplayName() {
        return "H_";
    }

    @Override
    protected int getImportance() {
        return 3;
    }
}
