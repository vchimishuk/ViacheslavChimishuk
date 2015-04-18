package chess.game;

import org.junit.Test;

import chess.game.pieces.Bishop;
import chess.game.pieces.King;
import chess.game.pieces.Knight;
import chess.game.pieces.Pawn;
import chess.game.pieces.Queen;
import chess.game.pieces.Rook;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PieceMoverFactoryTest {
    @Test
    public void testBishopMover() {
        assertThat(PieceMoverFactory.getInstance(new Bishop(Player.Black, new Position("a1"))),
                instanceOf(BishopMover.class));
    }

    @Test
    public void testKingMover() {
        assertThat(PieceMoverFactory.getInstance(new King(Player.Black, new Position("a1"))),
                instanceOf(KingMover.class));
    }

    @Test
    public void testKnightMover() {
        assertThat(PieceMoverFactory.getInstance(new Knight(Player.Black, new Position("a1"))),
                instanceOf(KnightMover.class));
    }

    @Test
    public void testPawnMover() {
        assertThat(PieceMoverFactory.getInstance(new Pawn(Player.Black, new Position("a1"))),
                instanceOf(PawnMover.class));
    }

    @Test
    public void testQueenMover() {
        assertThat(PieceMoverFactory.getInstance(new Queen(Player.Black, new Position("a1"))),
                instanceOf(QueenMover.class));
    }

    @Test
    public void testRookMover() {
        assertThat(PieceMoverFactory.getInstance(new Rook(Player.Black, new Position("a1"))),
                instanceOf(RookMover.class));
    }
}
