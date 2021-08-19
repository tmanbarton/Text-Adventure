import java.util.ArrayList;

public class RubyOnRails extends Location {
    public boolean rubyTaken;
    public RubyOnRails(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean rubyTaken) {
        super(description, shortDescription, items, connectingLocations, location, visited, name);
        this.rubyTaken = rubyTaken;
    }
}
