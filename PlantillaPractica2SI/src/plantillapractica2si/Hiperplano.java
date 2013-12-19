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
public class Hiperplano {
    
    public int []Punto;
    public float []Vector;
    public double TasaError;
    public float C;
    private static int Dimension=576;
    private static float MaxVector=1;
    private static float MinVector=-1;
    
    public Hiperplano(){
        
        int i;
        Punto= new int[Dimension];
        Vector= new float[Dimension];
        TasaError=0;
        for (i=0;i<Dimension;i++){
            
            Punto[i]=(int)(Math.random()*255);
            Vector[i]=(float)(Math.random()*(MaxVector-MinVector))+MinVector;    
        }
        NormalizarVector();
        CalcularC();
    }
    
    public Hiperplano(int MaxPunto[], int MinPunto[]){
        
        int i;
        Punto= new int[Dimension];
        Vector= new float[Dimension];
        for (i=0;i<Dimension;i++){
            
            Punto[i]=(int)(Math.random()*(MaxPunto[i]-MinPunto[i]))+MinPunto[i];
            Vector[i]=(float)(Math.random()*(MaxVector-MinVector))+MinVector;    
        }
        NormalizarVector();
        CalcularC();
    }
    
    public void CalcularC(){
        
        float sum=0;
        for(int i=0; i<Dimension; i++){
            
            sum+= Punto[i]*Vector[i];
        }
        C= sum;
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
    
    public void NormalizarVector(){
        
        float sum=0;
        for(int i=0; i<Dimension; i++){
            
            sum+=Vector[i];
        }
        for(int i=0; i<Dimension; i++){
            
            Vector[i]= Vector[i]/sum;
        }
    }
     public void Testear(ArrayList<Cara> test){
        
        int acierto=0, fallo=0;
        float varC, resta;
        for(int j=0; j<test.size(); j++){
                
            varC=ObtenerC(this, test.get(j));
            //lo comparamos con la c del hiperplano
            resta=varC - this.getC();
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
        System.out.println("aciertos     fallos    Tasa aciertos");
        float division;
            
        division=(float) acierto/test.size();
        System.out.println(acierto + "         " + fallo + "        " + division + "%");
    }
     
     public boolean Test (Hiperplano h, Cara c){
        
        boolean sol=false;
        float varC=ObtenerC(h, c);
        float resta=varC - h.getC();
        
        //si es mayor que 0 y es cara acierto
        //si es menor que 0 y es no cara acierto
        //si es mayor que 0 y no cara fallo
        //si es menor que 0 y cara fallo
        if(resta > 0){
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
    
    public void SobreEntrenar(double [] Peso, ArrayList<Cara> lista){
        
        double tasaError=0, tasaMenor=1;
        float varC, resta;
        for(int j=0; j<lista.size(); j++){

            if(!Test(this, lista.get(j))){

                tasaError+= Peso[j];
            }
        }
        this.setTasaError(tasaError);
    }
    
    public float getC(){
        
        return C;
    }
    public int[] getPunto(){
        
        return Punto;
    }
    public float[] getVector(){
        
        return Vector;
    }
    public double getTasaError(){
        
        return TasaError;
    }
    public void setC(float c){
        
        C=c;
    }
    public void setPunto(int[] p){
        
        Punto=p;
    }
    public void setVector(float[] v){
        
        Vector=v;
    }
    public void setTasaError(double t){
        
        TasaError= t;
    }
    public String PuntotoString(){
        
        String sol="";
        for(int i=0;i<Punto.length;i++){
            sol+=Punto[i];
            sol+=" ";
        }
        return sol;
    }
    public String VectortoString(){
        
        String sol="";
        for(int i=0;i<Vector.length;i++){
            sol+=Vector[i];
            sol+=" ";
        }
        return sol;
    }
}
