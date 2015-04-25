package chess.game;

import java.util.Objects;

/**
 * Move is a transition of some piece from one position to another.
 */
public class Move {
    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (from != null ? !from.equals(move.from) : move.from != null) return false;
        if (to != null ? !to.equals(move.to) : move.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return from + " " + to;
    }
}
