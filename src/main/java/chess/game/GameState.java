package chess.game;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.game.pieces.Bishop;
import chess.game.pieces.King;
import chess.game.pieces.Knight;
import chess.game.pieces.Pawn;
import chess.game.pieces.Piece;
import chess.game.pieces.Queen;
import chess.game.pieces.Rook;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White, new Position("a1")));
        placePiece(new Knight(Player.White, new Position("b1")));
        placePiece(new Bishop(Player.White, new Position("c1")));
        placePiece(new Queen(Player.White, new Position("d1")));
        placePiece(new King(Player.White, new Position("e1")));
        placePiece(new Bishop(Player.White, new Position("f1")));
        placePiece(new Knight(Player.White, new Position("g1")));
        placePiece(new Rook(Player.White, new Position("h1")));
        placePiece(new Pawn(Player.White, new Position("a2")));
        placePiece(new Pawn(Player.White, new Position("b2")));
        placePiece(new Pawn(Player.White, new Position("c2")));
        placePiece(new Pawn(Player.White, new Position("d2")));
        placePiece(new Pawn(Player.White, new Position("e2")));
        placePiece(new Pawn(Player.White, new Position("f2")));
        placePiece(new Pawn(Player.White, new Position("g2")));
        placePiece(new Pawn(Player.White, new Position("h2")));

        // Black Pieces
        placePiece(new Rook(Player.Black, new Position("a8")));
        placePiece(new Knight(Player.Black, new Position("b8")));
        placePiece(new Bishop(Player.Black, new Position("c8")));
        placePiece(new Queen(Player.Black, new Position("d8")));
        placePiece(new King(Player.Black, new Position("e8")));
        placePiece(new Bishop(Player.Black, new Position("f8")));
        placePiece(new Knight(Player.Black, new Position("g8")));
        placePiece(new Rook(Player.Black, new Position("h8")));
        placePiece(new Pawn(Player.Black, new Position("a7")));
        placePiece(new Pawn(Player.Black, new Position("b7")));
        placePiece(new Pawn(Player.Black, new Position("c7")));
        placePiece(new Pawn(Player.Black, new Position("d7")));
        placePiece(new Pawn(Player.Black, new Position("e7")));
        placePiece(new Pawn(Player.Black, new Position("f7")));
        placePiece(new Pawn(Player.Black, new Position("g7")));
        placePiece(new Pawn(Player.Black, new Position("h7")));
    }

    /**
     * Returns true if given position is present on the board.
     * @param position
     * @return
     */
    public boolean isPositionPresent(Position position) {
        char col = position.getColumn();
        int row = position.getRow();

        return col >= Position.MIN_COLUMN && col <= Position.MAX_COLUMN
                && row >= Position.MIN_ROW && row <= Position.MAX_ROW;
    }

    public boolean isPositionFree(Position position) {
        return !getPieceAt(position).isPresent();
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Optional<Piece> getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Optional<Piece> getPieceAt(Position position) {
        return Optional.ofNullable(positionToPieceMap.get(position));
    }

    /**
     * Returns all pieces and their positions for the given player.
     * @param player Player to get pieces for.
     * @return Pieces for the given player.
     */
    public Map<Position, Piece> getPlayerPieces(Player player) {
        return positionToPieceMap.entrySet().stream().filter(p -> p.getValue().getOwner() == player)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    /**
     * Returns all pieces and their positions for the current player.
     * @return Pieces for the current player.
     */
    public Map<Position, Piece> getCurrentPlayerPieces() {
        return getPlayerPieces(getCurrentPlayer());
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     */
    public void placePiece(Piece piece) {
        positionToPieceMap.put(piece.getPosition(), piece);
    }

    public void removePiece(Piece piece) {
        positionToPieceMap.remove(piece.getPosition());
    }
}
