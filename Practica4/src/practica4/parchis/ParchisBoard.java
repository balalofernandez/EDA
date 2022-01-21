package practica4.parchis;

import material.graphs.ALDigraph;
import material.graphs.Digraph;
import material.graphs.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvelez
 */
public class ParchisBoard {
    
    public enum Color {red,green,blue,yellow};
    public enum SquareType {Normal, Home, Destiny};

    private ALDigraph<Casilla, Integer> tablero;

    class Casilla{
        Color color;
        int numero;
        SquareType tipo;
        ArrayList<ParchisChip> fichas;

        public Casilla(Color color, int numero, SquareType tipo) {
            this.color = color;
            this.numero = numero;
            this.tipo = tipo;
            this.fichas = new ArrayList<>(2);
        }

        public Casilla(Color color, int numero, SquareType tipo, ArrayList<ParchisChip> fichas) {
            this.color = color;
            this.numero = numero;
            this.tipo = tipo;
            this.fichas = fichas;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public SquareType getTipo() {
            return tipo;
        }

        public void setTipo(SquareType tipo) {
            this.tipo = tipo;
        }

        public ArrayList<ParchisChip> getFichas() {
            return fichas;
        }

        public void setFichas(ArrayList<ParchisChip> fichas) {
            this.fichas = fichas;
        }
    }
    /**
     * Crea un tablero de parchis con todas sus casillas y con 4 piezas de cada
     * color en sus casas correspondientes.
     */
    public ParchisBoard() {
        tablero = new ALDigraph<>();
        Vertex<Casilla> anterior = null;
        Vertex<Casilla>  verticeB= null,verticeR= null,verticeG= null,verticeY= null,vertice = null;
        for (int i = 1; i <= 68; i++) {
            Casilla c = new Casilla(null,i,SquareType.Normal);
            if(i == 5 || i==12|| i==17|| i==22|| i==29|| i==34|| i==39|| i==46|| i==51|| i==56|| i==63|| i==68){
                c.setTipo(SquareType.Home);
            }
            if(i == 5) c.setColor(Color.yellow);
            if(i == 22) c.setColor(Color.blue);
            if(i == 39) c.setColor(Color.red);
            if(i == 56) c.setColor(Color.green);
            if(i == 17){
                verticeB = tablero.insertVertex(c);
                vertice = verticeB;
            }
            else if(i == 34){
                verticeR = tablero.insertVertex(c);
                vertice = verticeR;

            }else if(i == 51){
                verticeG = tablero.insertVertex(c);
                vertice = verticeG;

            }else if(i == 68){
                verticeY = tablero.insertVertex(c);
                vertice = verticeY;

            }else{
                vertice = tablero.insertVertex(c);
            }
            if(i != 1 && i != 68) tablero.insertEdge(anterior,vertice,1);
            anterior = vertice;
        }
        //Color Amarillo
        for (int i = 69; i <= 77; i++) {
            Casilla c = new Casilla(Color.yellow,i,SquareType.Destiny);
            vertice = tablero.insertVertex(c);
            if(i == 69){
                tablero.insertEdge(verticeY,vertice,1);
            }
            else{
                tablero.insertEdge(anterior,vertice,1);
            }
            anterior = vertice;
        }
        //Color Azul
        for (int i = 78; i <= 86; i++) {
            Casilla c = new Casilla(Color.blue,i,SquareType.Destiny);
            vertice = tablero.insertVertex(c);
            if(i == 78){
                tablero.insertEdge(verticeB,vertice,1);
            }
            else{
                tablero.insertEdge(anterior,vertice,1);
            }
            anterior = vertice;
        }
        //Color Rojo
        for (int i = 87; i <= 95; i++) {
            Casilla c = new Casilla(Color.red,i,SquareType.Destiny);
            vertice = tablero.insertVertex(c);
            if(i == 87){
                tablero.insertEdge(verticeR,vertice,1);
            }
            else{
                tablero.insertEdge(anterior,vertice,1);
            }
            anterior = vertice;
        }
        //Color Verde
        for (int i = 96; i <= 104; i++) {
            Casilla c = new Casilla(Color.green,i,SquareType.Destiny);
            vertice = tablero.insertVertex(c);
            if(i == 96){
                tablero.insertEdge(verticeG,vertice,1);
            }
            else{
                tablero.insertEdge(anterior,vertice,1);
            }
            anterior = vertice;
        }
    }
            
    /**
     * Devuelve una referencia a una ficha contenida en la casilla indicada 
     * por squeareName y cuyo color se corresponde con el color indicado.
     * @param squareNumber número de 0 a 68. 
     *                     - El número de las casillas normales varia de 1 a 68.
     *                     - El número de las casillas destino varia de 1 a 9.
     *                     - El número de las casillas home simpre es 0.
     * @param type tipo de la casilla.
     * @param color de la ficha a devolver.
     * @return Si en la casilla no está la ficha devuelve null, en otro caso 
     * devuelve la ficha obtenida.
     */
    ParchisChip getChip(int squareNumber, SquareType type, Color color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Mueve la ficha c el número de posiciones indicado por n. Si la ficha 
     * llega a la casilla de entrada a casa debe tomar el camino correspondiente.
     * @param c el color
     * @return Si la ficha cae en una casilla en la que hay una ficha 
     * de otro color (y no es segura) devuelve la otra ficha (que se ha comido)
     * y que automáticamente se habrá ido a la casa de su color.
     * En otro caso devuelve null.
     * Si el movimiento no fue posible (por haber un puente en el destino ) 
     * lanzará una excepción.
     */
    ParchisChip move(ParchisChip c, int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param c la ficha a mover
     * @param n el número de casillas a mover.
     * @return Devuelve true si el movimiento es posible y false en caso contraio.
     */
    boolean canMove(ParchisChip c, int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return Devuelve una cadena con las casillas ocupadas del tablero y su contenido.
     */
    String drawBoard() {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
