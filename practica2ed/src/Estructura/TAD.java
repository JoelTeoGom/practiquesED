package Estructura;

import Exceptions.ArestaNotFound;
import Exceptions.ImpossibleAfegir;
import Exceptions.ListIsEmpty;

import java.util.LinkedList;

public interface TAD<V,E> {
    void CrearGraf();
    // Constructor per inicialitzar la taula.

    void afegirAresta(V v1, V v2, E e) throws ImpossibleAfegir;
    //Funció per a afegir una aresta.
    //L’operació llença una excepció en cas que no es pugui afegir.

    boolean existeixAresta(V v1, V v2);
    //Funció que ens diu si una aresta existeix.

    E valorAresta(V v1, V v2) throws ArestaNotFound;
    //Funció que retorna el valor d'una aresta.
    //L’operació llença una excepció en cas que no existeixi.

    LinkedList<V> adjacents(V v) throws ListIsEmpty;
    // Funció que retorna una llista que conté tots els nodes adjacents al node passat per paràmetre.
    //L’operació llença una excepció en cas que no es pugui crear aquesta llista.

}
