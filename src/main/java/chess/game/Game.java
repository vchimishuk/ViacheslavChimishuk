package chess.game;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.Move;
import chess.Player;
import chess.Position;
import chess.pieces.King;
import chess.pieces.Piece;

/**
 * Game object represents the game itself: its rules and actions.
 */
public class Game {
    private GameState state;

    public Game() {
        state = new GameState();
        state.reset();
    }

    public List<Move> getAvailableMoves() {
        return state.getCurrentPlayerPieces().values().stream()
                .flatMap(piece -> MoverFactory.getInstance(piece).getMoves(state, piece).stream())
                .collect(Collectors.toList());
    }

    public Player getCurrentPlayer() {
        return state.getCurrentPlayer();
    }

    public Optional<Piece> getPieceAt(Position position) {
        return state.getPieceAt(position);
    }

    public boolean move(Move move) throws InvalidMoveException {
        boolean won = false;

        if (!state.isPositionPresent(move.getFrom())) {
            throw new InvalidMoveException("Invalid start position " + move.getFrom());
        }
        if (!state.isPositionPresent(move.getTo())) {
            throw new InvalidMoveException("Invalid end position " + move.getTo());
        }

        Optional<Piece> pieceO = state.getPieceAt(move.getFrom());
        if (!pieceO.isPresent()) {
            throw new InvalidMoveException("There is no piece at " + move.getFrom());
        }
        Piece piece = pieceO.get();
        if (piece.getOwner() != state.getCurrentPlayer()) {
            throw new InvalidMoveException("You can't move opponents' figure.");
        }

        List<Move> moves = MoverFactory.getInstance(piece).getMoves(state, piece);
        int i = moves.indexOf(move);
        if (i == -1) {
            throw new InvalidMoveException("This figure can't move this way.");
        }

        // Can we take opponent's figure?
        Optional<Piece> enemyO = state.getPieceAt(move.getTo());
        if (enemyO.isPresent()) {
            Piece enemy = enemyO.get();

            if (enemy.getOwner() == getCurrentPlayer()) {
                throw new InvalidMoveException("End position is busy");
            } else {
                if (enemy instanceof King) {
                    won = true;
                }
                state.removePiece(enemy);
            }
        }

        state.removePiece(piece);
        piece.setPosition(move.getTo());
        state.placePiece(piece);
        state.setPlayer(nextPlayer(state.getCurrentPlayer()));

        return won;
    }

    private Player nextPlayer(Player current) {
        return current == Player.White ? Player.Black : Player.White;
    }
}
