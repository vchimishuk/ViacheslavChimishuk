package chess.game.pieces;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import chess.game.Player;
import chess.game.Position;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class PiecesTest {

    @Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {'b', Player.White, Bishop.class},
                {'k', Player.White, King.class},
                {'n', Player.White, Knight.class},
                {'p', Player.White, Pawn.class},
                {'q', Player.White, Queen.class},
                {'r', Player.White, Rook.class},
                {'B', Player.Black, Bishop.class},
                {'K', Player.Black, King.class},
                {'N', Player.Black, Knight.class},
                {'P', Player.Black, Pawn.class},
                {'Q', Player.Black, Queen.class},
                {'R', Player.Black, Rook.class},
        });
    }

    private final char expected;
    private final Player player;
    private final Class<? extends Piece> clazz;

    public PiecesTest(char expected, Player player, Class<? extends Piece> clazz) {
        this.expected = expected;
        this.player = player;
        this.clazz = clazz;
    }

    @Test
    public void testGetIdentifyingCharacter() throws Exception {
        Constructor<? extends Piece> constructor = clazz.getDeclaredConstructor(Player.class, Position.class);
        Piece piece = constructor.newInstance(player, mock(Position.class));

        assertEquals(expected, piece.getIdentifier());
    }
}
