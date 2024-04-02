
public class Main {

    public static void main(String[] args) {

        int[][] side1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] side2 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] side3 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] side4 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] side5 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] side6 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};

        Cube cube1 = new Cube(side1, side2, side3, side4, side5, side6);

        cube1.outputCurrentState();

    }
}