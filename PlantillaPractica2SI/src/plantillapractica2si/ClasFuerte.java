/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plantillapractica2si;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class ClasFuerte {
    
    public Hiperplano Mejor;
    private int iteraciones;
    
    public ClasFuerte(int i){
        
        iteraciones= i;
    }
    
    public Hiperplano Adaboost(ArrayList<Cara> listaAprendizaje, int clasificadores, int[] max, int[] min){
        
        Hiperplano solucion= new Hiperplano();
        boolean encontrado=false;
        float [] Peso = new float[listaAprendizaje.size()];
        int tamaño= listaAprendizaje.size();
        for(int i=0; i<tamaño; i++){
            
            Peso[i]= (float) 1/tamaño;
        }
        
        for(int i=0; i<iteraciones && encontrado==false; i++){
            
            ClasDebil aux= new ClasDebil(clasificadores, max, min);
            solucion=aux.Aprender(listaAprendizaje);
            
            
            
        }
        
        return solucion;
    }
    
}
