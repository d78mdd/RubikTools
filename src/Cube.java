import java.util.Arrays;

/**
 * used notations: https://rubik.bg/bg/content/10-formuli-za-podrezhdane-na-rubik-kub
 */
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
     * rotate the entire cube on the X axis 90 degree clockwise
     */
    private void rotateCubeXC() {
        rotateCube('x');
    }

    /**
     * rotate the entire cube on the X axis 90 degree anti-clockwise
     */
    private void rotateCubeXAC() {
        rotateCube('x');
        rotateCube('x');
        rotateCube('x');
    }

    /**
     * rotate the entire cube on the Y axis 90 degree clockwise
     */
    private void rotateCubeYC() {
        rotateCube('y');
    }

    /**
     * rotate the entire cube on the Y axis 90 degree anti-clockwise
     */
    private void rotateCubeYAC() {
        rotateCube('y');
        rotateCube('y');
        rotateCube('y');
    }

    /**
     * rotate the entire cube on the Z axis 90 degree clockwise
     */
    private void rotateCubeZC() {
        rotateCubeXC();
        rotateCubeYC();
        rotateCubeXAC();
    }

    /**
     * rotate the entire cube on the Z axis 90 degree anti-clockwise
     */
    private void rotateCubeZAC() {
        rotateCubeXC();
        rotateCubeYAC();
        rotateCubeXAC();
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


    /**
     * rotate Up layer clockwise 90 degree
     */
    private void rotateUpperLayerC() {
        rotateCubeXC();
        rotateCubeXC();
        rotateCubeXC();
        rotateFrontLayerC();
        rotateCubeXC();
    }

    /**
     * rotate Down layer clockwise 90 degree
     */
    private void rotateBottomLayerC() {
        rotateCubeXC();
        rotateFrontLayerC();
        rotateCubeXC();
        rotateCubeXC();
        rotateCubeXC();
    }


    /**
     * rotate the front 3x3x1 layer clockwise 90 degree
     */
    private void rotateFrontLayerC() {

        // part 1/2:
//        System.out.println("rotating 9 front face pieces clockwise");

        sideFront = rotateC(sideFront);


        // Part 2/2:
//        System.out.println("rotating the 4x3 pieces 'around' the front face");

        char[][] tempSide = copy(sideUp);

        sideUp[2][0] = sideLeft[2][2];
        sideUp[2][1] = sideLeft[1][2];
        sideUp[2][2] = sideLeft[0][2];

        sideLeft[0][2] = sideDown[0][0];
        sideLeft[1][2] = sideDown[0][1];
        sideLeft[2][2] = sideDown[0][2];

        sideDown[0][0] = sideRight[2][0];
        sideDown[0][1] = sideRight[1][0];
        sideDown[0][2] = sideRight[0][0];

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

    /**
     * rotate Back layer clockwise 90 degree
     */
    private void rotateBackLayerC() {
        rotateCubeXC();
        rotateCubeXC();
        rotateFrontLayerC();
        rotateCubeXC();
        rotateCubeXC();
    }


    /**
     * rotate Left layer clockwise 90 degree
     */
    private void rotateLeftLayerC() {
        rotateCubeYC();
        rotateCubeYC();
        rotateCubeYC();
        rotateFrontLayerC();
        rotateCubeYC();
    }

    /**
     * rotate Right layer clockwise 90 degree
     */
    private void rotateRightLayerC() {
        rotateCubeYC();
        rotateFrontLayerC();
        rotateCubeYC();
        rotateCubeYC();
        rotateCubeYC();
    }

    /**
     * rotate Left layer anti-clockwise 90 degree
     */
    private void rotateLeftLayerAC() {
        rotateLeftLayerC();
        rotateLeftLayerC();
        rotateLeftLayerC();
    }

    /**
     * rotate Right layer anti-clockwise 90 degree
     */
    private void rotateRightLayerAC() {
        rotateRightLayerC();
        rotateRightLayerC();
        rotateRightLayerC();
    }

    /**
     * rotate Down layer anti-clockwise 90 degree
     */
    private void rotateBottomLayerAC() {
        rotateBottomLayerC();
        rotateBottomLayerC();
        rotateBottomLayerC();
    }

    /**
     * rotate Up layer anti-clockwise 90 degree
     */
    private void rotateUpperLayerAC() {
        rotateUpperLayerC();
        rotateUpperLayerC();
        rotateUpperLayerC();
    }

    /**
     * rotate Back layer anti-clockwise 90 degree
     */
    private void rotateBackLayerAC() {
        rotateBackLayerC();
        rotateBackLayerC();
        rotateBackLayerC();
    }




    /**
     * rotate Right layer clockwise 90 degree
     */
    public void R() {
        rotateRightLayerC();
    }

    /**
     * rotate Right layer 180 degree
     */
    public void R2() {
        rotateRightLayerC();
        rotateRightLayerC();
    }

    /**
     * rotate Right layer anti-clockwise 90 degree
     */
    public void Ri() {
        rotateRightLayerC();
        rotateRightLayerC();
        rotateRightLayerC();
    }

    /**
     * rotate Left layer clockwise 90 degree
     */
    public void L() {
        rotateLeftLayerC();
    }

    /**
     * rotate Left layer 180 degree
     */
    public void L2() {
        rotateLeftLayerC();
        rotateLeftLayerC();
    }

    /**
     * rotate Left layer anti-clockwise 90 degree
     */
    public void Li() {
        rotateLeftLayerAC();
    }

    /**
     * rotate Back layer clockwise 90 degree
     */
    public void B() {
        rotateBackLayerC();
    }

    /**
     * rotate Back layer 180 degree
     */
    public void B2() {
        rotateBackLayerC();
        rotateBackLayerC();
    }

    /**
     * rotate Back layer anti-clockwise 90 degree
     */
    public void Bi() {
        rotateBackLayerC();
        rotateBackLayerC();
        rotateBackLayerC();
    }

    /**
     * rotate Down layer clockwise 90 degree
     */
    public void D() {
        rotateBottomLayerC();
    }

    /**
     * rotate Down layer 180 degree
     */
    public void D2() {
        rotateBottomLayerC();
        rotateBottomLayerC();
    }

    /**
     * rotate Down layer anti-clockwise 90 degree
     */
    public void Di() {
        rotateBottomLayerC();
        rotateBottomLayerC();
        rotateBottomLayerC();
    }

    /**
     * rotate Front layer clockwise 90 degree
     */
    public void F() {
        rotateFrontLayerC();
    }

    /**
     * rotate Front layer 180 degree
     */
    public void F2() {
        rotateFrontLayerC();
        rotateFrontLayerC();
    }

    /**
     * rotate Front layer anti-clockwise 90 degree
     */
    public void Fi() {
        rotateFrontLayerAC();
    }

    /**
     * rotate Up later clockwise 90 degree
     */
    public void U() {
        rotateUpperLayerC();
    }

    /**
     * rotate Up later 180 degree
     */
    public void U2() {
        rotateUpperLayerC();
        rotateUpperLayerC();
    }

    /**
     * rotate Up layer anti-clockwise 90 degree
     */
    public void Ui() {
        rotateUpperLayerC();
        rotateUpperLayerC();
        rotateUpperLayerC();
    }


    /**
     * rotate middle X-axis layer/slice up 90 degree
     */
    public void Mi() {
        rotateCubeXC();
        rotateRightLayerAC();
        rotateLeftLayerC();
    }

    /**
     * rotate middle X-axis layer/slice down 90 degree
     */
    public void M() {
        rotateCubeXAC();
        rotateRightLayerC();
        rotateLeftLayerAC();
    }

    /**
     * rotate middle X-axis layer/slice 180 degree
     */
    public void M2() {
        M();
        M();
    }


    /**
     * rotate middle Y-axis layer/slice right 90 degree
     */
    public void E() {
        rotateCubeYAC();
        rotateUpperLayerC();
        rotateBottomLayerAC();
    }

    /**
     * rotate middle Y-axis layer/slice 180 degree
     */
    public void E2() {
        E();
        E();
    }

    /**
     * rotate middle Y-axis layer/slice left 90 degree
     */
    public void Ei() {
        rotateCubeYC();
        rotateUpperLayerAC();
        rotateBottomLayerC();
    }


    /**
     * rotate middle Z-axis layer/slice clockwise 90 degree
     */
    public void S() {
        rotateCubeZC();
        rotateFrontLayerAC();
        rotateBackLayerC();
    }

    /**
     * rotate middle Z-axis layer/slice 180 degree
     */
    public void S2() {
        S();
        S();
    }

    /**
     * rotate middle Z-axis layer/slice anti-clockwise 90 degree
     */
    public void Si() {
        rotateCubeZAC();
        rotateFrontLayerC();
        rotateBackLayerAC();
    }


    /**
     * print the content of the given side as a plain string
     * @param side side to print
     */
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


    /**
     * print the entire cube in the following format:<br>
     *_________r r r<br>
     *_________r r r<br>
     *_________r r r<br>
     * <p>
     * y y y   b b b   w w w<br>
     * y y y   b b b   w w w<br>
     * y y y   b b b   w w w<br>
     * <p>
     *_________o o o<br>
     *_________o o o<br>
     *_________o o o<br>
     * <p>
     *_________g g g<br>
     *_________g g g<br>
     *_________g g g<br>
     */
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

    /**
     * print the front side as a 3x3 matrix
     */
    public void printFrontSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideFront[i][0] + " " + sideFront[i][1] + " " + sideFront[i][2]);
        }
    }

    /**
     * print the back side as a 3x3 matrix
     */
    public void printBackSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideBack[i][0] + " " + sideBack[i][1] + " " + sideBack[i][2]);
        }
    }

    /**
     * print the right side as a 3x3 matrix
     */
    public void printRightSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideRight[i][0] + " " + sideRight[i][1] + " " + sideRight[i][2]);
        }
    }

    /**
     * print the left side as a 3x3 matrix
     */
    public void printLeftSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideLeft[i][0] + " " + sideLeft[i][1] + " " + sideLeft[i][2]);
        }
    }

    /**
     * print the top side as a 3x3 matrix
     */
    public void printTopSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideUp[i][0] + " " + sideUp[i][1] + " " + sideUp[i][2]);
        }
    }

    /**
     * print the bottom side as a 3x3 matrix
     */
    public void printBottomSide() {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideDown[i][0] + " " + sideDown[i][1] + " " + sideDown[i][2]);
        }
    }


    /**
     * print all sides as plain string
     */
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
