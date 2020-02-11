// anything that implements this can
// be displayed on the game map
public interface Building {
    public String getDisplayName(); // should be 3 chars long
    public int getLevel();
}