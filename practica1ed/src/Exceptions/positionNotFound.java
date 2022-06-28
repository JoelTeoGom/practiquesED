package Exceptions;

public class positionNotFound extends Exception{
    private static final long serialVersionUID = 1L;

    public positionNotFound(int e) {
        super("ERROR : la posicio "+e+" no existeix");
    }
}
