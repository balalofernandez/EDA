package EjerciciosRepaso;

import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

public class EjerciciosRepaso {
    public EjerciciosRepaso() {
    }

    public int sucesorN(int n, LinkedBinarySearchTree<Integer> tree){
        /*En clase se habló de que la implementación de la interfaz BinarySearchTree estaba mal
        * no tenía hasLeft ni hasRight, por ello hemos hecho un getter de binTree
        * */
        BinaryTree<Integer> prueba;

        LinkedBinaryTree<Integer> binTree = tree.getBinTree();
        if(binTree.isEmpty())throw new RuntimeException("El árbol es vacío");
        int sol = recursiveSucesor(binTree,binTree.root(), binTree.root(), n);
        if(sol<n)
            throw new RuntimeException("No se ha encontrado un elemento mayor");
        return sol;
    }

    protected int recursiveSucesor(LinkedBinaryTree<Integer> binTree, Position<Integer> pos, Position<Integer> suc, int n){
        if(pos.getElement()>n && pos.getElement()<suc.getElement() || suc.getElement()<n){
            suc = pos;
        }
        if(binTree.isLeaf(pos)){
            return suc.getElement();
        }
        if(pos.getElement()>n && binTree.hasLeft(pos)){
            return recursiveSucesor(binTree,binTree.left(pos),suc,n);
        }
        else if (pos.getElement()<=n && binTree.hasRight(pos)){
            return recursiveSucesor(binTree,binTree.right(pos),suc,n);
        }
        else if(pos.getElement()<=n && !binTree.hasRight(pos)){
            throw new RuntimeException("No hay un elemento mayor");
        }
        return suc.getElement();
    }


    public int predecesorN(int n, LinkedBinarySearchTree<Integer> tree){
        LinkedBinaryTree<Integer> binTree = tree.getBinTree();
        if(binTree.isEmpty())throw new RuntimeException("El árbol es vacío");
        int sol = recursivePredecesor(binTree,binTree.root(), binTree.root(), n);
        if(sol>n)
            throw new RuntimeException("No se ha encontrado en elemento menor");
        return sol;
    }

    protected int recursivePredecesor(LinkedBinaryTree<Integer> binTree, Position<Integer> pos, Position<Integer> pred, int n){
        if(pos.getElement()<n && pos.getElement()> pred.getElement() || pred.getElement()>n)
            pred = pos;
        if(binTree.isLeaf(pos)){
            return pred.getElement();
        }
        else if(pos.getElement()<n && binTree.hasRight(pos)){
            return recursivePredecesor(binTree,binTree.right(pos),pred,n);
        }
        else if(pos.getElement()>=n && binTree.hasLeft(pos)){
            return  recursivePredecesor(binTree,binTree.left(pos),pred,n);
        }
        else if(pos.getElement()>n && !binTree.hasLeft(pos)){
            throw new RuntimeException("No existe elemento menor");
        }
        return  pred.getElement();
    }
}
