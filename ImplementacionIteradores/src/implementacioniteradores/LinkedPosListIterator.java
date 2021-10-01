/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacioniteradores;

import java.util.Iterator;
import material.Position;

/**
 *
 * @author balalo
 */
public class LinkedPosListIterator<E> implements Iterator<E> {
    private LinkedPositionList<E> list;
    private DLinkedNode<E>  node;
    
    public LinkedPosListIterator(LinkedPositionList<E> list){
        this.list = list;
        this.node = (DLinkedNode<E>) list.get();
    }
    
    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public E next() {
        E nextElement = node.getElement();
        node = node.getNext();
        return nextElement;
    }

    @Override
    public void remove() {
        try {
            list.remove(node);
        } catch (InvalidPositionException e) {
            System.out.println("No se ha podido borrar el elemento");
        }
    }
    
}
