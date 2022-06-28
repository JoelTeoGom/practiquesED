package Dades;

import java.util.Iterator;

public class LlistaDoblementEnllaçadaOrdenada<T extends Comparable<T>> extends LlistaNodes<T>{

    @Override
    public void Inserir(T data) {
        Node<T> aux = new Node<>(data);
        aux.setNext(head);
        head = aux;
        numElems++;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorLlista<>(this.head);
    }

}
