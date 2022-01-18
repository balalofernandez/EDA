
package material.graphs;

import material.Position;

import javax.swing.border.EmptyBorder;
import java.util.*;

/**
 *
 * @author mayte
 * @param <V>
 * @param <E>
 */
public class ELGraph<V,E> implements Graph<V,E> {

    private HashSet<ElEdge<V,E>> edgeList = new HashSet<>();
    private HashSet<ElVertex<V>> vertexList = new HashSet<>();

    private class ElVertex<V> implements Vertex<V>{

        V value;

        public ElVertex(V value) {
            this.value = value;
        }

        @Override
        public V getElement() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if( obj == this)
                return true;
            if (!(obj instanceof ElVertex))
                return false;

            ElVertex<V> v = (ElVertex<V>) obj;
            return v.getElement().equals(value);
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    private class ElEdge<V,E> implements Edge<E>{
            Vertex<V> start;
            Vertex<V> end;
            E value;

        public ElEdge(Vertex<V> start, Vertex<V> end, E value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
            public E getElement() {
                return value;
            }

            public Vertex<V> getStart() {
                return start;
            }

            public void setStart(Vertex<V> start) {
                this.start = start;
            }

            public Vertex<V> getEnd() {
                return end;
            }

            public void setEnd(Vertex<V> end) {
                this.end = end;
            }

            public E getValue() {
                return value;
            }

            public void setValue(E value) {
                this.value = value;
            }
        }

    @Override
    public Collection<? extends Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public Collection<? extends Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        HashSet<Edge<E>> iE = new HashSet<>();
        for (ElEdge<V,E> edge: edgeList) {
            if(edge.getEnd().equals(v)){
                iE.add(edge);
            }
        }
        return iE;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        //Tenemos que devolver el extremo del vértice v que no sea e
        ElEdge<V,E> edge = (ElEdge<V, E>) e;
        return edge.getStart().equals(v) ?  edge.getEnd() :  edge.getStart();//Azúcar sintáctico
    }

    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        LinkedList<Vertex<V>> vList = new LinkedList<>();
        ElEdge<V,E> aux = (ElEdge<V, E>) edge;
        vList.add(aux.getStart());
        vList.add(aux.getEnd());
        return vList;
    }

    @Override
    public Edge<E> areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        for (ElEdge<V,E> edge: edgeList) {
            if(edge.getStart().equals(v1) && edge.getEnd().equals((v2))){
                return edge;
            }
            else if(edge.getStart().equals(v2) && edge.getEnd().equals((v1))){
                return edge;
            }
        }
        return null;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        V oldValue = vertex.getElement();
        ElVertex<V> v  = (ElVertex<V>) vertex;
        v.setValue(vertexValue);
        return oldValue;
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        E oldValue = edge.getElement();
        ElEdge<V,E> e = (ElEdge<V,E>) edge;
        e.value = edgeValue;
        return oldValue;
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        //Suponemos que puede haber vertices repetidos?
        ElVertex<V> v = new ElVertex<>(value);
        vertexList.add(v);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        //Por un lado tenemos que ver que ambos vértices existan
        if(!vertexList.contains(v1))
            throw new RuntimeException("V1 doesn't exist");
        if(!vertexList.contains(v2))
            throw new RuntimeException("V2 doesn't exist");
        
        //También tenemos que ver que esa arista no está en la lista
        ElEdge<V,E> edge = new ElEdge<>(v1,v2,edgeValue);
        if(!edgeList.contains(edge)){
            edgeList.add(edge);
        }
        return edge;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        //Para eliminar un vértice, también tenemos que eliminar todas las aristas que están apuntando al vértice
        V oldValue = vertex.getElement();
        ElVertex<V> v = (ElVertex<V>) vertex;

        //No se debe eliminar un elemento de un conjunto mientras se itera
        List<ElEdge<V,E>> listaAristas = new LinkedList<>();
        for (ElEdge<V,E> e: edgeList) {//Recorremos la lista de aristas
            if(e.getStart().equals(v) || e.getEnd().equals(v)){
                listaAristas.add(e);
            }
        }

        for (ElEdge<V,E> e: listaAristas){
            edgeList.remove(e);
        }
        return oldValue;
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        //Como en un vértice no tenemos los edge, simplemente lo borramos
        E value = edge.getElement();
        ElEdge<V,E> e = (ElEdge<V, E>) edge;
        edgeList.remove(e);
        return value;
    }
    
}
