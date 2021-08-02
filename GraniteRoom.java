import java.util.ArrayList;

public class GraniteRoom extends Location {
    boolean puzzleTaken;

    public GraniteRoom(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean puzzleTaken) {
        super(description, items, connectingLocations, location, visited, name);
        this.puzzleTaken = puzzleTaken;
    }
}
