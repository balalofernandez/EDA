package ExamenesAnteriores.ExamenJulio1920;

import material.graphs.ALDigraph;
import sun.awt.image.ImageWatched;
import sun.management.counter.perf.PerfLongCounter;

import java.util.*;

public class Netflix {

    public enum Genero {accion, ciencia_ficcion, comedia, documental, drama, española, europea, familiar, premiada, romantica, terror, thriller}

    HashMap<String, HashSet<Pelicula>> titulos;
    HashMap<String, HashSet<Pelicula>> anos;
    HashMap<String, HashSet<Pelicula>> tipos;

    class Pelicula{

        String titulo;
        int ano;
        ArrayList<String> tipo;

        public Pelicula() {
            tipo = new ArrayList<>(12);
        }

        public Pelicula(String titulo, int ano, ArrayList<String> tipo) {
            this.titulo = titulo;
            this.ano = ano;
            this.tipo = tipo;
        }
    }

    public Netflix() {
        titulos = new HashMap<>();
        anos = new HashMap<>();
    }

    public Netflix(HashMap<String, HashSet<Pelicula>> titulos, HashMap<String, HashSet<Pelicula>> anos, HashMap<String, HashSet<Pelicula>> tipos) {
        this.titulos = titulos;
        this.anos = anos;
        this.tipos = tipos;
    }

    public void addContent(String title, int year, ArrayList<String> types){
        Pelicula p = new Pelicula(title,year,types);
        if(titulos.containsKey(title)){
            titulos.get(title).add(p);
            anos.get(year).add(p);
            for(String tipo : types){
                tipos.get(tipo).add(p);
            }
        }else{
            HashSet<Pelicula> listaP = new HashSet<>();
            listaP.add(p);
            titulos.put(title,listaP);
            anos.put(title,listaP);
            for(String tipo : types){
                tipos.put(tipo,listaP);
            }
        }
    }

    public Iterable<Pelicula> findTitle(String title){
        return titulos.get(title);
    }

    public Iterable<Pelicula> findYear(String year){
        return anos.get(year);
    }

    public Iterable<Pelicula> findType(String type){
        return tipos.get(type);
    }
    public Iterable<Pelicula> findType(Set<String> type){
        LinkedList<Pelicula> pelis = new LinkedList<>();
        if (type == null) return pelis;
        for(String t : type){
            pelis.addAll(tipos.get(t));
        }
        return pelis;
    }



}
