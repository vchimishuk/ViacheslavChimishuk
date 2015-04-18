package chess.game;

import java.util.List;

import chess.Move;
import chess.pieces.Piece;

/**
 * Mover is an piece behavior login object, which defines piece behaviour.
 */
public interface PieceMover {
    /**
     * Returns all available moves which this figure can make.
     * @param state Current game state.
     * @param piece Piece to move.
     * @return Possible moves.
     */
    List<Move> getMoves(GameState state, Piece piece);
}
