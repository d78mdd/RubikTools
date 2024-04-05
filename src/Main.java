// patterns:
// https://ruwix.com/the-rubiks-cube/rubiks-cube-patterns-algorithms/

public class Main {

    public static void main(String[] args) {

        char[][] side1 = {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
        char[][] side2 = {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};
        char[][] side3 = {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
        char[][] side4 = {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
        char[][] side5 = {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
        char[][] side6 = {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};

        Cube cube1 = new Cube(side1, side2, side3, side4, side5, side6);
        Cube cube2 = new Cube(side1, side2, side3, side4, side5, side6);
        Cube cube3 = new Cube(side1, side2, side3, side4, side5, side6);
        Cube cube4 = new Cube(side1, side2, side3, side4, side5, side6);


        doPatternTheEasyCheckerboard(cube1);
        System.out.println("demonstration of Pattern 'The Easy Checkerboard'");
        System.out.println();
        cube1.printCube();

        doPatternPython(cube2);
        System.out.println();
        System.out.println();
        System.out.println("demonstration of Pattern 'Python'");
        System.out.println();
        cube2.printCube();

        doPatternTheSuperflip(cube3);
        System.out.println();
        System.out.println();
        System.out.println("demonstration of Pattern 'The Superflip'");
        System.out.println();
        cube3.printCube();

        doPatternCubeInACubeInACube(cube4);
        System.out.println();
        System.out.println();
        System.out.println("demonstration of Pattern 'Cube In A Cube In A Cube'");
        System.out.println();
        cube4.printCube();
    }

    private static void doPatternCubeInACubeInACube(Cube cube) {
        cube.Ui();
        cube.Li();
        cube.Ui();
        cube.Fi();
        cube.R2();
        cube.Bi();
        cube.R();
        cube.F();
        cube.U();
        cube.B2();
        cube.U();
        cube.Bi();
        cube.L();
        cube.Ui();
        cube.F();
        cube.U();
        cube.R();
        cube.Fi();
    }

    private static void doPatternPython(Cube cube) {
        cube.F2();
        cube.Ri();
        cube.Bi();
        cube.U();
        cube.Ri();
        cube.L();
        cube.Fi();
        cube.L();
        cube.Fi();
        cube.B();
        cube.Di();
        cube.R();
        cube.B();
        cube.L2();
    }

    private static void doPatternTheSuperflip(Cube cube) {

        cube.U();
        cube.R2();
        cube.F();
        cube.B();
        cube.R();
        cube.B2();
        cube.R();
        cube.U2();
        cube.L();
        cube.B2();
        cube.R();
        cube.Ui();
        cube.Di();
        cube.R2();
        cube.F();
        cube.Ri();
        cube.L();
        cube.B2();
        cube.U2();
        cube.F2();
    }

    private static void doPatternTheEasyCheckerboard(Cube cube) {
        cube.M2();
        cube.E2();
        cube.S2();
    }
}