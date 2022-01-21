package examenparcial;

import java.util.*;

import jdk.internal.loader.AbstractClassLoaderValue;
import material.dictionary.OAHashDictionary;
import material.utils.Pair;

/**
 *
 * @author mayte
 */
public class ClassroomOrganizer {

    HashMap<Integer,List<Integer>> mapa;//guardamos codigo asignatura y aula

    /**
     * Initializes the organiser
     */
    public ClassroomOrganizer(){
        mapa = new HashMap<>();
    }
    
    /**
     * Initializes the organiser
     * @param lista 
     */
    public ClassroomOrganizer(List<Pair<Subject, Integer>> lista) {
        mapa = new HashMap<>();
        for(Pair<Subject, Integer> par : lista){
            List<Integer> listares = mapa.get(par.getFirst().getCode());
            if(listares != null){
                listares.add(par.getSecond());
            }
            mapa.put(par.getFirst().getCode(),listares);
    }

    /**
     * Returns the list of classrooms in which the subject is imparted
     * @param Classroom identifier
     * @return 
     */
    public List<Integer> impartedSubject(Integer subject) {
        LinkedList<Integer> lista = new LinkedList<>();
        for(Integer aula:mapa.getAll(subject)){
            lista.add(aula);
        }
        if(lista.size() == 0){
            return null;
        }
        else{
            return lista;
        }

    }

    /**
     * Returns the classrooms in which the subject is imparted
     * @param subject Subject identifier
     * @return 
     */
    public Integer classroomAsigned(Integer subject) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }

    /**
     * Adds a new subject and a their list of classrooms to the classroom organizer
     * If the subject is already in the classroom organizer, it is a change of classrooms
     * @param Subject The subject
     * @param classrooms The list of classrooms
     * @return 
     */
    public void newSubject(Subject subject, List<Integer> classrooms) {
        for(Integer clss : classrooms){
            mapa.put(subject.getCode(),par.getSecond());
        }
    }

    /**
     * Adds a new subject and its classroom to the classroom organizer
     * @param subject The subject
     * @param classroom The classroom
     * @return 
     */
    public void newSubject(Subject subject, Integer classroom) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }

    /**
     * Adds a new classroom to the subject's classrooms
     * @param Subject The subject
     * @param classroom The classroom
     * @return 
     */
    public void addClassroom(Subject subject, Integer classroom) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }

    /**
     * Change the classroom in witch the subject is imparted
     * @param subject The subject
     * @param classroom The classroom
     * @return 
     */
    public void changeClassroom(Subject subject, Integer classroom) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }

    /**
     * Change the topic at index of the syllabus subject
     * @param subject The subject's code
     * @param topic
     * @param index
     * @return 
     */
    public void changeSyllabus(Integer subject, String topic, Integer index) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }

    /**
     * Prints the subject assigned to the given classroom
     * @param classroom
     * @return 
     */
    public void printSubject(Integer classroom) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }
    
    /**
     * Return true if the classroom is free, false otherwise
     * @param classroom
     * @return 
     */
    public boolean freeClassroom(Integer classroom) {
      
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
        
    }
    
    
}
