
package material.graphs;

import material.Position;

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

        @Override
        public V getElement() {
            return value;
        }
    }
    private class ElEdge<V,E> implements Edge<E>{
            Vertex<V> start;
            Vertex<V> end;
            E value;

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
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
