package Aplicacio;

import Dades.Ciutada;
import Dades.LlistaNodes;
import DadesHash.HashNode;
import DadesHash.HashTable;
import Exceptions.keyNotFound;
import Exceptions.objectNotFound;
import Exceptions.positionNotFound;

import java.security.KeyException;
import java.util.Iterator;

public class HashApp {
    public static void main(String[] args) throws positionNotFound, objectNotFound, keyNotFound {
        Ciutada c1=new Ciutada("Joel","Teodoro","39951947G");
        Ciutada c2=new Ciutada("Roger","Massana","42444869W");
        Ciutada c3=new Ciutada("Ot","Coll","55431953K");
        Ciutada c4=new Ciutada("Marti","Mestre","65568560E");
        Ciutada c5=new Ciutada("Oriol","Gomez","53627557K");
        Ciutada c6=new Ciutada("Pere","Bartolome","48680175P");
        Ciutada c7=new Ciutada("Gerard","Panisello","93868387M");
        Ciutada c8=new Ciutada("Pedro","Mendoza","34072700V");
        Ciutada c9=new Ciutada("David","Gonzalez","49993347H");
        Ciutada c10=new Ciutada("Joan", "Borras","27922241B");
        Ciutada c11=new Ciutada("Alex","Millan","49993347H");

        HashTable<String,Ciutada> taula = new HashTable<>();
        int size = 5;

        taula.Crear(size);
        taula.Inserir(c1.getDNI(), c1);
        taula.Inserir(c2.getDNI(), c2);
        taula.Inserir(c3.getDNI(), c3);
        taula.Inserir(c4.getDNI(), c4);
        taula.Inserir(c5.getDNI(), c5);
        taula.Inserir(c6.getDNI(), c6);
        taula.Inserir(c6.getDNI(), c6);
        taula.Inserir(c6.getDNI(), c6);
        taula.Inserir(c6.getDNI(), c6);
        taula.Inserir(c7.getDNI(), c7);
        taula.Inserir(c7.getDNI(), c7);
        taula.Inserir(c7.getDNI(), c7);
        taula.Inserir(c7.getDNI(), c7);
        taula.Inserir(c8.getDNI(), c8);
        taula.Inserir(c8.getDNI(), c8);
        taula.Inserir(c9.getDNI(), c9);
        taula.Inserir(c9.getDNI(), c9);
        taula.Inserir(c5.getDNI(), c5);
        taula.Inserir(c6.getDNI(), c6);
        taula.Inserir(c1.getDNI(), c1);
        taula.Inserir(c1.getDNI(), c1);
        taula.Inserir(c1.getDNI(), c1);

        LlistaNodes<Ciutada> l = taula.ObbtenirValors();

        System.out.println("\n========================================\n");
        l = taula.ObbtenirValors();
        for (Ciutada ciutada : l) {
            System.out.println(ciutada.toString());
        }
        System.out.println("\nINSERIM "+c11.getNom()+" QUE TE EL MATEIX DNI QUE "+c9.getNom()+"\n");
        taula.Inserir(c11.getDNI(),c11);
        l = taula.ObbtenirValors();
        for (Ciutada ciutada : l) {
            System.out.println(ciutada.toString());
        }

        System.out.println("\n========================================\n");
        System.out.println("COST DE BUSCAR DNI 93868387M: "+taula.Buscar("93868387M"));
        try {
            taula.Buscar("9323434683327M");
        }catch (objectNotFound e){
            System.out.println(e);
        }

        System.out.println("\n========================================\n");
        System.out.println("OBTENIR DNI 93868387M: \n"+taula.Obtenir("93868387M"));
        try {
            System.out.println(taula.Obtenir("9382323683327M"));
        }catch (keyNotFound e){
            System.out.println(e);
        }

        System.out.println("\n"+taula.Obtenir(c9.getDNI()));
        System.out.println("ESBORREM EL CIUTADA QUE ACABEM D'OBTENIR");
        taula.Esborrar(c9.getDNI());

        try {
            System.out.println(taula.Obtenir(c9.getDNI()));
        }catch (keyNotFound e){
            System.out.println(e);
        }

        System.out.println("\n========================================\n");

        System.out.println("Factor de carrega: "+taula.ObtenirFactorDeCarrega());

        for(int i=0;i< taula.getSize();i++){
            HashNode<String,Ciutada>aux = taula.getTable()[i];
            System.out.println("["+(i+1)+"]");
            while (aux!=null){
                System.out.println(aux.getObject());
                aux = aux.getNext();
            }
        }

        System.out.println("\n========================================\n");
        taula.Inserir(c11.getDNI(),c11);
        l = taula.ObbtenirValors();
        for (Ciutada ciutada : l) {
            System.out.println(ciutada.toString());
        }
        System.out.println("ESBORREM EL DNI 49993347H I 39951947G");
        taula.Esborrar(c11.getDNI());
        taula.Esborrar(c1.getDNI());

        l = taula.ObbtenirValors();
        System.out.println("\n");
        for (Ciutada ciutada : l) {
            System.out.println(ciutada.toString());
        }

        try {
            taula.Esborrar(c11.getDNI());
        }catch (keyNotFound e){
            System.out.println(e);
        }
        System.out.println("\n========================================\n");
        System.out.println("LLISTA DE CLAUS\n");
        LlistaNodes<String> l1 = taula.ObtenirClaus();
        Iterator<String> a = l1.iterator();
        while (a.hasNext()){
            System.out.println(a.next().toString());
        }
    }
}
