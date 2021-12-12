import java.util.ArrayList;

public class Location {

    String description;                         // Description of the location to be printed when user arrives there
    ArrayList<Item> items;                      // Items available to pick up at the location
    ArrayList<ConnectingLocation> connectingLocations;    // Legal locations that are reachable from this one
    Location previousLocation;                  // The last Location you were at. "back" will take you there
    boolean visited;                            // Has this location been visited yet? Used to know what to print for description
    String name;
    String shortDescription;

    public Location(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location previousLocation, boolean visited, String name) {
        this.description = description;
        this.items = items;
        this.connectingLocations = connectingLocations;
        this.previousLocation = previousLocation;
        this.visited = visited;
        this.name = name;
        this.shortDescription = shortDescription;
    }

    public Location(){}

    // Print Items in inventory or says if inventory is empty
    public void inventory(ArrayList<Item> inventory) {
        if(inventory.isEmpty()) {
            System.out.println("You aren't carrying anything!");
        }
        else {
            System.out.println("You are carrying the following:");
            for(Item i : inventory) {
                System.out.println(i.inventoryPrint);
            }
        }
    }

    // Can only unlock the shed. Can only unlock it if you have the key.
    public void unlock(Location location, ArrayList<Item> inventory) {
        if(!(location instanceof Shed)) {
            System.out.println("There's nothing here to unlock.");
        }
        else {
            ((Shed) location).unlock(((Shed) location), inventory);
        }
    }

    // Can only open the shed. Can only open it once it's been unlocked
    public void open(Location location) {
        if (!(location instanceof Shed)) {
            System.out.println("There's nothing here to open.");
        }
        else {
            ((Shed) location).open(((Shed) location));
        }
    }

    public void turn(Location location) {
        System.out.println("There's nothing to turn here.");
    }
}
