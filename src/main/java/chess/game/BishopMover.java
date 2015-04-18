package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import chess.Position;
import chess.pieces.Piece;

public class BishopMover extends AbstractPieceMover {
    @Override
    public List<Move> getMoves(GameState state, Piece piece) {
        Position position = piece.getPosition();
        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(getDirectionMoves(state, position, this::moveUpLeft));
        moves.addAll(getDirectionMoves(state, position, this::moveUpRight));
        moves.addAll(getDirectionMoves(state, position, this::moveDownRight));
        moves.addAll(getDirectionMoves(state, position, this::moveDownLeft));

        return moves;
    }
}
