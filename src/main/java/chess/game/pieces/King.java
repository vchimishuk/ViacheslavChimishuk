package chess.game.pieces;

import chess.game.Player;
import chess.game.Position;

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
