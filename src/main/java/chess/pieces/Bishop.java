package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner, Position position) {
        super(owner, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
}
