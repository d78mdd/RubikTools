import java.util.Arrays;

public class Cube {

    private char[][] sideRight = new char[3][3];
    private char[][] sideLeft = new char[3][3];
    private char[][] sideUp = new char[3][3];
    private char[][] sideDown = new char[3][3];
    private char[][] sideFront = new char[3][3];
    private char[][] sideBack = new char[3][3];


    public Cube(char[][] sideRight, char[][] sideLeft, char[][] sideUp, char[][] sideDown, char[][] sideFront, char[][] sideBack) {
        this.sideRight = sideRight;
        this.sideLeft = sideLeft;
        this.sideUp = sideUp;
        this.sideDown = sideDown;
        this.sideFront = sideFront;
        this.sideBack = sideBack;
    }




    public void moveLayer(char layer) {
    }
    public void rotateCube(char axis) {
    }




    public void moveLayerTop() {
        moveLayer('U');
    }

    public void moveLayerMiddleHorizontal() {
        moveLayer('U');
        moveLayer('D');
    }

    public void moveLayerBottom() {
        moveLayer('D');
    }



    public void moveLayerFront() {
        moveLayer('F');
    }

    public void moveLayerMiddleVertical() {
        moveLayer('F');
        moveLayer('B');
    }

    public void moveLayerBack() {
        moveLayer('B');
    }



    public void moveLayerLeft() {
        moveLayer('L');
    }

    public void moveLayerVertical2() {
        moveLayer('L');
        moveLayer('R');
    }

    public void moveLayerRight() {
        moveLayer('R');
    }



    public void rotateCubeX() {
        rotateCube('x');
    }

    public void rotateCubeY() {
        rotateCube('y');
    }

    public void rotateCubeZ() {
        rotateCube('z');
    }


    public void outputSide(String side) {
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


    public void outputCurrentState() {
        outputSide("right");
        outputSide("left");
        outputSide("up");
        outputSide("down");
        outputSide("front");
        outputSide("back");
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


}
