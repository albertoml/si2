/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plantillapractica2si;

/**
 *
 * @author alberto
 */
public class Hiperplano {
    
    public int []Punto;
    public float []Vector;
    public double TasaError;
    public double alfa;
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
    
    public void NormalizarVector(){
        
        float sum=0;
        for(int i=0; i<Dimension; i++){
            
            sum+=Vector[i];
        }
        for(int i=0; i<Dimension; i++){
            
            Vector[i]= Vector[i]/sum;
        }
    }
    
    public float getC(){
        
        return C;
    }
    public double getAlfa(){
        
        return alfa;
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
    public void setAlfa(double a){
        
        alfa=a;
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
}
