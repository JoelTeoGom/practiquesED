package DadesHash;

import Dades.Ciutada;
import Dades.IteradorLlista;
import Dades.LlistaNodes;
import Dades.Node;
import Exceptions.keyNotFound;
import Exceptions.objectNotFound;
import Exceptions.positionNotFound;

import java.util.Iterator;
import java.util.LinkedList;


public class HashTable<K extends Comparable<K>,T extends Comparable<T>> implements HashTAD<K,T> {
    private int size;
    private HashNode<K,T>[] table;
    private int numElems;


    public HashTable() {
    }


    @Override
    public void Crear(int size) {
        //creem la taula
        numElems = 0;
        this.size = size;
        table = new HashNode[size];
    }

    private int getHash (K key){
        String string = key.toString();
        int resultat = 0;
        for(int i =0;i<string.length();i++){
            resultat = (resultat*32 + string.charAt(i)) % size;
        }
        return resultat;
    }


    @Override
    public void Inserir(K key, T data) {

        if(ObtenirFactorDeCarrega()>0.75){
            resize(key,data);
        }else {
            int arrayIndex = getHash(key);
            HashNode<K, T> aux = new HashNode<>(key, data);
            if (table[arrayIndex] == null) {
                table[arrayIndex] = aux;
                numElems++;
            } else {
                HashNode<K, T> temp = table[arrayIndex];
                boolean trobat = false;
                while (temp.getNext() != null && !trobat) {
                    if (temp.getKey().equals(key)) {
                        trobat = true;
                    } else {
                        temp = temp.getNext();
                    }
                }
                if (trobat || temp.getKey().equals(key)) {
                    temp.setObject(data);
                } else {
                    temp.setNext(aux);
                    numElems++;
                }
            }
        }

    }

    @Override
    public T Obtenir(K key) throws keyNotFound {
        int arrayIndex = getHash(key);
        boolean trobat = false;
        HashNode<K, T> temp = table[arrayIndex];
        while (temp != null && !trobat) {
            if (temp.getKey().equals(key)) {
                trobat = true;
            } else {
                temp = temp.getNext();
            }
        }
        if (trobat) {
            return temp.getObject();
        } else throw new keyNotFound(key);

    }

    @Override
    public int Buscar(K key) throws objectNotFound {
        int arrayIndex = getHash(key);
        boolean trobat = false;
        int cost = 1;
        HashNode<K, T> temp = table[arrayIndex];
        while (temp != null && !trobat) {
            if (temp.getKey().equals(key)) {
                trobat = true;
            } else {
                temp = temp.getNext();
                cost++;
            }
        }
        if (trobat) {
            return cost;
        } else throw new objectNotFound(cost);
    }

    @Override
    public int Mida() {
        return numElems;
    }

    @Override
    public void Esborrar(K key) throws keyNotFound{
        int arrayIndex = getHash(key);
        HashNode<K,T> temp = table[arrayIndex];
        if(temp == null){
            throw new keyNotFound(key);
        }else{
            if(temp.getKey().equals(key)){
                table[arrayIndex] = temp.getNext();
                numElems--;
            }else{
                boolean trobat = false;
                while (temp.getNext() != null && !trobat) {
                    if (temp.getNext().getKey().equals(key)) {
                        trobat = true;
                    } else {
                        temp = temp.getNext();
                    }
                }
                if(trobat){
                    temp.setNext(temp.getNext().getNext());
                    numElems--;
                }else throw new keyNotFound(key);
            }
        }

    }

    @Override
    public LlistaNodes<T> ObbtenirValors() {
        LlistaNodes<T>valors = new LlistaNodes<>();
        for(int i=0;i<size;i++){
            HashNode<K,T>aux = table[i];
            while (aux!=null){
                valors.Inserir(aux.getObject());
                aux = aux.getNext();
            }
        }
        return valors;
    }

    @Override
    public LlistaNodes<K> ObtenirClaus() {
        LlistaNodes<K>keys= new LlistaNodes<>();
        for(int i=0;i<size;i++){
            HashNode<K,T>aux = table[i];
            while (aux!=null){
                keys.Inserir(aux.getKey());
                aux = aux.getNext();
            }
        }
        return keys;
    }

    @Override
    public Float ObtenirFactorDeCarrega() {
        int ocupat=0;
        for(int i=0; i<size;i++){
            if(table[i] != null){
               ocupat++;
            }
        }
        return (float)ocupat/size;
    }

    public void resize(K key, T data){

        int tamany = (int) (size *2);
        HashNode<K,T>[] taulaAux = new HashNode[tamany];

        LlistaNodes<K> claus = ObtenirClaus();
        LlistaNodes<HashNode<K,T>> llistaHash = new LlistaNodes<>();

        llistaHash.Inserir(new HashNode<>(key,data));

        Node<K> temp = claus.getHead();
        while (temp!=null){
            int arrayIndex = getHash(temp.getObject());
            boolean trobat = false;
            HashNode<K, T> temp2 = table[arrayIndex];
            while (temp2 != null && !trobat) {
                if (temp2.getKey().equals(temp.getObject())) {
                    trobat = true;
                } else {
                    temp2 = temp2.getNext();
                }
            }
            if (trobat) {
                llistaHash.Inserir(temp2);
            }
            temp = temp.getNext();
        }

        Node<HashNode<K,T>> temp3 = llistaHash.getHead();
        size = tamany;
        table = taulaAux;
        numElems = 0;
        while (temp3 != null){
            Inserir(temp3.getObject().getKey(),temp3.getObject().getObject());
            temp3 = temp3.getNext();
        }
        System.out.println("num elems: "+numElems);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HashNode<K, T>[] getTable() {
        return table;
    }

    public void setTable(HashNode<K, T>[] table) {
        this.table = table;
    }

    public int getNumElems() {
        return numElems;
    }

    public void setNumElems(int numElems) {
        this.numElems = numElems;
    }
}