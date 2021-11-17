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
    LinkedList<Position<T>> reverseList;

    public PosOrderTreeIterator(Tree<T> tree) {
        this.tree = tree;
        lista = new LinkedList<>();
        Position<T> node = tree.root();
        reverseList = new LinkedList<>();
        lista.addFirst(node);
        while (tree.children(node).iterator().hasNext()){
            node = tree.children(node).iterator().next();
            lista.add(node);
        }
    }

    public PosOrderTreeIterator(Tree<T> tree, Position<T> root) {
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
     * This method visits the nodes of a tree by following a pos-order
     */
    @Override
    public Position<T> next() {
        Position<T> aux = lista.getFirst();
        while(tree.children(aux).iterator().hasNext()){
            //Si añadiese en un conjunto los que ya he recorrido, está
            for(Position<T> p : tree.children(aux)){
                reverseList.addFirst(p);
            }
            while (!reverseList.isEmpty()){
                lista.addFirst(reverseList.pop());
            }
            aux = lista.getFirst();
        }
        return lista.pop();
    }

}
