
import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;


/**
 *
 * @author mayte
 * @param <E>
 */

public class CheckTree<E extends Comparable> {
   
    /**
     * Receives a BinaryTree and returns true if the tree is a BinarySearchTree
     * @param tree     
     * @return      
    */
    public boolean isBinarySearchTree(BinaryTree<E> tree){
        if(tree.isEmpty()){
            return true;
        }
        return treeSearch(tree,tree.root(),true);
    }

    private boolean treeSearch(BinaryTree<E> tree, Position<E> pos, boolean isBST){
        if(tree.hasLeft(pos) && tree.hasRight(pos)){
            isBST = tree.left(pos).getElement().compareTo(pos.getElement())<0 ? true : false;
            isBST = tree.right(pos).getElement().compareTo(pos.getElement())>=0 ? true : false;
            return isBST && treeSearch(tree,tree.left(pos),isBST) && treeSearch(tree,tree.right(pos),isBST);
        }else if(tree.hasRight(pos)){
            isBST = tree.right(pos).getElement().compareTo(pos.getElement())>=0 ? true : false;
            return isBST && treeSearch(tree,tree.right(pos),isBST);
        }else if(tree.hasLeft(pos)){
            isBST = tree.left(pos).getElement().compareTo(pos.getElement())<0 ? true : false;
            return isBST && treeSearch(tree,tree.left(pos),isBST);
        }
        return isBST;
    }

}


