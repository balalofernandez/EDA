/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose.velez
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> p = new LinkedList<>();
        p.add("Uno");
        p.add("Dos");
        p.add("Tres");
        p.add("Cuatro");
        p.add("Cinco");
        Iterator<String> i = p.iterator();
        int n = 5;
        while(i.hasNext() && n>0){
            String s = i.next();
            System.out.println(s);
            n--;
        }
        
//        Game p = new Game();
//        p.play();
    }
    
}
