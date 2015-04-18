package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.Move;
import chess.Position;
import chess.pieces.Piece;

public class KnightMover extends AbstractPieceMover {
    @Override
    public List<Move> getMoves(GameState state, Piece piece) {
        Position position = piece.getPosition();
        ArrayList<Move> moves = new ArrayList<>();

        moveUp(state, position)
                .flatMap(m -> moveUpLeft(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);
        moveUp(state, position)
                .flatMap(m -> moveUpRight(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);

        moveRight(state, position)
                .flatMap(m -> moveUpRight(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);
        moveRight(state, position)
                .flatMap(m -> moveDownRight(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);

        moveDown(state, position)
                .flatMap(m -> moveDownLeft(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);
        moveDown(state, position)
                .flatMap(m -> moveDownRight(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);

        moveLeft(state, position)
                .flatMap(m -> moveUpLeft(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);
        moveLeft(state, position)
                .flatMap(m -> moveDownLeft(state, m.getTo()))
                .filter(m -> canMove(state, m.getTo()))
                .map(m -> new Move(position, m.getTo()))
                .ifPresent(moves::add);

        return moves;
    }
}
