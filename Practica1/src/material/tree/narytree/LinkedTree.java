
package material.tree.narytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.InvalidIndexException;
import material.tree.InvalidPositionException;
import material.tree.PreOrderTreeIterator;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedTree<E> implements NAryTree<E> {

    private TreeNode<E> root;
    private class TreeNode<E> implements Position<E>{
        private E element;
        private TreeNode<E> parent;
        private List<TreeNode<E>> childs;

        public TreeNode(){
            element = null;
            parent = null;
            childs = new LinkedList<TreeNode<E>>();
        }
        public TreeNode(E e){
            element = e;
            parent = null;
            childs = new LinkedList<TreeNode<E>>();
        }
        public TreeNode(E e, TreeNode<E> parent){
            element = e;
            this.parent = parent;
            childs = new LinkedList<TreeNode<E>>();
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(TreeNode<E> parent) {
            this.parent = parent;
        }

        public void setChilds(List<TreeNode<E>> childs) {
            this.childs = childs;
        }

        @Override
        public E getElement() {
            return this.element;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public List<TreeNode<E>> getChilds() {
            return childs;
        }

        public Position<E> addChild(E e){
            TreeNode<E> element = new TreeNode<E>(e,this);
            this.childs.add(element);
            return (Position<E>) element;
        }
        public Position<E> addChild(E e, int n) throws InvalidIndexException {
            TreeNode<E> element = new TreeNode<E>(e,this);
            if(this.childs.size()<4)
                throw new InvalidIndexException("Not enough childs");
            this.childs.add(n, element);
            return (Position<E>) element;
        }

        public void swapElements(TreeNode<E> node){
            E aux = element;
            this.element = node.getElement();
            node.setElement(aux);
        }

    }

    public LinkedTree(){
        root=null;
    }

    @Override
    public Position<E> addRoot(E e) {//suponemos que el método simplemente añade una raíz al árbol solo si este es vacío
        try{
            if(!isEmpty())
                throw new IllegalStateException("Tree is not empty");

            root = new TreeNode<E>(e);
            return root;
        }
        catch(IllegalStateException error){
            error.printStackTrace();
            return null;
        }
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> node = new TreeNode<>(element, parent);
        parent.getChilds().add(node);
        return node;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        TreeNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> node = new TreeNode<>(element, parent);
        parent.getChilds().add(n, node);
        return node;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> node1 = (TreeNode<E>) p1;
        TreeNode<E> node2 = (TreeNode<E>) p2;
        node1.swapElements(node2);
    }

    @Override
    public E replace(Position<E> p, E e) {
        try{
            if(p==null)
                throw new InvalidPositionException("Not a valid position");
            TreeNode<E> node = (TreeNode<E>) p;
            E aux = node.getElement();
            node.setElement(e);
            return aux;
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public void remove(Position<E> p) {
        try{
            if(isRoot(p)){
                this.root = null;
            }
            else{
                TreeNode<E> node = checkPosition(p);
                TreeNode<E> parent = node.getParent();
                parent.getChilds().remove(node);
            }
        }catch (InvalidPositionException e){
            e.printStackTrace();
        }

    }

    private void setRoot(TreeNode<E> n){
        this.root = n;
    }
    @Override
    public NAryTree<E> subTree(Position<E> v) {
        TreeNode<E> node = null;
        try{
            node = checkPosition(v);
        }catch (InvalidPositionException e){
            e.printStackTrace();
        }
        TreeNode<E> parent = node.getParent();
        parent.getChilds().remove(node);
        node.setParent(null);
        LinkedTree<E> newTree = new LinkedTree<E>();
        newTree.setRoot(root);
        return newTree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(t.root());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        if(p==null){
            root = node;
        }
        else{
            TreeNode<E> parent = null;
            try {
                parent = checkPosition(p);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            node.setParent(parent);
            parent.getChilds().add(node);
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        TreeNode<E> n = null;
        try {
            n = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return n.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getChilds();
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v)||isRoot(v));
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getChilds().isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getParent()==null;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new PreOrderTreeIterator<>(this);
    }

    public int size(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private TreeNode<E> checkPosition(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof Position)){
            throw new InvalidPositionException("The position is invalid");
        }
        TreeNode<E> n = (TreeNode<E>) p;
        return n;
    }


    //Para implementar el iterador next
    /*
    private Position<E> next(){
        Position<E> p = lista.get(0);
        lista.remove(0);
        List<Position<E>> reverse_list = new LinkedList<>();
        for Position<E> q: tree.children(p){
            reverse_list.add(q);
        }
        for(Position<E> q: reverse_list){
            lista.add(q)
        }
        return p
    }
     */

    //POSIBLE EJERCICIO DE EXAMEN
    //IMPLEMENTAR EL INORDEN X2 PRIMERO LOS PARES, LUEGO EL CENTRO Y LUEGO LOS IMPARES.
}
