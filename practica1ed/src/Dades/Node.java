package Dades;

import java.nio.file.DirectoryNotEmptyException;


public class Node<T> {
    private T object;
    private Node<T> prev;
    private Node<T> next;

    public Node(T object){
        this.object = object;
        this.prev = null;
        this.next = null;
    }


    public T getObject(){
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
