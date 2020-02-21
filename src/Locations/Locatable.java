package Locations;

// for things to be displayed on the game map, they must be locatable
public interface Locatable {

    public Location getLocation();
    public boolean isFriendly();
    public String getDisplayName();
    public int getLevel();

}
