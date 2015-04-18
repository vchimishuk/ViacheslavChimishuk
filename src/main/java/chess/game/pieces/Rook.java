package chess.game.pieces;

import chess.game.Player;
import chess.game.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner, Position position) {
        super(owner, position);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
}
