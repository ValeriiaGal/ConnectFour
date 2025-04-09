package test;

import game.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DLLLoader.class)

class BoardInitializationTest {
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
        field.resetGame();
    }

    @Test
    void testBoardIsEmptyOnInitialization() {
        int[][] board = field.getGameBoard();
        for (int[] row : board) {
            for (int cell : row) {
                assertEquals(0, cell, "Board should be empty on initialization.");
            }
        }
    }
}
