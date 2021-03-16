import java.util.*;

public class Main implements Comparator<Item> {

    String abandonedGoldMineDescription;
    String archeryRangeDescription;
    String boatDescription;
    String damDescription;
    String dirtRoadDescription;
    String ditchDescription;
    String drivewayDescription;
    String eastEndOfMainstreetDescription;
    String insideLogCabinDescription;
    String intersectionDescription;
    String lakeDescription;
    String mineEntranceDescription;
    String mineShaftDescription;
    String outsideLogCabinDescription;
    String picnicTableDescription;
    String privatePropertyDescription;
    String shedDescription;
    String topOfHillDescription;
    String topOfStairsDescription;
    String undergroundLakeNorthDescription;
    String undergroundLakeWestDescription;
    String undergroundLakeSWDescription;
    String westEndOfMainstreetDescription;

    ArrayList<String> north;
    ArrayList<String> south;
    ArrayList<String> east;
    ArrayList<String> west;
    ArrayList<String> northeast;
    ArrayList<String> northwest;
    ArrayList<String> southeast;
    ArrayList<String> southwest;
    ArrayList<String> up;
    ArrayList<String> down;
    ArrayList<String> directions;

    Item key;
    Item hammer;
    Item jar;
    Item tent;
    Item bow;
    Item arrow;
    Item gold;
    Item shovel;
    Item magnet;

    ArrayList<String> allVerbs;

    public Main() {
        // All the descriptions of the locations. Some have a second description for if an item is picked up
        abandonedGoldMineDescription = "All around are large piles of tailings that look like they have been puked into this valley. There's\nnot much else to be seen except the entrance to a mine to the south. The shimmering in the west is\ndefinitely a lake and there's a path leading in that direction.";
        archeryRangeDescription = "You step in front of two archery targets made of hay bales and spray-painted circles that are in a\nmakeshift archery range created by a rope tied to four trees in a rectangle around them. There's a\nditch to the east and a long driveway leading west.";
        boatDescription = "You're sitting in a rickety wooden boat in a large underground lake with passages to the north, west,\nnorthwest, and southwest.";
        damDescription = "You're on a short dam that looks like it created this lake by stopping up a large river. The dam goes\nnorth and south along the west end of the lake. Close by is a wheel with it's axel extending deep\ninto the dam. It's orange metal is fading to rust except for some other metal at the center, shining\nin the sun. South leads around the lake and to the north there's a set of stairs."; // TODO add puzzle for unlocking wheel when magnet is dropped then draining lake when wheel is turned
        dirtRoadDescription = "You are on a badly washboarded dirt road in dire need of maintenance that extends far north and runs\nwinding down the hill to the south. Pine forests hug the road on both sides.";
        ditchDescription = "You are in the middle of the forest standing in a small ditch running north and south.";
        drivewayDescription = "You are at the north end of a dirt road surrounded by a forest of pines except for a small gap to\nthe east that exposes a steep, dirt driveway sloping down into the forest. To the south you can see\nthrough the trees and into the valley, thanks to whoever made the road. There you see what might be\nthe shimmering of a lake in the mountain sun.";
        eastEndOfMainstreetDescription = "You are at the east end of an abandon gold mining town's mainstreet. Branching off from this\nstreet to the north is a smaller road and this mainstreet goes back west.";
        insideLogCabinDescription = "You are inside a well-kept log cabin with a huge fireplace on the west wall with a magnificent fire\nburning inside. There's a little sign hanging on the same wall reads \"Portal Room\""; // TODO add an upstairs and maybe a portal room or something like that
        intersectionDescription = "You have reached an intersection in the road. Looking east, the road makes a gradual turn back into\nthe forest and to the west the shimmering look very much like a lake now. Behind you, to the north,\nthe road makes a sharp turn into the forest.";
        mineEntranceDescription = "You've come to the entrance to this abandoned gold mine. The supports on it are looking a little worn\nand there are some loose nails that might come in handy if you could safely get them out of the rotten\nwood. You could enter to the south if you're very careful. Piles of tailings are all over leaving one\npath away from the entrance to the north.";
        mineShaftDescription = "This is the mine shaft. It looks like it could cave in at any moment. If it does, hopefully there's\nanother way out. There's a small wooden sign here that says \"TOMMYKNOCKERS\" across the top. In smaller\nwriting underneath that it says, \"The most important thing you should know about tommyknockers is\nthat, when you hear them knocking on the walls of the mine it means there's about to be a cave-in.\nOne other thing to note is that they like to play tricks on those in the mines. One of their favorites\nis to take miners' possessions.\"";
        outsideLogCabinDescription = "You are in front of a log cabin that looks much less run-down than the rest of the town. Strange that\nsomeone would live here where it looks like no one has been in many years.";
        picnicTableDescription = "A sturdy looking picnic table is in this little clearing you've stepped into and farther south a shed\npeeks through the trees.";
        privatePropertyDescription = "All around you is a sparse pine forest that gives the air a friendly smell. It looks like there's\nnothing to be concerned about on this property since there's no gate, no fence, nothing to keep out\ntrespassers. There's not even a house. This is must be private property though since someone took the\neffort to put in a driveway that continues east and a neat trail leading somewhere southeast.";
        lakeDescription = "You are on the north side of a lake. The water sparkles in the intense sun and you can see far into\nthe clear water but the lake is very deep and there's nothing to see but lake bottom from here.\nThere's a path going east and there's a dam to the south.";
        shedDescription = "Here is a cheerful shed with wood matching that of the picnic table's and it's doors firmly shut and\nlocked, the one and only thing that needs to be on this plot of land.";
        topOfHillDescription = "You are at the top of a steep hill and have a wonderful view of the valley. The road goes around a\nbend to the north and down the hill to the south."; // TODO add more connecting locations
        topOfStairsDescription = "You are at the top of a set of wooden stairs embedded in the hill. A dam is to the north, at the\nbottom of the stairs, and the mainstreet of the abandoned gold mining town stretches east and west.";
        undergroundLakeNorthDescription = "You are on the north side of large underground lake with a rickety wooden boat at the shore. It\nseems odd that the miners tolerated this. There are two passages across the lake from where you\nare standing: one going west and one going southwest. There's a dim light coming from around a\ncorner to the east."; // TODO description doesn't match connecting locations. need to add 3 connecting locations
        undergroundLakeWestDescription = "You are on the west side of a large underground lake. There are passages to the south an sw across\nthe lake. The tunnel you're in now continues to the west.";
        undergroundLakeSWDescription = "You are on the sw side of a large underground lake. There are passages to the north and west across\nthe lake and a tunnel continues south from here.";
        westEndOfMainstreetDescription = "You are at the west and of an abandoned gold mining town's main street.";
        // Arraylists that contain all possible, valid inputs for directions. Use for location objects in arraylist of string arraylists
        north = new ArrayList<>(Arrays.asList("north", "n", "go north", "go n", "walk north", "walk n", "run north", "run n"));
        south = new ArrayList<>(Arrays.asList("south", "s", "go south", "go s", "walk south", "walk s", "run south", "run s"));
        east = new ArrayList<>(Arrays.asList("east", "e", "go east", "go e", "walk east", "walk e", "run east", "run e"));
        west = new ArrayList<>(Arrays.asList("west", "w", "go west", "go w", "walk west", "walk w", "run west", "run w"));
        northeast = new ArrayList<>(Arrays.asList("ne", "go ne", "walk ne", "run ne"));
        northwest = new ArrayList<>(Arrays.asList("nw", "go nw", "walk nw", "run nw"));
        southeast = new ArrayList<>(Arrays.asList("se", "go se", "walk se", "run se"));
        southwest = new ArrayList<>(Arrays.asList("sw", "go sw", "walk sw", "run sw"));
        up = new ArrayList<>(Arrays.asList("up", "u", "go up", "go u", "walk up", "walk u", "run u", "run up"));
        down = new ArrayList<>(Arrays.asList("down", "d", "go down", "go d", "walk down", "walk d", "run down", "run d"));
        // One big arraylist containing all possible direction commands to check if the entered command was a direction
        directions = new ArrayList<>(Arrays.asList("north", "n", "go north", "go n", "walk north", "walk n", "run north", "run n",
                "south", "s", "go south", "go s", "walk south", "walk s", "run south", "run s",
                "east", "e", "go east", "go e", "walk east", "walk e", "run east", "run e",
                "west", "w", "go west", "go w", "walk west", "walk w", "run west", "run w",
                "ne", "go ne", "walk ne", "run ne",
                "nw", "go nw", "walk nw", "run nw",
                "se", "go se", "walk se", "run se",
                "sw", "go sw", "walk sw", "run sw",
                "up", "u", "go up", "go u", "walk up", "walk u", "run u", "run up",
                "down", "d", "go down", "go d", "walk down", "walk d", "run down", "run d",
                "in", "out"));

        key = new Item(1, "There is a shiny key here", "Shiny key", "key");
        hammer = new Item(2, "There is a hammer here", "Hammer", "hammer");
        bow = new Item(3, "There is a bow here, strung an ready for shooting", "Bow", "bow");
        arrow = new Item(4, "There is an arrow here", "Arrow", "arrow");
        jar = new Item(5, "There is a jar here.", "Jar", "jar");
        gold = new Item(6, "There are some gold flakes on the ground here", "Gold flakes in jar", "gold");
        shovel = new Item(8, "There is a shovel here", "Shovel", "shovel");
        tent = new Item(9, "There is a tent here, packed neatly in a bag.", "Tent in bag", "tent");
        magnet = new Item(10, "There is a thick, circular magnet here, about the size of your palm.", "Magnet", "magnet");

        // Lists of all valid verbs that the player can enter that are not directions.
        allVerbs = new ArrayList<>(Arrays.asList("get", "drop", "inventory", "look", "throw", "open", "close", "unlock", "jqkkq", "turn", "shoot", "fill"));
    }

    public static void main(String[] args) {
        Main m = new Main();
        // Locations for graph
        Location abandonedGoldMine = new Location(m.abandonedGoldMineDescription, new ArrayList<>(), new ArrayList<>(), false, "abandoned gold mine");
        Location archeryRange = new Location(m.archeryRangeDescription, new ArrayList<>(), new ArrayList<>(), false, "archery range");
        Location boat = new Location(m.boatDescription, new ArrayList<>(), new ArrayList<>(), false, "boat");
        Location dirtRoad = new Location(m.dirtRoadDescription, new ArrayList<>(), new ArrayList<>(), false, "dirt road");
        Location ditch = new Location(m.ditchDescription, new ArrayList<>(Collections.singletonList(m.key)), new ArrayList<>(), false, "ditch");
        Location driveway = new Location(m.drivewayDescription, new ArrayList<>(), new ArrayList<>(), false, "driveway");
        Location eastEndOfMainstreet = new Location(m.eastEndOfMainstreetDescription, new ArrayList<>(), new ArrayList<>(), false, "east end of mainstreet");
        Location insideLogCabin = new Location(m.insideLogCabinDescription, new ArrayList<>(Collections.singletonList(m.magnet)), new ArrayList<>(), false, "inside log cabin");
        Location intersection = new Location(m.intersectionDescription, new ArrayList<>(), new ArrayList<>(), false, "intersection");
        Location lake = new Location(m.lakeDescription, new ArrayList<>(), new ArrayList<>(), false, "lake");
        Location mineShaft = new Location(m.mineShaftDescription, new ArrayList<>(), new ArrayList<>(), false, "mine shaft");
        Location outsideLogCabin = new Location(m.outsideLogCabinDescription, new ArrayList<>(), new ArrayList<>(), false, "outside log cabin");
        Location picnicTable = new Location(m.picnicTableDescription, new ArrayList<>(), new ArrayList<>(), false, "picnic table");
        Location privateProperty = new Location(m.privatePropertyDescription, new ArrayList<>(), new ArrayList<>(), false, "private property");
        Location topOfHill = new Location(m.topOfHillDescription, new ArrayList<>(), new ArrayList<>(), false, "top of hill");
        Location topOfStairs = new Location(m.topOfStairsDescription, new ArrayList<>(), new ArrayList<>(), false, "top of stairs");
        Location undergroundLakeNorth = new Location(m.undergroundLakeNorthDescription, new ArrayList<>(), new ArrayList<>(), false, "north side of underground lake");
        Location undergroundLakeWest = new Location(m.undergroundLakeWestDescription, new ArrayList<>(), new ArrayList<>(), false, "west side of underground lake");
        Location undergroundLakeSW = new Location(m.undergroundLakeSWDescription, new ArrayList<>(), new ArrayList<>(), false, "sw side of underground lake");
        Location westEndOfMainstreet = new Location(m.westEndOfMainstreetDescription, new ArrayList<>(), new ArrayList<>(), false, "west end of mainstreet");
        Dam dam = new Dam(m.damDescription, new ArrayList<>(), new ArrayList<>(), false, "dam", false, false);
        Shed shed = new Shed(m.shedDescription, new ArrayList<>(), new ArrayList<>(), false, "shed", false, false);
        MineEntrance mineEntrance = new MineEntrance(m.mineEntranceDescription, new ArrayList<>(Collections.singletonList(m.gold)), new ArrayList<>(), false, "mine entrance", false);
        // Add locations to connectingLocations arraylist parameter of Location to create graph
        // Some ConnectingLocations have in and out as directions since that will get you to another location in addition to entering a direction
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.south, mineEntrance));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.west, lake));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.east, intersection));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.west, privateProperty));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.east, ditch));
        boat.connectingLocations.add(new ConnectingLocation(m.north, undergroundLakeNorth));
        boat.connectingLocations.add(new ConnectingLocation(m.west, undergroundLakeWest));
        boat.connectingLocations.add(new ConnectingLocation(m.southwest, undergroundLakeSW));
        dam.connectingLocations.add(new ConnectingLocation(m.north, lake));
        dam.connectingLocations.add(new ConnectingLocation(m.south, topOfStairs));
        dam.connectingLocations.add(new ConnectingLocation(m.up, topOfStairs));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.north, driveway));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.south, intersection));
        driveway.connectingLocations.add(new ConnectingLocation(m.south, dirtRoad));
        ditch.connectingLocations.add(new ConnectingLocation(m.west, archeryRange));
        driveway.connectingLocations.add(new ConnectingLocation(m.south, dirtRoad));
        driveway.connectingLocations.add(new ConnectingLocation(m.east, privateProperty));
        driveway.connectingLocations.add(new ConnectingLocation(m.down, privateProperty));
        eastEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.west, topOfStairs));
        eastEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.north, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.east, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("out")), outsideLogCabin));
        intersection.connectingLocations.add(new ConnectingLocation(m.north, dirtRoad));
        intersection.connectingLocations.add(new ConnectingLocation(m.east, topOfHill));
        intersection.connectingLocations.add(new ConnectingLocation(m.west, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.east, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.south, dam));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.north, mineEntrance));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.south, undergroundLakeNorth));
        mineShaft.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("out")), mineEntrance));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.north, abandonedGoldMine));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.south, mineShaft));
        mineEntrance.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), mineShaft));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.south, eastEndOfMainstreet));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.west, insideLogCabin));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), insideLogCabin));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.north, privateProperty));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.south, shed));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.west, driveway));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.east, archeryRange));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.southeast, picnicTable));
        shed.connectingLocations.add(new ConnectingLocation(m.north, picnicTable));
        topOfHill.connectingLocations.add(new ConnectingLocation(m.west, intersection));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.north, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.down, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.east, eastEndOfMainstreet));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.west, westEndOfMainstreet));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(m.east, mineShaft));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
//        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(m.south, somelocation));
        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
//        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(m.west, somelocation));
        westEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.east, topOfStairs));

        // Arraylist of Items for when you get an Item from a location and a Scanner for input from keyboard
        ArrayList<Item> inventory = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        // First location is driveway. Change for debugging
        Location currentLocation = driveway;
//        Location currentLocation = shed;
//        mineEntrance.items.add(m.jar);
//        mineEntrance.items.add(m.bow);
//        mineEntrance.items.add(m.arrow);
//        mineEntrance.items.sort(m);
//        shed.items.add(m.key);
        currentLocation.visited = true;

        // Game introduction
        System.out.println("You're on an unnamed mountain with a rumored unnamed, abandoned gold mining town nearby. One of the\ngold mines might also be accessible somewhere around, but before you came here you heard that\ntommyknockers are in the mines here. You don't know much about them but the name doesn't give off\nthe most friendly vibes.\n");
        System.out.println("At the moment you stand at the north end of a dirt road surrounded by a forrest of pines except for\na small gap to the east that exposes a steep, dirt driveway sloping down into the forest. To the\nsouth you can see through the trees and into the valley, thanks to whoever made the road. There you\nsee what might be the shimmering of a lake in the mountain sun.");

        // Input will always be lowercase so don't have to worry about forgetting to check .equalsIgnoreCase
        String input = scan.nextLine().toLowerCase();
        // Always loop for main game play
        while(true) {
            // Check for input equals quit. End program if answer to question is yes. If answer is no, continue game.
            // If it's anything else print you must answer the question
            if(input.equals("quit")) {
                System.out.println("Are you sure you want to quit?");
                input = scan.nextLine().toLowerCase();
                while(!(input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n"))) {
                    System.out.println("Please answer the question.");
                    input = scan.nextLine().toLowerCase();
                }
                if(input.equals("yes") || input.equals("y")) {
                    System.out.println("Quitted");
                    break;
                }
                else {
                    System.out.println("OK");
                    input = scan.nextLine().toLowerCase();
                    continue;
                }
            }
            if(m.isDirection(input)) {
                // Loop through legal directions in the current location and compare to see if you can go that way
                for (int i = 0; i < currentLocation.connectingLocations.size(); i++) {
                    // It's a legal direction. Change current location and decide what to print based on visited or not
                    if (currentLocation.connectingLocations.get(i).directions.contains(input) && !currentLocation.equals(currentLocation.connectingLocations.get(i))) {
                        currentLocation = currentLocation.connectingLocations.get(i).location;
                        if((input.equals("in") || input.equals("out") && currentLocation instanceof Boat)) {
                            inBoat(input, currentLocation);
                        }
                        if (!currentLocation.visited) {
                            currentLocation.visited = true;
                            printLocation(currentLocation);
                        }
                        else {
                            System.out.println("You're at " + currentLocation.name + ".");
                            for (Item item : currentLocation.items) {
                                System.out.println(item.locationPrint);
                            }
                        }
                        break;
                    }
                    // It isn't a legal direction for current location
                    else if (i == currentLocation.connectingLocations.size() - 1) {
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
            }
            // Now know input isn't a direction. Separate first word from the last to be able to compare that verb to the arrayLists
            else {
                String inputCheck;
                String[] inputArray = input.split(" ");
                inputCheck = inputArray[0];
                // Is input a generic verb that can be applied at any location?
                if(m.allVerbs.contains(inputCheck) || (input.length() >= 5 && "inventory".contains(input))) {
                    findAction(input.toLowerCase(), currentLocation, inventory);
                }
                //If input isn't a legal verb, direction, or if it's "in" or "out" + another word, print one of 3 Strings to let user know it's not a legal input
                else if(!(m.allVerbs.contains(inputCheck) || m.directions.contains(inputCheck)) || ((inputCheck.equals("out") || inputCheck.equals("in") || input.equals("unlock") || input.equals("open")) && inputArray.length >= 2)) {
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
            }
            // input will always be lowercase so don't have to worry about checking .equalsIgnoreCase
            input = scan.nextLine().toLowerCase();
        }
    }

    public boolean isDirection(String input) {
        return new Main().directions.contains(input);
    }

    // For sorting items in inventory and at location so they print in the same order
    @Override
    public int compare(Item i1, Item i2) {
        return i1.order - i2.order;
    }

    // Format for printing Locations
    public static void printLocation(Location location) {
        System.out.println(location.description);
        if(!location.items.isEmpty()) {
            for(Item item : location.items) {
                System.out.println(item.locationPrint);
            }
        }
    }

    // Every time I add an Item to inventory I remove that thing from the location and vice versa. So this method does both of those and
    // sorts whatever arraylist got something added to it.
    public static void addAndRemove(ArrayList<Item> listToAddTo, ArrayList<Item> listToRemoveFrom, Item itemToAddAndRemove) {
        listToAddTo.add(itemToAddAndRemove);
        listToAddTo.sort(new Main());
        listToRemoveFrom.remove(itemToAddAndRemove);
    }

    // Check if an Item is in either inventory or the location depending on what's passed as parameter. Find out by looping through the list
    // and comparing the name of the Item to the input. Return true if it's in the list, false otherwise
    public static boolean isItemHere(String itemName, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemName)) {
                return true;
            }
        }
        return  false;
    }

    // Similar to isItemHere but returning an Item if it's there or null if it isn't instead of a boolean
    public static Item findItem(String itemToFind, ArrayList<Item> listToSearch) {
        for(Item i : listToSearch) {
            if(i.name.equals(itemToFind)) {
                return i;
            }
        }
        return null;
    }

    // Find what to print and what action to take if input is a generic verb
    // input is coming in as lowercase so don't need .equalsIgnoreCase in this or any other method
    public static void findAction(String input, Location location, ArrayList<Item> inventory) {
        String[] countWords = input.split(" ");
        if(countWords.length >= 3) {
            System.out.println("Try to stick to one or two word commands.");
        }
        else if(input.substring(0, 3).equals("get")) {
            // Corner case if only get was entered
            if(input.equals("get") || input.equals("get ")) {
                System.out.println("What do you want to get?");
                return;
            }
            if(input.substring(0, 3).equals("get")) {
                location.get(input.substring(4), location, inventory);
            }
        }
        else if(input.equals("fill")) {
            fill(input, location, inventory);
        }
        else if(input.substring(0, 4).equals("drop")) {
            // Corner case if nothing was entered to drop
            if(input.equals("drop") || input.equals("drop ")) {
                System.out.println("What do you want to drop?");
                return;
            }
            location.drop(input.substring(5), location, inventory);
        }
        else if(input.length() >= 5 && input.substring(0, 5).equals("shoot")) {
            shoot(input, location, inventory);
        }
        else if(input.length() >= 6 && input.substring(0, 6).equals("unlock")) {
            unlock(input, location, inventory);
        }
        else if(input.length() >= 4 && input.substring(0, 4).equals("open")) {
            open(input, location, inventory);
        }
        else if(input.length() >= 5 && "inventory".contains(input)) {
            inventory(inventory);
        }
        else if(input.equals("look")) {
            printLocation(location);
        }
    }

    /*public static void drop(String input, Location location, ArrayList<Item> inventory) {
        // boolean to check if the item is in inventory and items for jar and gold since they require special cases for getting and dropping
        // If you have gold in inventory and you drop jar, then remove both gold and jar from inventory since gold is in jar
        // If you drop just gold, keep jar and remove gold
        boolean itemInInventory = isItemHere(input, inventory);
        Item gold = findItem("gold", inventory);
        Item jar = findItem("jar", inventory);
        if(inventory.isEmpty()) {
            System.out.println("You're not carrying anything!");
            return;
        }
        // If you drop gold, change parameter inventoryPrint back to "Jar" since it no longer has gold in it
        else if(input.equals("gold") && itemInInventory) {
            addAndRemove(location.items, inventory, gold);
            jar.inventoryPrint = "Jar";
            System.out.println("OK");
            return;
        }
        // If you drop the jar the gold drops with it so remove gold and jar from inventory and add them to location items
        else if(input.equals("jar") && itemInInventory && jar != null && gold != null) {
            addAndRemove(location.items, inventory, jar);
            jar.inventoryPrint = "Jar";
            addAndRemove(location.items, inventory, gold);
            System.out.println("OK");
            return;
        }
        // Find the item in inventory and add it to location items then remove from inventory
        Item item = findItem(input, inventory);
        if(item == null) {
            // If findItem() returns null, the item isn't in inventory so say so
            System.out.println("You don't have that!");
        }
        // else if, check if findItem() returned the requested item to drop
        else if(item.name.equals(input)) {
            addAndRemove(location.items, inventory, item);
            System.out.println("OK");
            return;
        }
    }*/

    // Print Items in inventory or nothing if inventory is empty
    public static void inventory(ArrayList<Item> inventory) {
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

    // If input is shoot determine what action to take and what to print
    public static void shoot(String input, Location location, ArrayList<Item> inventory) {
        boolean bowInInventory = isItemHere("bow", inventory);
        if(!bowInInventory && input.startsWith("shoot")) {
            System.out.println("You have nothing to shoot with.");
        }
        // If bow is in inventory you can do something. I have to check more things
        else {
            boolean arrowInInventory = isItemHere("arrow", inventory);
            if(input.startsWith("shoot") && !arrowInInventory) {
                System.out.println("You have nothing to shoot");
            }
            else if(input.length() >= 7 && !input.substring(6).equals("arrow")) {
                System.out.println("You can't shoot that");
            }
            else {
                MineEntrance mineEntrance = new MineEntrance();
                if(!(location instanceof MineEntrance) || (location instanceof MineEntrance && mineEntrance.nailsOff)) {
                    System.out.println("Your arrow goes flying off into the the distance and lands with a soft thud into the ground.");
                    Item arrow = findItem("arrow", inventory);
                    addAndRemove(location.items, inventory, arrow);
                }
                else {
                    mineEntrance.shoot(inventory, location);
                }
            }
        }
    }

    // Similar to get but only applies to jar and gold items
    public static void fill(String input, Location location, ArrayList<Item> inventory) {
        // Find out if jar is in inventory. If it isn't you can't get gold
        boolean jarInInventory = isItemHere("jar", inventory);
        // You can't fill anything other than the jar, so if input is "fill (something other than jar)" tell user you can't fill that
        if(input.length() >= 6 && !input.substring(5).equals("jar")) {
            System.out.println("You can't fill that.");
        }
        // If jar isn't in inventory you have nothing to fill
        else if(!jarInInventory) {
            System.out.println("You have nothing to fill.");
        }
        // Else jar is inventory: if gold isn't at location you can't fill the jar with anything
        // If gold is at location all the stuff happens: change description of jar to "Jar full of gold flakes", add gold
        // to inventory and remove from location
        else {
            boolean goldAtLocation = isItemHere("gold", location.items);
            if(!goldAtLocation) {
                System.out.println("There's nothing here to fill the jar with.");
            } else {
                Item jar = findItem("jar", inventory);
                jar.inventoryPrint = "Jar full of gold flakes.";
                Item gold = findItem("gold", location.items);
                addAndRemove(inventory, location.items, gold);
                System.out.println("The jar is now full of gold flakes.");
            }
        }
    }

    // Can only unlock the shed if you have the key. If anywhere else say there's nothing here to unlock
    public static void unlock(String input, Location location, ArrayList<Item> inventory) {
        if(!(location instanceof Shed)) {
            System.out.println("There's nothing here to unlock.");
        }
        else {
            if(input.equals("unlock") || input.equals("unlock shed")) {
                ((Shed) location).unlock(((Shed) location), inventory);
            }
            else {
                System.out.println("That's not something you can unlock.");
            }
        }
    }

    // What to do when open is entered. Only applicable for shed
    public static void open(String input, Location location, ArrayList<Item> inventory) {
        if(!(location instanceof Shed)) {
            System.out.println("There's nothing here to open.");
        }
        else {
            if(input.equals("open") || input.equals("open shed")) {
                ((Shed) location).open(((Shed) location));
            }
            else {
                System.out.println("That's not something you can open.");
            }
        }
    }

    //
    public static void inBoat(String input, Location location) {
        Main m = new Main();
        if(location instanceof Boat) {
            if (input.equals("in")) {
                // Trying to get in something other than the boat
                if (input.length() >= 4 && !input.substring(3).equals("boat")) {
                    System.out.println("That's not something you can get in.");
                }
            }
        }
    }

    // Drain lake when turn is entered. Only applicable for wheel at dam
//    public static void turn(String input, Location location) {
//
//    }
}
