package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner, Position position) {
        super(owner, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }
}
