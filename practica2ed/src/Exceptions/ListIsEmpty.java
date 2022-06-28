package Exceptions;

public class ListIsEmpty extends Exception{
    private static final long serialVersionUID = 223L;
    public ListIsEmpty(){
        super("ERROR: La llista esta buida");
    }
}
