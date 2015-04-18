package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import chess.game.Game;
import chess.game.InvalidMoveException;
import chess.pieces.Piece;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final BufferedReader inReader;
    private final PrintStream outStream;

    private Game game;

    public CLI(InputStream inputStream, PrintStream outStream) {
        this.inReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outStream = outStream;
        writeOutput("Welcome to Chess!");
    }

    /**
     * Write the string to the output
     * @param str The string to write
     */
    private void writeOutput(String str) {
        this.outStream.println(str);
    }

    /**
     * Retrieve a string from the console, returning after the user hits the 'Return' key.
     * @return The input from the user, or an empty-length string if they did not type anything.
     */
    private String getInput() {
        try {
            this.outStream.print("> ");
            return inReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from input: ", e);
        }
    }

    void startEventLoop() {
        writeOutput("Type 'help' for a list of commands.");
        doNewGame();

        while (true) {
            try {
                showBoard();
                writeOutput(game.getCurrentPlayer() + "'s Move");

                String input = getInput();
                if (input == null) {
                    break; // No more input possible; this is the only way to exit the event loop
                } else if (input.length() > 0) {
                    if (input.equals("help")) {
                        showCommands();
                    } else if (input.equals("new")) {
                        doNewGame();
                    } else if (input.equals("quit")) {
                        writeOutput("Goodbye!");
                        System.exit(0);
                    } else if (input.equals("board")) {
                        writeOutput("Current Game:");
                    } else if (input.equals("list")) {
                        list();
                    } else if (input.startsWith("move")) {
                        List<Position> positions = parseMoveParameters(input.substring("move".length()).trim());
                        try {
                            move(positions.get(0), positions.get(1));
                        } catch (InvalidMoveException e) {
                            writeOutput(e.getMessage());
                        }
                    } else {
                        writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
                    }
                }
            } catch (InvalidInputException e) {
                writeOutput(e.getMessage());
            }
        }
    }

    private void doNewGame() {
        game = new Game();
    }

    private void showBoard() {
        writeOutput(getBoardAsString());
    }

    private void showCommands() {
        writeOutput("Possible commands: ");
        writeOutput("    'help'                       Show this menu");
        writeOutput("    'quit'                       Quit Chess");
        writeOutput("    'new'                        Create a new game");
        writeOutput("    'board'                      Show the chess board");
        writeOutput("    'list'                       List all possible moves");
        writeOutput("    'move <colrow> <colrow>'     Make a move");
    }

    /**
     * Display the board for the user(s)
     */
    String getBoardAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append(NEWLINE);

        printColumnLabels(builder);
        for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
            printSeparator(builder);
            printSquares(i, builder);
        }

        printSeparator(builder);
        printColumnLabels(builder);

        return builder.toString();
    }

    private void list() {
        List<Move> moves = game.getAvailableMoves();

        if (moves.isEmpty()) {
            writeOutput("You can't move! You are checkmated.");
        } else {
            writeOutput(game.getCurrentPlayer() + "'s Possible Moves:");

            for (Move move : moves) {
                writeOutput("\t" + move.getFrom() + " " + move.getTo());
            }
        }
    }

    private void move(Position from, Position to) throws InvalidMoveException {
        game.move(new Move(from, to));
    }

    private void printSquares(int rowLabel, StringBuilder builder) {
        builder.append(rowLabel);

        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            Optional<Piece> piece = game.getPieceAt(new Position(c, rowLabel));
            builder.append(" | ").append(piece.map(Piece::getIdentifier).orElse(' '));
        }
        builder.append(" | ").append(rowLabel).append(NEWLINE);
    }

    private void printSeparator(StringBuilder builder) {
        builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
    }

    private void printColumnLabels(StringBuilder builder) {
        builder.append("   ");
        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            builder.append(" ").append(c).append("  ");
        }

        builder.append(NEWLINE);
    }

    private List<Position> parseMoveParameters(String input) throws InvalidInputException {
        String[] positions = input.split(" ", 2);
        if (positions.length != 2) {
            throw new InvalidInputException("Invalid move command format. Type \"help\" for details.");
        }

        // TODO: Validate colrow values.
        return Arrays.asList(new Position(positions[0]), new Position(positions[1]));
    }

    public static void main(String[] args) {
        CLI cli = new CLI(System.in, System.out);
        cli.startEventLoop();
    }
}
