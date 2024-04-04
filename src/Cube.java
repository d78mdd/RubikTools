import java.util.Arrays;

public class Cube {

    // sides (faces) with 9 pieces each
    // assuming classic Rubik's cube 3x3 with flat single-colored pieces
    private char[][] sideRight;
    private char[][] sideLeft;
    private char[][] sideUp;
    private char[][] sideDown;
    private char[][] sideFront;
    private char[][] sideBack;


    public Cube(char[][] sideRight, char[][] sideLeft, char[][] sideUp, char[][] sideDown, char[][] sideFront, char[][] sideBack) {
        this.sideRight = copy(sideRight);
        this.sideLeft = copy(sideLeft);
        this.sideUp = copy(sideUp);
        this.sideDown = copy(sideDown);
        this.sideFront = copy(sideFront);
        this.sideBack = copy(sideBack);
    }


    /**
     * rotate the entire cube on the X axis 90 degree
     */
    private void rotateCubeX() {
        rotateCube('x');
    }

    /**
     * rotate the entire cube on the Y axis 90 degree
     */
    private void rotateCubeY() {
        rotateCube('y');
    }


    /**
     * rotate the entire 3x3 cube clockwise 90 degree
     * // https://ruwix.com/the-rubiks-cube/notation/
     *
     * @param axis x or y
     */
    private void rotateCube(char axis) {

        char[][] tempSide;

        switch (axis) {
            case 'x':
                tempSide = sideFront;
                sideFront = sideDown;
                sideDown = sideBack;
                sideBack = sideUp;
                sideUp = tempSide;
                sideRight = rotateC(sideRight);
                sideLeft = rotateAC(sideLeft);
                break;
            case 'y':
                tempSide = sideFront;
                sideFront = sideRight;
                sideRight = mirror(sideBack);
                sideBack = mirror(sideLeft);
                sideLeft = tempSide;
                sideUp = rotateC(sideUp);
                sideDown = rotateAC(sideDown);
                break;
            case 'z':

                break;
        }
    }


    /**
     * for internal use with method for rotating a physical 3x3x1 layer's 4 sets of 3 "attached" pieces or method for rotating the entire cube in 3D space
     * analogous to re-sticking a face's 9 stickers
     *
     * @param side the face/side (9 pieces) to be rotated
     * @return a new rotated face/side - clockwise 90 degree
     */
    private char[][] rotateC(char[][] side) {
        return new char[][]{
                {side[2][0], side[1][0], side[0][0]},
                {side[2][1], side[1][1], side[0][1]},
                {side[2][2], side[1][2], side[0][2]}
        };
    }

    /**
     * for internal use with method for rotating a physical 3x3x1 layer's 4 sets of 3 "attached" pieces or method for rotating the entire cube in 3D space
     * analogous to re-sticking a face's 9 stickers
     *
     * @param side the face/side (9 pieces) to be rotated
     * @return a new rotated face/side - anti-clockwise 90 degree
     */
    private char[][] rotateAC(char[][] side) {
        return rotateC(rotateC(rotateC(side)));
    }

    /**
     * for internal use with rotating the cube in 3D or rotating a 3x3x1 outer layer
     *
     * @param side the face/side (9 pieces) to be rotated
     * @return a new rotated face/side 180 degree
     */
    private char[][] mirror(char[][] side) {
        return rotateC(rotateC(side));
    }


    private void rotateUpperLayerC() {
        rotateCubeX();
        rotateCubeX();
        rotateCubeX();
        rotateFrontLayerC();
        rotateCubeX();
    }

    private void rotateBottomLayerC() {
        rotateCubeX();
        rotateFrontLayerC();
        rotateCubeX();
        rotateCubeX();
        rotateCubeX();
    }


    /**
     * rotate the front 3x3x1 layer clockwise 90 degree
     */
    private void rotateFrontLayerC() {

        // part 1/2:
        System.out.println("rotating 9 front face pieces clockwise");

        sideFront = rotateC(sideFront);


        // Part 2/2:
        System.out.println("rotating the 4x3 pieces 'around' the front face");

        char[][] tempSide = copy(sideUp);

        sideUp[2][0] = sideLeft[0][2];
        sideUp[2][1] = sideLeft[1][2];
        sideUp[2][2] = sideLeft[2][2];

        sideLeft[0][2] = sideDown[0][0];
        sideLeft[1][2] = sideDown[0][1];
        sideLeft[2][2] = sideDown[0][2];

        sideDown[0][0] = sideRight[0][0];
        sideDown[0][1] = sideRight[1][0];
        sideDown[0][2] = sideRight[2][0];

        sideRight[0][0] = tempSide[2][0];
        sideRight[1][0] = tempSide[2][1];
        sideRight[2][0] = tempSide[2][2];
    }

    /**
     * rotate the front 3x3x1 layer anti-clockwise 90 degree
     */
    private void rotateFrontLayerAC() {
        rotateFrontLayerC();
        rotateFrontLayerC();
        rotateFrontLayerC();
    }

    private void rotateBackLayerC() {
        rotateCubeX();
        rotateCubeX();
        rotateFrontLayerC();
        rotateCubeX();
        rotateCubeX();
    }


    private void rotateLeftLayerC() {
        rotateCubeY();
        rotateCubeY();
        rotateCubeY();
        rotateFrontLayerC();
        rotateCubeY();
    }

    private void rotateRightLayerC() {
        rotateCubeY();
        rotateFrontLayerC();
        rotateCubeY();
        rotateCubeY();
        rotateCubeY();
    }


    // shortened method names for each movement
    // according to https://rubik.bg/bg/content/10-formuli-za-podrezhdane-na-rubik-kub
    public void R() {
        rotateRightLayerC();
    }

    public void Ri() {
        rotateRightLayerC();
        rotateRightLayerC();
        rotateRightLayerC();
    }

    public void L() {
        rotateLeftLayerC();
    }

    public void Li() {
        rotateLeftLayerC();
        rotateLeftLayerC();
        rotateLeftLayerC();
    }

    public void B() {
        rotateBackLayerC();
    }

    public void Bi() {
        rotateBackLayerC();
        rotateBackLayerC();
        rotateBackLayerC();
    }

    public void D() {
        rotateBottomLayerC();
    }

    public void Di() {
        rotateBottomLayerC();
        rotateBottomLayerC();
        rotateBottomLayerC();
    }

    public void F() {
        rotateFrontLayerC();
    }

    public void Fi() {
        rotateFrontLayerAC();
    }

    public void U() {
        rotateUpperLayerC();
    }

    public void Ui() {
        rotateUpperLayerC();
        rotateUpperLayerC();
        rotateUpperLayerC();
    }


    public void showSide(String side) {
        switch (side) {
            case "right":
                System.out.println(Arrays.deepToString(sideRight));
                break;
            case "left":
                System.out.println(Arrays.deepToString(sideLeft));
                break;
            case "up":
                System.out.println(Arrays.deepToString(sideUp));
                break;
            case "down":
                System.out.println(Arrays.deepToString(sideDown));
                break;
            case "front":
                System.out.println(Arrays.deepToString(sideFront));
                break;
            case "back":
                System.out.println(Arrays.deepToString(sideBack));
                break;
        }

    }


    public void printCube() {

        // print top side
        for (int i = 0; i < 3; i++) {
            System.out.print("        ");
            System.out.println(sideUp[i][0] + " " + sideUp[i][1] + " " + sideUp[i][2]);
        }

        System.out.println();

        // print left, front and right side
        for (int i = 0; i < 3; i++) {
            System.out.print(sideLeft[i][0] + " " + sideLeft[i][1] + " " + sideLeft[i][2] + "   ");
            System.out.print(sideFront[i][0] + " " + sideFront[i][1] + " " + sideFront[i][2] + "   ");
            System.out.println(sideRight[i][0] + " " + sideRight[i][1] + " " + sideRight[i][2]);
        }

        System.out.println();

        // print bottom side
        for (int i = 0; i < 3; i++) {
            System.out.print("        ");
            System.out.println(sideDown[i][0] + " " + sideDown[i][1] + " " + sideDown[i][2]);
        }

        System.out.println();

        // print back side
        for (int i = 0; i < 3; i++) {
            System.out.print("        ");
            System.out.println(sideBack[i][0] + " " + sideBack[i][1] + " " + sideBack[i][2]);
        }
    }

    public void printFrontSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideFront[i][0] + " " + sideFront[i][1] + " " + sideFront[i][2]);
        }
    }

    public void printBackSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideBack[i][0] + " " + sideBack[i][1] + " " + sideBack[i][2]);
        }
    }

    public void printRightSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideRight[i][0] + " " + sideRight[i][1] + " " + sideRight[i][2]);
        }
    }

    public void printLeftSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideLeft[i][0] + " " + sideLeft[i][1] + " " + sideLeft[i][2]);
        }
    }

    public void printTopSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideUp[i][0] + " " + sideUp[i][1] + " " + sideUp[i][2]);
        }
    }

    public void printBottomSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideDown[i][0] + " " + sideDown[i][1] + " " + sideDown[i][2]);
        }
    }


    public void showAllSides() {
        showSide("right");
        showSide("left");
        showSide("up");
        showSide("down");
        showSide("front");
        showSide("back");
    }


    public void solveLayer1() {

    }

    public void solveLayer2() {

    }

    public void solveLayer3() {

    }

    public void solve() {
        solveLayer1();
        solveLayer2();
        solveLayer3();
    }


    /**
     * get a reference to a new char[][] array
     *
     * @param side the source array (cube side/face 3x3)
     * @return the new array
     */
    private static char[][] copy(char[][] side) {
        return Arrays.stream(side).map(char[]::clone).toArray(char[][]::new);
    }


}
