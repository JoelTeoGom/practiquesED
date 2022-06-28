package Estructura;

import java.util.Iterator;

public class LlistaArestes <V extends Comparable<V>,E>{

    private Arestes<V, E> headX;
    private Arestes<V, E> headY;
    private Arestes<V, E> tailX;
    private Arestes<V, E> tailY;
    private int numArestes;

    public LlistaArestes(){
        numArestes = 0;
        headX = null;
        headY = null;
        tailX = null;
        tailY = null;
    }

    void inserirArestaX(Arestes<V, E> compartida) {
        if (headX == null) {
            headX = tailX = compartida;
            headX.setPrevX(null);
            tailX.setNextX(null);
            numArestes++;
        } else {
            tailX.setNextX(compartida);
            compartida.setPrevX(tailX);
            tailX = compartida;
            tailX.setNextX(null);
            numArestes++;
        }
    }

    void inserirArestaY(Arestes<V, E> compartida) {
        if (headY == null) {
            headY = tailY = compartida;
            headY.setPrevY(null);
            tailY.setNextY(null);
            numArestes++;
        } else {
            tailY.setNextY(compartida);
            compartida.setPrevY(tailY);
            tailY = compartida;
            tailY.setNextY(null);
            numArestes++;
        }
    }





    public Arestes<V, E> getHeadX() {
        return headX;
    }

    public void setHeadX(Arestes<V, E> headX) {
        this.headX = headX;
    }

    public Arestes<V, E> getHeadY() {
        return headY;
    }

    public void setHeadY(Arestes<V, E> headY) {
        this.headY = headY;
    }

    public Arestes<V, E> getTailX() {
        return tailX;
    }

    public void setTailX(Arestes<V, E> tailX) {
        this.tailX = tailX;
    }

    public Arestes<V, E> getTailY() {
        return tailY;
    }

    public void setTailY(Arestes<V, E> tailY) {
        this.tailY = tailY;
    }

    public int getNumArestes() {
        return numArestes;
    }

    public void setNumArestes(int numArestes) {
        this.numArestes = numArestes;
    }
}
