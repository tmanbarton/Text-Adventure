import java.util.ArrayList;

public class Location {

    String description;                         // Description of the location to be printed when user arrives there
    ArrayList<Item> items;                      // Items available to pick up at the location
    ArrayList<ConnectingLocation> connectingLocations;    // Legal locations that are able to be reached from this one
    Location previousLocation;                  // The last Location you were at. "back" will take you there
    boolean visited;                            // Has this location been visited yet? Used to know what to print for description
    String name;

    public Location(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location previousLocation, boolean visited, String name) {
        this.description = description;
        this.items = items;
        this.connectingLocations = connectingLocations;
        this.previousLocation = previousLocation;
        this.visited = visited;
        this.name = name;
    }

    public Location(){}

    public void get(String input, Location location, ArrayList<Item> inventory) {
        // Check for whether requested item is in inventory or at location already
        MineEntrance mineEntrance = new MineEntrance();
        boolean itemInInventory = Main.isItemHere(input, inventory);
        boolean itemToGetAtLocation = Main.isItemHere(input, location.items);
        // Is jar in inventory? Is gold at location?
        Item jar = Main.findItem("jar", inventory);
        boolean jarInInventory = Main.isItemHere("jar", inventory);
        boolean goldAtLocation = Main.isItemHere("gold", location.items);
        //Corner case for mine entrance. If input is "get nails" special stuff happens at mine entrance.
        if(location instanceof MineEntrance && input.equals("nails") && !((MineEntrance) location).nailsOff) {
            mineEntrance.nails(location);
        }
        // Cases for trying to get stuff users might try to get but they aren't allowed to
        else if(location instanceof Shed && input.equals("shed")) {
            System.out.println("Don't be ridiculous! The shed is way too heavy to be lifted, let alone carried around.");
        }
        else if(location instanceof Dam && input.equals("magnet") && ((Dam) location).magnetDropped) {
            System.out.println("The magnet will not budge. You cannot get it off the wheel.");
        }
        else if(location.name.equals("picnic table") && input.equals("table")) {
            System.out.println("How could you carry such a thing? It's much to heavy and awkward to be picked up.");
        }
        else if(location.name.equals("mine shaft") && input.equals("sign")) {
            System.out.println("You can't get that. It's firmly attached to the wall.");
        }
        else if(input.equals("tree")) {
            System.out.println("You walk to the nearest tree and start pulling. After a couple minutes of this you give up. You\ncan't get a tree.");
        }
        else if(itemInInventory) {
            System.out.println("You're already carrying it!");
        }
        else if(!itemToGetAtLocation) {
            System.out.println("I see no " + input + " here");
        }
        // If you're getting gold at Mine Entrance and you have all the right stuff
        else if(input.equals("gold") && jarInInventory && goldAtLocation && jar != null) {
            System.out.println("The jar is now full of gold flakes.");
            jar.inventoryPrint = "Jar filled with gold flakes";
            for(Item item : location.items) {
                if(item.name.equals("gold")) {
                    Main.addAndRemove(inventory, location.items, item);
                    break;
                }
            }
        }
        // Try to get gold without jar in inventory
        else if(input.equals("gold") && !jarInInventory && goldAtLocation) {
            System.out.println("You don't have anything to put the gold flakes in");
        }
        // Everything's right for getting an Item.
        else {
            Item toGet = Main.findItem(input, location.items);
            if(input.equals("cube") && toGet instanceof Cube && !((Cube) toGet).taken) {
                toGet.locationPrint = "There is a plastic cube puzzle lying on the ground";
                ((Cube) toGet).taken = true;
                location.description = "There is a polished granite pedestal, black as night, in the middle of this room\nwith walls of the same black rock.";
            }
            Main.addAndRemove(inventory, location.items, toGet);
            System.out.println("OK");
        }
    }

    // Basically the reverse of get()
    public void drop(String input, Location location, ArrayList<Item> inventory) {
        // Items fall out of boat, let Boat class handle dropping if you're in the boat
        if(location instanceof Boat) {
            ((Boat) location).loseItem("drop " + input, inventory);
            return;
        }
        boolean itemInInventory = Main.isItemHere(input, inventory);
        Item gold = Main.findItem("gold", inventory);
        Item jar = Main.findItem("jar", inventory);
        if(inventory.isEmpty()) {
            System.out.println("You're not carrying anything!");
            return;
        }
        // If you request drop gold, jar must be in inventory.
        else if(input.equals("gold") && itemInInventory && jar != null) {
            Main.addAndRemove(location.items, inventory, gold);
            jar.inventoryPrint = "Jar";
            System.out.println("OK");
            return;
        }
        // If you drop the jar the gold drops with it
        else if(input.equals("jar") && itemInInventory && jar != null && gold != null) {
            Main.addAndRemove(location.items, inventory, jar);
            jar.inventoryPrint = "Jar";
            Main.addAndRemove(location.items, inventory, gold);
            System.out.println("OK");
            return;
        }
        Item item = Main.findItem(input, inventory);
        // Not in inventory
        if(item == null) {
            System.out.println("You don't have that!");
        }
        // Drop requested item
        else if(item.name.equals(input)) {
            // Drop manet at dam
            if(location instanceof Dam && input.equals("magnet")) {
                System.out.println("You drop the magnet and as it's falling it snaps to the shiny center of the wheel. You can hear some\nmechanical clicking somewhere inside the dam.");
                ((Dam)location).magnetDropped = true;
                location.description = "You're on a short dam that looks like it created this lake by stopping up a large river. The dam\ngoes north and south along the west end of the lake. Close by is a wheel with it's axel extending\ndeep into the dam. It's orange metal is fading to rust except for some other metal at the center,\nshining in the sun. There's a large magnet stuck to this part of the wheel. South leads around the\nlake and to the north there's a set of stairs.";
                Item magnet = Main.findItem("magnet", inventory);
                inventory.remove(magnet);
            }
            // Plain ol' drop item
            else {
                Main.addAndRemove(location.items, inventory, item);
                System.out.println("OK");
            }
        }
    }

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

    // Similar to get but only applies to jar and gold items
    public void fill(String input, Location location, ArrayList<Item> inventory) {
        // Jar must be in inventory to fill
        boolean jarInInventory = Main.isItemHere("jar", inventory);
        // You can't fill anything other than the jar
        if(input.length() >= 6 && !input.substring(5).equals("jar")) {
            System.out.println("You can't fill that.");
        }
        else if(!jarInInventory) {
            System.out.println("You have nothing to fill.");
        }
        // Else jar is inventory: Can only be filled with gold, change description if it's filled, and add gold to inventory if necessary
        else {
            boolean goldAtLocation = Main.isItemHere("gold", location.items);
            boolean goldInInventory = Main.isItemHere("gold", inventory);
            if(goldInInventory) {
                System.out.println("Your jar is already full.");
            }
            else if(!goldAtLocation) {
                System.out.println("There's nothing here to fill the jar with.");
            }
            else {
                Item jar = Main.findItem("jar", inventory);
                jar.inventoryPrint = "Jar full of gold flakes.";
                Item gold = Main.findItem("gold", location.items);
                Main.addAndRemove(inventory, location.items, gold);
                System.out.println("The jar is now full of gold flakes.");
            }
        }
    }

    public void shoot(String input, Location location, ArrayList<Item> inventory) {
        // Bow must be in inventory before you can shoot anything
        if(location instanceof Boat) {
            ((Boat)location).loseItem(input, inventory);
            return;
        }
        boolean bowInInventory = Main.isItemHere("bow", inventory);
        if(!bowInInventory) {
            System.out.println("You have nothing to shoot with.");
        }
        else {
            // You can only shoot the arrow
            boolean arrowInInventory = Main.isItemHere("arrow", inventory);
            if(!arrowInInventory) {
                System.out.println("You have nothing to shoot");
            }
            else {
                MineEntrance mineEntrance = new MineEntrance();
                if(!(location instanceof MineEntrance) || mineEntrance.nailsOff) {
                    System.out.println("Your arrow goes flying off into the the distance and lands with a soft thud into the ground.");
                    Item arrow = Main.findItem("arrow", inventory);
                    Main.addAndRemove(location.items, inventory, arrow);
                }
                else {
                    mineEntrance.shoot(inventory, location);
                }
            }
        }
    }

    public void turn(Location location) {
        System.out.println("There's nothing to turn here.");
    }
}
