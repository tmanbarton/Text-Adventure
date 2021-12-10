import java.util.*;

public class Main implements Comparator<Item> {
//    private ArrayList<Item> inventory = new ArrayList<>();
//    private Location currentLocation = new Location();

    public Main() {}

    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<>();
        Location currentLocation = new Location();
        new Main().mainGamePlay(inventory, currentLocation);
    }

    public void mainGamePlay(ArrayList<Item> inventory, Location currentLocation) {
        Graph graph = new Graph();
        scrambleCube();
//        currentLocation = graph.driveway;
        currentLocation = graph.dam;
        System.out.println(currentLocation);
//        this.inventory.add(m.cube);
        currentLocation.items.add(new Item(10, "There is a thick, circular magnet here, about the size of your palm.", "Magnet", "magnet"));
//        dam.items.add(m.jar);
        currentLocation.previousLocation = currentLocation;
        currentLocation.visited = true;
        Scanner scan = new Scanner(System.in);

        // Game introduction
        System.out.println("Welcome! Would you like some help?");   //TODO Help command
        String input = scan.nextLine().toLowerCase();
        input = answerQuestion(input, scan);
        if(input.equals("yes") || input.equals("y")) {
            System.out.println("You're on a mountain with an abandoned gold mining town somewhere nearby. You've heard that there\nare gold mines still accessible, but there have been stories of local miners seeing tommyknockers\nin them. I will be your eyes and hands. Use one or two word commands to guide me. If you would like\nfurther help, type \"help\".\n");
        }
        else if(input.equals("no") || input.equals("n")) {
            System.out.println("OK");
        }
        System.out.println(currentLocation.description);
        input = scan.nextLine().toLowerCase();
        // Main game play
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
            Main main = new Main();

            if(main.isDirection(input)) {
                currentLocation = main.move(input, currentLocation);
            }
            else {
                main.takeAction(input, scan, currentLocation, inventory);
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

    public static void scrambleCube() {
        Graph graph = new Graph();
        char[] top = {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'};
        char[] front = {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'};
        char[] right = {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'};
        char[] back = {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        char[] left = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] bottom = {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        char[][] charCube = {top, front, right, back, left, bottom};
        graph.cube.charCube = charCube;
        int[] turns = ScrambleGenerator.generateScramble();
        String algorithm = ScrambleGenerator.writeScramble(turns);
        Cube.findTurn(charCube, algorithm);
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
            if (currentConnections.get(i).directions != null && (currentConnections.get(i).directions.contains(input) && !currentLocation.equals(currentConnections.get(i).location)) || ((input.equals("back") || input.equals("go back")) && !currentLocation.previousLocation.equals(currentLocation)) || (input.equals("out") && currentLocation instanceof Boat)) {
                if(input.equals("back") || input.equals("go back") || (input.equals("out") && currentLocation instanceof Boat)) {
                    currentLocation = currentLocation.previousLocation;
                    printLocation(input, currentLocation);
                    break;
                }
                // Update previous location and current location. Only allowed to go back once, so
                // Location.previous.previous is set to itself.
                Location previous = currentLocation;
                currentLocation = currentConnections.get(i).location;
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

    public void takeAction(String input, Scanner scan, Location location, ArrayList<Item> inventory) {
        Graph graph = new Graph();
        String inputCheck;
        String[] inputArray = input.split(" ");
        inputCheck = inputArray[0];
        // Is input a generic verb that can be applied at any location?
        if(graph.allVerbs.contains(inputCheck) || (input.length() >= 5 && "inventory".contains(input))) {
            findAction(input.toLowerCase(), location, inventory);
        }
        //If input isn't a legal verb, direction, or if it's "in" or "out" + another word, print one of 3 Strings to let user know it's not a legal input
        else if(!(graph.allVerbs.contains(inputCheck) || graph.allDirections.contains(inputCheck)) || ((inputCheck.equals("out") || inputCheck.equals("in") || input.equals("unlock") || input.equals("open") || input.equals("shoot")) && inputArray.length >= 2)) {
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

    public boolean isDirection(String input) {
        return new Graph().allDirections.contains(input);
    }

    // For sorting items in inventory and at location so they print in the same order
    @Override
    public int compare(Item item1, Item item2) {
        return item1.order - item2.order;
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

    // Either add an item to inventory and remove from location or vice versa
    public static void addAndRemove(ArrayList<Item> listToAddTo, ArrayList<Item> listToRemoveFrom, Item itemToAddAndRemove) {
        listToAddTo.add(itemToAddAndRemove);
        listToAddTo.sort(new Main());
        listToRemoveFrom.remove(itemToAddAndRemove);
    }

    public static boolean isItemHere(String itemName, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemName)) {
                return true;
            }
        }
        return  false;
    }

    public static Item findItem(String itemToFind, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemToFind)) {
                return i;
            }
        }
        return null;
    }

    // Parse input and, based on first word, decide what action to take
    public void findAction(String input, Location location, ArrayList<Item> inventory) {
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
                location.get(input.substring(4), location, inventory);
            }
        }
        else if(input.startsWith("fill")) {
            location.fill(input, location, inventory);
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
                location.drop(input.substring(5), location, inventory);
            }
            else if (input.startsWith("throw")) {
                location.drop(input.substring(6), location, inventory);
            }
        }
        else if(input.equals("shoot")) {
            location.shoot(input, location, inventory);
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
                new Graph().cube.rubiksCubeSimulator(findItem("cube", inventory));
                printLocation(input, location);
            }
        }
        else {
            dontKnowWord();
        }
    }
}
