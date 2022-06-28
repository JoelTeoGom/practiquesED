package Dades;

import java.util.ArrayList;

public class Estacio implements Comparable<Estacio>{
    private int id_estacio;
    private double latitud, longitud;
    private ArrayList<Endoll> endolls;

    public Estacio(int id_estacio, double latitud, double longitud) {
        this.id_estacio = id_estacio;
        this.latitud = latitud;
        this.longitud = longitud;
        endolls = new ArrayList<>();
    }


    @Override
    public int compareTo(Estacio estacio) {
        if(id_estacio == estacio.getId_estacio()){
            return 0;
        }else{
            return -1;
        }
    }

    public int getId_estacio() {
        return id_estacio;
    }

    public void setId_estacio(int id_estacio) {
        this.id_estacio = id_estacio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public ArrayList<Endoll> getEndolls() {
        return endolls;
    }

    public void setEndolls(ArrayList<Endoll> endolls) {
        this.endolls = endolls;
    }
}
