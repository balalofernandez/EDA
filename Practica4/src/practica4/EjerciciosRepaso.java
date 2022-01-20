package practica4;

import material.graphs.Edge;
import material.graphs.Graph;
import material.graphs.Vertex;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EjerciciosRepaso<V,E> {
    public EjerciciosRepaso() {
    }
    public Collection<Vertex<V>> segundosAdyacentes(Graph<V,E> graph, Vertex<V> v){
        if(!graph.vertices().contains(v))
            throw new RuntimeException("El vértice dado no pertenece al grafo");
        HashSet<Vertex<V>> sol = new HashSet<>();
        for(Edge<E> edge : graph.incidentEdges(v)){
            Vertex<V> primerOp = graph.opposite(v,edge);
            if(primerOp == v) continue;
            for (Edge<E> edge2 : graph.incidentEdges(primerOp)) {
                Vertex<V> segundoOp = graph.opposite(primerOp, edge2);
                if(segundoOp == primerOp || segundoOp == v) continue;
                sol.add(segundoOp);
            }
        }
        return sol;
    }

    public Vertex<V> nodoMasAristas(Graph<V,E> graph){
        if(graph == null)
            throw new RuntimeException("Grafo vacío");
        Vertex<V> mejor = null;
        int num = -1;
        int auxNum;
        for(Vertex<V> nodo : graph.vertices()){
            auxNum = graph.incidentEdges(nodo).size();
            if(auxNum> num || num==-1){
                mejor = nodo;
            }
        }
        return mejor;
    }
}
