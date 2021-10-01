/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacioniteradores;

import java.util.Iterator;
import java.util.LinkedList;
import material.Position;

/**
 *
 * @author balalo
 * @param <E>
 */
public class LinkedPositionList<E> implements MyListBetter<E>{
    
    private DLinkedNode<E> head;
    private int size = 0;
    
    /**
     * @param args the command line arguments
     */
    public LinkedPositionList(){}
    
    public LinkedPositionList(Position<E> e) throws InvalidPositionException{
        this.head = this.checkPosition(e);
        size++;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isempty() {
        return size == 0;
    }

    @Override
    public Position<E> add(E value) {
        DLinkedNode<E> headNode = head;
        DLinkedNode<E> newNode = new DLinkedNode<>(value, null, head);//Add -> [E,head...]
        if(head != null){//Change prev
            headNode.setPrev(newNode);
        }
        head = newNode;//Change head
        size++;        
        return (Position<E>) head;
    }

    @Override
    public Position<E> addAfter(Position<E> pos, E value) throws InvalidPositionException {
        DLinkedNode<E> prevNode = this.checkPosition(pos);
        DLinkedNode<E> newNode = new DLinkedNode<>(value, prevNode, prevNode.getNext());//Add -> [...pos,value,pos.next...]
        if(prevNode.getNext() != null){
            prevNode.getNext().setPrev(newNode);
        }
        prevNode.setNext(newNode);
        this.size++;
        return (Position<E>) newNode;      
    }

    @Override
    public Position<E> addBefore(Position<E> pos, E value) throws InvalidPositionException {
        DLinkedNode<E> nextNode = this.checkPosition(pos);
        DLinkedNode<E> newNode = new DLinkedNode<E>(value, nextNode.getPrev(), nextNode);
        if (this.head==nextNode){
            this.head = newNode;
        }
        else{
            nextNode.getPrev().setNext(newNode);
        }
        nextNode.setPrev(newNode);
        this.size++;
        return (Position<E>) newNode;
    }

    @Override
    public E remove(Position<E> pos) throws InvalidPositionException{
        DLinkedNode<E> node = this.checkPosition(pos);
        if(node == head){
            head = node.getNext();
        }
        else{
            node.getPrev().setNext(node.getNext());
        }
        if(node.getNext() != null){
            node.getNext().setPrev(node.getPrev());
        }
        size--;
        return node.getElement();
    }

    @Override
    public Position<E> get() {
        return head;    
    }

    @Override
    public Position<E> set(Position<E> pos, E value) throws InvalidPositionException{
        DLinkedNode<E> node = this.checkPosition(pos);
        node.setElement(value);
        return (Position<E>) node;
    }

    @Override
    public Position<E> search(E value) {
        DLinkedNode<E> node = head;
        while (node!=null){
            if(node.getElement().equals(value)){
                return node;
            }
            else{
                node=node.getNext();
            }
        }
        return null;    
    }

    @Override
    public boolean contains(E value) {
        return this.search(value)!= null;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new LinkedPosListIterator(this);
    }
    
    private DLinkedNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
        if (p == null || !(p instanceof DLinkedNode)) {
            throw new InvalidPositionException("The position is invalid");
        }
        return (DLinkedNode<E>) p;
    }
    
}
