
package practica1;

/**
 *
 * @author mayte
 */
public class FendTree {

    int[] FTree;
    /**
     * Builds a Fendwick Tree with the array that receives
     * 
     * @param array
     */
    public FendTree(int [] array){
        FTree = new int[array.length+1];
        FTree[0] = 0;
        for(int i=1; i< FTree.length;i++){
            //Calculo cual es su padre
            int y = i-(i&(-i));
            //Ahora sumamos el intervalo del padre al hijo
            int suma = 0;
            for(int j = y; j<i;j++){
                suma += array[j];
            }
            FTree[i] = suma;
        }
    }
    
    /**
     * Receives an index and returns the partial sum from zero to that index.
     * An exception is thrown if the index is less than zero or greater than or equal to n.
     * @param index
     * @return 
     */
    public int getSum(int index){
        int valor = 0;
        int x = index+1;
        while (x >0){
            valor += FTree[x];
            x=x-(x&(-x));
        }
        return valor;
    }
 
    /**
     * Update the value of the position index in the array.
     * 
     * @param index
     * @param val
     */
    public void upDate (int index, int val){
        if(index<0 || index> FTree.length){
            throw new IndexOutOfBoundsException();
        }
        int x = index+1;
        while (x< FTree.length){
            FTree[x] +=val;
            x = x + (x &(-x));
        }
    }
    
}
