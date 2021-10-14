
package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;

import TreeExceptions.InvalidPositionException;
import material.Position;

/**
 *
 * @author mayte
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {
    private ArrayList<Node<E>> tree;

    private  class Node<T> implements Position<T>{
        private  T e;
        private boolean valid;
        private int n;

        @Override
        public T getElement() {
            return e;
        }

        public boolean isValid() {
            return valid;
        }

        public int getN() {
            return n;
        }

        public void setElement(E e) {
            this.e = e;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public void setN(int n) {
            this.n = n;
        }
    }

    @Override
    public Position<E> left(Position<E> v) {
        Node<E> n = new Node<>();
        try {
            n = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return tree.get(n.getN()*2 +1);
    }

    @Override
    public Position<E> right(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasRight(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> addRoot(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attachLeft(Position<String> h, BinaryTree<String> t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attachRight(Position<String> h, BinaryTree<String> t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BinaryTree<String> subTree(Position<String> h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Node<E> checkPosition(Position<E> p)throws InvalidPositionException {
        throw new UnsupportedOperationException();
    }
}
