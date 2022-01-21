import EjerciciosRepaso.EjerciciosRepaso;
import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author mayte
 */
public class EjerciciosRepasoTest {



    public EjerciciosRepasoTest() {
    }

    @Test
    /*public void TestEjerciciosRepaso() {
        System.out.println("closest");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        tree.insert(24);
        tree.insert(15);
        tree.insert(12);
        Position<Integer> expResult = tree.insert(4);
        tree.insert(19);
        tree.insert(17);
        tree.insert(21);
        tree.insert(31);
        tree.insert(29);
        Position<Integer> dos = tree.insert(44);

        LinkedBinarySearchTree<Integer> auxTree1 = new LinkedBinarySearchTree<>();
        auxTree1.insert(1);
        LinkedBinarySearchTree<Integer> auxTree2 = new LinkedBinarySearchTree<>();
        auxTree2.insert(10);
        EjerciciosRepaso instance = new EjerciciosRepaso();
        try{
            instance.sucesorN(50, tree);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        int result = instance.sucesorN(15, tree);
        assertEquals(result, 17);
        assertEquals( instance.sucesorN(10, tree),12);
        assertEquals(instance.predecesorN(31,tree),29);
        assertEquals(instance.predecesorN(23,tree),21);
        assertNotEquals(instance.predecesorN(1,auxTree1),1);
    }*/

}

