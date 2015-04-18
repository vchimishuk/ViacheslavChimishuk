package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(Player owner, Position position) {
        super(owner, position);

        firstMove = true;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        firstMove = false;
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
}
