package DadesHash;

import Dades.LlistaNodes;
import Exceptions.keyNotFound;
import Exceptions.objectNotFound;
import Exceptions.positionNotFound;


public interface HashTAD<K extends Comparable<K>,T extends Comparable<T>> {
    void Crear(int mida);

    void Inserir(K key, T data);

    T Obtenir(K key) throws keyNotFound;

    int Buscar(K key)throws objectNotFound;

    int Mida();

    void Esborrar(K key)throws keyNotFound;

    LlistaNodes<T> ObbtenirValors();

    LlistaNodes<K> ObtenirClaus();

    Float ObtenirFactorDeCarrega();

}
