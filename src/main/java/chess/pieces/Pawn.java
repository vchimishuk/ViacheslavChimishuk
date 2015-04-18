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
    public void move(Position position) {
        super.move(position);
        firstMove = false;
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
}
