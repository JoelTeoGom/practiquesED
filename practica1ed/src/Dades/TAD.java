package Dades;

import Exceptions.*;

public interface TAD <T> {
    void Crear();

    void Inserir(T data);

    void Inserir(int posicio, T data) throws positionNotFound;

    T Obtenir(int posicio)throws positionNotFound;

    int Longitud();

    void Esborrar(int posicio)throws positionNotFound;

    int Buscar(T data)throws objectNotFound;
}
