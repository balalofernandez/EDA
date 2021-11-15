
package material.tree.narytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.InvalidPositionException;
import material.tree.PreOrderTreeIterator;


/**
 *
 * @author mayte
 * @param <T>
 */
public class LCRSTree<T> implements NAryTree<T> {

    LCRSTreeNode<T> root;

    private class LCRSTreeNode<E> implements Position<E>{

        E element;
        LCRSTreeNode<E> parent;
        LCRSTreeNode<E> firstChild;
        LCRSTreeNode<E> sibling;


        public LCRSTreeNode(){
            element = null;
            parent = null;
            firstChild = null;
            sibling = null;
        }

        public LCRSTreeNode(E element) {
            this.element = element;
            parent = null;
            firstChild = null;
            sibling = null;
        }

        public LCRSTreeNode(E element, LCRSTreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
            this.firstChild = null;
            sibling = null;
        }
        public LCRSTreeNode(E element, LCRSTreeNode<E> parent,LCRSTreeNode<E> sibling) {
            this.element = element;
            this.parent = parent;
            this.firstChild = null;
            sibling = sibling;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public LCRSTreeNode<E> getParent() {
            return parent;
        }

        public void setParent(LCRSTreeNode<E> parent) {
            this.parent = parent;
        }

        public LCRSTreeNode<E> getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(LCRSTreeNode<E> firstChild) {
            this.firstChild = firstChild;
        }

        public LCRSTreeNode<E> getSibling() {
            return sibling;
        }

        public void setSibling(LCRSTreeNode<E> sibling) {
            this.sibling = sibling;
        }

        @Override
        public E getElement() {
            return null;
        }
    }

    public LCRSTree() {
    }

    public LCRSTree(LCRSTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public Position<T> addRoot(T e) {
        try{
            if(!isEmpty())
                throw new IllegalStateException("Tree is not empty");
            root = new LCRSTreeNode<>(e);
            return root;
        }catch (IllegalStateException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public Position<T> add(T element, Position<T> p) {
        LCRSTreeNode<T> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSTreeNode<T> node = new LCRSTreeNode<>(element,parent);
        LCRSTreeNode<T>  ultimoHijo = parent.getFirstChild();
        if(ultimoHijo != null){
            //Como tiene que ser el ultimo nodo:
            while(ultimoHijo.getSibling() != null){
                ultimoHijo = ultimoHijo.getSibling();
            }
            ultimoHijo.setSibling(node);
        }
        else{
            parent.setFirstChild(node);
        }
        return node;
    }

    @Override
    public Position<T> add(T element, Position<T> p, int n) {
        LCRSTreeNode<T> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSTreeNode<T> node = new LCRSTreeNode<>(element,parent);
        LCRSTreeNode<T>  ultimoHijo = parent.getFirstChild();
        if(ultimoHijo != null){
            //Como tiene que ser el ultimo nodo:
            int i = 1;
            while(ultimoHijo.getSibling() != null && i<n){
                ultimoHijo = ultimoHijo.getSibling();
                i++;
            }
            node.setSibling(ultimoHijo.getSibling());
            ultimoHijo.setSibling(node);
        }
        else{
            parent.setFirstChild(node);
        }
        return node;
    }

    @Override
    public void swapElements(Position<T> p1, Position<T> p2) {
        try {
            LCRSTreeNode<T> n1 = checkPosition(p1);
            LCRSTreeNode<T> n2 = checkPosition(p2);
            T e1 = n1.getElement();
            T e2 = n2.getElement();
            n1.setElement(e2);
            n2.setElement(e1);

        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T replace(Position<T> p, T e) {
        LCRSTreeNode<T> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException err) {
            err.printStackTrace();
        }
        T aux = node.getElement();
        node.setElement(e);
        return aux;
    }

    @Override
    public void remove(Position<T> p) {
        //vamos a eliminar los subarboles que cuelguen de p
        if(isRoot(p)){
            root = null;
        }
        else{
            try {
                LCRSTreeNode<T> node = checkPosition(p);
                LCRSTreeNode<T> parent = node.getParent();
                LCRSTreeNode<T> fSibling = node.getSibling();
                //Ahora tengo que buscar a su hermano izquierdo
                LCRSTreeNode<T> hijo = parent.getFirstChild();
                if(hijo.equals(node)){
                    parent.setFirstChild(fSibling);
                }
                else{
                    while (!node.equals(hijo.getSibling())){
                        hijo = hijo.getSibling();
                    }
                    hijo.setSibling(fSibling);
                }
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public NAryTree<T> subTree(Position<T> v) {
        LCRSTree<T> tree = null;
        if(isRoot(v)){
            tree = new LCRSTree<T>(root);
            root = null;
            return tree;
        }
        else{
            LCRSTreeNode<T> node = null;
            try {
                node = checkPosition(v);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            LCRSTreeNode<T> parent = node.getParent();
            LCRSTreeNode<T> fSibling = node.getSibling();
            LCRSTreeNode<T> hijo = parent.getFirstChild();
            if(hijo.equals(node)){
                parent.setFirstChild(fSibling);
            }
            else{
                while (!node.equals(hijo.getSibling())){
                    hijo = hijo.getSibling();
                }
                hijo.setSibling(fSibling);
            }
            node.setParent(null);
            tree = new LCRSTree<>(node);
            return tree;
        }
    }

    @Override
    public void attach(Position<T> p, NAryTree<T> t) {
        LCRSTreeNode<T> parent = null;
        try {
            parent = checkPosition(p);
            LCRSTreeNode<T> node = checkPosition(t.root());
            node.setSibling(parent.getFirstChild());
            parent.setFirstChild(node);
            node.setParent(parent);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<T> root() { return root;}

    @Override
    public Position<T> parent(Position<T> v) {
        LCRSTreeNode<T> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<T>> children(Position<T> v) {
        LCRSTreeNode<T> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSTreeNode<T> child = node.getFirstChild();
        List<Position<T>> list = new LinkedList<>();
        while(child!=null){
            list.add(child);
            child=child.getSibling();
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<T> v) {
        return !(isLeaf(v)||isRoot(v));
    }

    @Override
    public boolean isLeaf(Position<T> v) {
        LCRSTreeNode<T> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getFirstChild()==null;
    }

    @Override
    public boolean isRoot(Position<T> v) {
        LCRSTreeNode<T> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.equals(root);
    }

    @Override
    public Iterator<Position<T>> iterator() {
        return new PreOrderTreeIterator<>(this);
    }

    int size() {
        //Paso de meter todo el rato el size
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private LCRSTreeNode<T> checkPosition(Position<T> p) throws InvalidPositionException{
        if(p == null || !(p instanceof Position)){
            throw new InvalidPositionException("The position is invalid");
        }
        LCRSTreeNode<T> n = (LCRSTreeNode<T>) p;
        return n;
    }
    
}
