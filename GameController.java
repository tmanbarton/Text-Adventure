import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    public GameController() {}

    public void gameIntro(ArrayList<Item> inventory, Location currentLocation) {
        // Game introduction
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome! Would you like some help?");   //TODO Help command
        String input = scan.nextLine().toLowerCase();
        input = answerQuestion(input, scan);
        if (input.equals("yes") || input.equals("y")) {
            System.out.println("You're on a mountain with an abandoned gold mining town somewhere nearby. You've heard that there\nare gold mines still accessible, but there have been stories of local miners seeing tommyknockers\nin them. I will be your eyes and hands. Use one or two word commands to guide me. If you would like\nfurther help, type \"help\".\n");
        } else if (input.equals("no") || input.equals("n")) {
            System.out.println("OK");
        }
        System.out.println(currentLocation.description);
        input = scan.nextLine().toLowerCase();
        mainGameplay(input, currentLocation, inventory);
    }

    public void mainGameplay(String input, Location currentLocation, ArrayList<Item> inventory) {
        // Main game play
        Scanner scan = new Scanner(System.in);
        while(true) {
            if(input.equals("quit")) {
                if(confirmQuit(input, scan)) {
                    break;
                }
                else {
                    input = scan.nextLine().toLowerCase();
                    continue;
                }
            }
            Actions actions = new Actions();

            if(actions.isDirection(input)) {
                currentLocation = move(input, currentLocation);
            }
            else {
                new Actions().takeAction(input, currentLocation, inventory);
            }
            // input will always be lowercase so don't have to worry about checking .equalsIgnoreCase
            if(scan.hasNextLine()) {
                input = scan.nextLine().toLowerCase();
            }
        }
    }

    public static String answerQuestion(String input, Scanner scan) {
        while (!(input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n"))) {
            System.out.println("Please answer the question.");
            input = scan.nextLine().toLowerCase();
        }
        return input;
    }

    public static boolean confirmQuit(String input, Scanner scan) {
        System.out.println("Are you sure you want to quit?");
        input = scan.nextLine().toLowerCase();
        while (!(input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n"))) {
            System.out.println("Please answer the question.");
            input = scan.nextLine().toLowerCase();
        }
        if (input.equals("yes") || input.equals("y")) {
            System.out.println("Quitted");      //TODO have a better quit message
            return true;
        } else {
            System.out.println("OK");
            return false;
        }
    }

    public Location move(String input, Location currentLocation) {
        // Loop through legal directions in the current location and compare to see if you can go that way
        ArrayList<ConnectingLocation> currentConnections = currentLocation.connectingLocations;
        for (int i = 0; i < currentLocation.connectingLocations.size(); i++) {
            ArrayList<String> directions = currentConnections.get(i).directions;
            Location connectingLocation = currentConnections.get(i).location;

            if (directions != null && (directions.contains(input) && !currentLocation.equals(connectingLocation)) || ((input.equals("back") || input.equals("go back")) && !currentLocation.previousLocation.equals(currentLocation)) || (input.equals("out") && currentLocation instanceof Boat)) {
                if(input.equals("back") || input.equals("go back") || (input.equals("out") && currentLocation instanceof Boat)) {
                    currentLocation = currentLocation.previousLocation;
                    printLocation(input, currentLocation);
                    break;
                }
                // Update previous location and current location. Only allowed to go back once, so
                // Location.previous.previous is set to itself.
                Location previous = currentLocation;
                currentLocation = connectingLocation;
                currentLocation.previousLocation = previous;
                currentLocation.previousLocation.previousLocation = previous;
                printLocation(input, currentLocation);
                break;
            }
            // It isn't a legal direction for current location
            else if (i == currentConnections.size() - 1) {
                if(input.equals("in")) {
                    System.out.println("You can't get in anything here");
                }
                else if(input.equals("out")) {
                    System.out.println("You can't get out of anything here");
                }
                else {
                    System.out.println("You can't go that way");
                }
            }
        }
        return currentLocation;
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
}
