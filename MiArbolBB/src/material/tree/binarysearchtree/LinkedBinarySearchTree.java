/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree.binarysearchtree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import javafx.geometry.Pos;
import material.Position;
import material.tree.binarytree.LinkedBinaryTree;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedBinarySearchTree<E> implements BinarySearchTree<E> {

    LinkedBinaryTree<E> arbolBB;
    DefaultComparator<E> comparator;
    private int size;

    public LinkedBinarySearchTree(){
        this(null);
    }
    public LinkedBinarySearchTree(DefaultComparator<E> comparator) {
        if(comparator == null){
            this.comparator = new DefaultComparator<>();
        }
        else
            this.comparator = comparator;
        this.arbolBB = new LinkedBinaryTree<>();
        size = 0;
    }

    //Aux method to search in the tree
    protected Position<E> treeSearch(E value, Position<E> pos){
        E posVal = pos.getElement();
        int comp = comparator.compare(value,posVal);
        if( (comp<0) && arbolBB.hasLeft(pos))
            return treeSearch(value, arbolBB.left(pos));
        else if((comp > 0) && arbolBB.hasRight(pos))
            return treeSearch(value, arbolBB.right(pos));
        return pos;
    }
    //Adds all equals to K
    protected  void addAll(List<Position<E>>l, Position<E> pos,E k){
        //Primero buscamos en todo el árbol donde está el valor k.
        Position<E> primeraPos = treeSearch(k,pos);
        if(comparator.compare(primeraPos.getElement(),k)==0){
            l.add(primeraPos);
            if(arbolBB.hasLeft(pos)){
                addAll(l,arbolBB.left(pos),k);
            }
            if(arbolBB.hasRight(pos)){
                addAll(l,arbolBB.right(pos),k);
            }
        }
    }

    @Override
    public Position<E> find(E value) {
        if(size == 0){
            return null;
        }

        Position<E> currentPos = treeSearch(value, arbolBB.root());
        if(comparator.compare(currentPos.getElement(),value) == 0)
            return currentPos;
        else
            return null;

    }


    @Override
    public Iterable<? extends Position<E>> findAll(E value) {
        List<Position<E>> lista = new LinkedList<>();
        addAll(lista,arbolBB.root(),value);
        return lista;
    }

    @Override
    public Position<E> insert(E value) {
        if(arbolBB.isEmpty()){
            size =1;
            return arbolBB.addRoot(value);
        }
        else{
            Position<E> pos = treeSearch(value, arbolBB.root());
            if (comparator.compare(value, pos.getElement()) == 0) {
                //Vamos a buscar otros nodos que puedan tener el mismo valor (Se permiten repetidos)
                while (!this.arbolBB.isLeaf(pos) && this.arbolBB.hasRight(pos)) {
                    pos = treeSearch(value, this.arbolBB.right(pos));
                }
            }
            //Ahora tenemos que ver si tenemos que insertar a la izquierda o derecha del elemento
            if(comparator.compare(pos.getElement(),value)<0){
                //insertar izquierda
                size++;
                return arbolBB.insertLeft(pos,value);
            }
            else{
                //insertar derecha
                size++;
                return arbolBB.insertRight(pos,value);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(Position<E> pos) {
        //Caso 1 y 2: Es hoja o solo tiene un hijo.
        if(!arbolBB.isLeaf(pos) || !arbolBB.hasRight(pos) || !arbolBB.hasLeft(pos)){
            arbolBB.remove(pos);
        }
        //Caso 3: Tengo que reemplazarlo por el menor de sus mayores.
        else{
            Position<E> suc = sucesor(pos);
            arbolBB.swap(suc,pos);
            arbolBB.remove(pos);
        }
        size--;
    }
    //Busca el menor de sus mayores
    protected Position<E> sucesor(Position<E> pos){
        Position<E> aux = arbolBB.right(pos);
        while(arbolBB.hasLeft(aux)){
            aux = arbolBB.left(aux);
        }
        return aux;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return arbolBB.iterator();
    }
    void decreaseSize(){
        this.size --;
    }
    public Position<E> first() {
        //Comenzamos en la raiz
        Position<E> nodo = arbolBB.root();
        //mientras tenga hasnext, nodo pasara a ser el nodo hijo izquierdo
        while (arbolBB.hasLeft(nodo)) {
            nodo = arbolBB.left(nodo);
        }
        return nodo;
    }

    public Position<E> last() {
        //Comenzamos en la raiz
        Position<E> nodo = arbolBB.root();
        //mientras tenga hasnext, nodo pasara a ser el nodo hijo derecho
        while (arbolBB.hasRight(nodo)) {
            nodo = arbolBB.right(nodo);
        }
        return nodo;
    }
}
