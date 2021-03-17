import java.util.ArrayList;

public class Boat extends Location {
    boolean inBoat;

    public Boat(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean inBoat) {
        super(description, items, connectingLocations, location, visited, name);
        this.inBoat = inBoat;
    }
    public Boat(){}
}
