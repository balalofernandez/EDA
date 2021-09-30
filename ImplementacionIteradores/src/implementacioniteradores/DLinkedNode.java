/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacioniteradores;

import material.Position;

/**
 *
 * @author balalo
 * @param <E>
 */
public class DLinkedNode<E> implements Position<E>{
    private DLinkedNode<E> next;
    private DLinkedNode<E> prev;
    private E element;
    
    public DLinkedNode(E element){
        next = null;
        prev = null;
        this.element = element;
    }
    
    public DLinkedNode<E> getNext() {
        return next;
    }
    
    public DLinkedNode<E> getPrev() {
        return prev;
    }
    @Override
    public E getElement() {
        return element;
    }
    
    public void setNext(DLinkedNode<E> next) {
        this.next = next;
    }

    public void setPrev(DLinkedNode<E> prev) {
        this.prev = prev;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
