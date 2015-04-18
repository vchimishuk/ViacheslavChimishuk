package chess.game;

/**
 * @author Viacheslav Chimishuk <vchimishuk@yandex-team.ru>
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
