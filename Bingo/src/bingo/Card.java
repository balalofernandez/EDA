/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.*;

/**
 *
 * @author jose.velez
 */
class Card {
    private HashSet<Integer>[] lines;
    
    public Card(){
        HashSet<Integer> allNumbers = new HashSet<>();
        lines = new HashSet[3];//We have 3 rows
        int number;
        for(int i=0; i<3; i++){
            lines[i] = new HashSet<>();
            for(int j=0; j<5;j++){
                number = ((int)(Math.random()*90))+1;
                while(!allNumbers.add(number)){
                    number = ((int)(Math.random()*90))+1;
                }
                lines[i].add(number);
            }
        }
    }

    @Override
    public String toString() {
        String s ="";
        for(int i=0; i<3;i++){
            s += lines[i].toString();
        }
        return s;
    }
    
    
    
    
}
