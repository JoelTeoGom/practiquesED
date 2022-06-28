package Exceptions;

public class objectNotFound extends Exception{
    private static final long serialVersionUID = 1L;

    public objectNotFound(int cost) {
        super(""+cost);
    }
}
