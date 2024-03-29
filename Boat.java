import java.util.ArrayList;

public class Boat extends Location {
    boolean inBoat;

    public Boat(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean inBoat) {
        super(description, shortDescription, items, connectingLocations, location, visited, name);
        this.inBoat = inBoat;
    }
    public Boat(){}

    // Don't drop an item or shoot the arrow in the boat or it will be lost forever.
    public void loseItem(String input, ArrayList<Item> inventory) {
        Item arrow = Actions.getItemByName("arrow", inventory);
        if(input.equals("shoot") || input.equals("shoot arrow")) {
            System.out.println("Your arrow goes flying off into the the distance and splashes into the water, never to be found again.");
            inventory.remove(arrow);
        }
        else if(input.startsWith("drop")) {
            String itemName = input.substring(5);
            Item item = Actions.getItemByName(itemName, inventory);
            if(itemName.equals("jar") && Actions.isItemHere("gold", inventory)) {
                System.out.println("Your jar and gold splash into the water next to the boat and sinks to the bottom, never to be found\nagain.");
                Item jar = Actions.getItemByName("jar", inventory);
                Item gold = Actions.getItemByName("gold", inventory);
                inventory.remove(jar);
                inventory.remove(gold);
            }
            else if(item != null && inventory.contains(item)) {
                System.out.println("Your " + itemName + " splashes into the water next to the boat and sinks to the bottom, never to be found again.");
                inventory.remove(item);
            }
        }
    }
}
