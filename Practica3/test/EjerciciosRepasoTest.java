import EjerciciosRepaso.*;
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
    public void TestEjerciciosRepaso() {
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
    }

    @Test
    public void isPerfectTest() {
        System.out.println("closest");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();
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

        //Otro arbol
        tree2.insert(24);
        tree2.insert(15);
        tree2.insert(12);
        Position<Integer> expResult2 = tree2.insert(4);
        tree2.insert(19);
        tree2.insert(17);
        tree2.insert(21);
        tree2.insert(31);
        tree2.insert(29);
        Position<Integer> dos2 = tree2.insert(44);
        tree2.insert(13);


        BinaryTree<Integer> binTree = tree.getBinTree();
        BinaryTree<Integer> binTree2 = tree2.getBinTree();

        MoreFunctionality instance = new MoreFunctionality();

        assertEquals(false,instance.isPerfect(binTree));
        assertEquals(true,instance.isPerfect(binTree2));
    }
    @Test
    public void isImperfect() {
        System.out.println("is Imperfect");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();
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

        //Otro arbol
        tree2.insert(24);
        tree2.insert(15);
        tree2.insert(12);
        Position<Integer> expResult2 = tree2.insert(4);
        tree2.insert(19);
        tree2.insert(17);
        tree2.insert(21);
        tree2.insert(31);
        tree2.insert(29);
        Position<Integer> dos2 = tree2.insert(44);
        tree2.insert(13);


        BinaryTree<Integer> binTree = tree.getBinTree();
        BinaryTree<Integer> binTree2 = tree2.getBinTree();

        MoreFunctionality instance = new MoreFunctionality();

        assertEquals(true,instance.isImperfect(binTree));
        assertEquals(false,instance.isImperfect(binTree2));
    }
    @Test
    public void isEvenTest() {
        System.out.println("is Even");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();
        tree.insert(24);
        tree.insert(15);
        tree.insert(12);
        Position<Integer> expResult = tree.insert(13);
        tree.insert(19);
        tree.insert(17);
        tree.insert(21);
        tree.insert(31);
        tree.insert(29);
        Position<Integer> dos = tree.insert(44);

        //Otro arbol
        tree2.insert(24);
        tree2.insert(15);
        tree2.insert(12);
        Position<Integer> expResult2 = tree2.insert(4);
        tree2.insert(19);
        tree2.insert(17);
        tree2.insert(21);
        tree2.insert(31);
        tree2.insert(29);
        Position<Integer> dos2 = tree2.insert(44);
        tree2.insert(13);


        BinaryTree<Integer> binTree = tree.getBinTree();
        BinaryTree<Integer> binTree2 = tree2.getBinTree();

        MoreFunctionality instance = new MoreFunctionality();

        assertEquals(true,instance.isEven(binTree));
        assertEquals(false,instance.isEven(binTree2));
    }

    @Test
    public void isAmbidextrousTest() {
        System.out.println("is ambidextrous");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();
        tree.insert(24);
        tree.insert(15);
        tree.insert(12);
        Position<Integer> expResult = tree.insert(13);
        tree.insert(19);
        tree.insert(17);
        tree.insert(21);
        tree.insert(31);
        tree.insert(29);
        Position<Integer> dos = tree.insert(44);

        //Otro arbol
        tree2.insert(24);
        tree2.insert(15);
        tree2.insert(12);
        Position<Integer> expResult2 = tree2.insert(4);


        BinaryTree<Integer> binTree = tree.getBinTree();
        BinaryTree<Integer> binTree2 = tree2.getBinTree();

        MoreFunctionality instance = new MoreFunctionality();

        assertEquals(true,instance.isEven(binTree));
        assertEquals(false,instance.isEven(binTree2));
    }


}

