package EjerciciosRepaso;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.LinkedList;

/**
 *
 * @author mayte
 * @param <T>
 */
public class MoreFunctionality<T> {

    /**
     * Check if a BinaryTree is perfect. 
     * A BinaryTree is perfect if all its internal nodes have left chlid and right child.
     * @param t
     * @return 
     */
    public boolean isPerfect(BinaryTree<T> t){
        return isPerfectAux(t,t.root());
    }

    protected boolean isPerfectAux(BinaryTree<T> tree, Position<T> pos){

        if(tree.isLeaf(pos)){
            return true;
        }
        else if(tree.hasLeft(pos) && tree.hasRight(pos)){
            return isPerfectAux(tree, tree.left(pos)) && isPerfectAux(tree, tree.left(pos));
        }
        else{
            return false;
        }
    }

    /**
     * Check if a BinaryTree is imperfect. 
     * A BinaryTree is imperfect if not all its internal nodes have left chlid and right child.
     * @param t
     * @return 
     */
    public boolean isImperfect(BinaryTree<T> t){

        return !isPerfectAux(t, t.root());
    }
    
    /**
     * Check if a BinaryTree is odd. 
     * A BinaryTree is odd if more than half its nodes are left child.
     * @param t
     * @return 
     */
    public boolean isOdd(BinaryTree<T> t){
        return isEvenAux(t,t.root())<0;
    }


    
    /**
     * Check if a BinaryTree is even. 
     * A BinaryTree is even if more than half its nodes are right child.
     * @param t
     * @return 
     */
    public boolean isEven(BinaryTree<T> t){

        return isEvenAux(t,t.root())>0;
    }

    protected int isEvenAux(BinaryTree<T> tree, Position<T> pos){
        if(tree.isLeaf(pos)){
            return 1;
        }
        else if(!tree.hasLeft(pos)){
            return isEvenAux(tree,tree.right(pos))+1;
        }
        else if(!tree.hasRight(pos)){
            return isEvenAux(tree,tree.left(pos))-1;
        }
        return isEvenAux(tree,tree.right(pos)) - isEvenAux(tree,tree.left(pos));
    }
    
    /**
     * Check if a BinaryTree is ambidextrous. 
     * A BinaryTree is ambidextrous if its left and right children doesn't differ in more than one.
     * @param t
     * @return 
     */
    public boolean isAmbidextrous(BinaryTree<T> t){
        
        return isAmbidextrousAux(t,t.root())<2 &&isAmbidextrousAux(t,t.root())> -2;
    }

    protected int isAmbidextrousAux(BinaryTree<T> tree, Position<T> pos){
        if(tree.isLeaf(pos)){
            return 1;
        }
        else if(!tree.hasLeft(pos)){
            return isAmbidextrousAux(tree,tree.right(pos))+1;
        }
        else if(!tree.hasRight(pos)){
            return isAmbidextrousAux(tree,tree.left(pos))-1;
        }
        return isAmbidextrousAux(tree,tree.right(pos)) - isAmbidextrousAux(tree,tree.left(pos));
    }

}
