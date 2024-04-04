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

//        cube1.printCube();
//        System.out.println();

        cube1.D();
        cube1.rotateCubeYC();
        cube1.rotateFrontLayerC();
        cube1.printCube();
        cube1.rotateCubeYC();
        cube1.rotateCubeYC();
        cube1.rotateCubeYC();

//        doPatternTheEasyCheckerBoard(cube1); ok
//        doPatternPython(cube1); partly ok

//        doPatternTheSuperflip(cube1);
//        doPatternCubeInACubeInACube(cube1);

//        cube1.printCube();

    }

    private static void doPatternCubeInACubeInACube(Cube cube) {

    }

    private static void doPatternPython(Cube cube) {
        cube.F();
        cube.F();
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
        cube.L();
        cube.L();
    }

    private static void doPatternTheSuperflip(Cube cube) {

    }

    private static void doPatternTheEasyCheckerBoard(Cube cube) {
        cube.M();
        cube.M();
        cube.E();
        cube.E();
        cube.S();
        cube.S();
    }
}