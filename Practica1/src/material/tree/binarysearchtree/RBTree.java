package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.deploy.security.SelectableSecurityManager;
import javafx.geometry.Pos;
import material.Position;

/**
 * Realization of a red-black Tree by extending a binary search tree.
 * @author A. Duarte, J. Vélez
 * @param <E>
 */
public class RBTree<E> implements BinarySearchTree<E> {


    //Esta clase es necesaria para guardar el valor de la altura AVL en los nodos BTNodes
    private class RBInfo<T> implements Comparable<RBInfo<T>>, Position<T> {

        private boolean isRed; // we add a color field to a BTNode
        private final T element;
        private Position<RBInfo<T>> pos;

        RBInfo(T element) {
            this.element = element;
        }

        public void setTreePosition(Position<RBInfo<T>> pos) {
            this.pos = pos;
        }

        public Position<RBInfo<T>> getTreePosition() {
            return this.pos;
        }

        public boolean isRed() {
            return isRed;
        }

        public void setRed(boolean color) {
            isRed = color;
        }

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public boolean equals(Object obj) {
            RBInfo<T> info = (RBInfo<T>) obj;
            return element.equals(info.getElement());
        }

        @Override
        public int compareTo(RBInfo<T> o) {
            if (element instanceof Comparable && o.element instanceof Comparable) {
                Comparable<T> c1 = (Comparable<T>) element;
                return c1.compareTo(o.element);

            } else {
                throw new ClassCastException("Element is not comparable");
            }
        }
    }

    private class RBTreeIterator<T> implements Iterator<Position<T>> {

        private final Iterator<Position<RBInfo<T>>> it;

        public RBTreeIterator(Iterator<Position<RBInfo<T>>> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Position<T> next() {
            Position<RBInfo<T>> aux = it.next();
            return aux.getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    private final LinkedBinarySearchTree<RBInfo<E>> resBT = new LinkedBinarySearchTree<>();
    private final Reestructurator reestructurator = new Reestructurator();

    @Override
    public Position<E> find(E value) {
        RBInfo<E> searchedValue = new RBInfo<>(value);
        Position<RBInfo<E>> output = resBT.find(searchedValue);

        if (output == null) {
            return null;
        }
        return output.getElement();
    }

    @Override
    public Iterable<Position<E>> findAll(E value) {
        RBInfo<E> searchedValue = new RBInfo<>(value);
        List<Position<E>> aux = new ArrayList<>();
        for (Position<RBInfo<E>> n : resBT.findAll(searchedValue)) {
            aux.add(n.getElement());
        }
        return aux;
    }

    @Override
    public boolean isEmpty() {
        return resBT.isEmpty();
    }

    @Override
    public int size() {
        return resBT.size();
    }

    /**
     * Inserts an element into the RBTree and returns the newly created postion.
     * @param e
     */
    @Override
    public Position<E> insert(E e) {
        RBInfo<E> n = new RBInfo<>();
        n.setRed(true);
        Position<RBInfo<E>> p = resBT.insert(n);
        n.setTreePosition(p);
        if(resBT.binTree.isroot(p)){
            n.setRed(false);
        }
        else{
            remedyDoubleRed(p);
        }
        return p;
    }

    /**
     * Remedies a double red violation at a given node caused by insertion.
     * @param nodeZ
     */
    protected void remedyDoubleRed(RBInfo<E> nodeZ) {
        Position<RBInfo<E>> pparent = resBT.binTree.parent(nodeZ.getTreePosition());
        RBInfo<E> parent = pparent.getElement();
        if(!parent.isRed()){
            return;
        }

        Position<RBInfo<E>> pgparent = resBT.binTree.parent(pparent);
        boolean hasSibling = resBT.binTree.hasLeft(pgparent) && resBT.binTree.hasRight(pgparent);
        boolean blackUncle = true;
        if(hasSibling){
            Position<RBInfo<E>> puncle = resBT.binTree.sibling(pparent);
            blackUncle = !puncle.getElement().isRed();
        }

        //Caso 1: Tío negro
        if(blackUncle){
            pparent = reestructurator.restructure(nodeZ.getTreePosition(), resBT.binTree);//este el nodo del medio
            pparent.getElement().setRed(false);
            resBT.binTree.left(pparent).getElement().setRed(true);
            resBT.binTree.right(pparent).getElement().setRed(true);
        }
        //Caso 2: Tío Rojo
        else{
            if(!resBT.binTree.isRoot(pgparent)){
                pgparent.getElement().setRed(true);
            }
            if(hasSibling){
                resBT.binTree.sibling(pparent).getElement().setRed(false);
            }
            pparent.getElement().setRed(false);
            remedyDoubleRed(pgparent.getElement());
        }
    }

    @Override
    public void remove(Position<E> pos) throws IllegalStateException {
        boolean isRoot = resBT.binTree.isRoot(pos);
        pos = resBT.remove(pos);
        if(isRoot)return;
        if(pos.getElement().isRed) return;
        Position<RBInfo<E>> r = nulL;
        if(resBT.binTree.hasLeft(pos)){
            r = resBT.binTree.left(pos);
        }
        else if(resBT.binTree.hasRight(pos)){
            r = resBT.binTree.right(pos);
        }
        if(r != null && r.getElement().isRed()){
            r.getElement().setRed(false);
        }
        Position<RBInfo<E>> p = resBT.binTree.parent(pos);
        remedyDoubleBlack(p,r);
    }

    /**
     * Remedies a double black violation at a given node caused by removal.
     */
    protected void remedyDoubleBlack(Position<RBInfo<E>> doubleBlackParent, Position<RBInfo<E>> doubleBlack) {
        Position<RBInfo<E>> s = null;
        if(resBT.binTree.left(doubleBlackParent) == doubleBlack){
            s = resBT.binTree.right(doubleBlackParent);
        }
        else{
            s = resBT.binTree.left(doubleBlackParent);
        }
        //Caso 1 y 2
        if(s != null || !s.getElement().isRed){
            boolean redn = false;
            if(s!= null){
                if(resBT.binTree.hasRight(s))
                    redn = resBT.binTree.right(s).getElement().isRed;
                else if(resBT.binTree.hasLeft(s))
                    redn = resBT.binTree.right(s).getElement().isRed;
            }
            //Caso 1:Reestructuracion tri-nodo
            if (redn){
                boolean rootRed = doubleBlackParent.getElement().isRed();
                Position<RBInfo<E>> snred = null;
                if(redn){
                    snred = resBT.binTree.left(s);//Las comprobaciones de arriba las debería hacer aqui
                }
                //llamar al reestructurator...
            }
            else {//caso 2

            }
        }
        //Caso 3
        else{

        }


    }

    /**
     * Returns a red child of a node.
     * @param pos
     * @return
     */
    protected Position<RBInfo<E>> hasRedChild(Position<RBInfo<E>> pos) {
        Position<RBInfo<E>> child;
        if (resBT.binTree.hasLeft(pos)) {
            child = resBT.binTree.left(pos);
            final boolean redLeftChild = child.getElement() != null && child.getElement().isRed();
            if (redLeftChild) {
                return child;
            }
        }

        if (resBT.binTree.hasRight(pos)) {
            child = resBT.binTree.right(pos);
            final boolean redRightChild = child.getElement() != null && child.getElement().isRed();
            if (redRightChild) {
                return child;
            }
        }
        return null;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        Iterator<Position<RBInfo<E>>> bstIt = resBT.iterator();
        RBTreeIterator<E> it = new RBTreeIterator<>(bstIt);
        return it;
    }

    /**
     * Returns the position with the smallest value in the tree.
     * @return
     */
    public Position<E> first() {

        //Se guarda en el position
        Position<E> nodo = resBT.first().getElement();
        //Se devuelve
        return nodo;
    }

    /**
     *
     * @return
     */
    public Position<E> last() {
        Position<E> nodo = resBT.last().getElement();
        //Se devuelve
        return nodo;
    }

    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}