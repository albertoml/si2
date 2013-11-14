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
    public Hiperplano Mejor;
    private int Clasificadores;
    
    public ClasDebil(int clas){
        
        int i;
        
        Clasificadores=clas;
        Hiperplanos= new ArrayList();
        
        for(i=0;i<Clasificadores;i++){
            
            Hiperplanos.add(new Hiperplano());
        }
    }
    
    
    public ClasDebil(int clas, int max[], int min[]){
        
        int i;
        
        Clasificadores=clas;
        Hiperplanos= new ArrayList();
        
        for(i=0;i<Clasificadores;i++){
            
            Hiperplanos.add(new Hiperplano(max, min));
        }
    }
    
    public Hiperplano Aprender(ArrayList<Cara> aprender){
        
        Hiperplano solucion;
        int[] sol= new int[Hiperplanos.size()];
        //solo para probar su correcto funcionamiento
        int[] fail= new int[Hiperplanos.size()];
        float varC;
        float resta;
        
        for (int i=0; i<Hiperplanos.size(); i++){
            
            //probar hiperplano uno a uno
            int acierto=0, fallo=0;
            for(int j=0; j<aprender.size(); j++){
                
                varC=ObtenerC(Hiperplanos.get(i), aprender.get(j));
                //lo comparamos con la c del hiperplano
                resta=varC - Hiperplanos.get(i).getC();
                if(resta>0){
                    if(aprender.get(j).getTipo()==1){
                    
                        acierto++;
                    }
                    else{
                    
                        fallo++;
                    }
                }
                else{
                    if(aprender.get(j).getTipo()==-1){
                    
                        acierto++;
                    }
                    else{
                    
                        fallo++;
                    }
                }
            }
            sol[i]=acierto;
            fail[i]=fallo;
        }
        
        //Buscar mejor hiperplano
        System.out.println("Aprendizaje de Hiperplanos");
        System.out.println("Hiperplano    aciertos     fallos    Tasa aciertos");
        float division;
        int mejor=0;
        for(int i=0; i<Hiperplanos.size(); i++){
            
            if(sol[i]>sol[mejor]){
                mejor=i;
            }
            division=(float) sol[i]/aprender.size();
            System.out.println(i + "              " + sol[i] + "         " 
                    + fail[i] + "        " + division + "%");
        }
        solucion= Hiperplanos.get(mejor);
        Mejor=solucion;
        
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
    
    public void Testear(ArrayList<Cara> test){
        
        int acierto=0, fallo=0;
        float varC, resta;
        for(int j=0; j<test.size(); j++){
                
            varC=ObtenerC(Mejor, test.get(j));
            //lo comparamos con la c del hiperplano
            resta=varC - Mejor.getC();
            if(resta>0){
                if(test.get(j).getTipo()==1){
                    
                    acierto++;
                }
                else{
                    
                    fallo++;
                }
            }
            else{
                if(test.get(j).getTipo()==-1){
                    
                    acierto++;
                }
                else{
                    
                    fallo++;
                }
            }
        }
        System.out.println("Testear el mejor Hiperplano");
        System.out.println("aciertos     fallos    Tasa aciertos");
        float division;
            
        division=(float) acierto/test.size();
        System.out.println(acierto + "         " + fallo + "        " + division + "%");
    }
}
