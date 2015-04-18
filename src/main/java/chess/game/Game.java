package chess.game;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.Move;
import chess.Player;
import chess.Position;
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
}
