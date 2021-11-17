
package material.tree;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Pos;
import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.narytree.NAryTree;

/**
 *
 * @author mayte
 * @param <T>
 */
public class MoreFunctionality<T> {
  

    /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the left side.
     * @param tree
     * @return 
     */
    public List<T> leftView(NAryTree<T> tree){
        LinkedList<T> elements = new LinkedList<>();
        if(tree.root() != null){
            LinkedList<Position<T>> listaAux =new LinkedList<>();
            Position<T> nodo = tree.root();
            listaAux.add(nodo);//Añado la raíz
            while(!listaAux.isEmpty()){
                //Vamos a ir pasando por todos los nodos, quedandonos con el primero
                int n = listaAux.size();
                for (int i = 1; i <= n; i++) {
                    Position<T> aux = listaAux.pop();
                    if(i==1){
                        elements.add(aux.getElement());
                    }
                    for(Position<T> p: tree.children(aux)){
                        listaAux.add(p);
                    }

                }
            }
        }
        return elements;
    }

   /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the right side.
     * @param tree
     * @return 
     */
    public List<T> rightView(NAryTree<T> tree){
        LinkedList<T> elements = new LinkedList<>();
        if(tree.root() != null){
            LinkedList<Position<T>> listaAux =new LinkedList<>();
            LinkedList<Position<T>> listaNodos =new LinkedList<>();
            Position<T> nodo = tree.root();
            listaAux.add(nodo);//Añado la raíz
            while(!listaAux.isEmpty()){
                //Vamos a ir pasando por todos los nodos, quedandonos con el primero
                int n = listaAux.size();
                for (int i = 1; i <= n; i++) {
                    Position<T> aux = listaAux.pop();
                    if(i==1){
                        elements.add(aux.getElement());
                    }
                    for(Position<T> p: tree.children(aux)){
                        listaNodos.addFirst(p);
                    }
                    while (!listaNodos.isEmpty()){
                        listaAux.add(listaNodos.pop());
                    }
                }
            }
        }
        return elements;
    }
}
