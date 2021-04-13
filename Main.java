import java.util.*;

public class Main implements Comparator<Item> {

    String abandonedGoldMineDescription;
    String antHillDescription;
    String archeryRangeDescription;
    String boatDescription;
    String damDescription;
    String dirtRoadDescription;
    String ditchDescription;
    String drivewayDescription;
    String eastEndOfMainstreetDescription;
    String footPathDescription;
    String insideLogCabinDescription;
    String intersectionDescription;
    String lakeDescription;
    String lakeTownDescription;
    String lightningTreeDescription;
    String mineEntranceDescription;
    String mineShaftDescription;
    String outsideLogCabinDescription;
    String picnicTableDescription;
    String privatePropertyDescription;
    String shedDescription;
    String topOfHillDescription;
    String topOfStairsDescription;
    String upstairsLogCabin;
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
        abandonedGoldMineDescription = "All around are large piles of tailings that look like they have been puked into this valley.\nThere's not much else to be seen except the entrance to a mine to the south. The shimmering in the\nwest is definitely a lake and there's a path leading in that direction.";
        antHillDescription = "You are standing at an ant hill of unusual size. No ants of unusual size, just a fairly large ant\nhill. By your best guess, the highest point is at your belly button."; //TODO if input is "dig" and you have the shovel, reveal a salamander in ant hill. Then you can get the salamander
        archeryRangeDescription = "You step in front of two archery targets made of hay bales and spray-painted circles that are in a\nmakeshift archery range created by a rope tied to four trees in a rectangle around them. There's a\nditch to the east and a long driveway leading west.";
        boatDescription = "You're sitting in a rickety wooden boat in a large underground lake with passages to the north,\nwest, northwest, and southwest.";
        damDescription = "You're on a short dam that looks like it created this lake by stopping up a large river. The dam\ngoes north and south along the west end of the lake. Close by is a wheel with it's axel extending\ndeep into the dam. It's orange metal is fading to rust except for some other metal at the center,\nshining in the sun. South leads around the lake and to the north there's a set of stairs."; // TODO add puzzle for unlocking wheel when magnet is dropped then draining lake when wheel is turned
        dirtRoadDescription = "You are on a badly washboarded dirt road in dire need of maintenance that extends far north and\nruns winding down the hill to the south. Pine forests hug the road on both sides.";
        ditchDescription = "You are in the middle of the forest standing in a small ditch running north and south.";
        drivewayDescription = "You are at the north end of a dirt road surrounded by a forest of pines except for a small gap to\nthe east that exposes a steep, dirt driveway sloping down into the forest. To the south you can see\nthrough the trees and into the valley, thanks to whoever made the road. There you see what might be\nthe shimmering of a lake in the mountain sun. There's also a foot path going north.";
        eastEndOfMainstreetDescription = "You are at the east end of an abandon gold mining town's mainstreet. Branching off from this\nstreet to the south is a smaller road and this mainstreet goes back west.";
        footPathDescription = "You're on a foot path in the middle of a dense forest. Large pine trees are all around you, but not\nin an unnerving way. The path goes south and west from here.";
        insideLogCabinDescription = "You are inside a well-kept log cabin with a huge fireplace on the west wall with a magnificent fire\nburning inside. There's a little sign hanging on the wall next to the fireplace that reads \"Portal\nRoom\" and there's a spiral staircase in one corner."; // TODO add an upstairs and maybe a portal room or something like that
        intersectionDescription = "You have reached an intersection in the road. Looking east, the road makes a gradual turn back into\nthe forest and to the west the shimmering look very much like a lake now. Behind you, to the north,\nthe road makes a sharp turn into the forest.";
        mineEntranceDescription = "You've come to the entrance to this abandoned gold mine. The supports on it are looking a little\nworn and there are some loose nails that might come in handy if you could safely get them out of\nthe rotten wood. You could enter to the south if you're very careful. Piles of tailings are all\nover leaving one path away from the entrance to the north.";
        mineShaftDescription = "This is the mine shaft. It looks like it could cave in at any moment. If it does, hopefully there's\nanother way out. There's a small wooden sign here that says \"TOMMYKNOCKERS\" across the top. In\nsmaller writing underneath that it says, \"The most important thing you should know about\ntommyknockers is that, when you hear them knocking on the walls of the mine it means there's about\nto be a cave-in. One other thing to note is that they like to play tricks on those in the mines.\nOne of their favorites is to take miners' possessions.\"";
        outsideLogCabinDescription = "You are in front of a log cabin that looks much less run-down than the rest of the town. A warm\nlight shines from the windows.";
        picnicTableDescription = "A sturdy looking picnic table is in this little clearing you've stepped into and farther south a\nshed peeks through the trees.";
        privatePropertyDescription = "All around you is a dense pine forest that gives the air a friendly smell. It looks like there's\nnothing to be concerned about on this property since there's no gate, no fence, nothing to keep out\ntrespassers. There's not even a house. This is must be private property though since someone took\nthe effort to put in a driveway that continues east and a neat trail leading somewhere southeast.";
        lakeDescription = "You are on the north side of a lake. The water sparkles in the intense sun and you can see far into\nthe clear water but the lake is very deep and there's nothing to see but lake bottom from here.\nThere's a path going east and there's a dam to the south.";
        lakeTownDescription = "You are in what once was a charming little town. Now there is dripping wet plant life from the\nrecently drained lake clinging to the buildings. The muddy ground squelches as you walk. To the\nwest is the dam and you can go farther into the town to the east.";
        lightningTreeDescription = "You're in a little clearing with a large tree in the middle that looks like it was struck by\nlightning a long time ago. The bark has long since fallen off and the remaining part of the tree\nis a neat reddish color.";
        shedDescription = "Here is a cheerful shed with wood matching that of the picnic table's and it's doors firmly shut\nand locked, the one and only thing that needs to be on this plot of land.";
        topOfHillDescription = "You are at the top of a steep hill and have a wonderful view of the valley. The road goes into the\ntrees to the north and down the hill to the west.";
        topOfStairsDescription = "You are at the top of a set of wooden stairs embedded in the hill. A dam is to the north, at the\nbottom of the stairs, and the mainstreet of the abandoned gold mining town stretches east and west.";
        upstairsLogCabin = "The second floor of this cabin isn't nearly as well kept as the first floor. There are cob webs\nall over and dust blankets every uncovered surface. There's a spiral staircase going back to the\nfirst floor.";
        undergroundLakeNorthDescription = "You are on the north side of large underground lake with a rickety wooden boat at the shore. It\nseems odd that the miners tolerated this. There are two passages across the lake from where you\nare standing: one going west and one going southwest. There's a dim light coming from around a\ncorner to the east.";
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
                "in", "out", "back"));

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
        allVerbs = new ArrayList<>(Arrays.asList("get", "drop", "inventory", "look", "throw", "open", "unlock", "turn", "shoot", "fill"));
    }

    public static void main(String[] args) {
        Main m = new Main();
        // Locations for graph
        Location abandonedGoldMine = new Location(m.abandonedGoldMineDescription, new ArrayList<>(), new ArrayList<>(), new Location(),  false, "abandoned gold mine");
        Location antHill = new Location(m.antHillDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "ant hill");
        Location archeryRange = new Location(m.archeryRangeDescription, new ArrayList<>(), new ArrayList<>(), new Location(),false, "archery range");
        Location dirtRoad = new Location(m.dirtRoadDescription, new ArrayList<>(), new ArrayList<>(), new Location(),false, "dirt road");
        Location ditch = new Location(m.ditchDescription, new ArrayList<>(Collections.singletonList(m.key)), new ArrayList<>(), new Location(), false, "ditch");
        Location driveway = new Location(m.drivewayDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "driveway");
        Location eastEndOfMainstreet = new Location(m.eastEndOfMainstreetDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "east end of mainstreet");
        Location footPath = new Location(m.footPathDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "bend in road");
        Location insideLogCabin = new Location(m.insideLogCabinDescription, new ArrayList<>(Collections.singletonList(m.magnet)), new ArrayList<>(), new Location(), false, "inside log cabin");
        Location intersection = new Location(m.intersectionDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "intersection");
        Location lake = new Location(m.lakeDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "lake");
        Location lakeTown = new Location(m.lakeTownDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "lake town");
        Location lightningTree = new Location(m.lightningTreeDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "lightning tree");
        Location mineShaft = new Location(m.mineShaftDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "mine shaft");
        Location outsideLogCabin = new Location(m.outsideLogCabinDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "outside log cabin");
        Location picnicTable = new Location(m.picnicTableDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "picnic table");
        Location privateProperty = new Location(m.privatePropertyDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "private property");
        Location topOfHill = new Location(m.topOfHillDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "top of hill");
        Location topOfStairs = new Location(m.topOfStairsDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "top of stairs");
        Location undergroundLakeNorth = new Location(m.undergroundLakeNorthDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "north side of underground lake");
        Location undergroundLakeWest = new Location(m.undergroundLakeWestDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "west side of underground lake");
        Location undergroundLakeSW = new Location(m.undergroundLakeSWDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "sw side of underground lake");
        Location upstairsLogCabin = new Location(m.upstairsLogCabin, new ArrayList<>(), new ArrayList<>(), new Location(), false, "second floor of log cabin");
        Location westEndOfMainstreet = new Location(m.westEndOfMainstreetDescription, new ArrayList<>(), new ArrayList<>(), new Location(), false, "west end of mainstreet");
        Boat boat = new Boat(m.boatDescription, new ArrayList<>(), new ArrayList<>(), new Boat(), false, "rickety wooden boat", false);
        Dam dam = new Dam(m.damDescription, new ArrayList<>(), new ArrayList<>(), new Dam(), false, "dam", false, false);
        Shed shed = new Shed(m.shedDescription, new ArrayList<>(), new ArrayList<>(), new Shed(), false, "shed", false, false);
        MineEntrance mineEntrance = new MineEntrance(m.mineEntranceDescription, new ArrayList<>(Collections.singletonList(m.gold)), new ArrayList<>(), new MineEntrance(), false, "mine entrance", false);
        // Add locations to connectingLocations arraylist parameter of Location to create graph
        // Some ConnectingLocations have in and out as directions since that will get you to another location in addition to entering a direction
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.south, mineEntrance));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.west, lake));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.east, intersection));
        antHill.connectingLocations.add(new ConnectingLocation(m.north, ditch));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.west, privateProperty));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.east, ditch));
        footPath.connectingLocations.add(new ConnectingLocation(m.south, driveway));
        footPath.connectingLocations.add(new ConnectingLocation(m.west, topOfHill));
        boat.connectingLocations.add(new ConnectingLocation(m.north, undergroundLakeNorth));
        boat.connectingLocations.add(new ConnectingLocation(m.west, undergroundLakeWest));
        boat.connectingLocations.add(new ConnectingLocation(m.southwest, undergroundLakeSW));
        dam.connectingLocations.add(new ConnectingLocation(m.north, lake));
        dam.connectingLocations.add(new ConnectingLocation(m.south, topOfStairs));
        dam.connectingLocations.add(new ConnectingLocation(m.up, topOfStairs));
        dam.connectingLocations.add(new ConnectingLocation(m.east, lakeTown));
        dam.connectingLocations.add(new ConnectingLocation(m.down, lakeTown));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.north, driveway));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.south, intersection));
        ditch.connectingLocations.add(new ConnectingLocation(m.north, lightningTree));
        ditch.connectingLocations.add(new ConnectingLocation(m.south, antHill));
        ditch.connectingLocations.add(new ConnectingLocation(m.west, archeryRange));
        driveway.connectingLocations.add(new ConnectingLocation(m.north, footPath));
        driveway.connectingLocations.add(new ConnectingLocation(m.south, dirtRoad));
        driveway.connectingLocations.add(new ConnectingLocation(m.east, privateProperty));
        driveway.connectingLocations.add(new ConnectingLocation(m.down, privateProperty));
        eastEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.west, topOfStairs));
        eastEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.south, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.east, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("out")), outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.up, upstairsLogCabin));
        intersection.connectingLocations.add(new ConnectingLocation(m.north, dirtRoad));
        intersection.connectingLocations.add(new ConnectingLocation(m.east, topOfHill));
        intersection.connectingLocations.add(new ConnectingLocation(m.west, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.east, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.south, dam));
        lakeTown.connectingLocations.add(new ConnectingLocation(m.west, dam)); // east goes farther into the town
        lakeTown.connectingLocations.add(new ConnectingLocation(m.up, dam));
        lightningTree.connectingLocations.add(new ConnectingLocation(m.south, ditch));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.north, mineEntrance));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.south, undergroundLakeNorth));
        mineShaft.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("out")), mineEntrance));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.north, abandonedGoldMine));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.south, mineShaft));
        mineEntrance.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), mineShaft));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.north, eastEndOfMainstreet));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.west, insideLogCabin));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), insideLogCabin));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.north, privateProperty));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.south, shed));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.west, driveway));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.east, archeryRange));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.southeast, picnicTable));
        shed.connectingLocations.add(new ConnectingLocation(m.north, picnicTable));
        topOfHill.connectingLocations.add(new ConnectingLocation(m.north, footPath));
        topOfHill.connectingLocations.add(new ConnectingLocation(m.west, intersection));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.north, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.down, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.east, eastEndOfMainstreet));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.west, westEndOfMainstreet));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(m.east, mineShaft));
        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
//        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(m.south, somelocation));
        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(new ArrayList<>(Collections.singletonList("in")), boat));
//        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(m.west, somelocation));
        upstairsLogCabin.connectingLocations.add(new ConnectingLocation(m.down, insideLogCabin));
        westEndOfMainstreet.connectingLocations.add(new ConnectingLocation(m.east, topOfStairs));

        // Arraylist of Items for when you get an Item from a location and a Scanner for input from keyboard
        ArrayList<Item> inventory = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        // First location is driveway. Change for debugging
//        Location currentLocation = driveway;
        Location currentLocation = outsideLogCabin;
//        dam.items.add(m.magnet);
//        dam.items.add(m.jar);
        currentLocation.previousLocation = currentLocation;
        currentLocation.visited = true;

        // Game introduction
        System.out.println("You're on an unnamed mountain with a rumored unnamed, abandoned gold mining town nearby. One of the\ngold mines might also be accessible somewhere around, but before you came here you heard that\ntommyknockers are in the mines here. You don't know much about them but the name doesn't give off\nthe most friendly vibes.\n");
        System.out.println("Right now you stand at the north end of a dirt road surrounded by a forrest of pines except for a\nsmall gap to the east that exposes a steep, dirt driveway sloping down into the forest. To the\nsouth you can see through the trees and into the valley, thanks to whoever made the road. There you\nsee what might be the shimmering of a lake in the mountain sun. There's also a foot path going\nnorth.");
        // Input will always be lowercase so don't have to worry about forgetting to check .equalsIgnoreCase
        String input = scan.nextLine().toLowerCase();
        // Always loop for main game play
        while(true) {
            // End if input is "quit" -> "y"/"yes"
            // Continue if input is "quit" -> "n"/"no"
            // If "quit" -> anything else, anything else has to be yes or no
            if(input.equals("quit")) {
                System.out.println("Are you sure you want to quit?");
                input = scan.nextLine().toLowerCase();
                while(!(input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n"))) {
                    System.out.println("Please answer the question.");
                    input = scan.nextLine().toLowerCase();
                }
                if(input.equals("yes") || input.equals("y")) {
                    System.out.println("Quitted");      //TODO have a better quit message
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
                ArrayList<ConnectingLocation> currentConnections = currentLocation.connectingLocations;
                for (int i = 0; i < currentLocation.connectingLocations.size(); i++) {
                    if ((currentConnections.get(i).directions.contains(input) && !currentLocation.equals(currentConnections.get(i).location)) || (input.equals("back") && !currentLocation.previousLocation.equals(currentLocation)) || (input.equals("out") && currentLocation instanceof Boat)) {
                        if(input.equals("back") || (input.equals("out") && currentLocation instanceof Boat)) {
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
                else if(!(m.allVerbs.contains(inputCheck) || m.directions.contains(inputCheck)) || ((inputCheck.equals("out") || inputCheck.equals("in") || input.equals("unlock") || input.equals("open") || input.equals("shoot")) && inputArray.length >= 2)) {//                    Random r = new Random();
                    dontKnowWord();
                }
            }
            // input will always be lowercase so don't have to worry about checking .equalsIgnoreCase
            input = scan.nextLine().toLowerCase();
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
        return new Main().directions.contains(input);
    }

    // For sorting items in inventory and at location so they print in the same order
    @Override
    public int compare(Item i1, Item i2) {
        return i1.order - i2.order;
    }

    public static void printLocation(String input, Location location) {
        if (!location.visited || input.equals("look")) {
            location.visited = true;
            System.out.println(location.description);
            if(!location.items.isEmpty()) {
                for(Item item : location.items) {
                    System.out.println(item.locationPrint);
                }
            }
        }
        else {
            //Corner case for printing short version of boat location
            if(location instanceof Boat) {
                System.out.println("You're sitting in a rickety wooden boat in a large underground lake.");
            }
            else {
                System.out.println("You're at " + location.name + ".");
            }
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
    public static void findAction(String input, Location location, ArrayList<Item> inventory) {
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
        else if(input.length() >= 4 && input.startsWith("drop")) {
            if(input.equals("drop") || input.equals("drop ")) {
                System.out.println("What do you want to drop?");
                return;
            }
            location.drop(input.substring(5), location, inventory);
        }
        else if(input.length() >= 5 && input.equals("shoot")) {
            location.shoot(input, location, inventory);
        }
        else if(input.length() >= 6 && input.equals("unlock")) {
            location.unlock(location, inventory);
        }
        else if(input.length() >= 4 && input.equals("open")) {
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
        else {
            dontKnowWord();
        }
    }
}
