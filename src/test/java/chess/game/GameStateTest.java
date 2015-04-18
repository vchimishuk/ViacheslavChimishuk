package chess.game;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import chess.game.pieces.Piece;
import chess.game.pieces.Queen;
import chess.game.pieces.Rook;

import static chess.util.AssertUtils.assertNullable;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNullable("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Optional<Piece> whiteRookO = state.getPieceAt("a1");
        assertTrue("A rook should be present at a1", whiteRookO.isPresent());
        assertTrue("A rook should be at a1", whiteRookO.get() instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRookO.get().getOwner());


        Optional<Piece> blackQueenO = state.getPieceAt("d8");
        assertTrue("A queen should be present at d8", blackQueenO.get() instanceof Queen);
        assertTrue("A queen should be at d8", blackQueenO.get() instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueenO.get().getOwner());
    }
}
