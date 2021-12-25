package material.tree.binarysearchtree;

import material.Position;
import material.tree.binarytree.LinkedBinaryTree;

/**
 * LinkedBinarySearchTree that implements the tri-node restructuration
 * @author jvelez
 */
class Reestructurator {
   /**
     * Performs a tri-node restructuring. Assumes the nodes are in one of
     * following configurations:
     *
     * <pre>
     *          z=c       z=c        z=a         z=a
     *         /  \      /  \       /  \        /  \
     *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
     *      /  \      /  \           /  \         /  \
     *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
     *   /  \          /  \       /  \             /  \
     *  t1  t2        t2  t3     t2  t3           t3  t4
     * </pre>
     *
     * @return the new root of the restructured subtree
     */

    //Vamos a recibir un position con el nodo C y el árbol binario de búsqueda (AVL)
    public Position restructure(Position posNode, LinkedBinaryTree binTree){
        LinkedBinaryTree lowKey, midKey, highKey, t1, t2, t3, t4;

        //Lo primero que vamos a hacer es identificar los nodos, b y a
        //Asumimos que el nodo C tiene padre y abuelo (En realidad tratamos esta situación en ALV.java)
        Position padre = binTree.parent(posNode);
        Position abuelo = binTree.parent(padre);
        //Para modificar A, tenemos que tener un puntero al padre de A (bisabuelo)
        Position zParent = binTree.isRoot(abuelo) ? null : binTree.parent(abuelo);

        //Ahora vemos la estructura A-B-C para ver si es una rotación doble o simple
        boolean leftCase = zParent == null ? false : abuelo == binTree.left(zParent);
        boolean nodeLeft = (binTree.hasLeft(padre) && (posNode == binTree.left(padre)));
        boolean parentLeft = (binTree.hasLeft(abuelo) && (padre == binTree.left(abuelo)));
        //Ahora vamos a tratar los 4 casos del diagrama anterior: izq-izq, izq-der, der-izq, der-der
        if(nodeLeft && parentLeft){//Caso izq-izq
            //Los subárboles
            lowKey = binTree.subTree(posNode);
            midKey = binTree.subTree(padre);
            highKey = binTree.subTree(abuelo);
            //Obtenemos los subárboles t1,t2,t3,t4
            t1 = (lowKey.hasLeft(lowKey.root())) ? lowKey.subTree(lowKey.left(lowKey.root())) : null;
            t2 = (lowKey.hasRight(lowKey.root())) ? lowKey.subTree(lowKey.right(lowKey.root())) : null;
            t3 = (midKey.hasRight(midKey.root())) ? midKey.subTree(midKey.right(midKey.root())) : null;
            t4 = (highKey.hasRight(highKey.root())) ? highKey.subTree(highKey.right(highKey.root())) : null;

        } else if (!nodeLeft && parentLeft) {// izda-dcha
            midKey = binTree.subTree(posNode);
            lowKey = binTree.subTree(padre);
            highKey = binTree.subTree(abuelo);
            t1 = (lowKey.hasLeft(lowKey.root())) ? lowKey.subTree(lowKey.left(lowKey.root())) : null;
            t2 = (midKey.hasLeft(midKey.root())) ? midKey.subTree(midKey.left(midKey.root())) : null;
            t3 = (midKey.hasRight(midKey.root())) ? midKey.subTree(midKey.right(midKey.root())) : null;
            t4 = (highKey.hasRight(highKey.root())) ? highKey.subTree(highKey.right(highKey.root())) : null;
        } else if (nodeLeft && !parentLeft) {//dcha-izda
            midKey = binTree.subTree(posNode);
            highKey = binTree.subTree(padre);
            lowKey = binTree.subTree(abuelo);
            t1 = (lowKey.hasLeft(lowKey.root())) ? lowKey.subTree(lowKey.left(lowKey.root())) : null;
            t2 = (midKey.hasLeft(midKey.root())) ? midKey.subTree(midKey.left(midKey.root())) : null;
            t3 = (midKey.hasRight(midKey.root())) ? midKey.subTree(midKey.right(midKey.root())) : null;
            t4 = (highKey.hasRight(highKey.root())) ? highKey.subTree(highKey.right(highKey.root())) : null;
        } else {//dcha-dcha
            highKey = binTree.subTree(posNode);
            midKey = binTree.subTree(padre);
            lowKey = binTree.subTree(abuelo);
            t1 = (lowKey.hasLeft(lowKey.root())) ? lowKey.subTree(lowKey.left(lowKey.root())) : null;
            t2 = (midKey.hasLeft(midKey.root())) ? midKey.subTree(midKey.left(midKey.root())) : null;
            t3 = (highKey.hasLeft(highKey.root())) ? highKey.subTree(highKey.left(highKey.root())) : null;
            t4 = (highKey.hasRight(highKey.root())) ? highKey.subTree(highKey.right(highKey.root())) : null;
        }
        //Ahora queda reestructurar los nodos:
        /**
         *
         *           b
         *         /  \
         *        a    c
         *      /  \ /  \
         *    t1  t2 t3  t4
         */

        //t1 y t4 son siempre hijos de a y c
        lowKey.attachRight(lowKey.root(),t2);
        highKey.attachLeft(highKey.root(),t3);
        midKey.attachLeft(midKey.root(),lowKey);
        midKey.attachRight(midKey.root(),highKey);

        //Devolvemos b
        Position output = midKey.root();

        //ahora lo colocamos en el arbolbb
        if(zParent == null){
            binTree.attach(midKey);
        }else{
            //Tenemos que distinguir dos casos, hijo izquierdo e hijo derecho
            if(leftCase) {
                binTree.attachLeft(zParent,midKey);
            }else {
                binTree.attachRight(zParent,midKey);
            }
        }
        return output;
    }
}
