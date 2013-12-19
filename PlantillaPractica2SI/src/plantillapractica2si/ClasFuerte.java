/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plantillapractica2si;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author alberto
 */
public class ClasFuerte {
    
    public ArrayList<ClasDebil> Mejores;
    private int iteraciones;
    
    public ClasFuerte(int i){
        Mejores= new ArrayList<ClasDebil>();
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
    
    public int Test (Hiperplano h, Cara c){
        
        int sol=0;
        
        float varC=ObtenerC(h, c);
        float resta=varC - h.getC();
        
        //si es mayor que 0 y es cara acierto
        //si es menor que 0 y es no cara acierto
        //si es mayor que 0 y no cara fallo
        //si es menor que 0 y cara fallo
        if(resta > 0){
            sol=1;
        }
        else{
            sol=-1;
        }
        
        return sol;
    }
    public int determinarCara(Cara c)
    { 
        double res = 0.0;
        // A partir de todos los clasificadores que tengo, para actualizar el fuerte
        for(ClasDebil cDebil: this.Mejores){
            
            res += cDebil.getValorConfianza() * Test(cDebil.Mejor, c);
        }
        // Obtengo donde se encuentra contenido en el plano
        if(res < 0.0){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    public ArrayList<ClasDebil> Adaboost(ArrayList<Cara> listaAprendizaje, int clasificadores, 
            int[] max, int[] min){
        
        double clasificacion=0;
        //vector de pesos
        double [] Peso = new double[listaAprendizaje.size()];
        int tamaño= listaAprendizaje.size();
        //inicializo el vector para normalizarlo entre su tamaño
        for(int i=0; i<tamaño; i++){
            
            Peso[i]= (float) 1/tamaño;
        }
        
        for(int i=0; i<iteraciones; i++){
            
            ClasDebil cl = new ClasDebil(clasificadores, max, min);
            //entrenamos el clasificador debil con la tasa de error
            cl.Aprender(listaAprendizaje);
            //con la tasa de error calculamos alfa
            double alfa=0.5 * (Math.log((1-cl.Mejor.getTasaError())/cl.Mejor.getTasaError()));
            cl.setValorConfianza(alfa);
            Mejores.add(cl);
            //acualizamos el vector de pesos
            double suma=0;
            int j;
            for(j=0; j<tamaño-1; j++){
                
                
                if(Test(cl.Mejor, listaAprendizaje.get(j))==listaAprendizaje.get(j).getTipo()){
                    //clasifica bien
                    Peso[j+1]= Peso[j] * Math.pow(Math.E, (alfa));
                }
                else{
                    //clasifica mal
                    Peso[j+1]= Peso[j] * Math.pow(Math.E, (-alfa));
                }
                suma+=Peso[j];
            }
            //normalizamos el vector de pesos
            for(int k=0;k<tamaño; k++){
                
                Peso[k]= (Peso[k]/suma);
            }
            //actualizamos el clasificador fuerte
            int aciertos = 0;
            int clase;
            for(Cara c: listaAprendizaje)
            {
                clase=this.determinarCara(c);
                if(clase == c.getTipo()){
                    aciertos++;
                }
            }
            System.out.println("APRENDIZAJE. Tasa de aciertos: "+((float)aciertos/(float)(listaAprendizaje.size())*100.0f)+"%");
            //calcular condicion y devolver
            if(aciertos==listaAprendizaje.size()){
                
                return Mejores;
            }
        }
        
        return Mejores;
    }
    public void imprimirClas(){
        
        FileWriter f = null;
        PrintWriter pw = null;
        try
        {
            f = new FileWriter("prueba.txt");
            pw = new PrintWriter(f);
 
            for (int i = 0; i < Mejores.size(); i++){
                pw.println(Mejores.get(i).Mejor.PuntotoString());
                pw.println(Mejores.get(i).Mejor.VectortoString());
            }
            
            f.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}