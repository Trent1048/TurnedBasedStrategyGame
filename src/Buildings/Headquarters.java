package Buildings;

public class Headquarters extends Building {

    // how many buildings of a specific type allowed
    public int getAllowedBuildings(Building building) {
        return 1; // TODO make it different for each type of building
    }

    @Override
    public String getDisplayName() {
        return "HQ";
    }
}
