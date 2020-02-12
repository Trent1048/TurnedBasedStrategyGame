package Buildings;

public class ResourceCollector extends Building {

    @Override
    public int turn(int resources) {
        int newResources = resources + getLevel();
        System.out.println("Resource Collector Turn");
        // produces an amount of resources
        // equal to it's level
        return newResources;
    }

    @Override
    public String getDisplayName() {
        return "RC";
    }

    @Override
    protected int getImportance() {
        return 4;
    }
}