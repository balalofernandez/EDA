package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;

import material.Position;
import material.tree.Tree;
import sun.awt.image.ImageWatched;

/**
 *
 * @author mayte
 * @param <T>
 */
public class RightNodeIterator<T> implements Iterator<Position<T>> {

    Position<T> nodo;
    BinaryTree<T> tree;
    public RightNodeIterator (BinaryTree<T> tree){
        this.tree = tree;
        nodo = tree.root();
    }
    
    
    @Override
    public boolean hasNext() {
        return tree.hasRight(nodo);
    }

    @Override
    public Position<T> next() {
        Position<T> aux = nodo;
        if(tree.hasRight(nodo))
            nodo = tree.right(nodo);
        return aux;
    }
    
}
