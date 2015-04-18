package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import chess.Position;
import chess.pieces.Piece;

public class KingMover extends AbstractPieceMover {
    @Override
    public List<Move> getMoves(GameState state, Piece piece) {
        Position position = piece.getPosition();
        ArrayList<Move> moves = new ArrayList<>();

        moveUpLeft(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveUp(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveUpRight(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveRight(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveDownRight(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveDown(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveDownLeft(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);
        moveLeft(state, position).filter(m -> canMove(state, m.getTo())).ifPresent(moves::add);

        return moves;
    }
}
