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

    /*@Override
    public Position<E> next() {
        Position<E> first = list.remove(0);
        for(Position<E> p : tree.children(first)){
            list.add(p);
        }
        return first;
    }*/
    public Position<E> next(){
        Position<E> p = list.remove(0);
        List<Position<E>> reverse_list = new LinkedList<>();
        for (Position<E> q: tree.children(p)){
            reverse_list.add(q);
        }
        for(Position<E> q: reverse_list){
            list.add(q);
        }
        return p;
    }
}
