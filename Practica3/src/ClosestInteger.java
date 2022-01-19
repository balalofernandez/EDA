
import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;


/**
 *
 * @author mayte
 */
public class ClosestInteger {
    
    /**
     * Recives a BinarySearchTree and an integer i and returns the Position that contains the closest integer to i
     * @param tree     
     * @param i     
     * @return      
     */
    public Position<Integer> closest (BinarySearchTree<Integer> tree, int i){
        if(tree.isEmpty()){
            throw new RuntimeException("El arbol es vacío");
        }
        LinkedBinarySearchTree<Integer> t = (LinkedBinarySearchTree<Integer>) tree;
        LinkedBinaryTree<Integer> binTree = t.getBinTree();
        return treeSearch(binTree,binTree.root(),i,-1,binTree.root());
    }

    private Position<Integer> treeSearch (LinkedBinaryTree<Integer> binTree, Position<Integer> pos, int i, int diferencia, Position<Integer> aprox){

        int diferenciaAux = Math.abs(pos.getElement() - i);
        if(diferenciaAux<diferencia || diferencia == -1){
            aprox = pos;
        }
        if(pos.getElement() == i){
            return pos;
        }
        else if(pos.getElement()>i && binTree.hasLeft(pos)){
            return treeSearch(binTree,binTree.left(pos),i, diferenciaAux, aprox);
        }
        else if(pos.getElement()<i && binTree.hasRight(pos)){
            return  treeSearch(binTree,binTree.right(pos), i, diferenciaAux, aprox);
        }
        //Caso en el que no tenga más rama que recorrer
        return aprox;
    }
    
}
