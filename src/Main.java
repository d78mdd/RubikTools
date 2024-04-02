
public class Main {

    public static void main(String[] args) {

        char[][] side1 = {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
        char[][] side2 = {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};
        char[][] side3 = {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
        char[][] side4 = {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};
        char[][] side5 = {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
        char[][] side6 = {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};

        Cube cube1 = new Cube(side1, side2, side3, side4, side5, side6);

        cube1.showAllSides();
        System.out.println();

        cube1.R();

        cube1.showAllSides();

    }
}