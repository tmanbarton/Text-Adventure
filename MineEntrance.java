import java.util.ArrayList;
import java.util.Scanner;

public class MineEntrance extends Location {

    boolean screwsOff;  // Small old wash plant is needs this parameter because if the loose screws have
                        // been knocked off then it has to be checked that they are knocked off so they
                        // aren't able to be knocked off over and over

    public MineEntrance(String description, ArrayList<Item> items, ArrayList<ConnectingLocation> connectingLocations, boolean visited, String name, boolean screwsOff) {
        super(description, items, connectingLocations, visited, name);
        this.screwsOff = screwsOff;
    }

    public MineEntrance() {}

    // If you try to get screws if they haven't been knocked off yet
    public void screws(Location location) {
        // First ask if you actually want to get them. If you do get them you die. If not you don't. Loop until they answer the question
        if (!((MineEntrance) location).screwsOff) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Are you sure you want to get the screws? The structure is very fragile and may fall apart and onto\nyou.");
            // input will always be lowercase so don't have to worry about checking .equalsIgnoreCase
            String input = scan.nextLine().toLowerCase();
            while (!(input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n"))) {
                System.out.println("Please answer the question.");
                input = scan.nextLine().toLowerCase();
            }
            if (input.equals("yes") || input.equals("y")) {
                System.out.println("OK. I warned you.\nYou walk up to the wooden supports and start to remove the loose screws and, before you can even get\nthem out, there is a loud crack and the support you were working on snaps and the ceiling comes\ncrashing down on top of you. Unfortunately being crushed by a mountain and old wood is very\ndangerous, thus this decision has cost you your life.");
                System.exit(0);
            } else {
                System.out.println("Wise choice.");
            }
        }
        else {
            System.out.println("OK");
        }
    }

    // Shooting arrow at mine entrance
    public void shoot(ArrayList<Item> inventory, Location location) {
        // If screws are still on, print message, remove arrow from inventory add it to location, add screws to location, set screws off to
        // true, remove mine shaft from connecting locations since it's now blocked off by a caved in entrance, and change description to say that
        // the entrance has caved in.
        if(!(((MineEntrance) location).screwsOff)) {
            System.out.println("You shoot the arrow and the screws go flying off with a small ringing sound as your arrow glances\noff of them. The screws and your arrow land a few feet away then there's a loud crack of the support\nand the entrance caves in with an even loud crash and cloud of dust. Good thing you didn't try to\ntake them by hand.");
            Item arrow = Main.findItem("arrow", inventory);
            Main.addAndRemove(location.items, inventory, arrow);
            Item screws = new Item(7, "There are some screws scattered on the ground here", "Some screws", "screws");
            location.items.add(screws);
            ((MineEntrance) location).screwsOff = true;
            // Find mine shaft in connecting locations and remove it
            for(ConnectingLocation cl : location.connectingLocations) {
                if(cl.location.name.equals("mine shaft")) {
                    location.connectingLocations.remove(cl);
                    break;
                }
            }
            location.description = "You've come to the entrance to the abandoned gold mine. Looks like it had a recent cave-in. There's\nno way you're getting in there now. Piles of tailings are all over leaving one path away from the\nentrance to the north.";
        }
        // If screws are already off, print message and remove arrow from inventory and add to location.
        else {
            System.out.println("Your arrow goes flying off into the the distance and lands with a soft thud into the ground.");
            Item arrow = Main.findItem("arrow", inventory);
            Main.addAndRemove(location.items, inventory, arrow);
        }
    }
}
