package Ejercicio1;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        HashMap<Alumno,Integer> mapa = new HashMap<>();
        mapa.put(new Alumno("Andrés","11111111G"),4);
        mapa.put(new Alumno("Andrés","11111211G"),5);
        mapa.put(new Alumno("Valentina","52345421R"),2);
        mapa.put(new Alumno("Mia","52345482R"),9);
        mapa.put(new Alumno("Amaia","52345482R"),8);
        mapa.put(new Alumno("Ian","52345482R"),7);
        mapa.put(new Alumno("Ian","52345482R"),10);
        int a = mapa.get(new Alumno("Ian","52345482R"));
        System.out.print(a);

    }
}