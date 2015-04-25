package chess.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import chess.game.pieces.Bishop;

import static junit.framework.Assert.assertEquals;

public class BishopMoverTest {
    @Test
    public void testGetMoves() {
        Position start = new Position("d5");
        Bishop bishop = new Bishop(Player.White, start);

        // Create board with some pieces and test corner cases:
        // * board edges detection
        // * comrade figure detection
        // * enemy detection
        GameState state = new GameState();
        state.getPlayerPieces(Player.White).values().forEach(state::removePiece);
        state.getPlayerPieces(Player.Black).values().forEach(state::removePiece);
        state.placePiece(bishop);
        state.placePiece(new Bishop(Player.White, new Position("b7")));
        state.placePiece(new Bishop(Player.Black, new Position("g2")));

        BishopMover mover = new BishopMover();
        Set<Move> expected = new HashSet<>(Arrays.asList(
                new Move(start, new Position("a2")),
                new Move(start, new Position("b3")),
                new Move(start, new Position("c4")),
                new Move(start, new Position("c6")),
                new Move(start, new Position("e4")),
                new Move(start, new Position("e6")),
                new Move(start, new Position("f3")),
                new Move(start, new Position("f7")),
                new Move(start, new Position("g2")),
                new Move(start, new Position("g8"))));
        Set<Move> actual = new HashSet<>(mover.getMoves(state, bishop));
        assertEquals(expected, actual);
    }
}
