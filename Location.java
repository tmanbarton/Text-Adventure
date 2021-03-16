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

    public static void get(String input, Location location, ArrayList<Item> inventory) {
        // Conditionals to check for whether requested item is in inventory already or at location
        MineEntrance mineEntrance = new MineEntrance();
        boolean itemInInventory = Main.isItemHere(input, inventory);
        boolean itemToGetAtLocation = Main.isItemHere(input, location.items);
        // Conditionals for if jar is in inventory and gold at location
        Item jar = Main.findItem("jar", inventory);
        boolean jarInInventory = Main.isItemHere("jar", inventory);
        boolean goldAtLocation = Main.isItemHere("gold", location.items);
        //Corner case for mine entrance. If input is get nails special stuff happens at mine entrance.
        if(location instanceof MineEntrance && input.equals("nails") && !((MineEntrance) location).nailsOff) {
            mineEntrance.nails(location);
        }
        // Corner case if you try to get shed at shed location
        else if(location instanceof Shed && input.equals("shed")) {
            System.out.println("Don't be ridiculous! The shed is way too heavy to be lifted, let alone carried around.");
        }
        // Corner case if you try to get table at picnic table
        else if(location.name.equals("picnic table")) {
            System.out.println("How could you carry such a thing? It's much to heavy and awkward to be picked up.");
        }
        // Corner case if you try to get sign in mine shaft
        else if(location.name.equals("mine shaft") && input.equals("sign")) {
            System.out.println("You can't get that. It's firmly attached to the wall.");
        }
        // If item is already in inventory, let user know they already have it and do nothing
        else if(itemInInventory) {
            System.out.println("You're already carrying it!");
        }
        // If it's not in inventory and not at location, let user know it's not here
        else if(!itemToGetAtLocation) {
            System.out.println("I see no " + input + " here");
        }
        // Corner case if getting gold, you must have the jar in inventory to be able to get gold
        // If you do have the jar and there's gold to get print the message and change the parameter inventoryPrint for jar and
        // remove gold from location and add to inventory
        else if(input.equals("gold") && jarInInventory && goldAtLocation) {
            System.out.println("The jar is now full of gold flakes.");
            jar.inventoryPrint = "Jar filled with gold flakes";
            for(Item i : location.items) {
                if(i.name.equals("gold")) {
                    Main.addAndRemove(inventory, location.items, i);
                    break;
                }
            }
        }
        // If you don't have the jar in inventory and try to get gold
        else if(input.equals("gold") && !jarInInventory && goldAtLocation) {
            System.out.println("You don't have anything to put the gold flakes in");
        }
        else {
            // If it's at the location and it isn't in inventory, add it to inventory and remove from location's item arraylist
            Item toGet = Main.findItem(input, location.items);
            Main.addAndRemove(inventory, location.items, toGet);
            System.out.println("OK");
        }
    }

    public static void drop(String input, Location location, ArrayList<Item> inventory) {
        // boolean to check if the item is in inventory and items for jar and gold since they require special cases for getting and dropping
        // If you have gold in inventory and you drop jar, then remove both gold and jar from inventory since gold is in jar
        // If you drop just gold, keep jar and remove gold
        boolean itemInInventory = Main.isItemHere(input, inventory);
        Item gold = Main.findItem("gold", inventory);
        Item jar = Main.findItem("jar", inventory);
        if(inventory.isEmpty()) {
            System.out.println("You're not carrying anything!");
            return;
        }
        // If you drop gold, change parameter inventoryPrint back to "Jar" since it no longer has gold in it
        else if(input.equals("gold") && itemInInventory) {
            Main.addAndRemove(location.items, inventory, gold);
            jar.inventoryPrint = "Jar";
            System.out.println("OK");
            return;
        }
        // If you drop the jar the gold drops with it so remove gold and jar from inventory and add them to location items
        else if(input.equals("jar") && itemInInventory && jar != null && gold != null) {
            Main.addAndRemove(location.items, inventory, jar);
            jar.inventoryPrint = "Jar";
            Main.addAndRemove(location.items, inventory, gold);
            System.out.println("OK");
            return;
        }
        // Find the item in inventory and add it to location items then remove from inventory
        Item item = Main.findItem(input, inventory);
        if(item == null) {
            // If findItem() returns null, the item isn't in inventory so say so
            System.out.println("You don't have that!");
        }
        // else if, check if findItem() returned the requested item to drop
        else if(item.name.equals(input)) {
            Main.addAndRemove(location.items, inventory, item);
            System.out.println("OK");
            return;
        }
    }

}
