import java.util.*;

public class Main implements Comparator<Item> {

    String abandonedGoldMineDescription;
    String antHillDescription;
    String archeryRangeDescription;
    String boatDescription;
    String brokenRockDescription;
    String damDescription;
    String dankPassageDescription;
    String dirtRoadDescription;
    String dirtyPassageDescription;
    String ditchDescription;
    String drivewayDescription;
    String dynamiteHolesDescription;
    String eastEndOfSideStreetDescription;
    String footPathDescription;
    String graniteRoomDescription;
    String insideLogCabinDescription;
    String intersectionDescription;
    String lakeDescription;
    String lakeTownDescription;
    String lightningTreeDescription;
    String mineEntranceDescription;
    String mineShaftDescription;
    String mustyBendDescription;
    String narrowCorridorDescription;
    String outsideLogCabinDescription;
    String outsideTavernDescription;
    String picnicTableDescription;
    String privatePropertyDescription;
    String roadInValleyDescription;
    String rubyOnRailsDescription;
    String shedDescription;
    String tavernDescription;
    String topOfHillDescription;
    String topOfStairsDescription;
    String upstairsLogCabinDescription;
    String undergroundLakeNorthDescription;
    String undergroundLakeWestDescription;
    String undergroundLakeSWDescription;
    String bottomOfVerticalMineShaftDescription;
    String westEndOfSideStreetDescription;

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
    ArrayList<String> in;
    ArrayList<String> out;

    Item key;
    Item hammer;
    Item jar;
    Item tent;
    Item bow;
    Item arrow;
    Item gold;
    Item shovel;
    Item magnet;
    Item ruby;
    Cube cube;

    ArrayList<String> allVerbs;

    public Main() {
        // Location descriptions
        abandonedGoldMineDescription = "All around are large piles of tailings that look like they have been puked into this valley.\nThere's not much else to be seen except the entrance to a mine to the south. The shimmering in the\nwest is definitely a lake and there's a path leading in that direction.";
        antHillDescription = "You are standing at a very large ant hill. Looks like the highest point is at about your belly\nbutton."; //TODO if input is "dig" and you have the shovel, reveal a salamander in ant hill. Then you can get the salamander
        archeryRangeDescription = "You step in front of two archery targets made of hay bales and spray-painted circles that are in a\nmakeshift archery range created by a rope tied to four trees in a rectangle around them. There's a\nditch to the east and a long driveway leading west.";
        boatDescription = "You're sitting in a rickety wooden boat in a large underground lake with passages to the north,\nwest, and southwest.";
        brokenRockDescription = "The only thing up here is a bunch of broken rock.";
        damDescription = "You're on a short dam that looks like it created this lake by stopping up a large river. The dam\ngoes north and south along the west end of the lake. Close by is a wheel with it's axel extending\ndeep into the dam. It's orange metal is fading to rust except for some other metal at the center,\nshining in the sun. South leads around the lake and to the north there's a set of stairs."; // TODO add puzzle for unlocking wheel when magnet is dropped then draining lake when wheel is turned
        dankPassageDescription = "The walls and floor here are wet. The dank atmosphere of this place makes it a little muggy. The\nrails split, going down to the west. You can see some metal equipment to the south.";
        dirtRoadDescription = "You are on a badly washboarded dirt road in dire need of maintenance that extends far north and\nruns winding down the hill to the south. Pine forests hug the road on both sides.";
        dirtyPassageDescription = "You're in a dirty broken passage. To the east the passage gets wider. To the west the passage\nnarrows. Above you part of the ceiling caved in, leaving a hole and a pile of debris that cover the\nrails.";
        ditchDescription = "You are in the middle of the forest standing in a small ditch running north and south.";
        drivewayDescription = "You are at the north end of a dirt road surrounded by a forest of pine trees with a small gap to the\neast that exposes a steep, dirt driveway sloping down into the forest. Looking down the road to the\nsouth you can see over the trees and into the valley. There you see what might be the shimmering of\na lake in the mountain sun. There's also a foot path going north.";
        dynamiteHolesDescription = "There are a bunch of holes drilled in the wall here. The miners of old must have thought about\nusing dynamite to blow up this part but changed their minds. The rails split again. The railway now\nleads down, south, and up a slope to the ne.";
        eastEndOfSideStreetDescription = "You are at the east end of a side street in an abandon gold mining town. Branching off from this\nstreet to the south is a smaller road.";
        footPathDescription = "You're on a foot path in the middle of a dense forest. Large pine trees are all around you, but not\nin an unnerving way. The path goes south and west from here.";
        graniteRoomDescription = "On a polished granite pedestal, black as night, in the middle of this room\nwith walls of the same black rock sits a plastic puzzle. It is a 3 x 3 x 3 cube with different\ncolored stickers for each side in stark contrast of the black consuming the room. The sides can be\nturned. It is scrambled. The only exit is to the east.";
        insideLogCabinDescription = "You are inside a well-kept log cabin with a huge fireplace on the west wall with a magnificent fire\nburning inside. There's a little sign hanging on the wall next to the fireplace that reads \"Portal\nRoom\" and there's a spiral staircase in one corner."; // TODO maybe add a portal room or something like that
        intersectionDescription = "You have reached an intersection in the road. Looking east, the road makes a gradual turn back into\nthe forest and to the west the shimmering look very much like a lake now. Behind you, to the north,\nthe road makes a sharp turn into the forest.";
        lakeDescription = "You are on the north side of a lake. The water sparkles in the intense sun and you can see far into\nthe clear water but the lake is very deep and there's nothing to see but lake bottom from here.\nThere's a path going east and there's a dam to the south.";
        lakeTownDescription = "You are in what once was a charming little town. Now there is dripping wet plant life from the\nrecently drained lake clinging to the buildings. The muddy ground squelches as you walk. To the\nwest is the dam and you can go farther into the town to the east.";
        lightningTreeDescription = "You're in a little clearing with a large tree in the middle that looks like it was struck by\nlightning a long time ago. The bark has long since fallen off and the remaining part of the tree\nis a reddish color.";
        mineEntranceDescription = "You've come to the entrance to this abandoned gold mine. The supports on it are looking a little\nworn and there are some loose nails that might come in handy if you could safely get them out of\nthe rotten wood. You could enter to the south if you're very careful. Piles of tailings are all\nover leaving one path away from the entrance to the north.";
        mineShaftDescription = "This is the mine shaft. It looks like it could cave in at any moment. If it does, hopefully there's\nanother way out. There's a small wooden sign here that says \"BEWARE OF TOMMYKNOCKERS\" with an ugly\npicture of one the green-skinned, dwarf-sized creatures.";
        mustyBendDescription = "You're at a bend in the rails with a musty feel in the air. The rails head up to the east and north.";
        narrowCorridorDescription = "You are in a long, narrow corridor. At the end of the corridor the rails split. One set going down\nto the sw and one continuing on nw. At the eastern end the corridor widens a bit.";
        outsideLogCabinDescription = "You are in front of a log cabin that looks much less run-down than the rest of the town. A warm\nlight shines from the windows.";
        outsideTavernDescription = "On the west side of the road is a tavern with a wooden sign hanging above the door with\n\"Tommyknocker Tavern\" etched into it. Here is the former heartbeat of the town, now a dump of a\nplace. Its roof has caved in and the walls look as if they could collapse at any moment.";
        picnicTableDescription = "A sturdy looking picnic table is in this little clearing you've stepped into and farther south a\nshed peeks through the trees.";
        privatePropertyDescription = "All around you is a dense pine forest that gives the air a friendly smell. It looks like there's\nnothing to be concerned about on this property since there's no gate, no fence, nothing to keep out\ntrespassers. There's not even a house. This is must be private property though since someone took\nthe effort to put in a driveway that continues east and a neat trail leading somewhere southeast.";
        roadInValleyDescription = "You're on a road in the middle of a lush, green valley with grazing pastures all around.";
        rubyOnRailsDescription = "You've reached a dead end. A crumpled mine cart, no longer able to run on the rails, has fallen on its\nside and dumped a large ruby onto the rails.";
        shedDescription = "Here is a cheerful shed with wood matching that of the picnic table's and it's doors firmly shut\nand locked, the one and only thing that needs to be on this plot of land.";
        tavernDescription = "The Tommyknocker Tavern looks just as shabby on the inside as it does on the outside with all of its\nfurnishings falling apart and a musty smell in the air.";
        topOfHillDescription = "You are at the top of a steep hill and have a wonderful view of the valley. The road goes into the\ntrees to the north and down the hill to the west.";
        topOfStairsDescription = "You are at the top of a set of wooden stairs embedded in the hill now standing next to a street\nrunning east and west in the abandoned gold mining town. A dam is to the north, at the bottom of\nthe stairs.";
        upstairsLogCabinDescription = "The second floor of this cabin isn't nearly as well kept as the first floor. There are cob webs\nall over and dust blankets every uncovered surface. There's a spiral staircase going back to the\nfirst floor.";
        undergroundLakeNorthDescription = "You are on the north side of a large underground lake with a rickety wooden boat at the shore. Looks\nlike the place flooded long after it was abandoned. There are two passages across the lake from\nwhere you are standing: one going west and one going southwest. There's a dim light coming from\naround a corner to the east.";
        undergroundLakeWestDescription = "You are on the west side of a large underground lake. There are passages to the north an sw across\nthe lake. The tunnel you're in now continues to the west.";
        undergroundLakeSWDescription = "You are on the sw side of a large underground lake. There are passages to the north and west across\nthe lake. Mine rails lead into a passage to the west from where you are.";
        bottomOfVerticalMineShaftDescription = "You are at the bottom of a vertical mine shaft with a working mine cage. Next to the mine cage is a\nbutton labeled \"Up/Down\". A mine railway starts here and runs north";
        westEndOfSideStreetDescription = "You are at the west and of a side street in an abandoned gold mining town. The main street goes\nnorth and south form here.";
        // Arraylists that contain all possible, valid inputs for directions. Use for location objects
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
        in = new ArrayList<>(Arrays.asList("in", "go in"));
        out = new ArrayList<>(Arrays.asList("out", "go out"));
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
                "in", "go in", "out", "go out", "back", "go back"));

        key = new Item(1, "There is a shiny key here", "Shiny key", "key");
        hammer = new Item(2, "There is a hammer here", "Hammer", "hammer");
        bow = new Item(3, "There is a bow here, strung an ready for shooting", "Bow", "bow");
        arrow = new Item(4, "There is an arrow here", "Arrow", "arrow");
        jar = new Item(5, "There is a jar here.", "Jar", "jar");
        gold = new Item(6, "There are some gold flakes on the ground here", "Gold flakes in jar", "gold");
        shovel = new Item(8, "There is a shovel here", "Shovel", "shovel");
        tent = new Item(9, "There is a tent here, packed neatly in a bag.", "Tent in bag", "tent");
        magnet = new Item(10, "There is a thick, circular magnet here, about the size of your palm.", "Magnet", "magnet");
        ruby = new Item(11, "A large ruby lays on the ground", "Ruby", "ruby");
        cube = new Cube(12, "There is a plastic cube puzzle lying on the ground", "Cube", "cube", false, false, null);

        // Lists of all valid verbs that the player can enter that are not directions.
        allVerbs = new ArrayList<>(Arrays.asList("get", "drop", "inventory", "look", "throw", "open", "unlock", "turn", "shoot", "fill", "play"));
    }

    public static void main(String[] args) {
        Main m = new Main();
        // Locations for graph
        Location abandonedGoldMine = new Location(m.abandonedGoldMineDescription, "You're at an abandoned gold mine", new ArrayList<>(), new ArrayList<>(), new Location(),  false, "abandoned gold mine");
        Location antHill = new Location(m.antHillDescription, "You're at a large ant hill", new ArrayList<>(), new ArrayList<>(), new Location(), false, "ant hill");
        Location archeryRange = new Location(m.archeryRangeDescription, "You're at Archery Range", new ArrayList<>(), new ArrayList<>(), new Location(),false, "archery range");
        Location brokenRock = new Location(m.brokenRockDescription, "The only thing up here is a bunch of broken rock.", new ArrayList<>(), new ArrayList<>(), new Location(), false, "broken rock");
        Location dankPassage = new Location(m.dankPassageDescription, "You're at Dank Passage", new ArrayList<>(), new ArrayList<>(), new Location(), false, "dank passage");
        Location dirtRoad = new Location(m.dirtRoadDescription, "You're at Dirt Road", new ArrayList<>(), new ArrayList<>(), new Location(),false, "dirt road");
        Location dirtyPassage = new Location(m.dirtyPassageDescription, "You're at Dirty Passage", new ArrayList<>(), new ArrayList<>(), new Location(), false, "dirty passage");
        Location ditch = new Location(m.ditchDescription, "You're in a ditch", new ArrayList<>(Collections.singletonList(m.key)), new ArrayList<>(), new Location(), false, "ditch");
        Location driveway = new Location(m.drivewayDescription, "You're at Driveway", new ArrayList<>(), new ArrayList<>(), new Location(), false, "driveway");
        Location dynamiteHoles = new Location(m.dynamiteHolesDescription, "You're by a section of wall with a bunch of holes drilled for dynamite", new ArrayList<>(), new ArrayList<>(), new Location(), false, "dynamite holes");
        Location eastEndOfSideStreet = new Location(m.eastEndOfSideStreetDescription, "You're at the east end of the main street", new ArrayList<>(), new ArrayList<>(), new Location(), false, "east end of side street");
        Location footPath = new Location(m.footPathDescription, "You're on a foot path", new ArrayList<>(), new ArrayList<>(), new Location(), false, "foot path");
        Location insideLogCabin = new Location(m.insideLogCabinDescription, "You're inside Log Cabin", new ArrayList<>(Collections.singletonList(m.magnet)), new ArrayList<>(), new Location(), false, "inside log cabin");
        Location intersection = new Location(m.intersectionDescription, "You're at an intersection in the road", new ArrayList<>(), new ArrayList<>(), new Location(), false, "intersection");
        Location lake = new Location(m.lakeDescription, "You're on the north side of a lake", new ArrayList<>(), new ArrayList<>(), new Location(), false, "lake");
        Location lakeTown = new Location(m.lakeTownDescription, "You are next to Lake Town", new ArrayList<>(), new ArrayList<>(), new Location(), false, "lake town");
        Location lightningTree = new Location(m.lightningTreeDescription, "You're at Lightning Tree", new ArrayList<>(), new ArrayList<>(), new Location(), false, "lightning tree");
        Location mineShaft = new Location(m.mineShaftDescription, "You're in Mine Shaft", new ArrayList<>(), new ArrayList<>(), new Location(), false, "mine shaft");
        Location mustyBend = new Location(m.mustyBendDescription, "You're at a musty bend", new ArrayList<>(), new ArrayList<>(), new Location(), false, "musty bend");
        Location narrowCorridor = new Location(m.narrowCorridorDescription, "You're in a narrow corridor", new ArrayList<>(), new ArrayList<>(), new Location(), false, "narrow corridor");
        Location outsideLogCabin = new Location(m.outsideLogCabinDescription, "You're outside Log Cabin", new ArrayList<>(), new ArrayList<>(), new Location(), false, "outside log cabin");
        Location outsideTavern = new Location(m.outsideTavernDescription, "You're outside Tommyknocker Tavern", new ArrayList<>(), new ArrayList<>(), new Location(), false, "outside Tommyknocker Tavern");
        Location picnicTable = new Location(m.picnicTableDescription, "You're at Picnic Table", new ArrayList<>(), new ArrayList<>(), new Location(), false, "picnic table");
        Location privateProperty = new Location(m.privatePropertyDescription, "You're at Private Property", new ArrayList<>(), new ArrayList<>(), new Location(), false, "private property");
        Location roadInValley = new Location(m.roadInValleyDescription, "You're on a road in a valley", new ArrayList<>(), new ArrayList<>(), new Location(), false, "road in a valley");
        Location tavern = new Location(m.tavernDescription, "You're inside the Tommyknocker Tavern", new ArrayList<>(), new ArrayList<>(), new Location(), false, "Tommyknocker Tavern");
        Location topOfHill = new Location(m.topOfHillDescription, "You're at Top of Hill", new ArrayList<>(), new ArrayList<>(), new Location(), false, "top of hill");
        Location topOfStairs = new Location(m.topOfStairsDescription, "You're at Top of Stairs", new ArrayList<>(), new ArrayList<>(), new Location(), false, "top of stairs");
        Location undergroundLakeNorth = new Location(m.undergroundLakeNorthDescription, "You're on the north side of an underground lake", new ArrayList<>(), new ArrayList<>(), new Location(), false, "north side of underground lake");
        Location undergroundLakeWest = new Location(m.undergroundLakeWestDescription, "You're on the west side of an underground lake", new ArrayList<>(), new ArrayList<>(), new Location(), false, "west side of underground lake");
        Location undergroundLakeSW = new Location(m.undergroundLakeSWDescription, "You're on the sw side of an underground lake", new ArrayList<>(), new ArrayList<>(), new Location(), false, "sw side of underground lake");
        Location upstairsLogCabin = new Location(m.upstairsLogCabinDescription, "You're on the second floor of the log cabin", new ArrayList<>(), new ArrayList<>(), new Location(), false, "second floor of log cabin");
        Location bottomOfVerticalMineShaft = new Location(m.bottomOfVerticalMineShaftDescription, "You are at the bottom of a vertical mine shaft", new ArrayList<>(), new ArrayList<>(), new Location(), false, "bottom of vertical mine shaft");
        Location westEndOfSideStreet = new Location(m.westEndOfSideStreetDescription, "You're at the west end of the main street", new ArrayList<>(), new ArrayList<>(), new Location(), false, "west end of side street");
        Boat boat = new Boat(m.boatDescription, "You're in a rickety wooden boat in an underground lake", new ArrayList<>(), new ArrayList<>(), new Boat(), false, "rickety wooden boat", false);
        Dam dam = new Dam(m.damDescription, "You're at Dam", new ArrayList<>(), new ArrayList<>(), new Dam(), false, "dam", false, false);
        GraniteRoom graniteRoom = new GraniteRoom(m.graniteRoomDescription, "You're at Granite Room", new ArrayList<>(Collections.singletonList(m.cube)), new ArrayList<>(), new Location(), false, "granite room", false);
        Shed shed = new Shed(m.shedDescription, "You're standing before a cheerful little shed", new ArrayList<>(), new ArrayList<>(), new Shed(), false, "shed", false, false);
        RubyOnRails rubyOnRails = new RubyOnRails(m.rubyOnRailsDescription, "You're at Ruby On Rails", new ArrayList<>(Collections.singletonList(m.ruby)), new ArrayList<>(), new Location(), false, "ruby on rails", false);
        MineEntrance mineEntrance = new MineEntrance(m.mineEntranceDescription, "You're at the entrance of the mine", new ArrayList<>(Collections.singletonList(m.gold)), new ArrayList<>(), new MineEntrance(), false, "mine entrance", false);
        // Add locations to connectingLocations arraylist parameter of Location to create graph
        // Some ConnectingLocations have in and out as directions since that will get you to another location in addition to entering a direction
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.south, mineEntrance));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.west, lake));
        abandonedGoldMine.connectingLocations.add(new ConnectingLocation(m.east, intersection));
        antHill.connectingLocations.add(new ConnectingLocation(m.north, ditch));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.west, privateProperty));
        archeryRange.connectingLocations.add(new ConnectingLocation(m.east, ditch));
        boat.connectingLocations.add(new ConnectingLocation(m.north, undergroundLakeNorth));
        boat.connectingLocations.add(new ConnectingLocation(m.west, undergroundLakeWest));
        boat.connectingLocations.add(new ConnectingLocation(m.southwest, undergroundLakeSW));
        brokenRock.connectingLocations.add(new ConnectingLocation(m.down, dirtyPassage));
        dam.connectingLocations.add(new ConnectingLocation(m.north, lake));
        dam.connectingLocations.add(new ConnectingLocation(m.south, topOfStairs));
        dam.connectingLocations.add(new ConnectingLocation(m.up, topOfStairs));
        dam.connectingLocations.add(new ConnectingLocation(null, lakeTown));
        dam.connectingLocations.add(new ConnectingLocation(null, lakeTown));
        dankPassage.connectingLocations.add(new ConnectingLocation(m.south, bottomOfVerticalMineShaft));
        dankPassage.connectingLocations.add(new ConnectingLocation(m.west, mustyBend));
        dankPassage.connectingLocations.add(new ConnectingLocation(m.down, mustyBend));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.north, driveway));
        dirtRoad.connectingLocations.add(new ConnectingLocation(m.south, intersection));
        dirtyPassage.connectingLocations.add(new ConnectingLocation(m.east, undergroundLakeSW));
        dirtyPassage.connectingLocations.add(new ConnectingLocation(m.west, narrowCorridor));
        dirtyPassage.connectingLocations.add(new ConnectingLocation(m.up, brokenRock));
        ditch.connectingLocations.add(new ConnectingLocation(m.north, lightningTree));
        ditch.connectingLocations.add(new ConnectingLocation(m.south, antHill));
        ditch.connectingLocations.add(new ConnectingLocation(m.west, archeryRange));
        driveway.connectingLocations.add(new ConnectingLocation(m.north, footPath));
        driveway.connectingLocations.add(new ConnectingLocation(m.south, dirtRoad));
        driveway.connectingLocations.add(new ConnectingLocation(m.east, privateProperty));
        driveway.connectingLocations.add(new ConnectingLocation(m.down, privateProperty));
        dynamiteHoles.connectingLocations.add(new ConnectingLocation(m.south, mustyBend));
        dynamiteHoles.connectingLocations.add(new ConnectingLocation(m.northeast, narrowCorridor));
        dynamiteHoles.connectingLocations.add(new ConnectingLocation(m.up, narrowCorridor));
        dynamiteHoles.connectingLocations.add(new ConnectingLocation(m.down, rubyOnRails));
        eastEndOfSideStreet.connectingLocations.add(new ConnectingLocation(m.west, topOfStairs));
        eastEndOfSideStreet.connectingLocations.add(new ConnectingLocation(m.south, outsideLogCabin));
        footPath.connectingLocations.add(new ConnectingLocation(m.south, driveway));
        footPath.connectingLocations.add(new ConnectingLocation(m.west, topOfHill));
        graniteRoom.connectingLocations.add(new ConnectingLocation(m.east, undergroundLakeWest));
        graniteRoom.connectingLocations.add(new ConnectingLocation(m.out, undergroundLakeWest));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.east, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.out, outsideLogCabin));
        insideLogCabin.connectingLocations.add(new ConnectingLocation(m.up, upstairsLogCabin));
        intersection.connectingLocations.add(new ConnectingLocation(m.north, dirtRoad));
        intersection.connectingLocations.add(new ConnectingLocation(m.east, topOfHill));
        intersection.connectingLocations.add(new ConnectingLocation(m.west, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.east, abandonedGoldMine));
        lake.connectingLocations.add(new ConnectingLocation(m.south, dam));
        lakeTown.connectingLocations.add(new ConnectingLocation(m.west, dam)); // TODO east goes farther into the town
        lakeTown.connectingLocations.add(new ConnectingLocation(m.up, dam));
        lightningTree.connectingLocations.add(new ConnectingLocation(m.south, ditch));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.north, mineEntrance));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.south, undergroundLakeNorth));
        mineShaft.connectingLocations.add(new ConnectingLocation(m.out, mineEntrance));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.north, abandonedGoldMine));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.south, mineShaft));
        mineEntrance.connectingLocations.add(new ConnectingLocation(m.in, mineShaft));
        mustyBend.connectingLocations.add(new ConnectingLocation(m.north, dynamiteHoles));
        mustyBend.connectingLocations.add(new ConnectingLocation(m.east, dankPassage));
        mustyBend.connectingLocations.add(new ConnectingLocation(m.up, dankPassage));
        narrowCorridor.connectingLocations.add(new ConnectingLocation(m.east, dirtyPassage));
        narrowCorridor.connectingLocations.add(new ConnectingLocation(m.southwest, dynamiteHoles));
        narrowCorridor.connectingLocations.add(new ConnectingLocation(m.down, dynamiteHoles));
        narrowCorridor.connectingLocations.add(new ConnectingLocation(m.northwest, dankPassage));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.north, eastEndOfSideStreet));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.west, insideLogCabin));
        outsideLogCabin.connectingLocations.add(new ConnectingLocation(m.in, insideLogCabin));
        outsideTavern.connectingLocations.add(new ConnectingLocation(m.south, westEndOfSideStreet));
        outsideTavern.connectingLocations.add(new ConnectingLocation(m.west, tavern));
        outsideTavern.connectingLocations.add(new ConnectingLocation(m.in, tavern));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.north, privateProperty));
        picnicTable.connectingLocations.add(new ConnectingLocation(m.south, shed));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.west, driveway));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.east, archeryRange));
        privateProperty.connectingLocations.add(new ConnectingLocation(m.southeast, picnicTable));
        roadInValley.connectingLocations.add(new ConnectingLocation(m.north, westEndOfSideStreet));
        rubyOnRails.connectingLocations.add(new ConnectingLocation(m.up, dynamiteHoles));
        shed.connectingLocations.add(new ConnectingLocation(m.north, picnicTable));
        tavern.connectingLocations.add(new ConnectingLocation(m.east, outsideTavern));
        tavern.connectingLocations.add(new ConnectingLocation(m.out, outsideTavern));
        topOfHill.connectingLocations.add(new ConnectingLocation(m.north, footPath));
        topOfHill.connectingLocations.add(new ConnectingLocation(m.west, intersection));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.north, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.down, dam));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.east, eastEndOfSideStreet));
        topOfStairs.connectingLocations.add(new ConnectingLocation(m.west, westEndOfSideStreet));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(m.in, boat));
        undergroundLakeNorth.connectingLocations.add(new ConnectingLocation(m.east, mineShaft));
        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(m.in, boat));
        undergroundLakeSW.connectingLocations.add(new ConnectingLocation(m.west, dirtyPassage));
        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(m.in, boat));
        undergroundLakeWest.connectingLocations.add(new ConnectingLocation(m.west, graniteRoom));
        upstairsLogCabin.connectingLocations.add(new ConnectingLocation(m.down, insideLogCabin));
        bottomOfVerticalMineShaft.connectingLocations.add(new ConnectingLocation(m.north, dankPassage));
//        verticalMineShaft.connectingLocations.add(new ConnectingLocation(m.in, mine cage));
        westEndOfSideStreet.connectingLocations.add(new ConnectingLocation(m.north, outsideTavern));
        westEndOfSideStreet.connectingLocations.add(new ConnectingLocation(m.south, roadInValley));
        westEndOfSideStreet.connectingLocations.add(new ConnectingLocation(m.east, topOfStairs));

        char[] up_ = {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'};
        char[] front = {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'};
        char[] right = {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'};
        char[] back = {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        char[] left = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] down_ = {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        char[][] charCube = {up_, front, right, back, left, down_};
        m.cube.charCube = charCube;
        int[] turns = ScrambleGenerator.generateScramble();
        String algorithm = ScrambleGenerator.writeScramble(turns);
        Cube.findTurn(charCube, algorithm);
        // Arraylist of Items for when you get an Item from a location and a Scanner for input from keyboard
        ArrayList<Item> inventory = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        // First location is driveway. Change for debugging
//        Location currentLocation = driveway;
        Location currentLocation = bottomOfVerticalMineShaft;
//        inventory.add(m.cube);
//        dam.items.add(m.magnet);
//        dam.items.add(m.jar);
        currentLocation.previousLocation = currentLocation;
        currentLocation.visited = true;

        // Game introduction
        System.out.println("You're on a mountain with an abandoned gold mining town somewhere nearby. One of the gold mines is\nsupposedly still accessible, but you've heard that tommyknockers are in the mines here. I'll tell\nyou now that the #1 rule with tommyknockers is if you hear knocking on the walls, the mine is about\nto collapse.\n");
        System.out.println("Right now you are at the north end of a dirt road surrounded by a forest of pine trees with a small\ngap to the east that exposes a steep, dirt driveway sloping down into the forest. Looking down the\nroad ot the south you can see over the trees and into the valley. There you see what might be the\nshimmering of a lake in the mountain sun. There's also a foot path going north.");
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
            if(scan.hasNextLine()) {
                input = scan.nextLine().toLowerCase();
            }
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
        else if(input.equals("play")) {
            if(!isItemHere("cube", inventory)) {
                System.out.println("You have nothing to play with.");
            }
            else {
                new Main().cube.rubiksCubeSimulator(findItem("cube", inventory));
                printLocation(input, location);
            }
        }
        else {
            dontKnowWord();
        }
    }
}
