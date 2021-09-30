/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexnumber;
import java.lang.Math;

/**
 *
 * @author balalo
 */
public class ComplexNumber {
    private double real;
    private double imaginario;
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
        // TODO code application logic here
    //}
    public ComplexNumber(){
        
    }
    public ComplexNumber(double real, double imaginario){
        this.real = real;
        this.imaginario = imaginario;
    }
    
    public double getReal() {
        return real;
    }

    public double getImaginario() {
        return imaginario;
    }
    public double realPart(){
        return real;
    }
    public double imaginaryPart(){
        return imaginario;
    }
    public ComplexNumber add(ComplexNumber c){
        return new ComplexNumber(real + c.getReal(), imaginario + c.getImaginario());
    }
    public ComplexNumber subtract(ComplexNumber c) {
        return new ComplexNumber(real - c.getReal(), imaginario - c.getImaginario());
    }
    public ComplexNumber multiply(ComplexNumber c){
        return new ComplexNumber(real * c.getReal()-imaginario * c.getImaginario(),
                imaginario * c.getReal()+real *c.getImaginario());
    }
    public ComplexNumber division(ComplexNumber c){
        return new ComplexNumber((real * c.getReal()+imaginario * c.getImaginario())/(Math.pow(c.getReal(), 2)+Math.pow(c.getImaginario(), 2)),
                (imaginario * c.getReal()-real *c.getImaginario())/(Math.pow(c.getReal(), 2)+Math.pow(c.getImaginario(), 2)));
    }
    public ComplexNumber conjugate(){
        return new ComplexNumber(real, -imaginario);
    }
    public double module(){
        return Math.sqrt(Math.pow(real, 2)+ Math.pow(imaginario, 2));
    }
    
    
    
    
}
