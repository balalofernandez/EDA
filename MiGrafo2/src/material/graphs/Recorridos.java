package material.graphs;

import java.util.*;

import javafx.geometry.Pos;
import material.Position;
import material.tree.Tree;
import material.tree.narytree.LinkedTree;
import material.utils.Pair;

/**
 *
 * @author jvelez
 * @param <V>
 * @param <E>
 */
public class Recorridos<V, E> {

    enum Label {
        DISCOVERY, CROSS
    };

    /**
     * Devuelve el árbol que se genera al realizar el recorrido en profundidad
     * 
     * @param graph
     * @param source
     * @return 
     */

    public Tree<Vertex<V>> depthTravel(Graph<V,E> graph, Vertex<V> source){
        //Recorrido en profundidad, creamos una pila con el vértice y la posición del padre
        Stack<Pair<Vertex<V>, Position<Vertex<V>>>> pila = new Stack<>();
        HashSet<Vertex<V>> visitados = new HashSet<>();
        LinkedTree<Vertex<V>> arbol = new LinkedTree<>();
        //Comenzamos metiendo la raíz
        Pair<Vertex<V>,Position<Vertex<V>>> raiz = new Pair<>(source,null);
        pila.push(raiz);

        while (!pila.empty()){
            Pair<Vertex<V>,Position<Vertex<V>>> visitado = pila.pop();
            Vertex<V> vertice = visitado.getFirst();
            Position<Vertex<V>> parent = visitado.getSecond();
            if(!visitados.contains(vertice)){
                //Marcamos como visitado y añadimos al arbol
                visitados.add(vertice);
                Position<Vertex<V>> newPos;
                if(parent==null){
                    newPos = arbol.addRoot(vertice);
                }
                else{
                    newPos = arbol.add(vertice, parent);
                }
                for(Edge<E> incidente: graph.incidentEdges(vertice)){
                    Vertex<V> opuesto = graph.opposite(vertice,incidente);
                    pila.push(new Pair<Vertex<V>,Position<Vertex<V>>>(opuesto,newPos));
                }
            }
        }

        return arbol;
    }
    
    /**
     * Devuelve el árbol que se genera al realizar el recorrido en anchura
     * 
     * @param graph
     * @param source
     * @return 
     */
    
    public Tree<Vertex<V>> widthTravel(Graph<V,E> graph, Vertex<V> source){
        //Recorrido en anchura, creamos una pila con el vértice y la posición del padre
        Queue<Pair<Vertex<V>, Position<Vertex<V>>>> cola = new LinkedList<>();
        HashSet<Vertex<V>> visitados = new HashSet<>();
        LinkedTree<Vertex<V>> arbol = new LinkedTree<>();

        while(!cola.isEmpty()){
            //Recuperamos el elemento
            Pair<Vertex<V>,Position<Vertex<V>>> visitado = cola.remove();
            Vertex<V> vertice = visitado.getFirst();
            Position<Vertex<V>> parent = visitado.getSecond();
            if(!visitados.contains(vertice)){
                //Marcamos como visitado y añadimos al arbol
                visitados.add(vertice);
                Position<Vertex<V>> newPos;
                if(parent==null){
                    newPos = arbol.addRoot(vertice);
                }
                else{
                    newPos = arbol.add(vertice, parent);
                }
                for(Edge<E> incidente: graph.incidentEdges(vertice)){
                    Vertex<V> opuesto = graph.opposite(vertice,incidente);
                    cola.add(new Pair<Vertex<V>,Position<Vertex<V>>>(opuesto,newPos));
                }
            }
        }
        return arbol;
    }
    
    /**
     * Get the path between two vertex with minimun number of nodes.
     *
     * @param graph
     * @param startVertex
     * @param endVertex
     * @return The edge list
     */
    public List<Edge<E>> getPath(Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex) {
        //Vamos a hacer una implementación parecida a la de la práctica 4
        //Hemos creado el enum label para diferenciar el caso discovery y cross
        LinkedTree<Vertex<V>> tree = new LinkedTree<>();
        HashMap<Edge<E>, Label> edgeLabels = new HashMap<>();

        Queue<Position<Vertex<V>>> queue = new LinkedList<>();
        HashSet<Vertex<V>> visitedNodes = new HashSet<>();

        visitedNodes.add(startVertex);
        tree.addRoot(startVertex);
        queue.add(tree.root());

        while (!queue.isEmpty()) {
            Position<Vertex<V>> vetexPos = queue.poll();
            Vertex<V> vertex = vetexPos.getElement();
            for (Edge<E> edge : graph.incidentEdges(vertex)) {
                if (edgeLabels.get(edge) == null) {
                    Vertex<V> nextNode = graph.opposite(vertex, edge);
                    if (!visitedNodes.contains(nextNode)) {
                        edgeLabels.put(edge, Label.DISCOVERY);
                        visitedNodes.add(nextNode);
                        Position<Vertex<V>> treeNode = tree.add(nextNode, vetexPos);
                        queue.add(treeNode);
                        if (nextNode == endVertex) {
                            return pathToRoot(graph, treeNode, tree);
                        }
                    } else {
                        edgeLabels.put(edge, Label.CROSS);
                    }
                }
            }
        }
        return null;
    }

    private List<Edge<E>> pathToRoot(Graph<V,E> g, Position<Vertex<V>> node, LinkedTree<Vertex<V>> tree){
        List<Edge<E>> result = new LinkedList<>();

        while (node != tree.root()){
            Position<Vertex<V>> parent = tree.parent(node);
            Edge<E> edge = g.areAdjacent(node.getElement(),parent.getElement());
            result.add(edge);
            node = parent;
        }
        return result;
    }
    /**
     * Devuelve el conjunto de vértices visitados al hacer un recorrido en profundidad partiendo de root
     * @param g
     * @param root
     * @return 
     */
    public Set<Vertex<V>> traverse(Graph<V, E> g, Vertex<V> root) {
        Set<Vertex<V>> visited = new HashSet<>();
        Queue<Vertex<V>> q = new ArrayDeque<>();
        q.add(root);
        visited.add(root);
        while (!q.isEmpty()) {
            Vertex<V> v = q.peek();
            visited.add(v);
            for (Edge<E> e : g.incidentEdges(v)) {
                Vertex<V> op = g.opposite(v, e);
                if (!visited.contains(op)) {
                    q.add(op);
                    visited.add(op);
                }
            }
            q.poll();
        }
        return visited;
    }
    
    
}
