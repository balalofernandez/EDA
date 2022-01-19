
import com.sun.org.apache.bcel.internal.generic.LLOAD;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;


/**
 *
 * @author mayte
 */
public class ReferenciasCruzadas {
    TreeMap<String, LinkedList<Integer>> mapa;
    /**
    * Builds an ordered dictionary from a file
    * 
    * @param fichero
    * @throws java.io.IOException
    */
    public ReferenciasCruzadas (FileReader fichero) throws IOException{
        Scanner sc   =   new   Scanner(fichero).useDelimiter("\\`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\)|\\+|\\=|\\[|\\{|\\]|\\}|\\||\\\\|\\'|\\<|\\,|\\.|\\ >|\\?|\\/|\\\"\"|\\;|\\:|\\s+");
         mapa =  new TreeMap<>();
        int contador = 1;
        while (sc.hasNext()){
            String palabra = sc.next();
            if(palabra.equals(" ")) continue;
            if(palabra.equals("")) continue;
            if(!mapa.containsKey(palabra)){
                LinkedList<Integer> lista = new LinkedList<>();
                lista.add(contador);
                mapa.put(palabra,lista);
            }else{
                mapa.get(palabra).add(contador);
            }
            contador++;
        }
    }
    
    /**
    * Returns the list of indexes that the word occupies in the text with which the dictionary has been built. 
    * If the word does not belong to the file returns null
    * @param word     
    * @return      
    */
    public List<Integer> apariciones(String word){
        return mapa.get(word);
    }
    
}
