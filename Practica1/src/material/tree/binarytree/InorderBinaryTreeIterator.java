
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;

import material.Position;
import sun.awt.image.ImageWatched;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class InorderBinaryTreeIterator<T> implements Iterator<Position<T>> {

    BinaryTree<T> tree;
    LinkedList<Position<T>> lista;
    LinkedList<Position<T>> listaAux;


    public InorderBinaryTreeIterator(BinaryTree <T> tree) {
        this.lista = new LinkedList<Position<T>> ();
        this.listaAux = new LinkedList<Position<T>> ();
        this.tree = tree;
        Position<T> node = tree.root();
        lista.addFirst(node);
        while (tree.hasLeft(node)){
            lista.addFirst(tree.left(node));
            node = tree.left(node);
        }
    }

    public InorderBinaryTreeIterator(BinaryTree <T> tree, Position<T> node) {
        this.lista = new LinkedList<Position<T>> ();
        this.listaAux = new LinkedList<Position<T>> ();
        this.tree = tree;
        Position<T> Nnode = node;
        lista.addFirst(Nnode);
        while (tree.hasLeft(Nnode)){
            lista.addFirst(tree.left(Nnode));
            Nnode = tree.left(Nnode);
        }
    }

         
    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method visits the nodes of a binary tree first left-child, then the node and at last right-child
     */
    @Override
    public Position<T> next() {
        Position<T> aux = this.lista.pop();
        if(tree.hasRight(aux)){
            Position<T> aux2 = tree.right(aux);
            lista.addFirst(aux2);
            while (tree.hasLeft(aux2)){
                aux2 = tree.left(aux2);
                lista.addFirst(aux2);
            }
        }
        return aux;
    }

    
}
