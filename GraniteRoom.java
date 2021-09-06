import java.util.ArrayList;

public class GraniteRoom extends Location {
    boolean puzzleTaken;

    public GraniteRoom(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean puzzleTaken) {
        super(description, shortDescription, items, connectingLocations, location, visited, name);
        this.puzzleTaken = puzzleTaken;
    }
}
