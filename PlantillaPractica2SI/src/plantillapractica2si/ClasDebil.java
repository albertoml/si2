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
    
    
    public ClasDebil(int clas, int max[], int min[]){
        
        int i;
        
        Clasificadores=clas;
        Hiperplanos= new ArrayList();
        
        for(i=0;i<Clasificadores;i++){
            
            Hiperplanos.add(new Hiperplano(max, min));
        }
    }
    
    public Hiperplano Testear(ArrayList<Cara> test){
        
        Hiperplano solucion= new Hiperplano();
        int[] sol= new int[Hiperplanos.size()];
        float varC;
        
        for (int i=0; i<Hiperplanos.size(); i++){
            
            //probar hiperplano uno a uno
            int acierto=0;
            for(int j=0; j<test.size(); j++){
                
                varC=ObtenerC(Hiperplanos.get(i), test.get(j));
                //lo comparamos con la c del hiperplano
                
                if(varC>Hiperplanos.get(i).getC()){
                    
                    acierto++;
                }
            }
            sol[i]=acierto;
        }
        
        //Buscar mejor hiperplano
        for(int i=0; i<Hiperplanos.size(); i++){
            
            System.out.print(sol[i] + " ");
        }
        
        
        return solucion;
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
    /*Para clasificar sum(v(i)*pimg(i))-C si el res>0 una parte del plano
            si el res<0 otra parte del plano*/
    
    
}
