package Aplicacio;

import Dades.Ciutada;
import Dades.LlistaNodes;
import Dades.LlistaDoblementEnllaçadaOrdenada;
import Exceptions.*;
import java.util.Iterator;


//commit
public class App {
    public static void main(String[] args) throws positionNotFound, objectNotFound {
        Ciutada c1=new Ciutada("Joel","Teodoro","39951947G");
        Ciutada c2=new Ciutada("Roger","Massana","42444869W");
        Ciutada c3=new Ciutada("Ot","Coll","55431953K");
        Ciutada c4=new Ciutada("Marti","Mestre","65568560E");
        Ciutada c5=new Ciutada("Oriol","Gomez","53627557K");
        Ciutada c6=new Ciutada("Pere","Bartolome","48680175P");
        Ciutada c7=new Ciutada("Gerard","Panisello","93868387M");
        Ciutada c8=new Ciutada("Morad","Andreu","34072700V");
        Ciutada c9=new Ciutada("David","Gonzalez","49993347H");
        Ciutada c10=new Ciutada("Joan", "Borras","27922241B");
        Ciutada c11=new Ciutada("Alex","Millan","49993347H");

        LlistaNodes<Ciutada> ciutadans = new LlistaNodes<>();
        ciutadans.Crear();
        ciutadans.Inserir(c1);
        ciutadans.Inserir(c2);
        ciutadans.Inserir(c3);
        ciutadans.Inserir(c4);
        ciutadans.Inserir(c5);
        ciutadans.Inserir(c6);
        ciutadans.Inserir(1,c6);
        ciutadans.Inserir(4,c11);

        try{
            ciutadans.Inserir(10,c10);
        }catch (positionNotFound e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n========================================\n");

        System.out.println("PROVA DE CERCA\n");
        System.out.printf("COST DE BUSCAR "+c1.getNom()+": "+ciutadans.Buscar(c1)+"\n");

        try{
            System.out.printf("COST DE BUSCAR "+c10.getNom()+" "+ciutadans.Buscar(c10));
        }catch (objectNotFound e){
            System.out.println("No s'ha trobat, cost: "+Integer.parseInt(e.getMessage()));
        }

        System.out.println("\n========================================\n");

        System.out.println("PROVA DE OBTENIR\n");
        try{
            System.out.println("OBTENIR: "+ciutadans.Obtenir(11));
        }catch (positionNotFound e){
            System.out.println(e.getMessage());
        }
        System.out.println("OBTENIR: "+ciutadans.Obtenir(5));


        System.out.println("\n========================================\n");

        System.out.println("PROVA DE LONGITUD\n");
        System.out.println("longitud: "+ciutadans.Longitud()+"\n");
        ciutadans.Inserir(c2);
        System.out.println("longitud: "+ciutadans.Longitud());

        System.out.println("\n========================================\n");

        Iterator<Ciutada> i = ciutadans.iterator();
        while (i.hasNext()){
            System.out.println(i.next().toString());
        }
        System.out.println("BORREM LA PRIMERA,L'ULTIMA I LA 5");
        ciutadans.Esborrar(5);
        ciutadans.Esborrar(1);
        ciutadans.Esborrar(ciutadans.getNumElems());
        System.out.println("\n");

        i = ciutadans.iterator();
        while (i.hasNext()){
            System.out.println(i.next().toString());
        }

        try{
            ciutadans.Esborrar(10);
        }catch (positionNotFound e){
            System.out.println(e.getMessage());
        }

        System.out.println("\nTornem a utilitzar el iterador");
        i = ciutadans.iterator();
        while (i.hasNext()){
            System.out.println(i.next().toString());
        }

        System.out.println("\nEXTRA: Inserir al principi\n");
        LlistaDoblementEnllaçadaOrdenada<Ciutada> ciutadansPrincipi = new LlistaDoblementEnllaçadaOrdenada<>();
        ciutadansPrincipi.Crear();
        ciutadansPrincipi.Inserir(c1);
        ciutadansPrincipi.Inserir(c2);
        ciutadansPrincipi.Inserir(c3);
        ciutadansPrincipi.Inserir(c4);
        ciutadansPrincipi.Inserir(c5);
        ciutadansPrincipi.Inserir(c6);
        ciutadansPrincipi.Inserir(c1);

        i = ciutadansPrincipi.iterator();
        while (i.hasNext()){
            System.out.println(i.next().toString());
        }


    }

}
