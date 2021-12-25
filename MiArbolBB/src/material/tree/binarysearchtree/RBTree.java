package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        //Primero creamos el nodo
        RBInfo<E> nodo = new RBInfo<>(e);
        //Ahora lo insertamos
        Position<RBInfo<E>> pos= resBT.insert(nodo);
        nodo.setTreePosition(pos);
        nodo.setRed(true);
        if(resBT.arbolBB.isRoot(pos)){
            nodo.setRed(false);
        }
        //Ahora lo que tenemos que hacer es ver si tenemos un doble rojo y solucionarlo
        //No solo en el nodo insertado sino mirando hasta la raíz.
        else{
            remedyDoubleRed(nodo);//Aquí se incluye la parte de mirar hasta la raíz
        }
        return nodo;
    }

    /**
     * Remedies a double red violation at a given node caused by insertion.
     * @param nodeZ
     */
    protected void remedyDoubleRed(RBInfo<E> nodeZ) {
        //como recibimos el nodo tenemos que sacar la posición
        Position<RBInfo<E>> pos = nodeZ.getTreePosition();
        Position<RBInfo<E>> padre = resBT.arbolBB.parent(pos);
        if(padre.getElement().isRed() && !resBT.arbolBB.isRoot(padre)){
            //Como siempre entramos en esta función se puede dar el caso de que no tengamos doble rojo
            Position<RBInfo<E>> abuelo = resBT.arbolBB.parent(padre);
            boolean hayTios = resBT.arbolBB.hasLeft(abuelo) && resBT.arbolBB.hasRight(abuelo);
            //Vemos si el tío es negro
            boolean tioNegro = true;
            if(hayTios){
                Position<RBInfo<E>> tio = resBT.arbolBB.sibling(padre);
                tioNegro = tio.getElement().isRed;
            }
            //Ahora distinguimos los casos: restructuración-trinodo y recoloración
            if(tioNegro){//restructuración-trinodo
                pos = reestructurator.restructure(pos,resBT.arbolBB);
                //Recoloreamos el que se queda arriba
                pos.getElement().setRed(false);
                //Ponemos los dos hijos como rojos
                resBT.arbolBB.left(pos).getElement().setRed(true);
                resBT.arbolBB.right(pos).getElement().setRed(true);
            }
            else{//recoloración
                //vamos a recolorear
                padre.getElement().setRed(false);
                resBT.arbolBB.sibling(padre).getElement().setRed(false);//Si el tío es rojo es porque existe
                abuelo.getElement().setRed(true);
                if(resBT.arbolBB.isRoot(abuelo)){
                    abuelo.getElement().setRed(false);
                }
                else{
                    remedyDoubleRed(abuelo.getElement());
                }
            }
        }
    }

    @Override
    public void remove(Position<E> pos) throws IllegalStateException {
        //Para borrar, se vuelven a considerar los 3 casos
        RBInfo<E> nodo = (RBInfo<E>) pos;
        Position<RBInfo<E>> posNodo = nodo.getTreePosition();
        //Cojo también el padre para comprobar
        Position<RBInfo<E>> padre = null;
        if(resBT.arbolBB.isLeaf(posNodo) || !resBT.arbolBB.hasLeft(posNodo)|| !resBT.arbolBB.hasRight(posNodo)){//Caso 1 y 2
            if(!resBT.arbolBB.isRoot(posNodo))
                padre = resBT.arbolBB.parent(posNodo);
            resBT.remove(posNodo);
        }else{
            Position<RBInfo<E>> suc = resBT.sucesor(posNodo);
            resBT.arbolBB.swap(posNodo,suc);
            resBT.arbolBB.remove(posNodo);
            final boolean colorSuccesor = suc.getElement().isRed;
            suc.getElement().setRed(posNodo.getElement().isRed());
            posNodo.getElement().setRed(colorSuccesor);
            if(!resBT.arbolBB.isRoot(posNodo)){
                padre = resBT.arbolBB.parent(posNodo);
            }
            resBT.remove(posNodo);
        }
        //Ahora queda mirar que se cumpla la condición de profundidad negra
        //Sacamos el nodo R
        Position<RBInfo<E>> nodoR = null;
        if (resBT.arbolBB.hasLeft(posNodo))
            nodoR = resBT.arbolBB.left(posNodo);
        else if (resBT.arbolBB.hasRight(posNodo))
            nodoR = resBT.arbolBB.right(posNodo);
        //Distinguimos casos:
        //Si el nodo o el nodo R son rojos r se colorea de negro y ya
        if((nodoR != null) && nodoR.getElement().isRed()){
            nodoR.getElement().setRed(false);
            return;
        }
        if(posNodo.getElement().isRed()){
            return;
        }
        //Hemos eliminado un nodo negro y se forma un doble negro
        remedyDoubleBlack(padre,nodoR);
    }

    /**
     * Remedies a double black violation at a given node caused by removal.
     */
    protected void remedyDoubleBlack(Position<RBInfo<E>> doubleBlackParent, Position<RBInfo<E>> doubleBlack) {
        //Hay que tener cuidado porque el padre puede ser null
        Position<RBInfo<E>> x = doubleBlackParent;
        Position<RBInfo<E>> r = doubleBlack;
        Position<RBInfo<E>> y = null;//el otro hijo de x
        Position<RBInfo<E>> z= null;//el sobrino
        boolean esHIzq;

        if(x != null){
            if(resBT.arbolBB.hasLeft(x) && (resBT.arbolBB.left(x) != r)){
                y = resBT.arbolBB.left(x);
                esHIzq = true;
            }
            else{
                y = resBT.arbolBB.right(x);
                esHIzq = false;
            }
            RBInfo<E> nodoX = x.getElement();
            RBInfo<E> nodoY = y.getElement();

            // Ahora tenemos que distinguir los casos, si tenemos un tio negro o rojo
            if(!nodoY.isRed()){
                z = hasRedChild(y);
                if(z == null){//Caso que no tiene ningún hijo rojo recoloración
                    nodoY.setRed(true);
                    if(!nodoX.isRed()){
                        if (!resBT.arbolBB.isRoot(x)) { //Si es la raíz terminamos
                            remedyDoubleBlack(resBT.arbolBB.parent(x),x);
                        }
                        return;
                    }
                    nodoX.setRed(false);
                    return;
                }
                if(z != null){//Caso en el que hay un rojo -> restructuracion
                    boolean colorX = nodoX.isRed();
                    Position<RBInfo<E>> aux = reestructurator.restructure(z, resBT.arbolBB);
                    resBT.arbolBB.left(aux).getElement().setRed(false);
                    resBT.arbolBB.right(aux).getElement().setRed(false);
                    aux.getElement().setRed(colorX);
                    return;
                }
            }
            else{//Reajustado
                if (esHIzq && resBT.arbolBB.hasLeft(y)) {
                    z = resBT.arbolBB.left(y);
                } else if (resBT.arbolBB.hasRight(y)) {
                    z = resBT.arbolBB.right(y);
                }
                reestructurator.restructure(z, resBT.arbolBB);
                //Ahora recoloreamos
                nodoY.setRed(false);
                nodoX.setRed(true);
                //Simplemente se ha cambiado el caso, de forma que ahora el hermano de r es negro
                remedyDoubleBlack(doubleBlackParent, doubleBlack);
            }
        }
    }

    /**
     * Returns a red child of a node.
     * @param pos
     * @return
     */
    protected Position<RBInfo<E>> hasRedChild(Position<RBInfo<E>> pos) {
        Position<RBInfo<E>> child;
        if (resBT.arbolBB.hasLeft(pos)) {
            child = resBT.arbolBB.left(pos);
            final boolean redLeftChild = child.getElement() != null && child.getElement().isRed();
            if (redLeftChild) {
                return child;
            }
        }

        if (resBT.arbolBB.hasRight(pos)) {
            child = resBT.arbolBB.right(pos);
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
        RBTreeIterator<E> it = new RBTreeIterator<E>(bstIt);
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