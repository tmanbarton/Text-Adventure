import java.util.ArrayList;

public class Location {

    String description;                         // Description of the location to be printed when user arrives there
    ArrayList<Item> items;                      // Items available to pick up at the location
    ArrayList<ConnectingLocation> connectingLocations;    // Legal locations that are able to be reached from this one
    boolean visited;                            // Has this location been visited yet? Used to know what to print for description
    String name;

    public Location(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, boolean visited, String name) {
        this.description = description;
        this.items = items;
        this.connectingLocations = connectingLocations;
        this.visited = visited;
        this.name = name;
    }

    public Location(){}

}
