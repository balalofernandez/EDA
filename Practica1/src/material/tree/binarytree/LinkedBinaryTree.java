
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.InvalidPositionException;
import material.tree.PreOrderTreeIterator;

/**
 *
 * @author BALALO
 * @param <E>
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

    public LinkedBinaryTree(BTNode<E> root) {
        this.root = root;
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
        return n.getParent() == null;
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
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        try {
            BTNode<E>node = checkPosition(h);
            BTNode<E> left = checkPosition(t1.root());
            node.setLeftChild(node);
            left.setParent(node);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        try {
            BTNode<E>node = checkPosition(h);
            BTNode<E> right = checkPosition(t1.root());
            node.setRightChild(node);
            right.setParent(node);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }

    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> parent(Position<E> v) {

        BTNode<E> node = null;
        try{
            node = checkPosition(v);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }
        return node.getParent();
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new PreOrderTreeIterator<>(this);
    }

    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = null;
        try{
            node = checkPosition(h);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }
        remove(node);
        node.setParent(null);
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>(node);
        return tree;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTNode<E> node = null;
        try{
            node = checkPosition(v);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }
        List<Position<E>> list = new LinkedList<>();
        if(hasLeft(v)){
            list.add(node.getLeftChild());
        }
        if(hasRight(v)){
            list.add(node.getRightChild());
        }
        return list;
    }

    private BTNode<E> checkPosition(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof Position)){
            throw new InvalidPositionException("The position is invalid");
        }
        BTNode<E> n = (BTNode<E>) p;
        return n;
    }

}
