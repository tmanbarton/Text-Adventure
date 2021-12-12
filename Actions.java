import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Actions implements Comparator<Item> {
    public Actions(){}

    // For sorting items in inventory and at location so they print in the same order
    @Override
    public int compare(Item item1, Item item2) {
        return item1.order - item2.order;
    }

    public void takeAction(String input, Location location, ArrayList<Item> inventory) {
        Graph graph = new Graph();
        String inputCheck;
        String[] inputArray = input.split(" ");
        inputCheck = inputArray[0];
        // Is input a generic verb that can be applied at any location?
        if(graph.allVerbs.contains(inputCheck) || (input.length() >= 5 && "inventory".contains(input))) {
            findAction(input.toLowerCase(), location, inventory);
        }
        //If input isn't a legal verb, direction, or if it's "in" or "out" + another word, print one of 3 Strings to let user know it's not a legal input
        else if(!(graph.allVerbs.contains(inputCheck) || graph.allDirections.contains(inputCheck)) || inputArray.length >= 2) {
            dontKnowWord();
        }
    }

    // Parse input and, based on first word, decide what action to take
    public void findAction(String input, Location location, ArrayList<Item> inventory) {
        Actions actions = new Actions();
        String[] countWords = input.split(" ");
        if(countWords.length >= 3) {
            dontKnowWord();
        }
        else if(input.startsWith("get")) {
            if(input.equals("get") || input.equals("get ")) {
                System.out.println("What do you want to get?");
                return;
            }
            if(input.startsWith("get")) {
                actions.get(input.substring(4), location, inventory);
            }
        }
        else if(input.startsWith("fill")) {
            actions.fill(input, location, inventory);
        }
        else if(input.startsWith("drop") || input.startsWith("throw")) {
            if(input.equals("drop") || input.equals("drop ")) {
                System.out.println("What do you want to drop?");
                return;
            }
            else if(input.equals("throw") || input.equals("throw ")) {
                System.out.println("What do you want to throw?");
                return;
            }
            if (input.startsWith("drop")) {
                actions.drop(input.substring(5), location, inventory);
            }
            else if (input.startsWith("throw")) {
                actions.drop(input.substring(6), location, inventory);
            }
        }
        else if(input.equals("shoot")) {
            actions.shoot(input, location, inventory);
        }
        else if(input.equals("unlock")) {
            location.unlock(location, inventory);
        }
        else if(input.equals("open")) {
            location.open(location);
        }
        else if(input.length() >= 5 && "inventory".contains(input)) {
            location.inventory(inventory);
        }
        else if(input.equals("look")) {
            printLocation(input, location);
        }
        else if(input.equals("turn")) {
            location.turn(location);
        }
        else if(input.equals("solve")) {
            if(!isItemHere("cube", inventory)) {
                System.out.println("You have nothing to solve.");
            }
            else {
                new Graph().cube.rubiksCubeSimulator(getItemByName("cube", inventory));
                printLocation(input, location);
            }
        }
        else {
            dontKnowWord();
        }
    }

    public static void dontKnowWord() {
        Random r = new Random();
        int randomNum = r.nextInt(3);
        if(randomNum == 0) {
            System.out.println("I don't understand that.");
        }
        else if(randomNum == 1) {
            System.out.println("What?");
        }
        else {
            System.out.println("I don't know that word.");
        }
    }

    public static void printLocation(String input, Location location) {
        if (!location.visited || input.equals("look")) {
            System.out.println(location.description);
            // Don't print if no items at location or at granite room and puzzle not taken or
            if(!location.items.isEmpty()) {
                if (!(location instanceof GraniteRoom) || ((GraniteRoom) location).puzzleTaken) {
                    if (!(location instanceof RubyOnRails) || ((RubyOnRails) location).rubyTaken) {
                        for (Item item : location.items) {
                            if (!item.locationPrint.equals("")) {
                                System.out.println(item.locationPrint);
                            }
                        }
                    }
                }
            }
            location.visited = true;
        }
        else {
            System.out.println(location.shortDescription);
            for (Item item : location.items) {
                System.out.println(item.locationPrint);
            }
        }
    }

    public void get(String input, Location location, ArrayList<Item> inventory) {
        // Check for whether requested item is in inventory or at location already
        MineEntrance mineEntrance = new MineEntrance();
        boolean itemInInventory = Actions.isItemHere(input, inventory);
        boolean itemToGetAtLocation = Actions.isItemHere(input, location.items);
        Item jar = Actions.getItemByName("jar", inventory);
        boolean jarInInventory = Actions.isItemHere("jar", inventory);
        boolean goldAtLocation = Actions.isItemHere("gold", location.items);
        //Corner case for mine entrance. If input is "get nails" special stuff happens at mine entrance.
        if(location instanceof MineEntrance && input.equals("nails") && !((MineEntrance) location).nailsOff) {
            mineEntrance.getNails(location);
        }
        // Cases for stuff users might try to get, but aren't allowed to
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
                    Actions.transferItem(inventory, location.items, item);
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
            Item toGet = Actions.getItemByName(input, location.items);
            if(input.equals("cube") && location instanceof GraniteRoom && !((GraniteRoom)location).puzzleTaken) {
                toGet.locationPrint = "There is a plastic cube puzzle lying on the ground";
                ((GraniteRoom)location).puzzleTaken = true;
                location.description = "There is a polished granite pedestal, black as night, in the middle of this room\nwith walls of the same black rock.";
            }
            else if(input.equals("ruby") && location instanceof RubyOnRails && !((RubyOnRails)location).rubyTaken) {
                toGet.locationPrint = "A large ruby lays on the ground";
                ((RubyOnRails)location).rubyTaken = true;
                location.description = "You've reached a dead end. A crumpled mine cart, no longer able to run on the rails, has fallen on its\nside.";
            }
            Actions.transferItem(inventory, location.items, toGet);
            System.out.println("OK");
        }
    }

    public void drop(String input, Location location, ArrayList<Item> inventory) {
        // Items fall out of boat, let Boat class handle dropping if you're in the boat
        if(location instanceof Boat) {
            ((Boat) location).loseItem("drop " + input, inventory);
            return;
        }
        boolean itemInInventory = Actions.isItemHere(input, inventory);
        Item gold = Actions.getItemByName("gold", inventory);
        Item jar = Actions.getItemByName("jar", inventory);
        if(inventory.isEmpty()) {
            System.out.println("You're not carrying anything!");
            return;
        }
        // If you request drop gold, jar must be in inventory.
        else if(input.equals("gold") && itemInInventory && jar != null) {
            Actions.transferItem(location.items, inventory, gold);
            jar.inventoryPrint = "Jar";
            System.out.println("OK");
            return;
        }
        // If you drop the jar the gold drops with it
        else if(input.equals("jar") && itemInInventory && jar != null && gold != null) {
            Actions.transferItem(location.items, inventory, jar);
            jar.inventoryPrint = "Jar";
            Actions.transferItem(location.items, inventory, gold);
            System.out.println("OK");
            return;
        }
        Item item = Actions.getItemByName(input, inventory);
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
                location.description = "You're on a short dam that looks like it created this lake by stopping up a large river. The dam\ngoes north and south along the west end of the lake. Close by is a wheel with its axel extending\ndeep into the dam. Its orange metal is fading to rust except for some other metal at the center,\nshining in the sun. There's a large magnet stuck to this part of the wheel. South leads around the\nlake and to the north there's a set of stairs.";
                Item magnet = Actions.getItemByName("magnet", inventory);
                inventory.remove(magnet);
            }
            // Plain ol' drop item
            else {
                Actions.transferItem(location.items, inventory, item);
                System.out.println("OK");
            }
        }
    }

    // Similar to get but only applies to jar and gold items
    public void fill(String input, Location location, ArrayList<Item> inventory) {
        // Jar must be in inventory to fill
        boolean jarInInventory = Actions.isItemHere("jar", inventory);
        // You can't fill anything other than the jar
        if(input.length() >= 6 && !input.substring(5).equals("jar")) {
            System.out.println("You can't fill that.");
        }
        else if(!jarInInventory) {
            System.out.println("You have nothing to fill.");
        }
        // Else jar is inventory: Can only be filled with gold, change description if it's filled, and add gold to inventory if necessary
        else {
            boolean goldAtLocation = Actions.isItemHere("gold", location.items);
            boolean goldInInventory = Actions.isItemHere("gold", inventory);
            if(goldInInventory) {
                System.out.println("Your jar is already full.");
            }
            else if(!goldAtLocation) {
                System.out.println("There's nothing here to fill the jar with.");
            }
            else {
                Item jar = Actions.getItemByName("jar", inventory);
                jar.inventoryPrint = "Jar full of gold flakes.";
                Item gold = Actions.getItemByName("gold", location.items);
                Actions.transferItem(inventory, location.items, gold);
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
        boolean bowInInventory = Actions.isItemHere("bow", inventory);
        if(!bowInInventory) {
            System.out.println("You have nothing to shoot with.");
        }
        else {
            // You can only shoot the arrow
            boolean arrowInInventory = Actions.isItemHere("arrow", inventory);
            if(!arrowInInventory) {
                System.out.println("You have nothing to shoot");
            }
            else {
                MineEntrance mineEntrance = new MineEntrance();
                if(!(location instanceof MineEntrance) || mineEntrance.nailsOff) {
                    System.out.println("Your arrow goes flying off into the the distance and lands with a soft thud into the ground.");
                    Item arrow = Actions.getItemByName("arrow", inventory);
                    Actions.transferItem(location.items, inventory, arrow);
                }
                else {
                    mineEntrance.shoot(inventory, location);
                }
            }
        }
    }

    public boolean isDirection(String input) {
        return new Graph().allDirections.contains(input);
    }

    // Either add an item to inventory and remove from location or vice versa
    public static void transferItem(ArrayList<Item> listToAddTo, ArrayList<Item> listToRemoveFrom, Item item) {
        listToAddTo.add(item);
        listToAddTo.sort(new Actions());
        listToRemoveFrom.remove(item);
    }

    public static boolean isItemHere(String itemName, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemName)) {
                return true;
            }
        }
        return  false;
    }

    public static Item getItemByName(String itemToFind, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemToFind)) {
                return i;
            }
        }
        return null;
    }
}
