package chess.game.pieces;

import chess.game.Player;
import chess.game.Position;

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
