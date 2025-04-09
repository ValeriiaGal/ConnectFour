package test;

import game.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DLLLoader.class)
class MakeMoveTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
        field.resetGame();
    }

    @Test
    void testValidMove() {
        assertTrue(field.makeMove(3), "Move should be valid in an empty column.");
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        assertFalse(field.makeMove(7), "Move should be invalid if the column is out of bounds.");
        assertFalse(field.makeMove(-1), "Move should be invalid if the column is out of bounds.");
    }

    @Test
    void testColumnFullMove() {
        for (int i = 0; i < 6; i++) {
            field.makeMove(0);
        }
        assertFalse(field.makeMove(0), "Move should be invalid if the column is full.");
    }
}
