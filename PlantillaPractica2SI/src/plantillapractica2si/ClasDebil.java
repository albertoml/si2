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
public class ClasDebil {
    
    public ArrayList<Hiperplano> Hiperplanos;
    private int Clasificadores;
    
    
    public ClasDebil(int c, int max, int min){
        
        int i;
        
        Clasificadores=c;
        Hiperplanos= new ArrayList();
        
        for(i=0;i<Clasificadores;i++){
            
            Hiperplanos.add(new Hiperplano(max, min));
        }
    }
    
    
    
}
