package Exceptions;

public class ArestaNotFound extends Exception {
    private static final long serialVersionUID = 223L;
    public ArestaNotFound(){
        super("ERROR: La aresta no existeix");
    }
}
