package material.tree;

import javafx.geometry.Pos;
import material.Position;

import java.util.Iterator;
import java.util.LinkedList;

public class InorderNAryTreeIteratorTimes2<E> implements Iterator<Position<E>> {

    Tree<E> tree;
    LinkedList<Position<E>> lista;

    InorderNAryTreeIteratorTimes2(Tree<E>tree){
        this(tree, tree.root());
    }
    InorderNAryTreeIteratorTimes2(Tree<E> tree, Position<E>node){
        this.tree = tree;
        this.lista.add(node);

    }

    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    @Override
    public Position<E> next() {
        return null;}
}
