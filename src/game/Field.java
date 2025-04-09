package game;

public class Field {

    public native int[][] getGameBoard();
    public native boolean makeMove(int column);
    public native int getWinner();
    public native void resetGame();
    public native boolean mouseClick(int x);

}
