package material.tree;

import material.Position;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class PosOrderTreeIterator<T> implements Iterator<Position<T>> {

    Tree<T> tree;
    LinkedList<Position<T>> lista;
    //LinkedList<Position<T>> reverseList;

    public PosOrderTreeIterator(Tree<T> tree) {
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(tree.root());
    }

    public PosOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(root);
    }

    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a pos-order
     */
    @Override
    public Position<T> next() {

    }

}
