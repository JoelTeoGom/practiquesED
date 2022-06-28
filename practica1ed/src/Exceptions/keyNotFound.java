package Exceptions;


public class keyNotFound extends Exception{
    private static final long serialVersionUID = 223L;
    public <K> keyNotFound(K key){
        super("ERROR: L'element que te la clau "+key+" no s'ha trobat");
    }
}
