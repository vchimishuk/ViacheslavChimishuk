package chess.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

public final class MoverFactory {
    private static final Map<Class<? extends Piece>, PieceMover> MOVERS;

    static {
        Map<Class<? extends Piece>, PieceMover> m = new HashMap<>();
        m.put(Bishop.class, new BishopMover());
        m.put(King.class, new KingMover());
        m.put(Knight.class, new KnightMover());
        m.put(Pawn.class, new PawnMover());
        m.put(Queen.class, new QueenMover());
        m.put(Rook.class, new RookMover());

        MOVERS = Collections.unmodifiableMap(m);
    }

    private MoverFactory() {

    }

    public static PieceMover getInstance(Piece piece) {
        PieceMover mover = MOVERS.get(piece.getClass());
        if (mover == null) {
            throw new IllegalArgumentException("Unsupported piece.");
        }

        return mover;
    }
}
