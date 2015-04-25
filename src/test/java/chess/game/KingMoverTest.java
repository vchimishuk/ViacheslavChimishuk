package chess.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.game.pieces.King;
import chess.game.pieces.Pawn;

import static junit.framework.Assert.assertEquals;

public class KingMoverTest {
    private PieceMover mover = new KingMover();

    @Before
    public void setUp() {
        mover = new KingMover();
    }

    @Test
    public void testGetMoves() {
        GameState state = createEmptyBoard();
        Position start = new Position("d5");
        King king = new King(Player.White, start);
        state.placePiece(king);

        // Test on empty board.
        Set<Move> actual = new HashSet<>(mover.getMoves(state, king));
        HashSet<Move> expected = new HashSet<>(Arrays.asList(
                new Move(start, new Position("c6")),
                new Move(start, new Position("d6")),
                new Move(start, new Position("e6")),
                new Move(start, new Position("e5")),
                new Move(start, new Position("e4")),
                new Move(start, new Position("d4")),
                new Move(start, new Position("c4")),
                new Move(start, new Position("c5"))));
        assertEquals(expected, actual);

        // Set enemy in front of me and check if it can be taken.
        state.placePiece(new Pawn(Player.Black, new Position("c6")));
        state.placePiece(new Pawn(Player.Black, new Position("d6")));
        assertEquals(expected, new HashSet<>(mover.getMoves(state, king)));

        // Test comrade collisions.
        state.placePiece(new Pawn(Player.White, new Position("e6")));
        state.placePiece(new Pawn(Player.White, new Position("e5")));
        expected.remove(new Move(start, new Position("e6")));
        expected.remove(new Move(start, new Position("e5")));
        assertEquals(expected, new HashSet<>(mover.getMoves(state, king)));
    }

    @Test
    public void testBoardEdges() {
        GameState state = createEmptyBoard();
        Position start = new Position("a1");
        King king = new King(Player.Black, start);

        HashSet<Move> expected = new HashSet<>(Arrays.asList(
                new Move(start, new Position("a2")),
                new Move(start, new Position("b2")),
                new Move(start, new Position("b1"))));

        assertEquals(expected, new HashSet<>(mover.getMoves(state, king)));
    }

    private GameState createEmptyBoard() {
        GameState state = new GameState();
        state.getPlayerPieces(Player.White).values().forEach(state::removePiece);
        state.getPlayerPieces(Player.Black).values().forEach(state::removePiece);

        return state;
    }
}
