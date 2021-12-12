import java.util.ArrayList;

public class Shed extends Location {

    boolean visited;        // For checking if the shed has been unlocked and opened to know what to print and what items should be in
    boolean unlocked;       // Check if shed has been unlocked. Name and description will change
    boolean opened;         // Check if shed is open. Name, description and items will change
    public Shed(String description, String shortDescription, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, Location location, boolean visited, String name, boolean unlocked, boolean opened) {
        super(description, shortDescription, items, connectingLocations, location, visited, name);
        this.visited = visited;
        this.unlocked = unlocked;
        this.opened = opened;
    }
    public Shed(){}

    // Actions to take when unlock is entered at Shed location
    public void unlock(Shed shed, ArrayList<Item> inventory) {
        // Don't need to do anything if it's already unlocked or opened
        if(shed.unlocked || shed.opened) {
            System.out.println("The shed is already unlocked!");
        }
        // If it isn't unlocked
        else {
            // Check if key is inventory. If it isn't you can't unlock shed. If it is you can
            boolean keyInInventory = Actions.isItemHere("key", inventory);
            if(!keyInInventory) {
                System.out.println("You need a key to unlock the shed.");
            }
            // Key is inventory. Set unlocked to true, change name and description, and print message
            else {
                shed.unlocked = true;
                shed.name = "unlocked shed";
                shed.description = "A cheerful little shed stands with it's lock hanging open with a picnic table to the north.";
                System.out.println("The shed is now unlocked.");
            }
        }
    }

    // Actions to take when open is entered at Shed location
    public void open(Shed shed) {
        if(shed.opened) {
            System.out.println("The shed is already open!");
        }
        else if(!shed.unlocked) {
            System.out.println("You must unlock the shed before opening it.");
        }
        else {
            shed.opened = true;
            shed.description = "You stand before an open shed with a picnic table to the north.";
            Item jar = new Item(2, "There is a jar here.", "Jar", "jar");
            Item bow = new Item(3, "There is a bow here, strung an ready for shooting", "Bow", "bow");
            Item arrow = new Item(4, "There is an arrow here", "Arrow", "arrow");
            Item shovel = new Item(5, "There is a shovel here", "Shovel", "shovel");
            Item hammer = new Item(6, "There is a hammer here", "Hammer", "hammer");
            Item tent = new Item(7, "There is a tent here, neatly packed in a bag.", "Tent in bag", "tent");
            shed.items.add(hammer);
            shed.items.add(jar);
            shed.items.add(bow);
            shed.items.add(arrow);
            shed.items.add(shovel);
            shed.items.add(tent);
            Actions.printLocation("", shed);
        }
    }
}
