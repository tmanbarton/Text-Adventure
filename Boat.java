import java.util.ArrayList;

public class Boat extends Location {
    boolean inBoat;

    public Boat(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, boolean visited, String name, boolean inBoat) {
        super(description, items, connectingLocations, visited, name);
        this.inBoat = inBoat;
    }
    public Boat(){}
}
