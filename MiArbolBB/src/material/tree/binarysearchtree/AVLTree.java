package material.tree.binarysearchtree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Pos;
import material.Position;

/**
 *
 * @author mayte
 */
public class AVLTree<E> implements BinarySearchTree<E> {
    
    //Esta clase es necesaria para guardar el valor de la altura AVL en los nodos BTNodes
    private class AVLInfo<T> implements Comparable<AVLInfo<T>>, Position<T> {

        int h;
        private T element;
        private Position<AVLInfo<T>> pos;

        AVLInfo(T element) {
            this.element = element;
            this.pos = null;
            this.h = 1;
        }

        public void setTreePosition(Position<AVLInfo<T>> pos) {
            this.pos = pos;
        }

        public Position<AVLInfo<T>> getTreePosition() {
            return this.pos;
        }

        public void setHeight(int height) {
            this.h = height;
        }

        public int getHeight() {return h;}

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public int compareTo(AVLInfo<T> o) {
            return ((Comparable<T>) o.getElement()).compareTo(element);
        }

        @Override
        public String toString() {return element.toString();}

        @Override
        public boolean equals(Object obj) {
            AVLInfo<T> e = (AVLInfo<T>) obj;
            return element.equals(e.getElement());
        }

    }

    private class AVLTreeIterator<T> implements Iterator<Position<T>> {

        private Iterator<Position<AVLInfo<T>>> it;

        public AVLTreeIterator(Iterator<Position<AVLInfo<T>>> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Position<T> next() {
            Position<AVLInfo<T>> aux = it.next();
            return aux.getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    LinkedBinarySearchTree<AVLInfo<E>> arbolAVL = new LinkedBinarySearchTree<>();
    Reestructurator reestructurator = new Reestructurator();

    @Override
    public Position<E> find(E value) {
        AVLInfo<E> nodo = new AVLInfo<>(value);
        Position<AVLInfo<E>> pos = arbolAVL.find(nodo);
        return (pos == null)? null : pos.getElement();
    }

    @Override
    public Iterable<? extends Position<E>> findAll(E value) {
        AVLInfo<E> nodo = new AVLInfo<>(value);
        List<AVLInfo<E>> aux = new LinkedList<>();
        for (Position<AVLInfo<E>> e: arbolAVL.findAll(nodo)) {
            aux.add(e.getElement());
        }
        return aux;
    }

    //Miramos si está balanceado
    private boolean isBalanced(Position<AVLInfo<E>> p){
        int leftHeight = arbolAVL.arbolBB.hasLeft(p) ? arbolAVL.arbolBB.left(p).getElement().getHeight() : 0;
        int rightHeight = arbolAVL.arbolBB.hasRight(p) ? arbolAVL.arbolBB.right(p).getElement().getHeight() : 0;

        //Tenemos que devolver si left-rigth pertenece a (-1,1)
        return ((leftHeight-rightHeight) >= -1) && ((leftHeight-rightHeight)<=1);
    }

    private void calculateHeight(Position<AVLInfo<E>> p) {
        int leftHeight = (arbolAVL.arbolBB.hasLeft(p)) ? arbolAVL.arbolBB.left(p).getElement().getHeight() : 0;
        int rightHeight = (arbolAVL.arbolBB.hasRight(p)) ? arbolAVL.arbolBB.right(p).getElement().getHeight() : 0;
        p.getElement().setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private Position<AVLInfo<E>> tallerChild(Position<AVLInfo<E>> p){
        //Vamos a devolver el hijo más alto y en igualdad de condiciones haremos que sea una rotación simple
        int leftHeight = (arbolAVL.arbolBB.hasLeft(p)) ? arbolAVL.arbolBB.left(p).getElement().getHeight() : 0;
        int rightHeight = (arbolAVL.arbolBB.hasRight(p)) ? arbolAVL.arbolBB.right(p).getElement().getHeight() : 0;

        if(leftHeight > rightHeight){
            return arbolAVL.arbolBB.left(p);
        }else if(rightHeight > leftHeight){
            return arbolAVL.arbolBB.right(p);
        }
        //Si son iguales vamos a elegir una rotación simple.
        //Esto es que vamos a mirar que p sea el hijo (izq o der) del padre
        if(arbolAVL.arbolBB.isRoot(p)){
            return arbolAVL.arbolBB.left(p);//En este caso devolvemos uno cualquiera
        }

        if(p == arbolAVL.arbolBB.left(arbolAVL.arbolBB.parent(p))) {
            return arbolAVL.arbolBB.left(p);
        }else {
            return arbolAVL.arbolBB.right(p);
        }
    }

    private void rebalance(Position<AVLInfo<E>> p){
        //En primer lugar ponemos la altura al nodo
        if(arbolAVL.arbolBB.isInternal(p)){
            calculateHeight(p);
        }
        else {
            p.getElement().setHeight(1);//Las hojas tienen altura 1
        }
        //Tenemos que ir recorriendo el árbol hacia arriba y ver si está desvalanceado
        while(!arbolAVL.arbolBB.isRoot(p)){
            p = arbolAVL.arbolBB.parent(p);
            calculateHeight(p);
            if(!isBalanced(p)){//hacemos la reestructuración
                //Recordamos que al reestructurator le pasamos el nodo más abajo del árbol
                Position<AVLInfo<E>> lowChild = tallerChild(tallerChild(p));
                p = reestructurator.restructure(lowChild, arbolAVL.arbolBB);
                //Tenemos que cambiar las alturas de los nodos que hemos cambiado
                calculateHeight(arbolAVL.arbolBB.left(p));
                calculateHeight(arbolAVL.arbolBB.right(p));
                calculateHeight(p);
            }
        }
    }

    @Override
    public Position<E> insert(E value) {
        //Para insertar basta con insertar en el árbolBB y subir hacia arriba comprobando los desequilibrios
        //Lo primero que hacemos es constrtuir el nodo
        AVLInfo<E> nodo = new AVLInfo<>(value);
        //Ahora insertamos
        Position<AVLInfo<E>> position =  arbolAVL.insert(nodo);
        nodo.setTreePosition(position);
        //Vemos si la rama del árbol está desvalanceada y en el caso que esté rebalanceamos
        rebalance(position);
        return nodo;
    }

    @Override
    public boolean isEmpty() {
        return arbolAVL.isEmpty();
    }

    @Override
    public void remove(Position<E> pos) {
        //Vamos a eliminar el nodo
        AVLInfo<E> nodo = (AVLInfo<E>) pos;
        Position<AVLInfo<E>> posNodo = nodo.getTreePosition();
        Position<AVLInfo<E>> posPadre = null;

        //Por la implementación del arbol como remove no devuelve nada
        //Entonces no sabemos desde dónde hay que comprobar que no esté desequilibrado.
        if(arbolAVL.arbolBB.isLeaf(posNodo) || !arbolAVL.arbolBB.hasLeft(posNodo) || !arbolAVL.arbolBB.hasRight(posNodo)){//Caso 1 y 2
            //Por cómo está implementado el borrado, no guardamos el padre
            posPadre = arbolAVL.arbolBB.isRoot(posNodo) ? null :  arbolAVL.arbolBB.parent(posNodo);
            //Eliminamos y miramos desde el padre si está balanceado
            arbolAVL.arbolBB.remove(posNodo);
        }else{ //Caso 3 (Ambos hijos)
            Position<AVLInfo<E>> suc = arbolAVL.sucesor(posNodo);
            posPadre = arbolAVL.arbolBB.isRoot(posNodo) ? null :  arbolAVL.arbolBB.parent(posNodo);
            arbolAVL.arbolBB.swap(posNodo,suc);
            arbolAVL.arbolBB.remove(posNodo);
        }
        //Ahora simplemente vemos que esté balanceado
        if(posPadre != null) rebalance(posPadre);
        //Cuidado que como no hemos usado el remove del arbolAVL tenemos que quitar el size
        arbolAVL.decreaseSize();
    }

    @Override
    public int size() {
        return this.arbolAVL.size();
    }

    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Position<E>> iterator() {
        Iterator<Position<AVLInfo<E>>> aux = arbolAVL.iterator();
        return  new AVLTreeIterator<E>(aux);
    }
    
}
