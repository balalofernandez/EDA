/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import implementacioniteradores.LinkedPositionList;
import material.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balalo
 */
public class LinkedPositionListTest {
    
    public LinkedPositionListTest() {
    }
    public LinkedPositionList inicializa(){
       
        LinkedPositionList<Float> instance = new LinkedPositionList<>();
        
        instance.add(new Float(3));
        instance.add(new Float(8));
        instance.add(new Float(12));
        
        return instance; //[3,8,12]
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
    
    @Test
    public void testSize() {
        System.out.println("size");
        LinkedPositionList instance = inicializa();
              
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isempty method, of class LinkedPositionList.
     */
    @Test
    public void testIsempty1() {
        System.out.println("isempty");
        LinkedPositionList instance = inicializa();
        boolean expResult = false;
        boolean result = instance.isempty();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of isempty method, of class LinkedPositionList.
     */
    @Test
    public void testIsempty2() {
        System.out.println("isempty");
        LinkedPositionList instance = new LinkedPositionList();
        boolean expResult = true;
        boolean result = instance.isempty();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of add method, of class LinkedPositionList.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Float value = null;
        LinkedPositionList instance = new LinkedPositionList();
        assertTrue(instance.isempty());
        instance.add(new Float(2));
        assertFalse(instance.isempty());
        assertEquals(instance.size(),1);
    }
    @Test
    public void testAddAfter(){
        try{
            System.out.println("add After");
            LinkedPositionList<Float> instance = inicializa();
            Position<Float> p = instance.get();
            instance.addAfter(p,new Float(2));
            instance.remove(p);
            assertEquals(new Float(2),instance.get().getElement());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
