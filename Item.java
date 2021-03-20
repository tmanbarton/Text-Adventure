
public class Item {

    int order;                  // Determine the order to be printed. Will always be the same
    String locationPrint;       // What will be printed when an item is in a description
    String inventoryPrint;      // What will be printed when an item is in inventory
    String name;

    public Item(int order, String locationPrint, String inventoryPrint, String name) {
        this.order = order;
        this.locationPrint = locationPrint;
        this.inventoryPrint = inventoryPrint;
        this.name = name;
    }
}
