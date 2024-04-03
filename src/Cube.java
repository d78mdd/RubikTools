import java.util.Arrays;

public class Cube {

    // sides (faces) with 9 pieces each
    // assuming classic Rubik's cube 3x3 with flat single-colored pieces
    private char[][] sideRight = new char[3][3];
    private char[][] sideLeft = new char[3][3];
    private char[][] sideUp = new char[3][3];
    private char[][] sideDown = new char[3][3];
    private char[][] sideFront = new char[3][3];
    private char[][] sideBack = new char[3][3];


    public Cube(char[][] sideRight, char[][] sideLeft, char[][] sideUp, char[][] sideDown, char[][] sideFront, char[][] sideBack) {
        this.sideRight = copy(sideRight);
        this.sideLeft = copy(sideLeft);
        this.sideUp = copy(sideUp);
        this.sideDown = copy(sideDown);
        this.sideFront = copy(sideFront);
        this.sideBack = copy(sideBack);
    }


    /**
     * rotate a physical outer 3x3x1 layer clockwise
     * @param layer f b r l u d
     * @param direction not implemented yet; direction is always 90 degree clockwise
     */
    private void rotateLayer(char layer, char direction) {

        // part 1/2 - rotate the face/side

        // determine side (9 pieces) to rotate and
        // assign the new state to the appropriate side
        switch(layer)
        {
            case 'R':
                System.out.println("rotating right face clockwise");
                sideRight = rotateC(sideRight);
                break;
            case 'L':
                System.out.println("rotating left face clockwise");
                sideLeft = rotateC(sideLeft);
                break;
            case 'U':
                System.out.println("rotating up face clockwise");
                sideUp = rotateC(sideUp);
                break;
            case 'D':
                System.out.println("rotating down face clockwise");
                sideDown = rotateC(sideDown);
                break;
            case 'F':
                System.out.println("rotating front face clockwise");
                sideFront = rotateC(sideFront);
                break;
            case 'B':
                System.out.println("rotating back face clockwise");
                sideBack = rotateC(sideBack);
                break;
        }



        // Part 2/2 : swap the 4 sets of 3 pieces "around" this side/face:

        //the 4 sides whose 3 pieces will swap
        char[][] side1ToSwap = new char[3][3];
        char[][] side2ToSwap = new char[3][3];
        char[][] side3ToSwap = new char[3][3];
        char[][] side4ToSwap = new char[3][3];
        // determine position of swap
        // direction = right (clockwise)
        switch(layer)
        {
            case 'R':
                side1ToSwap = copy(sideUp);
                side2ToSwap = copy(sideBack);
                side3ToSwap = copy(sideDown);
                side4ToSwap = copy(sideFront);
                break;
            case 'L':
                side1ToSwap = copy(sideUp);
                side2ToSwap = copy(sideFront);
                side3ToSwap = copy(sideDown);
                side4ToSwap = copy(sideBack);
                break;
            case 'U':
                side1ToSwap = copy(sideBack);
                side2ToSwap = copy(sideRight);
                side3ToSwap = copy(sideFront);
                side4ToSwap = copy(sideLeft);
                break;
            case 'D':
                side1ToSwap = copy(sideFront);
                side2ToSwap = copy(sideRight);
                side3ToSwap = copy(sideBack);
                side4ToSwap = copy(sideLeft);
                break;
            case 'F':
                side1ToSwap = copy(sideUp);
                side2ToSwap = copy(sideRight);
                side3ToSwap = copy(sideDown);
                side4ToSwap = copy(sideLeft);
                break;
            case 'B':
                side1ToSwap = copy(sideUp);
                side2ToSwap = copy(sideLeft);
                side3ToSwap = copy(sideDown);
                side4ToSwap = copy(sideRight);
                break;
        }
        // swap
        char[][] newSide1State = copy(side1ToSwap);
        newSide1State[2][0] = side4ToSwap[0][2];
        newSide1State[2][1] = side4ToSwap[1][2];
        newSide1State[2][2] = side4ToSwap[2][2];
        char[][] newSide2State = copy(side2ToSwap);
        newSide2State[0][0] = side1ToSwap[2][0];
        newSide2State[1][0] = side1ToSwap[2][1];
        newSide2State[2][0] = side1ToSwap[2][2];
        char[][] newSide3State = copy(side3ToSwap);
        newSide3State[0][0] = side2ToSwap[0][0];
        newSide3State[0][1] = side2ToSwap[1][0];
        newSide3State[0][2] = side2ToSwap[2][0];
        char[][] newSide4State = copy(side4ToSwap);
        newSide4State[0][2] = side3ToSwap[0][0];
        newSide4State[1][2] = side3ToSwap[0][1];
        newSide4State[2][2] = side3ToSwap[0][2];
        // assign the new states to the appropriate sides
        switch(layer)
        {
            case 'R':
                sideUp = copy(newSide1State);
                sideBack = copy(newSide2State);
                sideDown = copy(newSide3State);
                sideFront = copy(newSide4State);
                break;
            case 'L':
                sideUp = copy(newSide1State);
                sideFront = copy(newSide2State);
                sideDown = copy(newSide3State);
                sideBack = copy(newSide4State);
                break;
            case 'U':
                sideBack = copy(newSide1State);
                sideRight = copy(newSide2State);
                sideFront = copy(newSide3State);
                sideLeft = copy(newSide4State);
                break;
            case 'D':
                sideFront = copy(newSide1State);
                sideRight = copy(newSide2State);
                sideBack = copy(newSide3State);
                sideLeft = copy(newSide4State);
                break;
            case 'F':
                sideUp = copy(newSide1State);
                sideRight = copy(newSide2State);
                sideDown = copy(newSide3State);
                sideLeft = copy(newSide4State);
                break;
            case 'B':
                sideUp = copy(newSide1State);
                sideLeft = copy(newSide2State);
                sideDown = copy(newSide3State);
                sideRight = copy(newSide4State);
                break;
        }
    }


    /**
     * rotate the entire cube on the X axis
     */
    public void rotateCubeX() {
        rotateCube('x');
    }

    /**
     * rotate the entire cube on the Y axis
     */
    public void rotateCubeY() {
        rotateCube('y');
    }


    // https://ruwix.com/the-rubiks-cube/notation/
    private void rotateCube(char axis) {

        char[][] tempSide = new char[3][3];

        switch (axis)
        {
            case 'x':
                tempSide = sideFront;
                sideFront = sideDown;
                sideDown = sideBack;
                sideBack = sideUp;
                sideUp = tempSide;
                sideRight = rotateC(sideRight); //  identical to :  private void rotateLayer(char layer, char direction) { // part 1/2 - rotate the face/side
                sideLeft = rotateAC(sideLeft);
                break;
            case 'y':
                tempSide = sideFront;
                sideFront = sideRight;
                sideRight = sideBack;
                sideBack = sideLeft;
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
     * @param side the face/side (9 pieces) to be rotated
     * @return a new rotated face/side - anti-clockwise 90 degree
     */
    private char[][] rotateAC(char[][] side) {
        return rotateC(rotateC(rotateC(side)));
    }

    /**
     * for internal use with rotating the cube in 3D or rotating a 3x3x1 outer layer
     * @param side the face/side (9 pieces) to be rotated
     * @return a new rotated face/side 180 degree
     */
    private char[][] mirror(char[][] side) {
        return rotateC(rotateC(side));
    }




    private void rotateUpperLayer(char direction) {
        rotateCubeX();
        rotateCubeX();
        rotateCubeX();
        rotateFrontLayer(direction);
        rotateCubeX();
    }
    private void rotateBottomLayer(char direction) {
        rotateCubeX();
        rotateFrontLayer(direction);
        rotateCubeX();
        rotateCubeX();
        rotateCubeX();
    }



    private void rotateFrontLayer(char direction) {
        rotateLayer('F', direction);
    }
    private void rotateBackLayer(char direction) {
        rotateCubeX();
        rotateCubeX();
        rotateFrontLayer(direction);
        rotateCubeX();
        rotateCubeX();
    }



    private void rotateLeftLayer(char direction) {
        rotateCubeY();
        rotateCubeY();
        rotateCubeY();
        rotateFrontLayer(direction);
        rotateCubeY();
    }
    private void rotateRightLayer(char direction) {
        rotateCubeY();
        rotateFrontLayer(direction);
        rotateCubeY();
        rotateCubeY();
        rotateCubeY();
    }


    // shortened method names for each movement
    // according to https://rubik.bg/bg/content/10-formuli-za-podrezhdane-na-rubik-kub
    public void R()
    {
        rotateRightLayer('r');
    }
    public void Ri()
    {
        rotateRightLayer('r');
        rotateRightLayer('r');
        rotateRightLayer('r');
    }
    public void L()
    {
        rotateLeftLayer('r');
    }
    public void Li()
    {
        rotateLeftLayer('r');
        rotateLeftLayer('r');
        rotateLeftLayer('r');
    }
    public void B()
    {
        rotateBackLayer('r');
    }
    public void Bi()
    {
        rotateBackLayer('r');
        rotateBackLayer('r');
        rotateBackLayer('r');
    }
    public void D()
    {
        rotateBottomLayer('r');
    }
    public void Di()
    {
        rotateBottomLayer('r');
        rotateBottomLayer('r');
        rotateBottomLayer('r');
    }
    public void F()
    {
        rotateFrontLayer('r');
    }
    public void Fi()
    {
        rotateFrontLayer('r');
        rotateFrontLayer('r');
        rotateFrontLayer('r');
    }
    public void U()
    {
        rotateUpperLayer('r');
    }
    public void Ui()
    {
        rotateUpperLayer('r');
        rotateUpperLayer('r');
        rotateUpperLayer('r');
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
        for (int i = 2; i >= 0; i--) {
            System.out.print("        ");
            System.out.println(sideBack[i][0] + " " + sideBack[i][1] + " " + sideBack[i][2]);
        }
    }

    public void printFrontSide()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideFront[i][0] + " " + sideFront[i][1] + " " + sideFront[i][2]);
        }
    }
    public void printBackSide()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideBack[i][0] + " " + sideBack[i][1] + " " + sideBack[i][2]);
        }
    }
    public void printRightSide()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideRight[i][0] + " " + sideRight[i][1] + " " + sideRight[i][2]);
        }
    }
    public void printLeftSide()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideLeft[i][0] + " " + sideLeft[i][1] + " " + sideLeft[i][2]);
        }
    }
    public void printTopSide()
    {
        for (int i = 0; i < 3; i++) {
            System.out.println(sideUp[i][0] + " " + sideUp[i][1] + " " + sideUp[i][2]);
        }
    }
    public void printBottomSide()
    {
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
     * @param side the source array (cube side/face 3x3)
     * @return the new array
     */
    private static char[][] copy(char[][] side) {
        return Arrays.stream(side).map(char[]::clone).toArray(char[][]::new);
    }


}
