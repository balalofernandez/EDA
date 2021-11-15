
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

    private BTNode<E> root = null;

    private class BTNode<T> implements Position<T>{
        private T element;
        private BTNode leftChild;
        private BTNode rightChild;
        private BTNode parent;

        public BTNode() {
            element = null;
            this.parent = null;
            rightChild = null;
            leftChild = null;
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
            rightChild = null;
            leftChild = null;
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

    public LinkedBinaryTree(){
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
            return node.getRightChild();
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
            return null;
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
        return this.root = new BTNode<>(e);
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        BTNode<E> newNode = new BTNode<>(e);
        if(node!=null&&node.getLeftChild()==null){
            newNode.setParent(node);
            node.setLeftChild(newNode);
        }
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        BTNode<E> newNode = new BTNode<>(e);
        if(node!=null&&node.getRightChild()==null){
            newNode.setParent(node);
            node.setRightChild(newNode);
        }
        return newNode;}

    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = null;
        try {
            node = checkPosition(p);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        if(hasRight(node)||hasLeft(node)){
            throw new RuntimeException("The node has a child");
        }
        E element = node.getElement();
        BTNode<E> parent = node.getParent();
        node.setParent(null);
        if (parent==null){
            if(node.equals(root)) root=null;
        }
        else{
            if(parent.getRightChild().equals(node)){
                parent.setRightChild(null);
            }
            else{
                parent.setLeftChild(null);
            }
        }
        return element;
    }


    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> node1 = null;
        BTNode<E> node2 = null;
        try {
            node1 = checkPosition(p1);
            node2 = checkPosition(p2);

        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        if(node1!=null&&node2!=null){
            E aux =node1.getElement();
            node1.setElement(node2.getElement());
            node2.setElement(aux);
        }
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        try {
            BTNode<E>node = checkPosition(h);
            BTNode<E> left = checkPosition(t1.root());
            node.setLeftChild(left);
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
            node.setRightChild(right);
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
            if(isRoot(v)){
                throw new RuntimeException("Doesnot have parent");
            }
            node = checkPosition(v);
        }
        catch (InvalidPositionException err){
            err.printStackTrace();
        }

        return node.getParent();
    }

    @Override
    public Iterator<Position<E>> iterator() {

        return new InorderBinaryTreeIterator<>(this);
    }

    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = null;
        try {
            node = checkPosition(h);
        } catch (InvalidPositionException e) {
            throw new RuntimeException("Invalid position");
        }
        BTNode<E> parent = node.getParent();
        if(parent==null){
            this.root=null;
        }
        else{
            if(parent.getLeftChild().equals(node)){
                parent.setLeftChild(null);
            }
            else if(parent.getRightChild().equals(node)){
                parent.setRightChild(null);
            }
            node.setParent(null);
        }
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root=node;
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
