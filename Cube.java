import java.util.Scanner;

public class Cube extends Item {
    boolean solved;
    boolean taken;
    public Cube(int order, String locationPrint, String inventoryPrint, String name, boolean solved, boolean taken) {
        super(order, locationPrint, inventoryPrint, name);
        this.solved = solved;
        this.taken = taken;
    }

    public void rubiksCubeSimulator(Item item) {
        char[] up = new char[] {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'};
        char[] front = new char[] {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'};
        char[] right = new char[] {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'};
        char[] back = new char[] {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        char[] left = new char[] {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] down = new char[] {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        char[][] cube = new char[][] {up, front, right, back, left, down};

        System.out.println("                                   ______");
        System.out.println("      W W W                       |      |");
        System.out.println("      W W W                       |   U  |");
        System.out.println("      W W W                 ______|______|______ ______");
        System.out.println("O O O G G G R R R B B B    |      |      |      |      |");
        System.out.println("O O O G G G R R R B B B    |   L  |   F  |   R  |   B  |");
        System.out.println("O O O G G G R R R B B B    |______|______|______|______|");
        System.out.println("      Y Y Y                       |      |");
        System.out.println("      Y Y Y                       |   D  |");
        System.out.println("      Y Y Y                       |______|");
        System.out.println("Possible commands:\nb, f, u, d, l, or r for face turns\nm, s, or e for slice turns\nx, y, or z and all prime for cube turns\n"
                + "or any combination of these, separated by a space\nAdd a ' after a letter for a counterclockwise turn\nAdd a 2 after a letter for a 180 degree turn\nq or quit to stop playing");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q"))) {
            findTurn(cube, input, item);
            input = sc.nextLine();
        }
        if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
            System.out.println("Thank you for playing!");
        }
    }

    /* Scan from keyboard, split input at spaces since there might be multiple entries into
     * String array: letters, check for quit, check for wrong multiple entries, loop through
     * letters and go to respective method based on letter, any other letter is invalid
     */
    public static void findTurn(char[][] cube, String input, Item item) {
        String[] commands = input.split(" ");

//        if(item.locationPrint.equals("")) {           TODO scramble only first time you start playing with the cube
//            int[] turns = ScrambleGenerator.generateScramble();
//            String algorithm = ScrambleGenerator.writeScramble(turns);
//            System.out.println();
//            findTurn(cube, algorithm, item);
//        }
        for(int i = 0; i < commands.length; i++) {
            String command = commands[i];
            if(input.equalsIgnoreCase("help") || input.equalsIgnoreCase("h")) {
                System.out.println("            ______");
                System.out.println("           |      |");
                System.out.println("           |   U  |");
                System.out.println("     ______|______|______ ______");
                System.out.println("    |      |      |      |      |");
                System.out.println("    |   L  |   F  |   R  |   B  |");
                System.out.println("    |______|______|______|______|");
                System.out.println("           |      |");
                System.out.println("           |   D  |");
                System.out.println("           |______|");
                System.out.println("Possible commands:\nb, f, u, d, l, or r for face turns\nm, s, or e for slice turns\nx, y, or z and all prime for cube turns\n"
                        + "or any combination of these, separated by a space\nAdd a ' after a letter for a counterclockwise turn\nAdd a 2 after a letter for a 180 degree turn\nq or quit to stop playing");
            }
            if(command.equalsIgnoreCase("u") || command.equalsIgnoreCase("u'") || command.equalsIgnoreCase("u2"))
                upTurn(cube, command);
            else if(command.equalsIgnoreCase("f") || command.equalsIgnoreCase("f'") || command.equalsIgnoreCase("f2"))
                frontTurn(cube, command);
            else if(command.equalsIgnoreCase("r") || command.equalsIgnoreCase("r'") || command.equalsIgnoreCase("r2"))
                rightTurn(cube, command);
            else if(command.equalsIgnoreCase("b") || command.equalsIgnoreCase("b'") || command.equalsIgnoreCase("b2"))
                backTurn(cube, command);
            else if(command.equalsIgnoreCase("l") || command.equalsIgnoreCase("l'") || command.equalsIgnoreCase("l2"))
                leftTurn(cube, command);
            else if(command.equalsIgnoreCase("d") || command.equalsIgnoreCase("d'") || command.equalsIgnoreCase("d2"))
                downTurn(cube, command);
            else if(command.equalsIgnoreCase("m") || command.equalsIgnoreCase("m'") || command.equalsIgnoreCase("m2"))
                middleTurn(cube, command);
            else if(command.equalsIgnoreCase("e") || command.equalsIgnoreCase("e'") || command.equalsIgnoreCase("e2"))
                equatorialTurn(cube, command);
            else if(command.equalsIgnoreCase("s") || command.equalsIgnoreCase("s'") || command.equalsIgnoreCase("s2"))
                standingTurn(cube, command);
            else if(command.equalsIgnoreCase("x") || command.equalsIgnoreCase("x'") || command.equalsIgnoreCase("x2"))
                xCubeTurn(cube, command);
            else if(command.equalsIgnoreCase("y") || command.equalsIgnoreCase("y'") || command.equalsIgnoreCase("y2"))
                yCubeTurn(cube, command);
            else if(command.equalsIgnoreCase("z") || command.equalsIgnoreCase("z'") || command.equalsIgnoreCase("z2"))
                zCubeTurn(cube, command);
            else if(i > 0 && command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q") ||
                    command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h") || command.equalsIgnoreCase("scramble")) {
                System.out.println("Invalid input!");
                break;
            }
            else {
                System.out.println("Invalid input!");
                break;
            }
        }
        if(!(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("h") || input.equalsIgnoreCase("help") || input.equalsIgnoreCase("scramble"))) {
            printCube(cube);
        }
    }

    /*Update d char array and other arrays a D turn would affect*/
    public static void downTurn(char[][] cube, String dTurn) {
        char temp;
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(dTurn.equalsIgnoreCase("d")) {
            temp = l[6]; l[6] = b[6]; b[6] = r[6]; r[6] = f[6]; f[6] = temp;
            temp = l[7]; l[7] = b[7]; b[7] = r[7]; r[7] = f[7]; f[7] = temp;
            temp = l[8]; l[8] = b[8]; b[8] = r[8]; r[8] = f[8]; f[8] = temp;
            temp = d[0]; d[0] = d[6]; d[6] = d[8]; d[8] = d[2]; d[2] = temp;
            temp = d[1]; d[1] = d[3]; d[3] = d[7]; d[7] = d[5]; d[5] = temp;
        }
        else if(dTurn.equalsIgnoreCase("d'")) {
            temp = l[6]; l[6] = f[6]; f[6] = r[6]; r[6] = b[6]; b[6] = temp;
            temp = l[7]; l[7] = f[7]; f[7] = r[7]; r[7] = b[7]; b[7] = temp;
            temp = l[8]; l[8] = f[8]; f[8] = r[8]; r[8] = b[8]; b[8] = temp;
            temp = d[0]; d[0] = d[2]; d[2] = d[8]; d[8] = d[6]; d[6] = temp;
            temp = d[1]; d[1] = d[5]; d[5] = d[7]; d[7] = d[3]; d[3] = temp;
        }
        else if(dTurn.equalsIgnoreCase("d2")) {
            temp = f[6]; f[6] = b[6]; b[6] = temp; temp = f[7]; f[7] = b[7]; b[7] = temp; temp = f[8]; f[8] = b[8]; b[8] = temp;
            temp = l[6]; l[6] = r[6]; r[6] = temp; temp = l[7]; l[7] = r[7]; r[7] = temp; temp = l[8]; l[8] = r[8]; r[8] = temp;
            temp = d[0]; d[0] = d[8]; d[8] = temp;
            temp = d[1]; d[1] = d[7]; d[7] = temp;
            temp = d[2]; d[2] = d[6]; d[6] = temp;
            temp = d[3]; d[3] = d[5]; d[5] = temp;
        }
    }

    /*Update u char array and other arrays that a u turn would affect*/
    public static void upTurn(char[][] cube, String uTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        if(uTurn.equalsIgnoreCase("u")) {
            temp = f[0]; f[0] = r[0]; r[0] = b[0]; b[0] = l[0]; l[0] = temp;
            temp = f[1]; f[1] = r[1]; r[1] = b[1]; b[1] = l[1]; l[1] = temp;
            temp = f[2]; f[2] = r[2]; r[2] = b[2]; b[2] = l[2]; l[2] = temp;
            temp = u[0]; u[0] = u[6]; u[6] = u[8]; u[8] = u[2]; u[2] = temp;
            temp = u[1]; u[1] = u[3]; u[3] = u[7]; u[7] = u[5]; u[5] = temp;
        }
        else if(uTurn.equalsIgnoreCase("u'")) {
            temp = f[0]; f[0] = l[0]; l[0] = b[0]; b[0] = r[0]; r[0] = temp;
            temp = f[1]; f[1] = l[1]; l[1] = b[1]; b[1] = r[1]; r[1] = temp;
            temp = f[2]; f[2] = l[2]; l[2] = b[2]; b[2] = r[2]; r[2] = temp;
            temp = u[0]; u[0] = u[2]; u[2] = u[8]; u[8] = u[6]; u[6] = temp;
            temp = u[1]; u[1] = u[5]; u[5] = u[7]; u[7] = u[3]; u[3] = temp;
        }
        else if(uTurn.equalsIgnoreCase("u2")) {
            temp = f[0]; f[0] = b[0]; b[0] = temp; temp = f[1]; f[1] = b[1]; b[1] = temp; temp = f[2]; f[2] = b[2]; b[2] = temp;
            temp = l[0]; l[0] = r[0]; r[0] = temp; temp = l[1]; l[1] = r[1]; r[1] = temp; temp = l[2]; l[2] = r[2]; r[2] = temp;
            temp = u[0]; u[0] = u[8]; u[8] = temp;
            temp = u[1]; u[1] = u[7]; u[7] = temp;
            temp = u[2]; u[2] = u[6]; u[6] = temp;
            temp = u[3]; u[3] = u[5]; u[5] = temp;
        }
    }

    /*Update r char array and any other arrays an R turn would affect*/
    public static void rightTurn(char[][] cube, String rTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] d = cube[5];
        if(rTurn.equalsIgnoreCase("r")) {
            temp = f[2]; f[2] = d[2]; d[2] = b[6]; b[6] = u[2]; u[2] = temp;
            temp = f[5]; f[5] = d[5]; d[5] = b[3]; b[3] = u[5]; u[5] = temp;
            temp = f[8]; f[8] = d[8]; d[8] = b[0]; b[0] = u[8]; u[8] = temp;
            temp = r[0]; r[0] = r[6]; r[6] = r[8]; r[8] = r[2]; r[2] = temp;
            temp = r[1]; r[1] = r[3]; r[3] = r[7]; r[7] = r[5]; r[5] = temp;
        }
        else if(rTurn.equalsIgnoreCase("r'")) {
            temp = f[2]; f[2] = u[2]; u[2] = b[6]; b[6] = d[2]; d[2] = temp;
            temp = f[5]; f[5] = u[5]; u[5] = b[3]; b[3] = d[5]; d[5] = temp;
            temp = f[8]; f[8] = u[8]; u[8] = b[0]; b[0] = d[8]; d[8] = temp;
            temp = r[0]; r[0] = r[2]; r[2] = r[8]; r[8] = r[6]; r[6] = temp;
            temp = r[1]; r[1] = r[5]; r[5] = r[7]; r[7] = r[3]; r[3] = temp;
        }
        else if(rTurn.equalsIgnoreCase("r2")) {
            temp = u[2]; u[2] = d[2]; d[2] = temp; temp = u[5]; u[5] = d[5]; d[5] = temp; temp = u[8]; u[8] = d[8]; d[8] = temp;
            temp = f[2]; f[2] = b[6]; b[6] = temp; temp = f[5]; f[5] = b[3]; b[3] = temp; temp = f[8]; f[8] = b[0]; b[0] = temp;
            temp = r[0]; r[0] = r[8]; r[8] = temp;
            temp = r[1]; r[1] = r[7]; r[7] = temp;
            temp = r[2]; r[2] = r[6]; r[6] = temp;
            temp = r[3]; r[3] = r[5]; r[5] = temp;
        }
    }

    /*Update l char array and any other arrays an L turn would affect*/
    public static void leftTurn(char[][]cube, String lTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(lTurn.equalsIgnoreCase("l")) {
            temp = f[0]; f[0] = u[0]; u[0] = b[8]; b[8] = d[0]; d[0] = temp;
            temp = f[3]; f[3] = u[3]; u[3] = b[5]; b[5] = d[3]; d[3] = temp;
            temp = f[6]; f[6] = u[6]; u[6] = b[2]; b[2] = d[6]; d[6] = temp;
            temp = l[0]; l[0] = l[6]; l[6] = l[8]; l[8] = l[2]; l[2] = temp;
            temp = l[1]; l[1] = l[3]; l[3] = l[7]; l[7] = l[5]; l[5] = temp;
        }
        else if(lTurn.equalsIgnoreCase("l'")) {
            temp = f[0]; f[0] = d[0]; d[0] = b[8]; b[8] = u[0]; u[0] = temp;
            temp = f[3]; f[3] = d[3]; d[3] = b[5]; b[5] = u[3]; u[3] = temp;
            temp = f[6]; f[6] = d[6]; d[6] = b[2]; b[2] = u[6]; u[6] = temp;
            temp = l[0]; l[0] = l[2]; l[2] = l[8]; l[8] = l[6]; l[6] = temp;
            temp = l[1]; l[1] = l[5]; l[5] = l[7]; l[7] = l[3]; l[3] = temp;
        }
        else if(lTurn.equalsIgnoreCase("l2")) {
            temp = u[0]; u[0] = d[0]; d[0] = temp; temp = u[3]; u[3] = d[3]; d[3] = temp; temp = u[6]; u[6] = d[6]; d[6] = temp;
            temp = f[0]; f[0] = b[8]; b[8] = temp; temp = f[3]; f[3] = b[5]; b[5] = temp; temp = f[6]; f[6] = b[2]; b[2] = temp;
            temp = l[0]; l[0] = l[8]; l[8] = temp;
            temp = l[1]; l[1] = l[7]; l[7] = temp;
            temp = l[2]; l[2] = l[6]; l[6] = temp;
            temp = l[3]; l[3] = l[5]; l[5] = temp;
        }
    }

    /*Update f char array and any other arrays an F turn would affect*/
    public static void frontTurn(char[][] cube, String fTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] l = cube[4];
        char[] d = cube[5];
        if(fTurn.equalsIgnoreCase("f")) {
            temp = u[6]; u[6] = l[8]; l[8] = d[2]; d[2] = r[0]; r[0] = temp;
            temp = u[7]; u[7] = l[5]; l[5] = d[1]; d[1] = r[3]; r[3] = temp;
            temp = u[8]; u[8] = l[2]; l[2] = d[0]; d[0] = r[6]; r[6] = temp;
            temp = f[0]; f[0] = f[6]; f[6] = f[8]; f[8] = f[2]; f[2] = temp;
            temp = f[1]; f[1] = f[3]; f[3] = f[7]; f[7] = f[5]; f[5] = temp;
        }
        else if(fTurn.equalsIgnoreCase("f'")) {
            temp = u[6]; u[6] = r[0]; r[0] = d[2]; d[2] = l[8]; l[8] = temp;
            temp = u[7]; u[7] = r[3]; r[3] = d[1]; d[1] = l[5]; l[5] = temp;
            temp = u[8]; u[8] = r[6]; r[6] = d[0]; d[0] = l[2]; l[2] = temp;
            temp = f[0]; f[0] = f[2]; f[2] = f[8]; f[8] = f[6]; f[6] = temp;
            temp = f[1]; f[1] = f[5]; f[5] = f[7]; f[7] = f[3]; f[3] = temp;
        }
        else if(fTurn.equalsIgnoreCase("f2")) {
            temp = u[6]; u[6] = d[2]; d[2] = temp; temp = u[7]; u[7] = d[1]; d[1] = temp; temp = u[8]; u[8] = d[0]; d[0] = temp;
            temp = l[2]; l[2] = r[6]; r[6] = temp; temp = l[5]; l[5] = r[3]; r[3] = temp; temp = l[8]; l[8] = r[0]; r[0] = temp;
            temp = f[0]; f[0] = f[8]; f[8] = temp;
            temp = f[1]; f[1] = f[7]; f[7] = temp;
            temp = f[2]; f[2] = f[6]; f[6] = temp;
            temp = f[3]; f[3] = f[5]; f[5] = temp;
        }
    }

    /*Update b char array and any other arrays an B turn would affect */
    public static void backTurn(char[][] cube, String bTurn) {
        char temp;
        char[] u = cube[0];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(bTurn.equalsIgnoreCase("b")) {
            temp = u[0]; u[0] = r[2]; r[2] = d[8]; d[8] = l[6]; l[6] = temp;
            temp = u[1]; u[1] = r[5]; r[5] = d[7]; d[7] = l[3]; l[3] = temp;
            temp = u[2]; u[2] = r[8]; r[8] = d[6]; d[6] = l[0]; l[0] = temp;
            temp = b[8]; b[8] = b[2]; b[2] = b[0]; b[0] = b[6]; b[6] = temp;
            temp = b[7]; b[7] = b[5]; b[5] = b[1]; b[1] = b[3]; b[3] = temp;
        }
        else if(bTurn.equalsIgnoreCase("b'")) {
            temp = u[0]; u[0] = l[6]; l[6] = d[8]; d[8] = r[2]; r[2] = temp;
            temp = u[1]; u[1] = l[3]; l[3] = d[7]; d[7] = r[5]; r[5] = temp;
            temp = u[2]; u[2] = l[0]; l[0] = d[6]; d[6] = r[8]; r[8] = temp;
            temp = b[8]; b[8] = b[6]; b[6] = b[0]; b[0] = b[2]; b[2] = temp;
            temp = b[7]; b[7] = b[3]; b[3] = b[1]; b[1] = b[5]; b[5] = temp;
        }
        else if(bTurn.equalsIgnoreCase("b2")) {
            temp = u[0]; u[0] = d[8]; d[8] = temp; temp = u[1]; u[1] = d[7]; d[7] = temp; temp = u[2]; u[2] = d[6]; d[6] = temp;
            temp = l[0]; l[0] = r[8]; r[8] = temp; temp = l[3]; l[3] = r[5]; r[5] = temp; temp = l[6]; l[6] = r[2]; r[2] = temp;
            temp = b[0]; b[0] = b[8]; b[8] = temp;
            temp = b[1]; b[1] = b[7]; b[7] = temp;
            temp = b[2]; b[2] = b[6]; b[6] = temp;
            temp = b[3]; b[3] = b[5]; b[5] = temp;
        }
    }

    /*Update arrays an M turn would affect */
    public static void middleTurn(char[][] cube, String mTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] b = cube[3];
        char[] d = cube[5];
        if(mTurn.equalsIgnoreCase("m")) {
            temp = u[1]; u[1] = b[7]; b[7] = d[1]; d[1] = f[1]; f[1] = temp;
            temp = u[4]; u[4] = b[4]; b[4] = d[4]; d[4] = f[4]; f[4] = temp;
            temp = u[7]; u[7] = b[1]; b[1] = d[7]; d[7] = f[7]; f[7] = temp;
        }
        else if(mTurn.equalsIgnoreCase("m'")) {
            temp = u[1]; u[1] = f[1]; f[1] = d[1]; d[1] = b[7]; b[7] = temp;
            temp = u[4]; u[4] = f[4]; f[4] = d[4]; d[4] = b[4]; b[4] = temp;
            temp = u[7]; u[7] = f[7]; f[7] = d[7]; d[7] = b[1]; b[1] = temp;
        }
        else if(mTurn.equalsIgnoreCase("m2")) {
            temp = u[1]; u[1] = d[1]; d[1] = temp; temp = u[4]; u[4] = d[4]; d[4] = temp; temp = u[7]; u[7] = d[7]; d[7] = temp;
            temp = f[1]; f[1] = b[7]; b[7] = temp; temp = f[4]; f[4] = b[4]; b[4] = temp; temp = f[7]; f[7] = b[1]; b[1] = temp;
        }
    }

    /*Update arrays an E turn would affect */
    public static void equatorialTurn(char[][] cube, String eTurn) {
        char temp;
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        if(eTurn.equalsIgnoreCase("e")) {
            temp = f[3]; f[3] = l[3]; l[3] = b[3]; b[3] = r[3]; r[3] = temp;
            temp = f[4]; f[4] = l[4]; l[4] = b[4]; b[4] = r[4]; r[4] = temp;
            temp = f[5]; f[5] = l[5]; l[5] = b[5]; b[5] = r[5]; r[5] = temp;
        }
        else if(eTurn.equalsIgnoreCase("e'")) {
            temp = f[3]; f[3] = r[3]; r[3] = b[3]; b[3] = l[3]; l[3] = temp;
            temp = f[4]; f[4] = r[4]; r[4] = b[4]; b[4] = l[4]; l[4] = temp;
            temp = f[5]; f[5] = r[5]; r[5] = b[5]; b[5] = l[5]; l[5] = temp;
        }
        else if(eTurn.equalsIgnoreCase("e2")) {
            temp = f[3]; f[3] = b[3]; b[3] = temp; temp = f[4]; f[4] = b[4]; b[4] = temp; temp = f[5]; f[5] = b[5]; b[5] = temp;
            temp = l[3]; l[3] = r[3]; r[3] = temp; temp = l[4]; l[4] = r[4]; r[4] = temp; temp = l[5]; l[5] = r[5]; r[5] = temp;
        }
    }

    /*Update arrays an S turn would affect */
    public static void standingTurn(char[][] cube, String sTurn) {
        char temp;
        char[] u = cube[0];
        char[] r = cube[2];
        char[] l = cube[4];
        char[] d = cube[5];
        if(sTurn.equalsIgnoreCase("s")) {
            temp = u[3]; u[3] = l[7]; l[7] = d[5]; d[5] = r[1]; r[1] = temp;
            temp = u[4]; u[4] = l[4]; l[4] = d[4]; d[4] = r[4]; r[4] = temp;
            temp = u[5]; u[5] = l[1]; l[1] = d[3]; d[3] = r[7]; r[7] = temp;
        }
        else if(sTurn.equalsIgnoreCase("s'")) {
            temp = u[3]; u[3] = r[1]; r[1] = d[5]; d[5] = l[7]; l[7] = temp;
            temp = u[4]; u[4] = r[4]; r[4] = d[4]; d[4] = l[4]; l[4] = temp;
            temp = u[5]; u[5] = r[7]; r[7] = d[3]; d[3] = l[1]; l[1] = temp;
        }
        else if(sTurn.equalsIgnoreCase("s2")) {
            temp = u[3]; u[3] = d[5]; d[5] = temp; temp = u[4]; u[4] = d[4]; d[4] = temp; temp = u[5]; u[5] = d[3]; d[3] = temp;
            temp = l[1]; l[1] = r[7]; r[7] = temp; temp = l[4]; l[4] = r[4]; r[4] = temp; temp = l[7]; l[7] = r[1]; r[1] = temp;
        }
    }

    /*Update all arrays with equivalent of r' and l turns and m' turn*/
    public static void xCubeTurn(char[][] cube, String xTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(xTurn.equalsIgnoreCase("x")) {
            temp = f[2]; f[2] = d[2]; d[2] = b[6]; b[6] = u[2]; u[2] = temp;
            temp = f[5]; f[5] = d[5]; d[5] = b[3]; b[3] = u[5]; u[5] = temp;
            temp = f[8]; f[8] = d[8]; d[8] = b[0]; b[0] = u[8]; u[8] = temp;

            temp = u[1]; u[1] = f[1]; f[1] = d[1]; d[1] = b[7]; b[7] = temp;
            temp = u[4]; u[4] = f[4]; f[4] = d[4]; d[4] = b[4]; b[4] = temp;
            temp = u[7]; u[7] = f[7]; f[7] = d[7]; d[7] = b[1]; b[1] = temp;

            temp = f[0]; f[0] = d[0]; d[0] = b[8]; b[8] = u[0]; u[0] = temp;
            temp = f[3]; f[3] = d[3]; d[3] = b[5]; b[5] = u[3]; u[3] = temp;
            temp = f[6]; f[6] = d[6]; d[6] = b[2]; b[2] = u[6]; u[6] = temp;

            temp = r[0]; r[0] = r[6]; r[6] = r[8]; r[8] = r[2]; r[2] = temp;
            temp = r[1]; r[1] = r[3]; r[3] = r[7]; r[7] = r[5]; r[5] = temp;
            temp = l[0]; l[0] = l[2]; l[2] = l[8]; l[8] = l[6]; l[6] = temp;
            temp = l[1]; l[1] = l[5]; l[5] = l[7]; l[7] = l[3]; l[3] = temp;
        }
        else if(xTurn.equalsIgnoreCase("x'")) {
            temp = f[2]; f[2] = u[2]; u[2] = b[6]; b[6] = d[2]; d[2] = temp;
            temp = f[5]; f[5] = u[5]; u[5] = b[3]; b[3] = d[5]; d[5] = temp;
            temp = f[8]; f[8] = u[8]; u[8] = b[0]; b[0] = d[8]; d[8] = temp;

            temp = u[1]; u[1] = b[7]; b[7] = d[1]; d[1] = f[1]; f[1] = temp;
            temp = u[4]; u[4] = b[4]; b[4] = d[4]; d[4] = f[4]; f[4] = temp;
            temp = u[7]; u[7] = b[1]; b[1] = d[7]; d[7] = f[7]; f[7] = temp;

            temp = f[0]; f[0] = u[0]; u[0] = b[8]; b[8] = d[0]; d[0] = temp;
            temp = f[3]; f[3] = u[3]; u[3] = b[5]; b[5] = d[3]; d[3] = temp;
            temp = f[6]; f[6] = u[6]; u[6] = b[2]; b[2] = d[6]; d[6] = temp;

            temp = r[0]; r[0] = r[2]; r[2] = r[8]; r[8] = r[6]; r[6] = temp;
            temp = r[1]; r[1] = r[5]; r[5] = r[7]; r[7] = r[3]; r[3] = temp;
            temp = l[0]; l[0] = l[6]; l[6] = l[8]; l[8] = l[2]; l[2] = temp;
            temp = l[1]; l[1] = l[3]; l[3] = l[7]; l[7] = l[5]; l[5] = temp;
        }
        else if(xTurn.equalsIgnoreCase("x2")) {
            temp = u[2]; u[2] = d[2]; d[2] = temp; temp = u[5]; u[5] = d[5]; d[5] = temp; temp = u[8]; u[8] = d[8]; d[8] = temp;
            temp = f[2]; f[2] = b[6]; b[6] = temp; temp = f[5]; f[5] = b[3]; b[3] = temp; temp = f[8]; f[8] = b[0]; b[0] = temp;
            temp = u[1]; u[1] = d[1]; d[1] = temp; temp = u[4]; u[4] = d[4]; d[4] = temp; temp = u[7]; u[7] = d[7]; d[7] = temp;
            temp = f[1]; f[1] = b[7]; b[7] = temp; temp = f[4]; f[4] = b[4]; b[4] = temp; temp = f[7]; f[7] = b[1]; b[1] = temp;
            temp = u[0]; u[0] = d[0]; d[0] = temp; temp = u[3]; u[3] = d[3]; d[3] = temp; temp = u[6]; u[6] = d[6]; d[6] = temp;
            temp = f[0]; f[0] = b[8]; b[8] = temp; temp = f[3]; f[3] = b[5]; b[5] = temp; temp = f[6]; f[6] = b[2]; b[2] = temp;
            temp = r[0]; r[0] = r[8]; r[8] = temp;
            temp = r[1]; r[1] = r[7]; r[7] = temp;
            temp = r[2]; r[2] = r[6]; r[6] = temp;
            temp = r[3]; r[3] = r[5]; r[5] = temp;
            temp = l[0]; l[0] = l[8]; l[8] = temp;
            temp = l[1]; l[1] = l[7]; l[7] = temp;
            temp = l[2]; l[2] = l[6]; l[6] = temp;
            temp = l[3]; l[3] = l[5]; l[5] = temp;
        }
    }

    public static void yCubeTurn(char[][] cube, String yTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(yTurn.equalsIgnoreCase("y")) {
            temp = f[0]; f[0] = r[0]; r[0] = b[0]; b[0] = l[0]; l[0] = temp;
            temp = f[1]; f[1] = r[1]; r[1] = b[1]; b[1] = l[1]; l[1] = temp;
            temp = f[2]; f[2] = r[2]; r[2] = b[2]; b[2] = l[2]; l[2] = temp;

            temp = l[6]; l[6] = f[6]; f[6] = r[6]; r[6] = b[6]; b[6] = temp;
            temp = l[7]; l[7] = f[7]; f[7] = r[7]; r[7] = b[7]; b[7] = temp;
            temp = l[8]; l[8] = f[8]; f[8] = r[8]; r[8] = b[8]; b[8] = temp;

            temp = f[3]; f[3] = r[3]; r[3] = b[3]; b[3] = l[3]; l[3] = temp;
            temp = f[4]; f[4] = r[4]; r[4] = b[4]; b[4] = l[4]; l[4] = temp;
            temp = f[5]; f[5] = r[5]; r[5] = b[5]; b[5] = l[5]; l[5] = temp;

            temp = u[0]; u[0] = u[6]; u[6] = u[8]; u[8] = u[2]; u[2] = temp;
            temp = u[1]; u[1] = u[3]; u[3] = u[7]; u[7] = u[5]; u[5] = temp;
            temp = d[0]; d[0] = d[2]; d[2] = d[8]; d[8] = d[6]; d[6] = temp;
            temp = d[1]; d[1] = d[5]; d[5] = d[7]; d[7] = d[3]; d[3] = temp;
        }
        else if(yTurn.equalsIgnoreCase("y'")) {
            temp = f[0]; f[0] = l[0]; l[0] = b[0]; b[0] = r[0]; r[0] = temp;
            temp = f[1]; f[1] = l[1]; l[1] = b[1]; b[1] = r[1]; r[1] = temp;
            temp = f[2]; f[2] = l[2]; l[2] = b[2]; b[2] = r[2]; r[2] = temp;

            temp = l[6]; l[6] = b[6]; b[6] = r[6]; r[6] = f[6]; f[6] = temp;
            temp = l[7]; l[7] = b[7]; b[7] = r[7]; r[7] = f[7]; f[7] = temp;
            temp = l[8]; l[8] = b[8]; b[8] = r[8]; r[8] = f[8]; f[8] = temp;

            temp = f[3]; f[3] = l[3]; l[3] = b[3]; b[3] = r[3]; r[3] = temp;
            temp = f[4]; f[4] = l[4]; l[4] = b[4]; b[4] = r[4]; r[4] = temp;
            temp = f[5]; f[5] = l[5]; l[5] = b[5]; b[5] = r[5]; r[5] = temp;

            temp = u[0]; u[0] = u[2]; u[2] = u[8]; u[8] = u[6]; u[6] = temp;
            temp = u[1]; u[1] = u[5]; u[5] = u[7]; u[7] = u[3]; u[3] = temp;
            temp = d[0]; d[0] = d[6]; d[6] = d[8]; d[8] = d[2]; d[2] = temp;
            temp = d[1]; d[1] = d[3]; d[3] = d[7]; d[7] = d[5]; d[5] = temp;
        }
        else if(yTurn.equalsIgnoreCase("y2")) {
            temp = l[0]; l[0] = r[0]; r[0] = temp; temp = l[1]; l[1] = r[1]; r[1] = temp; temp = l[2]; l[2] = r[2]; r[2] = temp;
            temp = l[3]; l[3] = r[3]; r[3] = temp; temp = l[4]; l[4] = r[4]; r[4] = temp; temp = l[5]; l[5] = r[5]; r[5] = temp;
            temp = l[6]; l[6] = r[6]; r[6] = temp; temp = l[7]; l[7] = r[7]; r[7] = temp; temp = l[8]; l[8] = r[8]; r[8] = temp;

            temp = f[0]; f[0] = b[0]; b[0] = temp; temp = f[1]; f[1] = b[1]; b[1] = temp; temp = f[2]; f[2] = b[2]; b[2] = temp;
            temp = f[3]; f[3] = b[3]; b[3] = temp; temp = f[4]; f[4] = b[4]; b[4] = temp; temp = f[5]; f[5] = b[5]; b[5] = temp;
            temp = f[6]; f[6] = b[6]; b[6] = temp; temp = f[7]; f[7] = b[7]; b[7] = temp; temp = f[8]; f[8] = b[8]; b[8] = temp;

            temp = u[0]; u[0] = u[8]; u[8] = temp;
            temp = u[1]; u[1] = u[7]; u[7] = temp;
            temp = u[2]; u[2] = u[6]; u[6] = temp;
            temp = u[3]; u[3] = u[5]; u[5] = temp;
            temp = d[0]; d[0] = d[8]; d[8] = temp;
            temp = d[1]; d[1] = d[7]; d[7] = temp;
            temp = d[2]; d[2] = d[6]; d[6] = temp;
            temp = d[3]; d[3] = d[5]; d[5] = temp;
        }
    }

    public static void zCubeTurn(char[][] cube, String zTurn) {
        char temp;
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        if(zTurn.equalsIgnoreCase("z")) {
            temp = u[6]; u[6] = l[8]; l[8] = d[2]; d[2] = r[0]; r[0] = temp;
            temp = u[7]; u[7] = l[5]; l[5] = d[1]; d[1] = r[3]; r[3] = temp;
            temp = u[8]; u[8] = l[2]; l[2] = d[0]; d[0] = r[6]; r[6] = temp;

            temp = u[3]; u[3] = l[7]; l[7] = d[5]; d[5] = r[1]; r[1] = temp;
            temp = u[4]; u[4] = l[4]; l[4] = d[4]; d[4] = r[4]; r[4] = temp;
            temp = u[5]; u[5] = l[1]; l[1] = d[3]; d[3] = r[7]; r[7] = temp;

            temp = u[0]; u[0] = l[6]; l[6] = d[8]; d[8] = r[2]; r[2] = temp;
            temp = u[1]; u[1] = l[3]; l[3] = d[7]; d[7] = r[5]; r[5] = temp;
            temp = u[2]; u[2] = l[0]; l[0] = d[6]; d[6] = r[8]; r[8] = temp;

            temp = f[0]; f[0] = f[6]; f[6] = f[8]; f[8] = f[2]; f[2] = temp;
            temp = f[1]; f[1] = f[3]; f[3] = f[7]; f[7] = f[5]; f[5] = temp;
            temp = b[8]; b[8] = b[6]; b[6] = b[0]; b[0] = b[2]; b[2] = temp;
            temp = b[7]; b[7] = b[3]; b[3] = b[1]; b[1] = b[5]; b[5] = temp;
        }
        else if(zTurn.equalsIgnoreCase("z'")) {
            temp = u[6]; u[6] = r[0]; r[0] = d[2]; d[2] = l[8]; l[8] = temp;
            temp = u[7]; u[7] = r[3]; r[3] = d[1]; d[1] = l[5]; l[5] = temp;
            temp = u[8]; u[8] = r[6]; r[6] = d[0]; d[0] = l[2]; l[2] = temp;

            temp = u[3]; u[3] = r[1]; r[1] = d[5]; d[5] = l[7]; l[7] = temp;
            temp = u[4]; u[4] = r[4]; r[4] = d[4]; d[4] = l[4]; l[4] = temp;
            temp = u[5]; u[5] = r[7]; r[7] = d[3]; d[3] = l[1]; l[1] = temp;

            temp = u[0]; u[0] = r[2]; r[2] = d[8]; d[8] = l[6]; l[6] = temp;
            temp = u[1]; u[1] = r[5]; r[5] = d[7]; d[7] = l[3]; l[3] = temp;
            temp = u[2]; u[2] = r[8]; r[8] = d[6]; d[6] = l[0]; l[0] = temp;

            temp = f[0]; f[0] = f[2]; f[2] = f[8]; f[8] = f[6]; f[6] = temp;
            temp = f[1]; f[1] = f[5]; f[5] = f[7]; f[7] = f[3]; f[3] = temp;
            temp = b[8]; b[8] = b[2]; b[2] = b[0]; b[0] = b[6]; b[6] = temp;
            temp = b[7]; b[7] = b[5]; b[5] = b[1]; b[1] = b[3]; b[3] = temp;
        }
        else if(zTurn.equalsIgnoreCase("z2")) {
            temp = u[0]; u[0] = d[8]; d[8] = temp; temp = u[1]; u[1] = d[7]; d[7] = temp; temp = u[2]; u[2] = d[6]; d[6] = temp;
            temp = u[3]; u[3] = d[5]; d[5] = temp; temp = u[4]; u[4] = d[4]; d[4] = temp; temp = u[5]; u[5] = d[3]; d[3] = temp;
            temp = u[6]; u[6] = d[2]; d[2] = temp; temp = u[7]; u[7] = d[1]; d[1] = temp; temp = u[8]; u[8] = d[0]; d[0] = temp;

            temp = l[0]; l[0] = r[8]; r[8] = temp; temp = l[1]; l[1] = r[7]; r[7] = temp; temp = l[2]; l[2] = r[6]; r[6] = temp;
            temp = l[3]; l[3] = r[5]; r[5] = temp; temp = l[4]; l[4] = r[4]; r[4] = temp; temp = l[5]; l[5] = r[3]; r[3] = temp;
            temp = l[6]; l[6] = r[2]; r[2] = temp; temp = l[7]; l[7] = r[1]; r[1] = temp; temp = l[8]; l[8] = r[0]; r[0] = temp;

            temp = f[0]; f[0] = f[8]; f[8] = temp;
            temp = f[1]; f[1] = f[7]; f[7] = temp;
            temp = f[2]; f[2] = f[6]; f[6] = temp;
            temp = f[3]; f[3] = f[5]; f[5] = temp;
            temp = b[0]; b[0] = b[8]; b[8] = temp;
            temp = b[1]; b[1] = b[7]; b[7] = temp;
            temp = b[2]; b[2] = b[6]; b[6] = temp;
            temp = b[3]; b[3] = b[5]; b[5] = temp;
        }
    }

    public static void printCube(char[][] cube) {
        char[] u = cube[0];
        char[] f = cube[1];
        char[] r = cube[2];
        char[] b = cube[3];
        char[] l = cube[4];
        char[] d = cube[5];
        System.out.println("      " + u[0] +  " " + u[1] + " " + u[2]);
        System.out.println("      " + u[3] +  " " + u[4] + " " + u[5]);
        System.out.println("      " + u[6] +  " " + u[7] + " " + u[8]);
        System.out.println(l[0] + " " + l[1] + " " + l[2] + " " + f[0] + " " + f[1] + " " + f[2] + " " + r[0] + " " + r[1] + " " + r[2] + " " + b[0] + " " + b[1] + " " + b[2]);
        System.out.println(l[3] + " " + l[4] + " " + l[5] + " " + f[3] + " " + f[4] + " " + f[5] + " " + r[3] + " " + r[4] + " " + r[5] + " " + b[3] + " " + b[4] + " "+ b[5]);
        System.out.println(l[6] + " " + l[7] + " " + l[8] + " " + f[6] + " " + f[7] + " " + f[8] + " " + r[6] + " " + r[7] + " " + r[8] + " " + b[6] + " " + b[7] + " "+ b[8]);
        System.out.println("      " + d[0] +  " " + d[1] + " " + d[2]);
        System.out.println("      " + d[3] +  " " + d[4] + " " + d[5]);
        System.out.println("      " + d[6] +  " " + d[7] + " " + d[8]);
    }
}
