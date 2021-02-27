import java.util.ArrayList;

public class UndergroundLake extends Location {

    boolean inBoat; // You have to be in the boat before you can go any direction besides north since north is out of
                    // the mine shaft and others are across the lake
    public UndergroundLake(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, boolean visited, String name, boolean inBoat) {
        super(description, items, connectingLocations, visited, name);
        this.inBoat = inBoat;
    }
    public UndergroundLake(){}

    public void direction(String input, Location location) {
        UndergroundLake undergroundLake = ((UndergroundLake)location);
        Main m = new Main();
        // If you're not in the boat and you try to go west, southwest, or northwest, say you have to be in the boat and set connecting
        // locations for west, southwest, and northwest to this location since that shouldn't go anywhere. First clear that arraylist so
        // I'm not adding more locations on top of the ones that it's actually allowed to go to
        if(!undergroundLake.inBoat && (m.northwest.contains(input) || m.southwest.contains(input) || m.west.contains(input))) {
            System.out.println("You have to be in the boat to cross the lake.");
            // Mine shaft is at index 0, must always have mine shaft as a connecting location so have to add it again after I clear the arraylist
            ConnectingLocation mineShaft = undergroundLake.connectingLocations.get(0);
            undergroundLake.connectingLocations.clear();
            undergroundLake.connectingLocations.add(mineShaft);
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.west, undergroundLake));
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.southwest, undergroundLake));
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.northwest, undergroundLake));
        }
        // Else you're in the boat and can go to the west, southwest, and northwest locations so add those to this location. First
        // clearing the arraylist.
        else {
            // Mine shaft is at index 0, must always have mine shaft as a connecting location so have to add it again after I clear the arraylist
            ConnectingLocation mineShaft = undergroundLake.connectingLocations.get(0);
            undergroundLake.connectingLocations.clear();
            undergroundLake.connectingLocations.add(mineShaft);
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.west, undergroundLake));
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.southwest, undergroundLake));
            undergroundLake.connectingLocations.add(new ConnectingLocation(m.northwest, undergroundLake));
        }
    }
}
