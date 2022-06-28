package Estructura;

import Exceptions.ArestaNotFound;
import Exceptions.ImpossibleAfegir;
import Exceptions.ListIsEmpty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafGenerica<V extends Comparable<V>, E> implements TAD<V, E> {

    HashMap<V, Node<V, E>> mapa;

    public GrafGenerica() {

    }

    public void CrearGraf() {
        mapa = new HashMap<>();
    }
//hashnode () k , t ---> k = v estacio, t = nodev,e
    @Override
    public void afegirAresta(V objX, V objY, E distancia) throws ImpossibleAfegir {
        Node<V, E> nodeA = mapa.get(objX);
        Node<V, E> nodeB = mapa.get(objY);
        Arestes<V, E> compartida = new Arestes<>(objX, objY, distancia);

        if (!existeixAresta(objX, objY)) {
            nodeA.getLlista().inserirArestaX(compartida);
            nodeB.getLlista().inserirArestaY(compartida);
        }else throw new ImpossibleAfegir();
    }

    @Override
    public boolean existeixAresta(V objX, V objY) {
        boolean existeix = false;
        Node<V, E> nodeA = mapa.get(objX);
        Arestes<V, E> temp = nodeA.getLlista().getHeadX();
        if (temp != null) {
            while (temp.getNextX() != null && !existeix) {
                if (temp.getObjY().compareTo(objY) == 0) {
                    existeix = true;
                } else temp = temp.getNextX();
            }
            if (temp.getObjY().compareTo(objY) == 0) {
                existeix = true;
            }
        }
        return existeix;
    }

    @Override
    public E valorAresta(V v1, V v2) throws ArestaNotFound {
        Node<V, E> aux = mapa.get(v1);
        boolean trobat = false;
        Arestes<V, E> llistaX = aux.getLlista().getHeadX();
        Arestes<V,E> llistaY = aux.getLlista().getHeadY();
        if (llistaX != null) {
            while ( llistaX.getNextX() != null) {
                if(llistaX.getObjY().equals(v2)){
                    trobat = true;
                    return llistaX.getDistancia();
                }
                llistaX = llistaX.getNextX();
            }

            if(llistaX.getObjY().equals(v2) && llistaX != null){
                trobat = true;
                return llistaX.getDistancia();
            }
        }
        if (llistaY != null) {
            while ( llistaY.getNextY() != null) {
                if(llistaY.getObjX().equals(v2)){
                    trobat = true;
                    return llistaY.getDistancia();
                }
                llistaY = llistaY.getNextY();
            }
            if(llistaY.getObjX().equals(v2) && llistaY != null){
                trobat = true;
                return llistaY.getDistancia();
            }
        }
        if(trobat){
            System.out.println("trobat");
        }else throw new ArestaNotFound();
        return null;

    }

    @Override
    public LinkedList<V> adjacents(V v) throws ListIsEmpty {
        Node<V, E> aux = mapa.get(v);
        LinkedList<V> llista = new LinkedList<>();
        Arestes<V, E> llistaX = aux.getLlista().getHeadX();
        Arestes<V,E> llistaY = aux.getLlista().getHeadY();
        if (llistaX != null) {
            while ( llistaX.getNextX() != null) {
                llista.add(llistaX.getObjY());
                llistaX = llistaX.getNextX();
            }
            llista.add(llistaX.getObjY());
        }
        if (llistaY != null) {
            while ( llistaY.getNextY() != null) {
                llista.add(llistaY.getObjX());
                llistaY = llistaY.getNextY();
            }
            llista.add(llistaY.getObjX());
        }
        if(llista.isEmpty()){
           throw new ListIsEmpty();
        }
            return llista;

    }



    public HashMap<V, Node<V, E>> getMapa() {
        return mapa;
    }

    public void setMapa(HashMap<V, Node<V, E>> mapa) {
        this.mapa = mapa;
    }
}
