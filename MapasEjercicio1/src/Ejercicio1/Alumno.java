package Ejercicio1;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Alumno {
    private String nombre;
    private String dni;

    public Alumno(){
        nombre = "";
        dni = "";
    }
    public Alumno(String n,String dni){
        this.nombre = n;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return this.dni.equals(alumno.getDni()) && this.nombre.equals(alumno.getNombre());
    }

    @Override
    public int hashCode() {
        //return Objects.hash(nombre, dni);
        return this.dni.getBytes(StandardCharsets.UTF_8).length + this.nombre.getBytes(StandardCharsets.UTF_8).length;
    }
}
