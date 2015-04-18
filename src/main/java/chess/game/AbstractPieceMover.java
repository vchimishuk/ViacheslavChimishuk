package chess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import chess.Move;
import chess.Position;
import chess.pieces.Piece;

public abstract class AbstractPieceMover implements PieceMover {
    protected List<Move> getDirectionMoves(GameState state, Position start,
                                BiFunction<GameState, Position, Optional<Move>> posGenerator)
    {
        List<Move> moves = new ArrayList<>();
        Position pos = start;

        while (true) {
            Optional<Move> moveO = posGenerator.apply(state, pos);
            Optional<Position> newPosO = moveO.map(Move::getTo);

            if (!newPosO.map(state::isPositionPresent).orElse(false)) {
                break; // End of the board.
            }

            Optional<Piece> otherO = state.getPieceAt(newPosO.get());
            // Someone standing on my way.
            if (otherO.isPresent()) {
                if (isEnemy(state, otherO.get())) {
                    moves.add(new Move(start, moveO.map(Move::getTo).get()));
                }
                break;
            }

            pos = newPosO.get();
            moves.add(new Move(start, moveO.map(Move::getTo).get()));
        }

        return moves;
    }

    protected Optional<Move> moveUpLeft(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() - 1), position.getRow() + 1));
    }

    protected Optional<Move> moveUp(GameState state, Position position) {
        return move(state, position, new Position(position.getColumn(), position.getRow() + 1));
    }

    protected Optional<Move> moveUpRight(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() + 1), position.getRow() + 1));
    }

    protected Optional<Move> moveRight(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() + 1), position.getRow()));
    }

    protected Optional<Move> moveDownRight(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() + 1), position.getRow() - 1));
    }

    protected Optional<Move> moveDown(GameState state, Position position) {
        return move(state, position, new Position(position.getColumn(), position.getRow() - 1));
    }

    protected Optional<Move> moveDownLeft(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() - 1), position.getRow() - 1));
    }

    protected Optional<Move> moveLeft(GameState state, Position position) {
        return move(state, position, new Position((char) (position.getColumn() - 1), position.getRow()));
    }

    private Optional<Move> move(GameState state, Position from, Position to) {
        if (state.isPositionPresent(to)) {
            return Optional.of(new Move(from, to));
        } else {
            return Optional.empty();
        }
    }

    private boolean isEnemy(GameState state, Piece piece) {
        return state.getCurrentPlayer() != piece.getOwner();
    }

    protected boolean canMove(GameState state, Position position) {
        Optional<Piece> other = state.getPieceAt(position);

        return !other.isPresent() || other.get().getOwner() != state.getCurrentPlayer();
    }
}
