package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.game.pieces.Piece;

public class RookMover extends AbstractPieceMover {
    @Override
    public List<Move> getMoves(GameState state, Piece piece) {
        Position position = piece.getPosition();
        ArrayList<Move> moves = new ArrayList<>();

        moves.addAll(getDirectionMoves(state, position, this::moveUp));
        moves.addAll(getDirectionMoves(state, position, this::moveRight));
        moves.addAll(getDirectionMoves(state, position, this::moveDown));
        moves.addAll(getDirectionMoves(state, position, this::moveLeft));

        return moves;
    }
}
