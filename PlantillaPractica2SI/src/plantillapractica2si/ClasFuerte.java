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
    
    public boolean Test (float v, Cara c){
        
        boolean sol=false;
        
        //si es mayor que 0 y es cara acierto
        //si es menor que 0 y es no cara acierto
        //si es mayor que 0 y no cara fallo
        //si es menor que 0 y cara fallo
        if(v > 0){
            if(c.getTipo()==1){
                    
                sol=true;
            }
        }
        else{
            if(c.getTipo()==-1){
                    
                sol=true;
            }
        }
        
        return sol;
    }
    
    public Hiperplano Adaboost(ArrayList<Cara> listaAprendizaje, int clasificadores, int[] max, int[] min){
        
        Hiperplano solucion= new Hiperplano();
        boolean encontrado=false;
        //vector de pesos
        float [] Peso = new float[listaAprendizaje.size()];
        int tamaño= listaAprendizaje.size();
        //inicializo el vector para normalizarlo entre su tamaño
        for(int i=0; i<tamaño; i++){
            
            Peso[i]= (float) 1/tamaño;
        }
        
        for(int i=0; i<iteraciones && encontrado==false; i++){
            
            ClasDebil aux= new ClasDebil(clasificadores, max, min);
            solucion=aux.Aprender(listaAprendizaje);
            
            for(int j=0; j<Peso.length; j++){
                
                float varC=aux.ObtenerC(solucion, listaAprendizaje.get(j));
                //lo comparamos con la c del hiperplano
                float resta;
                resta=varC - solucion.getC();
                //Nos dira si acierta o no
                if(!Test(resta, listaAprendizaje.get(j))){
                    
                    //sumamos la tasa de error
                    //DUDA EXISTENCIA
                    //LA TASA DE ERROR ES DE UN HIPERPLANO COMPLETO
                    //NO DE UN PUNTO ENTONCS QUE SUMAMOS EN CADA ITERACION
                    //QUE FALLE
                }
            }
            
            
            
        }
        
        return solucion;
    }
    
}
