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
    
    public float []Punto;
    public float []Vector;
    private static int Dimension=576;
    private static float MaxVector=1;
    private static float MinVector=-1;
    
    public Hiperplano(float MaxPunto, float MinPunto){
        
        int i;
        for (i=0;i<Dimension;i++){
            
            Punto[i]=(float)(Math.random()*(MaxPunto-MinPunto))+MinPunto;
            Vector[i]=(float)(Math.random()*(MaxVector-MinVector))+MinVector;    
        }
    }
}
