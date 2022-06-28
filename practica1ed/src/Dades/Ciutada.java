package Dades;
import java.lang.Comparable;

public class Ciutada implements Comparable<Ciutada> {
    protected String Nom;
    protected String Cognom;
    protected String DNI;

    public Ciutada(String nom, String cognom, String DNI) {
        this.Nom = nom;
        this.Cognom = cognom;
        this.DNI = DNI;
    }

    //METODE PER COMPARAR DOS CIUTADANS PER DNI
    @Override
    public int compareTo(Ciutada ciutada){
        if(this.getDNI().equals(ciutada.getDNI())){
            return 0;
        }else{
            return -1;
        }
    }

    //GETTER AND SETTER
    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognom() {
        return Cognom;
    }

    public void setCognom(String cognom) {
        Cognom = cognom;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "Ciutada{" +
                "Nom='" + Nom + '\'' +
                ", Cognom='" + Cognom + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}
