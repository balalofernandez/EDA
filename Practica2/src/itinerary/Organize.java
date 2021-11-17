
package itinerary;

import java.util.*;

import material.Pair;
import sun.awt.image.ImageWatched;

/**
 *
 * @author mayte
 */
public class Organize {
    HashMap<String,String> mapa;
    HashSet<String> origenes,destinos;
    String origen ="";

    public Organize (List<Pair<String,String>> lista){
        mapa = new HashMap<String,String>();
        origenes = new HashSet<String>();
        destinos = new HashSet<String>();
        for(Pair<String,String> par : lista){
            origenes.add(par.getFirst());
            if(destinos.contains(par.getSecond())){
                throw new RuntimeException("Destino Cíclico");
            }
            destinos.add(par.getSecond());
            mapa.put( par.getFirst(),par.getSecond());

        }
        for(String o:origenes){
            if(!destinos.contains(o)){
                origen = o;
            }
        }
        if(origen == ""){
            throw new RuntimeException("No hay origen");
        }

    }
    
    /**
     * Returns the itinerary to travel or thrown an exception
     * @return 
     */
    public List<String> itineratio(){
        LinkedList<String> lista = new LinkedList<>();
        String ciudad = origen;
        lista.add(origen);
        while(destinos.size()>0){
            ciudad = mapa.get(ciudad);
            lista.add(ciudad);
            destinos.remove(ciudad);
        }
        return lista;
    }
}
