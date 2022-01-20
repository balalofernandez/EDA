package practica1;

import javafx.geometry.Pos;
import material.Position;
import material.tree.Tree;

import java.util.*;

public class EjerciciosRepaso<E> {
    public EjerciciosRepaso() {
    }

    public Collection<Position<E>> padresHoja(Tree<E> tree){
        HashSet<Position<E>> padres = new HashSet<>();
        if(tree.isEmpty())
            return padres;
        for(Position<E> p : tree){
            if(tree.isLeaf(p)){
                padres.add(tree.parent(p));
            }
        }
        return padres;
    }

    public Position<E> nodoMasCercano(Tree<E> tree){
        //En caso de que el árbol tenga un único nodo se devuelve la raiz
        //Vamos a hacer un recorrido en anchura (Se podría simplemente usar BreadthFirstIterator)
        Queue<Position<E>> cola = new LinkedList<>();
        cola.add(tree.root());
        Position<E> nodo = tree.root();
        while (!cola.isEmpty()){
            nodo = cola.remove();
            for(Position<E> hijo: tree.children(nodo)){
                if(tree.isLeaf(hijo)) return hijo;
                cola.add(hijo);
            }
        }
        return nodo;
    }
}
