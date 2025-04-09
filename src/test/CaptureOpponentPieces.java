package test;

import game.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DLLLoader.class)

public class CaptureOpponentPieces {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
        field.resetGame();
    }

    @Test
    void testCaptureOpponentPieces() {
        field.makeMove(3);
        field.makeMove(3);
        int[][] boardState = field.getGameBoard();

        assertEquals(1, boardState[5][3], "Player 1's piece should be at (5,3)");

        assertEquals(5, boardState[4][3], "Player 2's piece should be at (4,3)");

        assertEquals(0, boardState[3][3], "Position (3,3) should be empty.");
        assertEquals(0, boardState[2][3], "Position (2,3) should be empty.");
        assertEquals(0, boardState[1][3], "Position (1,3) should be empty.");
        assertEquals(0, boardState[0][3], "Position (0,3) should be empty.");
    }
}
