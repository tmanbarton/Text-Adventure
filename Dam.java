import java.util.ArrayList;

public class Dam extends Location {
    boolean turned;     // Check if wheel has been turned to know if you can access town under the lake
    boolean magnetDropped;
    public Dam(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean turned, boolean magnetDropped) {
        super(description, items, connectingLocations, location, visited, name);
        this.turned = turned;
        this.magnetDropped = magnetDropped;
    }

    public Dam(){}

    // Turn wheel if magnet has been dropped
    public void turn(Location location) {

    }
}
