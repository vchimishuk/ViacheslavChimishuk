package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner, Position position) {
        super(owner, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
}
