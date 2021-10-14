/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeExceptions;

/**
 *
 * @author balalo
 */
public class InvalidPositionException extends Throwable{
    public InvalidPositionException(String message){
        super(message);
    }
}
