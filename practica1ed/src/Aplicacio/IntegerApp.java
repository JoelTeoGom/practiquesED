package Aplicacio;

import Dades.Ciutada;
import Dades.LlistaNodes;
import DadesHash.HashNode;
import DadesHash.HashTable;
import Exceptions.objectNotFound;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class IntegerApp {
    public static void main(String[] args) throws objectNotFound {

        double [] medias = new double[50];
        double [] standardDev = new double[50];

        double [] mediasHash = new double[50];
        double [] standardDevHash = new double[50];

        int contador = 0;


        for(int mida = 1000; mida <= 50000; mida = mida + 1000){

            //creem una linked list
            LlistaNodes<Integer>linkedlist = new LlistaNodes<>();
            linkedlist.Crear();

            HashTable<Integer,Integer> hashTable = new HashTable<>();
            hashTable.Crear(mida);
            //inserim numeros randoms de entre 1 i mida/2
            int random;
            for (int i = 0; i < mida; i++) {
                random = (int)(Math.random()*(mida / 2)+1);
                linkedlist.Inserir(random);
                hashTable.Inserir(random,random);
            }

            //creem un array de enters on guardarem el resultat de cerca
            int []cercaLinkedList = new int[mida];
            int []cercaHash = new int[mida];

            for(int i= 0; i<mida; i++){
                random = (int)(Math.random()*(mida / 2)+1);
                try{
                    cercaLinkedList[i]=linkedlist.Buscar(random);

                }catch (objectNotFound e){
                      cercaLinkedList[i]=Integer.parseInt(e.getMessage());
                }

                try{
                    cercaHash[i] = hashTable.Buscar(random);
                }catch (objectNotFound e){
                    cercaHash[i] = Integer.parseInt(e.getMessage());
                }
            }


            int sumatori=0;
            int sumatoriHash = 0;
            double media;
            double mediaHash;
            double varianza = 0.0;
            double varianzaHash = 0.0;
            double desviacion;
            double desviacionHash;

            for(int i = 0; i<mida; i++){
                sumatori += cercaLinkedList[i];
                sumatoriHash += cercaHash[i];
            }
            media = (double) sumatori/mida;
            mediaHash = (double) sumatoriHash/mida;
            medias[contador] = media;
            mediasHash[contador] = mediaHash;

            for(int i = 0 ; i < mida; i++){
                double rango;
                double rangoHash;
                rango = Math.pow(cercaLinkedList[i] - media, 2);
                rangoHash = Math.pow(cercaHash[i] - mediaHash, 2);
                varianza = varianza + rango;
                varianzaHash = varianzaHash + rangoHash;
            }
            varianza = varianza / mida;
            varianzaHash = varianzaHash / mida;
            desviacion = Math.sqrt(varianza);
            desviacionHash = Math.sqrt(varianzaHash);
            standardDev[contador] = desviacion;
            standardDevHash[contador] = desviacionHash;



            //actualitzem les taules
            contador++;
        }

        try {
            FileWriter fw = new FileWriter("C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica1ed\\src\\Integers.csv");
            FileWriter fw2 = new FileWriter("C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica1ed\\src\\IntegersHash.csv");
            fw.write("MIDA;COST;DEVIATION\n");
            fw2.write("MIDA;COST;DEVIATION\n");
            int aux = 1000;
            for (int i = 0; i < 50; i++){
                fw.write(""+aux+";" + medias[i] + ";" + standardDev[i]+"\n");
                fw2.write(""+aux+";" + mediasHash[i] + ";" + standardDevHash[i]+"\n");
                aux+=1000;
            }
            System.out.println("Writen succesfully");
            fw.close();
            fw2.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

}



