package chess.game;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import chess.game.pieces.Piece;

import static chess.util.AssertUtils.assertNonNullable;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class GameTest {
    private GameState state;

    private Game game;

    @Before
    public void setUp() {
        state = new GameState();
        state.reset();

        game = new Game(state);
    }
    @Test
    public void testMove() throws InvalidMoveException {
        // Whites move.
        assertEquals(Player.White, game.getCurrentPlayer());
        game.move(new Move(new Position("a2"), new Position("a3")));
        assertEquals(Player.Black, game.getCurrentPlayer());
        // And Blacks move.
        game.move(new Move(new Position("d7"), new Position("d5")));
        assertEquals(Player.White, game.getCurrentPlayer());

        assertTrue(state.isPositionFree(new Position("a2")));
        assertFalse(state.isPositionFree(new Position("a3")));
        assertTrue(state.isPositionFree(new Position("d7")));
        assertFalse(state.isPositionFree(new Position("d5")));
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveEnemy() throws InvalidMoveException {
        game.move(new Move(new Position("a7"), new Position("a6")));
    }

    @Test(expected = InvalidMoveException.class)
    public void testIncorrectRookMove() throws InvalidMoveException {
        game.move(new Move(new Position("a1"), new Position("a5")));
    }

    @Test(expected = InvalidMoveException.class)
    public void testOutOfBoardMove() throws InvalidMoveException {
        game.move(new Move(new Position("a8"), new Position("a9")));
    }

    @Test
    public void testFigureTaking() throws InvalidMoveException {
        game.move(new Move(new Position("a2"), new Position("a4")));
        game.move(new Move(new Position("b7"), new Position("b5")));
        game.move(new Move(new Position("a4"), new Position("b5")));
        assertTrue(state.isPositionPresent(new Position("b5")));

        Optional<Piece> pieceO = state.getPieceAt(new Position("b5"));
        assertNonNullable(pieceO);
        Piece piece = pieceO.get();
        assertEquals(Player.White, piece.getOwner());
        assertEquals('p', piece.getIdentifier());
    }

    @Test
    public void testCheckmate() throws InvalidMoveException {
        assertFalse(game.move(new Move(new Position("c2"), new Position("c3"))));
        assertFalse(game.move(new Move(new Position("d7"), new Position("d6"))));
        assertFalse(game.move(new Move(new Position("d1"), new Position("a4"))));
        assertFalse(game.move(new Move(new Position("a7"), new Position("a6"))));
        assertTrue(game.move(new Move(new Position("a4"), new Position("e8"))));
    }
}
