package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece {
    public Queen(Player owner, Position position) {
        super(owner, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
}
