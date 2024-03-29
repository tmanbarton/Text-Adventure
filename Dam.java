import java.util.ArrayList;

public class Dam extends Location {
    boolean turned;     // Check if wheel has been turned to know if you can access town under the lake
    boolean magnetDropped;
    public Dam(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean turned, boolean magnetDropped) {
        super(description, shortDescription, items, connectingLocations, location, visited, name);
        this.turned = turned;
        this.magnetDropped = magnetDropped;
    }

    public Dam(){}

    // Turn wheel if magnet has been dropped
    public void turn(Location location) {
        if(((Dam)location).magnetDropped) {
            System.out.println("The ground starts to rumble and you see a massive concrete wall start to rise out of the water on\nthe opposite side of the lake, blocking the flow of water from the river into the lake. There's\nanother shudder and a huge whirl pool form near the middle of the lake and the water level starts\ngoing down. Soon the water is completely gone, revealing a town that had been under water only a\nfew minutes ago. You can probably get to the town if you go down the dam to the west.");
            Graph graph = new Graph();
            location.connectingLocations.get(3).directions = graph.west;
            location.connectingLocations.get(4).directions = graph.down;
            location.connectingLocations.get(1).location.description = "You are on the south side of an empty lake. There's a path going west and there's a dam to the\nnorth.";
            //TODO change description of tailings, intersection, and driveway to get rid of lake also
        }
        else {
            System.out.println("The wheel is locked firmly in place.");
        }
    }
}
