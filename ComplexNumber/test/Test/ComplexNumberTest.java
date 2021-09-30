/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import complexnumber.ComplexNumber;
import junit.framework.Assert;
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
public class ComplexNumberTest {
    
    public ComplexNumberTest() {
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
    public void realPartTest(){
        ComplexNumber complejo = new ComplexNumber(2.3,5);
        assertEquals("No corresponde a la parte real",2.3,complejo.realPart(),0.00001);
    }
    @Test
    public void imaginaryPartTest(){
        ComplexNumber complejo = new ComplexNumber(4.2,9.1);
        assertEquals("No corresponde a la parte real",9.1,complejo.imaginaryPart(),0.00001);
    }
    @Test
    public void addTest(){
        ComplexNumber complejo = new ComplexNumber(3,-4);
        ComplexNumber complejo2 = new ComplexNumber(2,7);
        assertEquals("No corresponde a la parte real",5,complejo.add(complejo2).realPart(),0.00001);
        assertEquals("No corresponde a la parte real",3,complejo.add(complejo2).imaginaryPart(),0.00001);
    }
    @Test
    public void subtractTest(){
        ComplexNumber complejo = new ComplexNumber(9,5);
        ComplexNumber complejo2 = new ComplexNumber(4,7);
        assertEquals("No corresponde a la parte real",5,complejo.subtract(complejo2).realPart(),0.00001);
        assertEquals("No corresponde a la parte real",-2,complejo.subtract(complejo2).imaginaryPart(),0.00001);
    }
    @Test
    public void multiplyTest(){
        ComplexNumber complejo = new ComplexNumber(5,6);
        ComplexNumber complejo2 = new ComplexNumber(3,2);
        assertEquals("No corresponde a la parte real",3,complejo.multiply(complejo2).realPart(),0.00001);
        assertEquals("No corresponde a la parte real",28,complejo.multiply(complejo2).imaginaryPart(),0.00001);
    }
    @Test
    public void divisionTest(){
        ComplexNumber complejo = new ComplexNumber(13,1);
        ComplexNumber complejo2 = new ComplexNumber(1,-3);
        assertEquals("No corresponde a la parte real",1,complejo.division(complejo2).realPart(),0.00001);
        assertEquals("No corresponde a la parte real",4,complejo.division(complejo2).imaginaryPart(),0.00001);
    }
    @Test
    public void conjugateTest(){
        ComplexNumber complejo = new ComplexNumber(8,-2);
        assertEquals("No corresponde a la parte real",8,complejo.conjugate().realPart(),0.00001);
        assertEquals("No corresponde a la parte imaginaria",2,complejo.conjugate().imaginaryPart(),0.00001);
    }
    @Test
    public void moduleTest(){
        ComplexNumber complejo = new ComplexNumber(4,-3);
        assertEquals("No corresponde a la parte real",5,complejo.module(),0.00001);
    }
    
    
}
