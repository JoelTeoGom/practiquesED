package Estructura;

public class Node<V extends Comparable<V>, E> {

    private V object;
    private LlistaArestes<V,E> llista;

    public Node(V object) {
        this.object = object;
        llista = new LlistaArestes<>();
    }


    public V getObject() {
        return object;
    }

    public void setObject(V object) {
        this.object = object;
    }

    public LlistaArestes<V, E> getLlista() {
        return llista;
    }

    public void setLlista(LlistaArestes<V, E> llista) {
        this.llista = llista;
    }
}
