package Exceptions;

public class ImpossibleAfegir extends Exception{
    private static final long serialVersionUID = 223L;
    public ImpossibleAfegir(){
        super("ERROR: Aresta ja existeix");
    }

}
