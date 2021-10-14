
package Tree.BinaryTree;

import java.util.Iterator;

import javafx.geometry.Pos;
import material.Position;
import TreeExceptions.*;

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

        public BTNode() {

        }

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
        try{
            BTNode<E> node = checkPosition(v);
            return node.getLeftChild();
        }catch (InvalidPositionException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Position<E> right(Position<E> v) {
        try{
            BTNode<E> node = checkPosition(v);
            return node.getLeftChild();
        }catch (InvalidPositionException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        return left(v) != null;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        return right(v) != null;
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
        BTNode<E> n = new BTNode<E>();
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException e){
            e.printStackTrace();
        }
        return n.getParent() == NULL;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        //Se entiende que devolvemos el extraido
        BTNode<E> n = new BTNode<E>();
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException error){
            error.printStackTrace();
        }
        E aux = n.getElement();
        n.setElement(e);
        return aux;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> n = new BTNode<E>();
        if(isRoot(p)){
            throw  new UnsupportedOperationException();
        }
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException e){
            e.printStackTrace();
        }
        BTNode<E> parent = n.getParent();
        if(parent.getLeftChild() == n){
            return parent.getRightChild();
        }
        else{
            return parent.getLeftChild();
        }

    }

    @Override
    public Position<E> addRoot(E e) {
        return this.root = new BTNode<E>(e);
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> n = new BTNode<E>();
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException error){
            error.printStackTrace();
        }
        if(hasLeft(p)){
            throw new UnsupportedOperationException();
        }
        n.setLeftChild(new BTNode(e,n));
        return n;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> n = new BTNode<E>();
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException error){
            error.printStackTrace();
        }
        if(hasRight(p)){
            throw new UnsupportedOperationException();
        }
        n.setRightChild(new BTNode(e,n));
        return n;
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> n = new BTNode<E>();
        try {
            n = checkPosition(p);
        }catch (InvalidPositionException error){
            error.printStackTrace();
        }
        //Tengo que hacer una llamada recursiva para ir eliminando los hijos
        return n.getElement();
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
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

    private BTNode<E> checkPosition(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof Position)){
            throw new InvalidPositionException("The position is invalid");
        }
        BTNode<E> n = (BTNode<E>) p;
        return n;
    }
   
}
