package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    private Position position;

    protected Piece(Player owner, Position position) {
        this.owner = owner;
        this.position = position;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position) {
        // TODO:
    }

    protected abstract char getIdentifyingCharacter();
}
