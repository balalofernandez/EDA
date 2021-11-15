
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;

import javafx.geometry.Pos;
import material.Position;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class BreadthFirstTreeIterator<T> implements Iterator<Position<T>> {

    LinkedList<Position<T>> lista;
    Tree<T> tree;

    public BreadthFirstTreeIterator(Tree<T> tree, Position<T> root){
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(root);
    }

    public BreadthFirstTreeIterator(Tree<T> tree){
        this.tree = tree;
        lista = new LinkedList<>();
        if(tree.root() != null){
            lista.add(tree.root());
        }
    }

    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breath-first order
     */
    @Override
    public Position<T> next() {
        Position<T> aux = lista.pop();
        for(Position<T> p : tree.children(aux)){
            lista.add(p);
        }
        return aux;
    }

   
}
