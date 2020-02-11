package Buildings;

public class Headquarters extends Building {

    // how many buildings of a specific type allowed
    public int getAllowedBuildings(Building building) {
        // can have 1 house for each level
        if(building.getClass().equals(House.class)) return getLevel();
        else if(building.getClass().equals(MilitaryBase.class)) {
            // level 1-3 = 1 allowed
            // level 4-6 = 2 allowed
            // level 7+ = 3 allowed
            if(getLevel() <= 9) {
                return 1 + (getLevel() - 1) / 3;
            }
            // 3 is the max
            return 3;
        } else if(building.getClass().equals(ResourceCollector.class)) {
            // get an extra collector every other level, starting with 1
            return getLevel() / 2 + 1;
        }
        // if it isn't recognized or is another Headquarters
        // don't allow it to be placed
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "HQ";
    }
}
