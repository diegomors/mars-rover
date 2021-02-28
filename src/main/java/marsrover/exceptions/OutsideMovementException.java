package marsrover.exceptions;

public class OutsideMovementException extends Exception {
    private static final long serialVersionUID = 1L;

    public OutsideMovementException(String string) {
        super(string);
	}
}
