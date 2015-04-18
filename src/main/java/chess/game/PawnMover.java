package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import chess.Player;
import chess.Position;
import chess.pieces.Pawn;
import chess.pieces.Piece;

public class PawnMover extends AbstractPieceMover {
    @Override
    public List<Move> getMoves(GameState state, Piece piece) {
        List<Move> moves = new ArrayList<>();
        Position position = piece.getPosition();
        Pawn pawn = (Pawn) piece;

        // Whites move up and blacks are down.
        if (piece.getOwner() == Player.White) {
            moveUp(state, position).ifPresent(moves::add);
            if (pawn.isFirstMove()) {
                moveUp(state, position)
                        .filter(m -> state.isPositionFree(m.getTo()))
                        .flatMap(m -> moveUp(state, m.getTo()))
                        .filter(m -> state.isPositionFree(m.getTo()))
                        .map(m -> new Move(position, m.getTo()))
                        .ifPresent(moves::add);
            }
        } else if (piece.getOwner() == Player.Black) {
            moveDown(state, position).ifPresent(moves::add);
            if (pawn.isFirstMove()) {
                moveDown(state, position)
                        .filter(m -> state.isPositionFree(m.getTo()))
                        .flatMap(m -> moveUp(state, m.getTo()))
                        .filter(m -> state.isPositionFree(m.getTo()))
                        .map(m -> new Move(position, m.getTo()))
                        .ifPresent(moves::add);
            }
        } else {
            throw new IllegalStateException("Not supported player: " + piece.getOwner());
        }

        return moves;
    }
}
