package ExamenesAnteriores.ExamenJulio1920;

import material.graphs.ALGraph;
import material.graphs.Edge;
import material.graphs.Graph;
import material.graphs.Vertex;

import java.util.*;

public class SocialNetwork {

    class Usuario{
        String nombre;
        public Usuario() {
        }

        public Usuario(String nombre) {
            this.nombre = nombre;
        }
    }

    Graph<Usuario,Boolean> network = new ALGraph<>();

    public SocialNetwork(Graph<Usuario, Boolean> red) {
        this.network = network;
    }

    public void addUser(Usuario u){
        network.insertVertex(u);
    }
    public void MakeFriends(Vertex<Usuario> u1, Vertex<Usuario> u2){
        network.insertEdge(u1,u2,true);
    }

    public List<Usuario> filter(int k){
        LinkedList<Usuario> eliminados = new LinkedList<>();
        boolean done = false;
        boolean todas = true;
        while(!done && todas){
            for(Vertex<Usuario> usu : network.vertices()){
                int k2 = 0;
                for(Edge<Boolean> conex : network.incidentEdges(usu)){
                    k2++;
                }
                if(k2<k){
                    eliminados.add(network.removeVertex(usu));
                    todas = false;
                }
            }
            done = todas;
            todas = true;
        }
        return  eliminados;
    }

    public int groups(){
        Set<Set<Vertex<Usuario>>> conjuntos = createConjuntos();

        Collection<? extends Edge<Boolean>> edges = network.edges();
        for(Edge<Boolean> edge : network.edges()){
            //Ahora miramos si los conjuntos son disjuntos
            Vertex<Usuario> v1 = network.endVertices(edge).get(0);
            Vertex<Usuario> v2 = network.endVertices(edge).get(1);
            Set<Vertex<Usuario>> conjunto1 = null;
            Set<Vertex<Usuario>> conjunto2 = null;
            for(Set<Vertex<Usuario>> conjunto : conjuntos){
                if(conjunto.contains(v1)){
                    conjunto1 = conjunto;
                }else if(conjunto.contains(v2)){
                    conjunto2 = conjunto;
                }
            }
            if (conjunto1 != null && conjunto2 != null && !conjunto1.equals(conjunto2)){
                Set<Vertex<Usuario>> conjunto3 = new HashSet<>();
                conjunto3.addAll(conjunto1);
                conjunto3.addAll(conjunto2);
                conjuntos.remove(conjunto1);
                conjuntos.remove(conjunto2);
                conjuntos.add(conjunto3);
            }
        }
        return  conjuntos.size();

    }

    protected Set<Set<Vertex<Usuario>>> createConjuntos(){
        Set<Set<Vertex<Usuario>>> sol = new HashSet<>();
        for(Vertex<Usuario> v : network.vertices()){
            Set<Vertex<Usuario>> u = new HashSet<>();
            u.add(v);
            sol.add(u);
        }
        return sol;
    }

}
