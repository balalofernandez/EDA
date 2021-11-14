import material.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PreOrderIterator<E> implements Iterator<Position<E>> {

    List<Position<E>> list = new LinkedList<>();
    Tree<E> tree;
    public PreOrderIterator(Tree<E> tree){
        this.tree = tree;
        list.add(tree.root());
    }
    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> first = list.remove(0);
        for(Position<E> p : tree.children(first)){
            list.add(p);
        }
        return first;
    }
}
