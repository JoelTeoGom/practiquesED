package App;

import Dades.Endoll;
import Dades.Estacio;
import Estructura.Arestes;
import Estructura.GrafGenerica;
import Estructura.Node;
import Exceptions.ArestaNotFound;
import Exceptions.ImpossibleAfegir;
import Exceptions.ListIsEmpty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Aplicacio {


    public static void main(String[] args) throws ListIsEmpty, ImpossibleAfegir, ArestaNotFound {

        /*variables*/
        int id_estacio, id;
        float potencia;
        double latitud, longitud;
        Estacio aux;
        Endoll aux2;
        GrafGenerica<Estacio, Double> graf = new GrafGenerica<>();
        LinkedList<Estacio> llistaMapa = new LinkedList<>();
        /*variables*/

        graf.CrearGraf();

        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica2ed\\src\\icaen.json");
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {

                JSONObject estacio = (JSONObject) obj;
                id_estacio = Integer.parseInt((String) estacio.get("id_estacio"));
                id = Integer.parseInt((String) estacio.get("id"));
                if (estacio.get("potencia").equals("")) potencia = 0;
                else potencia = Float.parseFloat((String) estacio.get("potencia"));

                latitud = Double.parseDouble((String) estacio.get("latitud"));
                longitud = Double.parseDouble((String) estacio.get("longitud"));

                aux = new Estacio(id_estacio, latitud, longitud);
                aux2 = new Endoll(id, potencia);


                if (llistaMapa.isEmpty()) {
                    aux.getEndolls().add(aux2);
                    llistaMapa.add(aux);
                } else {
                    int i = 0;
                    while (llistaMapa.get(i).getId_estacio() != aux.getId_estacio() && i + 1 < llistaMapa.size()) {
                        i++;
                    }
                    if (llistaMapa.get(i).getId_estacio() == aux.getId_estacio()) {
                        llistaMapa.get(i).getEndolls().add(aux2);
                    } else {
                        aux.getEndolls().add(aux2);
                        llistaMapa.add(aux);
                    }
                }

            }

            for (Estacio estacio : llistaMapa) {
                Node<Estacio, Double> node = new Node<>(estacio);
                graf.getMapa().put(estacio, node);

            }


            for (int i = 0; i < llistaMapa.size(); i++) {
                int indexMin = 0;
                double minimaDistancia = 1000;
                Node<Estacio, Double> key = graf.getMapa().get(llistaMapa.get(i));
                for (int j = i; j < llistaMapa.size(); j++) {

                    Node<Estacio, Double> key2 = graf.getMapa().get(llistaMapa.get(j));
                    double distanciaX = Math.pow(key.getObject().getLatitud() - key2.getObject().getLatitud(), 2);
                    double distanciaY = Math.pow(key.getObject().getLongitud() - key2.getObject().getLongitud(), 2);
                    double distancia = Math.sqrt(distanciaX + distanciaY);

                    if (distancia <= minimaDistancia && distancia != 0.0) {
                        minimaDistancia = distancia;
                        indexMin = j;
                    }
                    if (distancia < 40 && distancia != 0.0) {
                        try {
                            graf.afegirAresta(key.getObject(), key2.getObject(), distancia);
                        } catch (ImpossibleAfegir e) {
                            System.out.println(e);
                        }
                    }
                }
                if (key.getLlista().getNumArestes() == 0) {
                    double anteriorMin = 1000;
                    int indexAnterior = 0;

                    for (int k = 0; k < i; k++) {
                        Node<Estacio, Double> key2 = graf.getMapa().get(llistaMapa.get(k));
                        double distanciaX = Math.pow(key.getObject().getLatitud() - key2.getObject().getLatitud(), 2);
                        double distanciaY = Math.pow(key.getObject().getLongitud() - key2.getObject().getLongitud(), 2);
                        double distancia = Math.sqrt(distanciaX + distanciaY);

                        if (distancia <= minimaDistancia) {
                            indexAnterior = k;
                            anteriorMin = distancia;
                        }
                    }
                    if (anteriorMin <= minimaDistancia) {
                        try {
                            graf.afegirAresta(key.getObject(), llistaMapa.get(indexAnterior), anteriorMin);
                        } catch (ImpossibleAfegir e) {
                            System.out.println(e);
                        }
                    } else {
                        try {
                            graf.afegirAresta(key.getObject(), llistaMapa.get(indexMin), minimaDistancia);
                        } catch (ImpossibleAfegir e) {
                            System.out.println(e);
                        }
                    }

                }
            }

            guardarAdjacents(graf,llistaMapa,"C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica2ed\\src\\adjacents.txt");





        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //GRAF DE PROVA
        GrafGenerica<Estacio, Double> grafProva = new GrafGenerica<>();
        grafProva.CrearGraf();
        Estacio e1 = new Estacio(4200, 40.006, 2);
        Estacio e2 = new Estacio(4201, 40.0021, 1.89);
        Estacio e3 = new Estacio(4202, 40.012, 1.94);
        Estacio e4 = new Estacio(4203, 40.23, 2.001);
        Estacio e5 = new Estacio(4204, 40.2345, 2.101);
        Estacio e6 = new Estacio(4205, 300, -6);

        Node<Estacio, Double> n1 = new Node<>(e1);
        Node<Estacio, Double> n2 = new Node<>(e2);
        Node<Estacio, Double> n3 = new Node<>(e3);
        Node<Estacio, Double> n4 = new Node<>(e4);
        Node<Estacio, Double> n5 = new Node<>(e5);
        Node<Estacio, Double> n6 = new Node<>(e6);

        grafProva.getMapa().put(e1, n1);
        grafProva.getMapa().put(e2, n2);
        grafProva.getMapa().put(e3, n3);
        grafProva.getMapa().put(e4, n4);
        grafProva.getMapa().put(e5, n5);
        grafProva.getMapa().put(e6, n6);

        LinkedList<Estacio> estacios = new LinkedList<>();
        estacios.add(e1);
        estacios.add(e2);
        estacios.add(e3);
        estacios.add(e4);
        estacios.add(e5);
        estacios.add(e6);

        int contador = 0;
        for (int i = 0; i < estacios.size(); i++) {
            double minimaDistancia = 1000;
            int indexMin = 0;
            Node<Estacio, Double> key = grafProva.getMapa().get(estacios.get(i));
            for (int j = i; j < estacios.size(); j++) {
                Node<Estacio, Double> key2 = grafProva.getMapa().get(estacios.get(j));
                double distanciaX = Math.pow(key.getObject().getLatitud() - key2.getObject().getLatitud(), 2);
                double distanciaY = Math.pow(key.getObject().getLongitud() - key2.getObject().getLongitud(), 2);
                double distancia = Math.sqrt(distanciaX + distanciaY);
                 //per fer prova de arestes

                if(distancia <= minimaDistancia && distancia != 0.0){
                    minimaDistancia = distancia;
                    indexMin = j;
                }
                if (distancia < 40 && distancia != 0.0) {
                    try {
                        contador++;
                        grafProva.afegirAresta(key.getObject(), key2.getObject(), distancia);
                    }catch (ImpossibleAfegir e){
                        System.out.println(e);
                    }
                }
            }
            if(key.getLlista().getNumArestes() == 0){
                double anteriorMin = 1000;
                int indexAnterior = 0;

                for(int k = 0; k<i; k++){
                    Node<Estacio, Double> key2 = grafProva.getMapa().get(estacios.get(k));
                    double distanciaX = Math.pow(key.getObject().getLatitud() - key2.getObject().getLatitud(), 2);
                    double distanciaY = Math.pow(key.getObject().getLongitud() - key2.getObject().getLongitud(), 2);
                    double distancia = Math.sqrt(distanciaX + distanciaY);

                    if(distancia <= minimaDistancia){
                        indexAnterior = k;
                        anteriorMin = distancia;
                    }
                }
                if(anteriorMin<= minimaDistancia){
                    try {
                        grafProva.afegirAresta(key.getObject(), estacios.get(indexAnterior), anteriorMin);
                    }catch (ImpossibleAfegir e){
                        System.out.println(e);
                    }
                }else {
                    try {
                        grafProva.afegirAresta(key.getObject(), estacios.get(indexMin), minimaDistancia);
                    }catch (ImpossibleAfegir e){
                        System.out.println(e);
                    }
                }

            }

        }

        //guardarAdjacents(grafProva,estacios,"C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica2ed\\src\\adjacentsGrafProva.txt");

        Node<Estacio, Double> A = new Node<>(e1);
        Node<Estacio, Double> B = new Node<>(e2);
        Node<Estacio, Double> C = new Node<>(e3);
        Node<Estacio, Double> D = new Node<>(e4);
        Node<Estacio, Double> E = new Node<>(e5);
        Node<Estacio, Double> F = new Node<>(e6);

        GrafGenerica<Estacio,Double> grafAlgoritmes = new GrafGenerica<>();
        grafAlgoritmes.CrearGraf();
        grafAlgoritmes.getMapa().put(e1, A);
        grafAlgoritmes.getMapa().put(e2, B);
        grafAlgoritmes.getMapa().put(e3, C);
        grafAlgoritmes.getMapa().put(e4, D);
        grafAlgoritmes.getMapa().put(e5, E);
        grafAlgoritmes.getMapa().put(e6, F);

        //conexions A
        grafAlgoritmes.afegirAresta(e1,e2,20.0);
        grafAlgoritmes.afegirAresta(e1,e3,100.0);
        grafAlgoritmes.afegirAresta(e1,e4,25.0);
        grafAlgoritmes.afegirAresta(e1,e6,200.0);

        //conexions B
        grafAlgoritmes.afegirAresta(e2,e4,30.0);

        //conexions C
        grafAlgoritmes.afegirAresta(e3,e5,30.0);
        grafAlgoritmes.afegirAresta(e3,e6,50.0);


        //conexiones D
        grafAlgoritmes.afegirAresta(e4,e5,40.0);

        //conexiones E
        grafAlgoritmes.afegirAresta(e5,e6,60.0);

        guardarAdjacents(grafAlgoritmes,estacios,"C:\\Users\\joelt\\Escritorio\\PractiquesED\\practica2ed\\src\\algorithm.txt");

//        ArrayList<String> nodesNoVist = zonesDistMaxNoGarantida(grafAlgoritmes,"4200",41,estacios);
//        for(int i=0; i< nodesNoVist.size();i++){
//            System.out.println(""+nodesNoVist.get(i)+"");
//        }

        //valor aresta aresta
        System.out.println(grafAlgoritmes.valorAresta(e1,e2));
        System.out.println(grafAlgoritmes.valorAresta(e2,e1));
        System.out.println(grafAlgoritmes.valorAresta(e1,e3));
        System.out.println(grafAlgoritmes.valorAresta(e3,e1));
        System.out.println(grafAlgoritmes.valorAresta(e1,e4));
        System.out.println(grafAlgoritmes.valorAresta(e1,e6));
        System.out.println(grafAlgoritmes.valorAresta(e2,e4));
        System.out.println(grafAlgoritmes.valorAresta(e3,e5));
        System.out.println(grafAlgoritmes.valorAresta(e3,e6));
        System.out.println(grafAlgoritmes.valorAresta(e4,e5));
        System.out.println(grafAlgoritmes.valorAresta(e5,e6));


        System.out.println("DFS");
        try{
            ArrayList<String> nodesNoVist = zonesDistMaxNoGarantida(grafAlgoritmes,"4200",0,estacios);
            for(int i=0; i< nodesNoVist.size();i++){
                System.out.println(""+nodesNoVist.get(i)+"");
            }
        }catch (ArestaNotFound e){}
        System.out.println("\n");
        try{
            ArrayList<String> nodesNoVist = zonesDistMaxNoGarantida(grafAlgoritmes,"4200",1000,estacios);
            for(int i=0; i< nodesNoVist.size();i++){
                System.out.println(""+nodesNoVist.get(i)+"");
            }
        }catch (ArestaNotFound e){}

        System.out.println("======================================================");
        System.out.println("DIJSKTRA");
        try{
            ArrayList<String> cami = camiOptim(grafAlgoritmes,"4200","4205",55,estacios);
            int suma = 0;
            for(int i=0; i< cami.size();i++){
                System.out.println(""+cami.get(i)+"");
            }

        }catch (ArestaNotFound e){
            System.out.println("no s'ha trobat el cami");
        }
        System.out.println("\n");
        try{
            ArrayList<String> cami = camiOptim(grafAlgoritmes,"4200","4205",1000,estacios);
            int suma = 0;
            for(int i=0; i< cami.size();i++){
                System.out.println(""+cami.get(i)+"");
            }
        }catch (ArestaNotFound e){
            System.out.println("no s'ha trobat el cami");
        }

    }

    private static void guardarAdjacents(GrafGenerica<Estacio,Double> graf, LinkedList<Estacio> llistaMapa, String path){
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("LLISTA ADJACENTS\n\n");

            for (int i = 0; i < llistaMapa.size(); i++){
                try {
                    LinkedList<Estacio> adjacents = graf.adjacents(llistaMapa.get(i));
                    fw.write("ESTACIO ID: "+llistaMapa.get(i).getId_estacio()+"----------->");
                    for(int j = 0; j< adjacents.size(); j++){
                        fw.write(""+adjacents.get(j).getId_estacio()+"---->");
                    }
                    fw.write("\n");
                }catch (ListIsEmpty e){
                    System.out.println(e);
                }
            }
            System.out.println("Writen succesfully");
            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }


    private static ArrayList<String> camiOptim(GrafGenerica<Estacio, Double> graf, String origen, String desti, double autonomia, LinkedList<Estacio> estacios) throws ArestaNotFound, ListIsEmpty {
        ArrayList<String> camiOptim = new ArrayList<>();
        Map<Estacio, Double> mapaDistancies = new HashMap<>();
        Map<Estacio, Estacio> anteriors = new HashMap<>();
        LinkedList<Estacio> cua = new LinkedList<>();
        Estacio ini = null;
        Estacio fi = null;
        double acum = 0.0;
        boolean trobat = false, excepcio = false;

        ArrayList<Estacio> estacions = new ArrayList<>();
        estacions.addAll(estacios);

        //inicialitzar totes les distàncies a un valor infinit , ya que son
        //desconegudes al principi, excepte el primer node, que serà 0.
        for (Estacio estacio : estacions) {
            mapaDistancies.put(estacio, Double.MAX_VALUE);
            cua.add(estacio);
            if (String.valueOf(estacio.getId_estacio()).equals(origen)) {
                ini = estacio;
            }
            if (String.valueOf(estacio.getId_estacio()).equals(desti)) {
                fi = estacio;
            }
        }

        //fiquem el origen a 0
        mapaDistancies.put(ini, 0.0);
        Estacio minima = null;

        while (!cua.isEmpty() && !trobat) {
            //trobem la minima distancia
            Double distMin = Double.MAX_VALUE;
            for (Estacio estacio : cua) {
                if (mapaDistancies.get(estacio) < distMin) {
                    distMin = mapaDistancies.get(estacio);
                    minima = estacio;
                }
            }

            //si ja no esta a la cua el minim surt
            if (!cua.remove(minima)) {
                excepcio = true;
            }

            //si la excepcio == true o basicament la distancia entre la minima es 0 (ja l'hem trobat) sortim
            if (minima.compareTo(fi) == 0 || excepcio) {
                trobat = true;
            } else {
                double acumulada;
                //mirem tots els adjacents a la minima
                for ( Estacio estacio : graf.adjacents(minima)) {
                    if (cua.contains(estacio)) {
                        try {
                            //sumem la distancia entre la minima i els adjacents
                            acumulada = mapaDistancies.get(minima) + graf.valorAresta(minima, estacio);
                            System.out.println("Distancia acumulada (No es la suma de les arestes del camiOptim): "+acumulada);
                            //guardem la minima acumulada i actualitzem la distancia minima
                            if (acumulada < mapaDistancies.get(estacio) && graf.valorAresta(minima, estacio) <= autonomia) {
                                mapaDistancies.put(estacio, acumulada);
                                anteriors.put(estacio, minima);
                            }
                        } catch (ArestaNotFound e){}
                    }
                }
            }

        }
        while (minima != ini) {
            //afegim el cami que tenim guardat a la
            camiOptim.add(0, String.valueOf(minima.getId_estacio()));
            minima = anteriors.get(minima);
        }

        camiOptim.add(0, String.valueOf(minima.getId_estacio()));
        if (excepcio)
            throw new ArestaNotFound();


        return camiOptim;
    }




    private static ArrayList<String> zonesDistMaxNoGarantida(GrafGenerica<Estacio, Double> graf, String origen, double autonomia, LinkedList<Estacio> estacios) throws ListIsEmpty, ArestaNotFound {
        Estacio est = null;
        ArrayList<String> nodesNoVisitats = new ArrayList<>();
        ArrayList<Estacio> llistEst = new ArrayList<>();
        llistEst.addAll(estacios);

        //guardem la estacio inicial i inicialitzem una llista de nodes no visitats
        for (Estacio estacio : llistEst) {
            if (String.valueOf(estacio.getId_estacio()).equals(origen))
                est = estacio;
            nodesNoVisitats.add(String.valueOf(estacio.getId_estacio()));
        }
        //si no trobem la estacio retornem null, perque la llista estara buida
        if (est == null)
            return null;

        //treiem el origen de no visitats i l'afegim a la pila
        nodesNoVisitats.remove(String.valueOf(est.getId_estacio()));
        Stack<Estacio> stack = new Stack<>();
        Map<Estacio, Estacio> estanVisitats = new HashMap<>();

        //fiquem la estacio d'origen a la pila
        stack.push(est);

        //mentres la pila no estigui buida
        while (!stack.isEmpty()) {

            //treieme el primer element de la pila i el guardem en current
            Estacio current = stack.pop();
            //si no esta visitat el marquem com a visitat
            if (!estanVisitats.containsKey(current)) {
                estanVisitats.put(current, current);
                //en aquest bucle anirem apilant tots els adjacents del node current fins que no en tingui mes
                for (Estacio dest : graf.adjacents(current)) {
                    try {
                        if (!estanVisitats.containsKey(dest) && graf.valorAresta(current, dest) <= autonomia) {
                            stack.push(dest);
                            nodesNoVisitats.remove(String.valueOf(dest.getId_estacio()));
                        }
                    }catch (ArestaNotFound e){}
                }
                //cuan ja ha vist a tots els adjacents desapilem i tornem a repetir el pas de mirar tots els adjacents
            }
        }
        return nodesNoVisitats;
    }


}




