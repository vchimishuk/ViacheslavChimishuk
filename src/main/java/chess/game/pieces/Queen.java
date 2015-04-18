package chess.game.pieces;

import chess.game.Player;
import chess.game.Position;

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
