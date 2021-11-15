
package material.tree;

import material.Position;
import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class PreOrderTreeIterator<T> implements Iterator<Position<T>> {

    Tree<T> tree;
    LinkedList<Position<T>> lista;
    LinkedList<Position<T>> reverseList;
    public PreOrderTreeIterator(Tree<T> tree) {
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(tree.root());
        reverseList = new LinkedList<>();
    }

    public PreOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(root);
        reverseList = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a pre-order
     */
    @Override
    public Position<T> next() {
        Position<T> aux = lista.pop();
        for(Position<T> p : tree.children(aux)){
            reverseList.addFirst(p);
        }
        while (!reverseList.isEmpty()){
            lista.addFirst(reverseList.pop());
        }
        return aux;
    }

}
