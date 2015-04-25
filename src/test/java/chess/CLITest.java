package chess;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Basic unit tests for the CLITest
 */
@RunWith(MockitoJUnitRunner.class)
public class CLITest {
    private static final String INITIAL_WHITE_MOVES = "a2 a3, "
            + "a2 a4, "
            + "b1 a3, "
            + "b1 c3, "
            + "b2 b3, "
            + "b2 b4, "
            + "c2 c3, "
            + "c2 c4, "
            + "d2 d3, "
            + "d2 d4, "
            + "e2 e3, "
            + "e2 e4, "
            + "f2 f3, "
            + "f2 f4, "
            + "g1 f3, "
            + "g1 h3, "
            + "g2 g3, "
            + "g2 g4, "
            + "h2 h3, "
            + "h2 h4";

    @Mock
    private PrintStream testOut;

    @Mock
    private InputStream testIn;

    /**
     * Make sure the CLI initially prints a welcome message
     */
    @Test
    public void testCLIWritesWelcomeMessage() {
        new CLI(testIn, testOut);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(testOut, times(1)).println(captor.capture());

        String message = captor.getValue();

        assertTrue("The CLI should initially print a welcome message", message.startsWith("Welcome"));
    }

    /**
     * Test that the CLI can initially accept input
     */
    @Test
    public void testHelpCommand() throws Exception {
        runCliWithInput("help");

        List<String> output = captureOutput();
        assertEquals("Should have 13 output calls", 13, output.size());
    }

    @Test
    public void testNewCommand() throws Exception {
        runCliWithInput("new");
        List<String> output = captureOutput();

        assertEquals("Should have had 6 calls to print strings", 6, output.size());
        assertEquals("It should have printed the board first", 701, output.get(2).length());
        assertEquals("It should have printed the board again", 701, output.get(4).length());
    }

    @Test
    public void testBoardCommand() throws Exception {
        runCliWithInput("new", "board");
        List<String> output = captureOutput();

        assertEquals("Should have had 9 output calls", 9, output.size());
        assertEquals("It should have printed the board three times", output.get(2), output.get(4));
    }

    // NOTICE: I do not test output.size(), because my commands invoke
    // CLI.writeOutput() more than one time. It can be changed to fits the
    // existing style.
    @Test
    public void testListCommand() {
        runCliWithInput("list");
        List<String> output = captureOutput();

        // Find moves list start and end.
        int start = -1;
        for (int i = 0; i < output.size(); i++) {
            if ("White's Possible Moves:".equals(output.get(i))) {
                start = i + 1;
            }
        }
        assertTrue("Moves list start not found.", start != -1 && start < output.size());

        int end;
        for (end = start + 1; end < output.size(); end++) {
            if (!output.get(end).startsWith("\t")) {
                break;
            }
        }
        assertTrue("End of possible moves list not found.", end < output.size());

        String moves = output.subList(start, end).stream().map(String::trim)
                .sorted().collect(Collectors.joining(", "));
        assertEquals(INITIAL_WHITE_MOVES, moves);
    }

    @Test
    public void testMoveCommand() {
        runCliWithInput("move a2 a4", "move d7 d6");
        List<String> output = captureOutput();

        // Last line expected to be a service message and before that
        // new board state should be printed.
        assertTrue(output.size() >= 2);

        String board = output.get(output.size() - 2);
        // Now I want to check if new board state is correct.
        // This is a homebrew implementation in a real world it is
        // better to save new board in resource file or something.
        assertEquals("Invalid board state found.", 105482053, board.hashCode());
    }

    private List<String> captureOutput() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // 9 times means we printed Welcome, the input prompt twice, and the 'help' screen
        verify(testOut, atLeastOnce()).println(captor.capture());

        return captor.getAllValues();
    }

    private CLI runCliWithInput(String... inputLines) {
        StringBuilder builder = new StringBuilder();
        for (String line : inputLines) {
            builder.append(line).append(System.getProperty("line.separator"));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        CLI cli = new CLI(in, testOut);
        cli.startEventLoop();

        return cli;
    }
}
