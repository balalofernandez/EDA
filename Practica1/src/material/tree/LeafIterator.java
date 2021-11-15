
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;

import javafx.geometry.Pos;
import material.Position;

/**
 *
 * @author mayte
 * @param <T>
 */
public class LeafIterator<T> implements Iterator<Position<T>>  {
    
    LinkedList<Position<T>> lista;
    Tree<T> tree;
    LinkedList<Position<T>> reverseList;

    public LeafIterator(Tree<T> tree, Position<T> root){
        this.tree = tree;
        lista = new LinkedList<>();
        lista.add(root);
        reverseList = new LinkedList<>();
    }
    
    public LeafIterator(Tree<T> tree){
        this.tree = tree;
        lista = new LinkedList<>();
        if(tree.root() != null){
            lista.add(tree.root());
        }
        reverseList = new LinkedList<>();
    }
    
    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method only visits the leaf nodes 
     */
    @Override
    public Position<T> next() {
        Position<T> aux = lista.pop();
        while(!tree.isLeaf(aux)){
            for(Position<T> k : tree.children(aux)){
                reverseList.addFirst(k);
            }
            while(!reverseList.isEmpty()){
                lista.addFirst(reverseList.pop());
            }
            aux = lista.pop();
        }
        return aux;
    }

    
    
}
