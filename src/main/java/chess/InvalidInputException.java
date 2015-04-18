package chess;

/**
 * @author Viacheslav Chimishuk <vchimishuk@yandex-team.ru>
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
