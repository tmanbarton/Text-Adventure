import java.util.ArrayList;

public class Dam extends Location {
    boolean turned;     // Check if wheel has been turned to know if you can access town under the lake
    boolean magnetized;
    public Dam(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, boolean visited, String name, boolean turned, boolean magnetized) {
        super(description, items, connectingLocations, visited, name);
        this.turned = turned;
        this.magnetized = magnetized;
    }

    public Dam(){}

    public void magnet(Location location) {

    }

    public void turn(Location location) {

    }
}
