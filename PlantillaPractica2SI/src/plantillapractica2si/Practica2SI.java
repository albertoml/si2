/*
 * CLASIFICADOR DEBIL
 * GENERAR PLANO PUNTO Y VECTOR
 * generar PUNTO 576 dim mirar min y max del conjunto de aprendizaje
 * para definir el val aleatorio
 * generar VECTOR  obtener direccion
 * definir un vector de 576 elem definido entre -1 y 1 aleatorio para especificar direccion
 * evitar problemas normalizar el vector sumar sus componentes y dividir entre la suma
 * para k la suma valga 1
 * 
 * CLASIFICAR
 * necesitamos un valor generar HIPERPLANO sumatorio (p(i)*v(i)) - C cierto valor
 * C= sum(p(i)*v(i))
 * 
 * CLASIFICACION
 * Cojer como punto cada vector de la imagen con nuestro vector direccion y nuestra C
 * Para clasificar sum(v(i)*pimg(i))-C si el res>0 una parte del plano
 *                                     si el res<0 otra parte del plano
 * 
 * comprobar si acierta o no y ver la tasa de aciertos
 * repetir el proceso las veces que indique el parametro del programa
 * y quedarnos con el mejor hiperplano, el que mas tasa de aciertos de.
 * 
 * 2 SEMANAS (HECHO A FALTA DE LEER EL GUION DE LA PRACTICA A VER SI PIDEN ESO
 *              PERO FUNCIONAR FUNCIONA)
 * 
 * CLASIFICADOR FUERTE
 * DESCRIPCION EN EL GUION DE LA PRACTICA 
 * 3 SEMANAS
 * 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plantillapractica2si;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dviejo
 */
public class Practica2SI {

    private String rutaDir;
    private File []files;
    private int NUM_ITERACIONES; 
    private int NUM_CLASIFICADORES;
    private boolean VERBOSE;

    private double testRate;

    private ArrayList<Cara> listaAprendizaje;
    private ArrayList<Cara> listaTest;

    public Practica2SI()
    {
        rutaDir = "";
        testRate = 0.8;
	NUM_ITERACIONES = 1;
	NUM_CLASIFICADORES = 1;
        VERBOSE = false;
    }
    
    public void init()
    {
        int cont;
	int aciertos, clase;
	System.out.println("Sistemas Inteligentes. Segunda practica");
		
	getFileNames(rutaDir+"cara/");
	listaAprendizaje = new ArrayList<Cara>();
	for(cont=0;cont<files.length;cont++)
	{
		if(!files[cont].isDirectory())
		{
			listaAprendizaje.add(new Cara(files[cont],1));
		}
	}
	getFileNames(rutaDir+"noCara/");
	for(cont=0;cont<files.length;cont++)
	{
		if(!files[cont].isDirectory())
		{
			listaAprendizaje.add(new Cara(files[cont], -1));
		}
	}
	System.out.println(listaAprendizaje.size()+ " imágenes encontradas");
		
	//inicializamos las listas
	listaTest = new ArrayList<Cara>();
		
	//Separamos los conjuntos de aprendizaje y test
	CrearConjuntoAprendizajeyTest();
	System.out.println(listaAprendizaje.size()+" imagenes para aprendizaje, "+listaTest.size()+" imagenes para el test ("+(testRate*100)+"%)");
        
        int max[];
        max=BuscarMax(listaAprendizaje);
        int min[];
        min=BuscarMin(listaAprendizaje);
	//Comenzamos el aprendizaje
	long t1 = System.currentTimeMillis();
        //TODO Aquí debéis poner vuestra llamada al método de entrenamiento de AdaBoost
        //ClasDebil miclasi = new ClasDebil(NUM_CLASIFICADORES, max, min);
        //miclasi.Aprender(listaAprendizaje);
	long t2 = System.currentTimeMillis();
	long time;
        
        //Crear hiperplanos 
        //LLAMAR A MI CLASIFICADOR DEBIL
        
        //miclasi.Mejor.Testear(listaTest);
        
        //LLAMAR A MI CLASIFICADOR FUERTE
        ClasFuerte miclasi = new ClasFuerte(NUM_ITERACIONES);
        miclasi.Adaboost(listaAprendizaje, NUM_CLASIFICADORES, max, min);
        //miclasi.imprimirClas();  
        
	time = t2 - t1;
	System.out.println("Tiempo empleado en el aprendizaje: "+((float)time/1000f)+" segundos");
	System.out.println("Número de clasificadores encontrados: " + NUM_CLASIFICADORES);
        //TODO añadir el valor puesto arriba
	//Test final
        /*if(VERBOSE)
        {
            aciertos = 0;
            for(Cara c: listaAprendizaje)
            {
                clase = -1;  //TODO Cambiar -1 por la llamada a clasificar utilizando el clasificador fuerte
                        //de Adaboost para el ejemplo c
                if(clase == c.getTipo()){
                    aciertos++;
                }
            }
            System.out.println("APRENDIZAJE. Tasa de aciertos: "+((float)aciertos/(float)(listaAprendizaje.size())*100.0f)+"%");
        }
        
	//Comprobamos el conjunto de test
	aciertos = 0;
	for(Cara c: listaTest)
	{
		clase = -1;  //TODO Cambiar -1 por la llamada a clasificar utilizando el clasificador fuerte
                            //de Adaboost para el ejemplo c
		if(clase == c.getTipo()){
			aciertos++;
                }
	}
	System.out.println("TEST. Tasa de aciertos: "+((float)aciertos/(float)(listaTest.size())*100.0f)+"%");*/
        
         
    }
    
    public int[] BuscarMax(ArrayList<Cara> a){
        
        int sol[]=new int [a.get(0).getData().length];
        
        for(int i=0; i<a.size(); i++){
            
            int aux[]=a.get(i).getData();
            
            for(int j=0; j<aux.length ; j++){
                
                if(aux[j]>sol[j]){
                    
                    sol[j]=aux[j];
                } 
            }       
        }
        return sol;
    }
    public int[] BuscarMin(ArrayList<Cara> a){
       
        int sol[]=new int[a.get(0).getData().length];
        
        for(int i=0; i<a.size(); i++){
            
            int aux[]=a.get(i).getData();
            
            for(int j=0; j<aux.length ; j++){
               
                if(aux[j]<sol[j]){
                    
                    sol[j]=aux[j];
                }      
            }       
        }
        return sol;
    }
    
    /**
     * Selecciona al azar un subconjunto para Test. El resto compondrá el conjunto de aprendizaje
     */
    private void CrearConjuntoAprendizajeyTest()
    {
    	int totalTam = listaAprendizaje.size();
	int tamTest = (int)(totalTam * testRate);
	int cont;
	Random rnd = new Random(System.currentTimeMillis());
		
	for(cont=0;cont<tamTest;cont++)
	{
            listaTest.add(listaAprendizaje.remove(rnd.nextInt(totalTam)));
            totalTam--;
	}
    }
    
    public void getFileNames(String ruta)
    {
    	File directorio = new File(ruta);
	if (!directorio.isDirectory())
            throw new RuntimeException("La ruta debe ser un directorio");
	ImageFilter filtro = new ImageFilter();
	files = directorio.listFiles(filtro);
    }

	public void setRuta(String r)
	{
		rutaDir = r;
	}
	
	public void setRate(double t)
	{
		testRate = t;
	}
	
	public void setNumIteraciones(int t)
	{
		NUM_ITERACIONES = t;
	}
	public void setNumClasificadores(int c)
	{
		NUM_CLASIFICADORES = c;
	}

	public void setVerbose(boolean v)
	{
		VERBOSE = v;
	}
	
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		int cont;
		Practica2SI programa;
		String option;
		boolean maluso = true;
		int paso = 2;
		
		programa = new Practica2SI();

		for(cont = 0; cont < args.length; cont+=paso)
		{
			option = args[cont];
			if(option.charAt(0) == '-')
			{
				switch(option.charAt(1))
				{
				case 'd':
					programa.setRuta(args[cont+1]);
					paso = 2;
                                        maluso = false;
					break;
				case 't':
					programa.setRate(Double.parseDouble(args[cont+1]));
					paso = 2;
					break;
				case 'T':
					programa.setNumIteraciones(Integer.parseInt(args[cont + 1]));
					paso = 2;
					break;
				case 'c':
					programa.setNumClasificadores(Integer.parseInt(args[cont + 1]));
					paso = 2;
					break;
				case 'v':
					programa.setVerbose(true);
					paso = 1;
					break;
				default:
					maluso = true;
				}
			}
			else maluso = true;
		}
		
		if(!maluso)
			programa.init();
		else
		{
			System.out.println("Lista de parametros incorrecta");
			System.out.println("Uso: java Practica2SI -d ruta [-t testrate] [-T maxT] [-c numClasificadores] [-v]");
		}
    }
}
