

import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class CheckTreeTest {
    
    public CheckTreeTest() {
    }

    /**
     * Test of isBinarySearchTree method, of class CheckTree.
     */
    @Test
    public void testIsBinarySearchTree() {
        System.out.println("isBinarySearchTree");
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();
        Position<Integer> r = tree.insert(24);
        Position<Integer> p1 = tree.insert(15);
        Position<Integer> p2 = tree.insert(31);
        Position<Integer> p3 = tree.insert( 12);
        Position<Integer> p4 = tree.insert( 19);
        tree.insert(  29);
        tree.insert(  44);
        tree.insert( 4);
        tree.insert(  17);
        tree.insert(  21);
        tree.remove(p2);
    }
    
}
