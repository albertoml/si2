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
    
    public float ObtenerC(Hiperplano h, Cara c){
        
        float sol=0;
        int [] auxPunto= c.getData();
        float [] auxVector= h.getVector();
        
        for(int i=0; i<auxPunto.length; i++){
            
            sol+= auxPunto[i]*auxVector[i];
        }
        
        return sol;
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
    
    public Hiperplano Entrenar(int clasificadores, ArrayList<Cara> aprender, int max[], 
            int min[], double peso[]){
        
        float varC, resta;
        double tasaError, tasaMenor=1;
        
        Hiperplano solucion = new Hiperplano();
        ArrayList<Hiperplano> Hiperplanos = new ArrayList();
        for(int i=0; i<clasificadores; i++){
            
            Hiperplanos.add(new Hiperplano(max, min));
        }
        
        for (int i=0; i<Hiperplanos.size(); i++){
            
            //probar hiperplano uno a uno
            tasaError=0;
            for(int j=0; j<aprender.size(); j++){
                
                varC=ObtenerC(Hiperplanos.get(i), aprender.get(j));
                resta=varC - Hiperplanos.get(i).getC();
                
                if(!Test(resta, aprender.get(j))){
                    
                    tasaError+= peso[j];
                }
            }
            Hiperplanos.get(i).setTasaError(tasaError);
            if(tasaError<tasaMenor){
                
                tasaMenor=tasaError;
                solucion=Hiperplanos.get(i);
            }
        }
        
        return solucion;
    }
    
    public Hiperplano Adaboost(ArrayList<Cara> listaAprendizaje, int clasificadores, 
            int[] max, int[] min){
        
        Hiperplano solucion= new Hiperplano();
        boolean encontrado=false;
        //vector de pesos
        double [] Peso = new double[listaAprendizaje.size()];
        int tamaño= listaAprendizaje.size();
        //inicializo el vector para normalizarlo entre su tamaño
        for(int i=0; i<tamaño; i++){
            
            Peso[i]= (float) 1/tamaño;
        }
        
        for(int i=0; i<iteraciones && encontrado==false; i++){
            
            //entrenamos el clasificador debil con la tasa de error
            solucion=Entrenar(clasificadores, listaAprendizaje, max, min, Peso);
            //con la tasa de error calculamos alfa
            double alfa= 1/2 * (Math.log((1-solucion.getTasaError())/solucion.getTasaError()));
            //acualizamos el vector de pesos
            float varC, resta;
            double suma=0;
            int j;
            for(j=0; i<tamaño-1; j++){
                
                varC=ObtenerC(solucion, listaAprendizaje.get(j));
                resta=varC - solucion.getC();
                if(Test(resta, listaAprendizaje.get(j))){
                    //clasifica bien
                    Peso[j+1]= Peso[j] * Math.pow(Math.E, (alfa));
                }
                else{
                    //clasifica mal
                    Peso[j+1]= Peso[j] * Math.pow(Math.E, (-alfa));
                }
                suma+=Peso[j];
            }
            suma+=Peso[j+1];
            //normalizamos el vector de pesos
            for(int k=0;k<tamaño; k++){
                
                Peso[k]= (Peso[k]/suma);
            }
            //actualizamos el clasificador fuerte
            
                //NO ENTIENDO FORMULA PREGUNTAR
                //DUDA COMO CREAR LOS HIPERPLANOS A PARTIR DE ESTE PARA
                //MINIMIZAR LA TASA DE ERROR YA QUE ESTOS SE CREAN ALEATORIOS
                //A PARTIR DE MAX Y MIN
            
            //calcular condicion y devolver
            if(solucion.getTasaError()==0){
                
                return solucion;
            }
        }
        
        return solucion;
    } 
}
