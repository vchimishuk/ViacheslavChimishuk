package chess.util;

import java.util.Optional;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * @author Viacheslav Chimishuk <vchimishuk@yandex-team.ru>
 */
public final class AssertUtils {
    private AssertUtils() {

    }

    public static <T> void assertNullable(Optional<T> o) {
        assertNullable("Nullable Optional expected but non nullable received.", o);
    }

    public static <T> void assertNullable(String msg, Optional<T> o) {
        assertFalse(msg, o.isPresent());
    }

    public static <T> void assertNonNullable(Optional<T> o) {
        assertNonNullable("Non nullable Optional expected but nullable received.", o);
    }

    public static <T> void assertNonNullable(String msg, Optional<T> o) {
        assertTrue(msg, o.isPresent());
    }
}
