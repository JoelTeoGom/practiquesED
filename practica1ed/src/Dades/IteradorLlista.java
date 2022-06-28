package Dades;

import java.util.Iterator;

public class IteradorLlista<T extends Comparable<T>> implements Iterator<T>{
    private Node<T>current;

    public IteradorLlista(Node<T> head){
        current = head;
    }
    @Override
    public boolean hasNext() {
        return current !=null;
    }

    @Override
    public T next() {
        if (hasNext()){
            T object = current.getObject();
            current = current.getNext();
            return object;
        }
        return null;
    }

}
