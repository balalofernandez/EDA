package ExamenesAnteriores.ExamenJulio1920;

import material.Position;
import material.linear.Queue;
import material.tree.Tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SumBinaryLevels {

    public SumBinaryLevels() {
    }

    public int sumLevels(Tree<Integer> tree, List<Integer> niveles){
        LinkedList<Position<Integer>> cola = new LinkedList<Position<Integer>>();
        LinkedList<Position<Integer>> siguienteLvl = new LinkedList<Position<Integer>>();
        int suma =0;
        int i = -1;
        siguienteLvl.add(tree.root());
        cola.add(tree.root());
        Collections.sort(niveles);
        while (!siguienteLvl.isEmpty()){
            siguienteLvl = new LinkedList<>();
            while(!cola.isEmpty()){
                Position<Integer> p = cola.pop();
                if(niveles.contains(i)){
                    suma += p.getElement();
                }
                for(Position<Integer> pos : tree.children(p)){
                    siguienteLvl.add(pos);
                }
            }
            i++;
            cola = siguienteLvl;
            if(niveles.contains(i)) {
                niveles.remove(i);//Para disminuir la complejidad
            }else if(niveles.isEmpty()){
                return suma;
            }
        }
        return suma;
    }
}
