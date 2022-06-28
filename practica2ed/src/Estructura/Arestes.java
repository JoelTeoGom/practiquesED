package Estructura;

public class Arestes<V extends Comparable<V>, E> {

    private V objX;
    private V objY;
    private Arestes<V, E> nextX;
    private Arestes<V, E> nextY;
    private Arestes<V, E> prevX;
    private Arestes<V, E> prevY;
    private E distancia;


    public Arestes(V objX, V objY, E distancia) {
        nextX = null;
        nextY = null;
        prevX = null;
        prevY = null;
        this.objX = objX;
        this.objY = objY;
        this.distancia = distancia;
    }


    public V getObjX() {
        return objX;
    }

    public void setObjX(V objX) {
        this.objX = objX;
    }

    public V getObjY() {
        return objY;
    }

    public void setObjY(V objY) {
        this.objY = objY;
    }

    public Arestes<V, E> getNextX() {
        return nextX;
    }

    public void setNextX(Arestes<V, E> nextX) {
        this.nextX = nextX;
    }

    public Arestes<V, E> getNextY() {
        return nextY;
    }

    public void setNextY(Arestes<V, E> nextY) {
        this.nextY = nextY;
    }

    public E getDistancia() {
        return distancia;
    }

    public void setDistancia(E distancia) {
        this.distancia = distancia;
    }

    public Arestes<V, E> getPrevX() {
        return prevX;
    }

    public void setPrevX(Arestes<V, E> prevX) {
        this.prevX = prevX;
    }

    public Arestes<V, E> getPrevY() {
        return prevY;
    }

    public void setPrevY(Arestes<V, E> prevY) {
        this.prevY = prevY;
    }
}
