package Dades;

import java.lang.Iterable;
import java.util.Iterator;
import Exceptions.*;

public class LlistaNodes<T extends Comparable<T>>  implements TAD<T>, Iterable<T> {

    protected Node<T> head;
    private Node<T> tail;
    protected int numElems;

    public LlistaNodes(){
    }

    @Override
    public void Crear() {
        head=null;
        tail=null;
        numElems=0;
    }

    @Override
    public void Inserir(T data) {
        if(numElems==0){
            head = tail = new Node<>(data);
            head.setPrev(null);
            tail.setNext(null);
            numElems++;
        }else {
            Node<T>aux= new Node<>(data);
            tail.setNext(aux);
            aux.setPrev(tail);
            tail=aux;
            tail.setNext(null);
            numElems++;
        }
    }

    @Override
    public void Inserir(int posicio, T data)throws positionNotFound{
        if(posicio<=numElems+1 && posicio>=1){
            Node<T>aux = new Node<>(data);
            if(posicio==1){
                head.setPrev(aux);
                aux.setNext(head);
                head=aux;
                head.setPrev(null);
                numElems++;
            }else if(posicio==numElems+1) {
                tail.setNext(aux);
                aux.setPrev(tail);
                tail=aux;
                tail.setNext(null);
                numElems++;
            }else {
                Node<T> nodePosicio = obtenirNode(posicio);
                Node<T> nodeAnterior = nodePosicio.getPrev();
                nodeAnterior.setNext(aux);
                nodePosicio.setPrev(aux);
                aux.setNext(nodePosicio);
                aux.setPrev(nodeAnterior);
                numElems++;
            }
        }else throw new positionNotFound(posicio);
    }
    @Override
    public T Obtenir(int posicio)throws positionNotFound{
        if(posicio<=numElems && posicio>=1) {
            return obtenirNode(posicio).getObject();
        }else throw new positionNotFound(posicio);
    }

    private Node<T> obtenirNode(int posicio){
        Node<T> temp = head;
        int i = 1;
        while (i < posicio) {
            temp = temp.getNext();
            i++;
        }
        return temp;
    }
    
    @Override
    public int Longitud() {
        return numElems;
    }

    @Override
    public void Esborrar(int posicio) throws positionNotFound{
        if(posicio<=numElems && posicio>=1) {
            if(posicio == 1){
                head = head.getNext();
                head.setPrev(null);
                numElems--;
            }else if(posicio== numElems){
                tail = tail.getPrev();
                tail.setNext(null);
                numElems--;
            }else{
                Node<T> temp = obtenirNode(posicio);
                Node<T> anterior = temp.getPrev();
                Node<T> seguent = temp.getNext();
                anterior.setNext(seguent);
                seguent.setPrev(anterior);
                numElems--;
            }
        }else throw new positionNotFound(posicio);
    }

    @Override
    public int Buscar(T data)throws objectNotFound{
        boolean trobat = false;
        int cost = 0;
        Node<T> aux = head;
        while (aux.getNext() != null && !trobat){
            if(aux.getObject().compareTo(data) == 0){
                trobat = true;
            }else {
                aux = aux.getNext();
                cost++;
            }
        }
        if(trobat){
            return cost;
        }else throw new objectNotFound(cost);
    }


    @Override
    public Iterator<T> iterator() {
        return new IteradorLlista<>(this.head);
    }

    //GETTER AND SETTER
    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getNumElems() {
        return numElems;
    }

    public void setNumElems(int numElems) {
        this.numElems = numElems;
    }
}
