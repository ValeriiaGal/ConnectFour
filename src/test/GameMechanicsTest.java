package test;

import game.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DLLLoader.class)
class GameMechanicsTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
        field.resetGame();
    }

    @Test
    void testVerticalWin() {
        for (int i = 0; i < 4; i++) {
            field.makeMove(0);
            if (i < 3) field.makeMove(1);
        }
        assertEquals(1, field.getWinner(), "Player 1 should win with a vertical line.");
    }

    @Test
    void testHorizontalWin() {
        for (int i = 0; i < 4; i++) {
            field.makeMove(i);
            if (i < 3) field.makeMove(i + 1);
        }
        assertEquals(1, field.getWinner(), "Player 1 should win with a horizontal line.");
    }

    @Test
    void testDiagonalWin() {
        field.makeMove(0);
        field.makeMove(1);
        field.makeMove(1);
        field.makeMove(2);
        field.makeMove(2);
        field.makeMove(3);
        field.makeMove(2);
        field.makeMove(3);
        field.makeMove(3);
        field.makeMove(4);

        assertEquals(5, field.getWinner(), "Player 1 should win with a diagonal line.");
    }
}

