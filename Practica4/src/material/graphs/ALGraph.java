package material.graphs;

import java.util.*;

/**
 *
 * @author mayte
 * @param <V>
 * @param <E>
 */
public class ALGraph<V,E> implements Graph <V,E> {//Implementar un grafo de lista de adyacencias
    //En la lista de adyacencia cada vértice conoce las aristas en las que incide

    HashSet<ALVertex<E,V>> vertices = new HashSet<>();
    HashSet<ALEdge<E,V>> edges = new HashSet<>();


    class ALVertex<E,V> implements Vertex<V>{

        private V value;
        private HashSet<ALEdge<E,V>> edges;
        private final Graph graph;

        public ALVertex(V value, Graph graph) {
            value = value;
            edges = new HashSet<>();
            this.graph = graph;
        }

        public V getValue() {
            return value;
        }

        public void setEdges(HashSet<ALEdge<E, V>> edges) {
            this.edges = edges;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public HashSet<ALEdge<E, V>> getEdges() {
            return edges;
        }

        public Graph getGraph() {
            return graph;
        }

        @Override
        public V getElement() {
            return value;
        }
    }
    class ALEdge<E,V> implements Edge<E>{

        private E value;
        private final Graph graph;

        private final Vertex <V> startVertex;
        private final Vertex <V> endVertex;

        public ALEdge(E value, Graph graph, Vertex<V> startVertex, Vertex<V> endVertex) {
            this.value = value;
            this.graph = graph;
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        public E getValue() {
            return value;
        }

        public Vertex<V> getStartVertex() {
            return startVertex;
        }

        public Vertex<V> getEndVertex() {
            return endVertex;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Graph getGraph() {
            return graph;
        }

        @Override
        public E getElement() {
            return value;
        }
    }


    @Override
    public Collection<? extends Vertex<V>> vertices() {return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public Collection<? extends Edge<E>> edges() {
        return Collections.unmodifiableCollection(edges);}

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        ALVertex<E,V> vertice = checkVertex(v);
        return Collections.unmodifiableCollection(vertice.getEdges());}

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        ALEdge<E,V> edge = checkEdge(e);
        return  edge.startVertex.equals(v) ?  edge.endVertex : edge.startVertex;
    }

    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        ALEdge<E,V> e = checkEdge(edge);
        LinkedList<Vertex<V>> vList = new LinkedList<>();
        vList.add(e.getStartVertex());
        vList.add(e.getEndVertex());
        return vList;}


    @Override
    public Edge<E> areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        //Vamos a mirar en el conjunto de adyacencia de v1 que va a ser menor o igual que el total
        ALVertex<E,V> vertex = checkVertex(v1);
        for(ALEdge<E,V> e : vertex.edges){
            if(e.getStartVertex().equals(v1) && e.getEndVertex().equals(v2)){
                return e;
            }else if(e.getStartVertex().equals(v2) && e.getEndVertex().equals(v1)){
                return e;
            }
        }
        return null;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        ALVertex<E,V> v = checkVertex(vertex);
        V e = v.getElement();
        v.setValue(vertexValue);
        return e;
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        ALEdge<E,V> e = checkEdge(edge);
        E ele = e.getElement();
        e.setValue(edgeValue);
        return  ele;
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        ALVertex<E,V> vertex = new ALVertex<>(value, this);
        vertices.add(vertex);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        //Primero hay que ver que ambos vértices existan:
        if(!vertices.contains(v1))
            throw new RuntimeException("V1 doesn't exist");
        if(!vertices.contains(v2))
            throw new RuntimeException("V2 doesn't exist");

        ALEdge<E,V> edge = new ALEdge<>(edgeValue,this,v1,v2);
        if (edges.contains(edge))
            edges.remove(edge);
        edges.add(edge);
        return edge;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        //Primero tenemos que eliminar todas las aristas del vértice
        ALVertex<E,V> v = checkVertex(vertex);
        for(ALEdge<E,V> e: v.edges){
            edges.remove(e);//Borramos todas las aristas
        }
        V value = vertex.getElement();
        vertices.remove(v);
        return value;
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        //Aquí no tenemos que eliminar los vertices
        E value = edge.getElement();
        vertices.remove(edge);
        return value;
    }

    private ALEdge<E,V> checkEdge(Edge<E> edge) {
        if (edge instanceof ALEdge){
            ALEdge<E, V> e = (ALEdge<E,V>) edge;
            if (e.getGraph() == this)
                return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }

    private ALVertex<E,V> checkVertex(Vertex<V> vertex) {
        if (vertex instanceof ALVertex){
            ALVertex<E,V> v = (ALVertex<E,V>)vertex;
            if (v.getGraph() == this)
                return v;
        }
        throw new RuntimeException("The vertex is not in the graph");
    }
    
}
