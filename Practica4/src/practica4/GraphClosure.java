
package practica4;

import material.graphs.Digraph;
import material.graphs.ELDigraph;
import material.graphs.Edge;
import material.graphs.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mayte
 * @param <V>
 * @param <E>
 */
public class GraphClosure<V,E> {
    /**
     * Computes the Digraph's transitive clousure using the Floyd-Wharsall algorithm
     * @param g
     * @return 
     */
    public Digraph<V,E> transitiveClosure (Digraph<V,E> g){
        ELDigraph<V,E> grafoAux = new ELDigraph<>();
        for(Vertex<V> vertice:g.vertices()){
            HashSet<Vertex<V>> verticesTransitivos = transitiveVertices(vertice,g);
            Vertex<V> v1 = grafoAux.insertVertex(vertice.getElement());
            for(Vertex<V> v :verticesTransitivos){
                Vertex<V> v2 = grafoAux.insertVertex(v.getElement());
                grafoAux.insertEdge(v1,v2,null);
            }
        }
        return grafoAux;
    }

    private HashSet<Vertex<V>> transitiveVertices (Vertex<V> vertex, Digraph<V,E> g){
        //Cuidado con los ciclos
        HashSet<Vertex<V>> adyacents = new HashSet<Vertex<V>>();
        LinkedList<Vertex<V>> aux = new LinkedList<>();
        aux.add(vertex);
        //Primero vamos a ir añadiendo a la lista y al hashset
        while(!aux.isEmpty()){
            Vertex<V> v = aux.pop();
            List<Edge<E>> outs = g.outputEdges(v);
            for (Edge<E> e : outs){
                if(!adyacents.contains(g.opposite(v,e)) && !g.opposite(v,e).equals(vertex)){
                    adyacents.add(g.opposite(v,e));//En realidad el add ya comprueba si está
                    aux.add(g.opposite(v,e));
                }
            }
        }
        return  adyacents;
    }

    public Digraph<V,E> FloydWarshall(Digraph<V,E> g){
        ArrayList<V,E>[] grafos = new ArrayList<Digraph<V,E>>(g.vertices().size());

        grafos[0] = g;
    }
}
