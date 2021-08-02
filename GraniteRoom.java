import java.util.ArrayList;

public class PuzzleRoom extends Location {
    boolean puzzleTaken;

    public PuzzleRoom(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean puzzleTaken) {
        super(description, items, connectingLocations, location, visited, name);
        this.puzzleTaken = puzzleTaken;
    }
}
