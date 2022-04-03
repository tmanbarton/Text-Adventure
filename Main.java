import java.util.*;

public class Main {

    public Main() {}

    public static void main(String[] args) {
        Graph graph = new Graph();
        scrambleCube(graph);
//        Location currentLocation = graph.graniteRoom;
        Location currentLocation = graph.driveway;
        System.out.println(currentLocation);
//        this.inventory.add(m.cube);
//        currentLocation.items.add(new Item(10, "There is a thick, circular magnet here, about the size of your palm.", "Magnet", "magnet"));
//        dam.items.add(m.jar);
        currentLocation.previousLocation = currentLocation;
        currentLocation.visited = true;
        ArrayList<Item> inventory = new ArrayList<>();
        new GameController().gameIntro(inventory, currentLocation);
    }

    public static void scrambleCube(Graph graph) {
        char[] top = {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'};
        char[] front = {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'};
        char[] right = {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'};
        char[] back = {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        char[] left = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] bottom = {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        char[][] charCube = {top, front, right, back, left, bottom};
        graph.cube.charCube = charCube;
        String algorithm = ScrambleGenerator.generateScramble();
        Cube.findTurn(charCube, algorithm);
    }
}
