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
        //The ground starts to rumble. On the opposite side of the lake you see a massive concrete wall start\nto rise out of the water, blocking the flow of\nwater from the river into the lake. Then there's\nanother shudder and a huge wave starts to move out\nin a circle from the middle of the lake and the\nwater level starts going down. After waiting in awe\nfor a few minutes, the water is completely\ngone, leaving a town that had been flooded by this dam at\nthe bottom of the valley.");
    }
}
