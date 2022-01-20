/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package practica1;

import material.Position;
import material.tree.narytree.LinkedTree;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author mayte
 */
public class EjerciciosRepasoTest {

    public EjerciciosRepasoTest() {
    }


    private LinkedTree<String> tree = new LinkedTree<>();
    Position<String> b;
    public void setTree() {

        Position<String> p = tree.addRoot("A");
        b = tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        Position<String> padre2 = tree.add("D", p);
        Position<String> padre3 =tree.add("E", p);

         tree.add("F", padre2);

        Position<String> p2 = tree.add("G", padre3);
        Position<String> p3 = tree.add("H", padre3);
        tree.add("I", padre3);

        Position<String> padre5 =tree.add("J", p2);
        tree.add("K", p3);
        tree.add("L", padre5);
    }
    @Test
    public void testParentHojas() {
        setTree();
        EjerciciosRepaso<String> ej = new EjerciciosRepaso<>();
        Collection<Position<String>> result = ej.padresHoja(tree);
        assertEquals(result.size(), 5);
    }
    @Test
    public void testHojaMasProx() {
        setTree();
        EjerciciosRepaso<String> ej = new EjerciciosRepaso<>();
        Position<String> result = ej.nodoMasCercano(tree);
        Position<String> result2 = ej.nodoMasCercano(new LinkedTree<>());
        assertEquals(result, b);
        assertEquals(result2, null);
    }

    
}
