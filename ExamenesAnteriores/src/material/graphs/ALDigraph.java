package material.graphs;

import java.util.*;

public class ALDigraph<V,E> implements Digraph<V, E>{

    private final Set<ALVertex<E,V>> vertexList = new HashSet<>();
    private final Set<ALEdge<E,V>> edgeList = new HashSet<>();

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
        ALVertex<E,V> vertice = checkVertex(v);
        return vertice.edgeSet;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        ALEdge<E,V> arista = checkEdge(e);
        if(arista.getStartVertex().equals(v)) return arista.getEndVertex();
        if(arista.getEndVertex().equals(v)) return arista.getStartVertex();
        return null;
    }

    @Override
    public Vertex<V> endVertice(Edge<E> edge) {
        ALEdge<E,V> arista = checkEdge(edge);
        return arista.getEndVertex();
    }

    @Override
    public Vertex<V> startVertice(Edge<E> edge) {
        ALEdge<E,V> arista = checkEdge(edge);
        return arista.getStartVertex();
    }

    @Override
    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        ALVertex<E,V> vertice = checkVertex(v1);
        for(ALEdge<E,V> arista : vertice.edgeSet){
            if(arista.getEndVertex().equals(v2)) return true;
        }
        return false;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        ALVertex<E,V> vertice = checkVertex(vertex);
        vertice.setElement(vertexValue);
        return vertice.getElement();
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        ALEdge<E,V> arista = checkEdge(edge);
        arista.setElement(edgeValue);
        return edgeValue;
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        ALVertex<E,V> vertice = new ALVertex<E,V>(value,this);
        this.vertexList.add(vertice);
        return vertice;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        ALEdge<E,V> arista = new ALEdge<>(edgeValue,v1,v2,this);
        ALVertex<E,V> vertice = checkVertex(v1);
        vertice.addEdge(arista);
        this.edgeList.add(arista);
        return arista;
    }

    @Override
    public List<Edge<E>> outputEdges(Vertex<V> v) {
        List<Edge<E>> lista = new LinkedList<>();
        for(ALEdge<E,V> x : this.edgeList){
            if(x.getEndVertex().equals(v)) lista.add(x);
        }
        return lista;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        ALVertex<E,V> vertice = checkVertex(vertex);
        this.vertexList.remove(vertice);
        for(ALEdge<E,V> arista : vertice.edgeSet){
            removeEdge(arista);
        }
        return vertice.vertexValue;
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        ALEdge<E,V> arista = checkEdge(edge);
        this.edgeList.remove(arista);
        ALVertex<E,V> vertice = checkVertex(arista.getStartVertex());
        vertice.edgeSet.remove(arista);
        return arista.edgeValue;
    }





    class ALVertex <E,V> implements Vertex <V> {
        private V vertexValue;
        private final Digraph graph;
        private Set<ALEdge<E,V>> edgeSet;

        @Override
        public V getElement() {
            return vertexValue;
        }

        public void setElement(V value) {
            vertexValue = value;
        }

        public ALVertex(V vertexValue, Digraph graph, Set<ALEdge<E,V>> edgeList) {
            this.vertexValue = vertexValue;
            this.graph = graph;
            this.edgeSet = edgeList;
        }

        public ALVertex(V value, Digraph graph) {
            edgeSet=new HashSet<>();
            vertexValue = value;
            this.graph = graph;
        }

        public void addEdge(ALEdge<E,V> edge){
            this.edgeSet.add(edge);
        }

        /**
         * @return the graph
         */
        public Digraph getGraph() {
            return graph;
        }
    }


    class ALEdge <E,V> implements Edge <E> {
        private E edgeValue;
        private final Digraph graph;

        private final Vertex <V> startVertex;
        private final Vertex <V> endVertex;

        @Override
        public int hashCode() {
            int hash = 3;

            final int min = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
            final int max = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));

            hash = 67 * hash + Objects.hashCode(this.getGraph());
            hash = 67 * hash + min;
            hash = 67 * hash + max;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ALEdge<E, V> other = (ALEdge<E, V>)  obj;
            if (!Objects.equals(this.graph, other.graph)) {
                return false;
            }

            final int min1 = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
            final int max1 = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));

            final int min2 = Math.min(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));
            final int max2 = Math.max(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));

            if (!Objects.equals(min1, min2)) {
                return false;
            }
            if (!Objects.equals(max1, max2)) {
                return false;
            }
            return true;
        }

        @Override
        public E getElement() {
            return edgeValue;
        }

        public ALEdge(E value,Vertex<V> startVertex, Vertex<V> endVertex, Digraph graph) {
            edgeValue = value;
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.graph = graph;
        }

        public void setElement(E value) {
            edgeValue = value;
        }

        /**
         * @return the startVertex
         */
        public Vertex <V> getStartVertex() {
            return startVertex;
        }

        /**
         * @return the endVertex
         */
        public Vertex <V> getEndVertex() {
            return endVertex;
        }

        /**
         * @return the graph
         */
        public Digraph getGraph() {
            return graph;
        }
    }

    private ALEdge<E, V> checkEdge(Edge<E> edge) {
        if (edge instanceof ELDigraph.ELEdge) {
            ALEdge<E, V> e = (ALEdge<E, V>) edge;
            if (e.getGraph() == this) {
                return e;
            }
        }
        throw new RuntimeException("The edge is not in the graph");
    }

    private ALVertex<E,V> checkVertex(Vertex<V> vertex) {
        if (vertex instanceof ELDigraph.ELVertex) {
            ALVertex<E,V> v = (ALVertex<E,V>) vertex;
            if (v.getGraph() == this) {
                return v;
            }
        }
        throw new RuntimeException("The vertex is not in the graph");
    }

}
