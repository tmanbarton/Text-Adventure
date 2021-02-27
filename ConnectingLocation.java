import java.util.ArrayList;

public class ConnectingLocation {
    ArrayList<String> directions;       // The direction that leads to the location
    Location location;                  // The location that that direction leads to
    public ConnectingLocation(ArrayList<String> directions, Location location) {
        this.directions = directions;
        this.location = location;
    }
}
