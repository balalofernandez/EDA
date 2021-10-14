
package Tree.BinaryTree;

import java.util.Iterator;

import javafx.geometry.Pos;
import material.Position;

/**
 *
 * @author mayte
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{

    private BTNode<E> root;

    private class BTNode<T> implements Position<T>{
        private T element;
        private BTNode leftChild;
        private BTNode rightChild;
        private BTNode parent;

        BTNode(T e){
            this.element = e;
            leftChild = null;
            rightChild = null;
            parent = null;
        }
        BTNode(T e, BTNode<T> parent){
            element = e;
            this.parent = parent;
        }

        public T getElement() {
            return element;
        }

        public BTNode getLeftChild() {
            return leftChild;
        }

        public BTNode getRightChild() {
            return rightChild;
        }

        public BTNode getParent() {
            return parent;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setLeftChild(BTNode leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(BTNode rightChild) {
            this.rightChild = rightChild;
        }

        public void setParent(BTNode parent) {
            this.parent = parent;
        }
    }

    LinkedBinaryTree(){
        root = null;
    }

    @Override
    public Position<E> left(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return !(hasLeft(p) || hasRight(p));
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTNode<E> n = checkPosition(p);
        return n.getParent() == NULL;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> addRoot(E e) {
        this.root = new BTNode<E>(e)
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
    public void attachLeft(Position<String> h, BinaryTree<String> t1) {

    }

    @Override
    public void attachRight(Position<String> h, BinaryTree<String> t1) {

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
    public LinkedBinaryTree<String> subTree(Position<String> h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Position<E> checkPosition(Position<E> p){
        if (p != null || !(p instanceof Position)){
            return p;
        }
    }
   
}
