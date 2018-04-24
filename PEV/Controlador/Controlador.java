package Controlador;

import java.awt.EventQueue;
import java.util.Arrays;

import CrossingAlgorithms.BinaryMonocross;
import CrossingAlgorithms.CrossingAlgorithm;
import Elitismo.BasicElitismAlgorithm;
import Elitismo.ElitismAlgorithm;
import Mutation.MultipleMutation;
import Mutation.MutationAlgorithm;
import Mutation.OneMutation;
import Population.Chromosome;
import PopulationFactory.PopulationFactory;
import PopulationFactory.PopulationFactoryP1;
import PopulationFactory.PopulationFactoryP2;
import PopulationFactory.PopulationFactoryP3;
import PopulationFactory.PopulationFactoryP4;
import PopulationFactory.PopulationFactoryP5;
import Selection.DeterministicTournamentSelection;
import Selection.ProbabilisticTournamentSelection;
import Selection.ProportionalSelection;
import Selection.SelectionAlgorithm;
import Selection.StochasticSelection;
import Selection.TruncateSelection;
import Vista.Window;

public class Controlador {
	//Interfaz
	private static Window ventana;
	
	//Algoritmos que se utilizar�n en la ejecuci�n posterior. Depender�n de lo 
	//que introduzca el usuario.
	private static PopulationFactory p;
	private static SelectionAlgorithm sa;
	private static MutationAlgorithm ma;
	private static ElitismAlgorithm ea;
	private static CrossingAlgorithm ca;
	
	//Datos recogidos de la UI	
	
	private static String problema;
	private static int numProblema; //Lo usaremos en el caso de que problema==5.
	private static String seleccion;
	
	private static String tipoMutacion;
	
	private static int poblacion;
	private static int generaciones;
	
	private static double prob_mutacion;
	private static double prob_cruce;
	private static double precision;
	
	private static boolean elitismo;
	private static double prob_elitismo;
	
	private static double prob_trunc;
	private static double prob_peor;
	private static int tam_conjunto;
	
	//Constructor
	public Controlador(){
	}
	
	//Recoge los datos introducidos por el usuario para usarlos en un futuro.
	public static void recogerDatos(){
		
		problema=  ventana.problema.getSelectedItem().toString();
		numProblema= Integer.parseInt(ventana.numProblema.getSelectedItem().toString());
		seleccion= ventana.seleccion.getSelectedItem().toString();
		
		tipoMutacion=ventana.mutacion.getSelectedItem().toString();
		
		poblacion= Integer.parseInt(ventana.num_pob.getValue().toString());
		generaciones=  Integer.parseInt(ventana.num_generaciones.getValue().toString());
		
		prob_mutacion= Double.parseDouble(ventana.prob_muta.getValue().toString())/100;
		prob_cruce= Double.parseDouble(ventana.prob_cruce.getValue().toString())/100;
		precision=  Double.parseDouble(ventana.precision.getText().toString().trim());
		
		if(ventana.elitismo.isSelected()){	
			prob_elitismo= Double.parseDouble(ventana.num_elitismo.getValue().toString());
			elitismo=true;
        }else{
        	elitismo=false;
        }
	
		switch(ventana.seleccion.getSelectedItem().toString()){
        case "Ruleta":
        	break;
        case "Estocastico":
        	break;
        case "Truncamiento":
        	prob_trunc=Double.parseDouble(ventana.prob_trunc.getValue().toString());
        	break;
        case "Torneo Deterministico":
        	tam_conjunto=Integer.parseInt(ventana.num_conjuntos.getValue().toString());
        	break;
        case "Torneo Probabilistico":
        	tam_conjunto=Integer.parseInt(ventana.num_conjuntos.getValue().toString());
        	prob_peor=Double.parseDouble( ventana.prob_peor.getValue().toString());
        	break;
		}
	}
	
	//Se activar� cuando el usuario presione el bot�n "calcular" de la interfaz.
	public static  boolean calcularBoton(){
		//Recogemos los datos.
		recogerDatos();
		/*Creamos los objetos que implementar�n la selecci�n, mutaci�n, etc
		para usarlos en la ejecuci�n posterior. Depender�n de lo que haya
		introducido el usuario.*/
		parametrizarCalculos();
		//Se ejecuta el algoritmo gen�tico con las opciones introducidas por el usuario.
		//Se actualizan los resultados en la interfaz.
		realizarCalculos();
		return true;
	}
	
	/*En funci�n de los datos introducidos por el usuario, crea unos objetos u 
	otros que implementar�n	los distintos aspectos de la ejecuci�n (selecci�n, cruce...) */
	public static void parametrizarCalculos(){
			
		//Referente al algoritmo de mutaci�n
		switch(tipoMutacion){
		case "Mutacion multiple":
			ma=new MultipleMutation(prob_mutacion);
			break;
		case "Mutacion unica":
			ma=new OneMutation(prob_mutacion);
			break;
		}
		
		//Referente al algoritmo de cruce
		ca=new BinaryMonocross(prob_cruce);
		
		//Referente al problema elegido
		switch(problema){
		case "Problema 1":
			p= new PopulationFactoryP1(precision);
			break;
		case "Problema 2":
			p= new PopulationFactoryP2(precision);
			break;
		case "Problema 3":
			p= new PopulationFactoryP3(precision);
			break;
		case "Problema 4":
			p= new PopulationFactoryP4(precision);
			break;
		case "Problema 5":
			p= new PopulationFactoryP5(precision, numProblema);
			break;
		}
		
		//Referente al metodo de seleccion
		switch(seleccion){
		case "Ruleta":
			sa= new ProportionalSelection();
			break;
		case "Estocastico":
			sa= new StochasticSelection();
			break;
		case "Truncamiento":
			sa= new TruncateSelection((int)prob_trunc);
			break;
		case "Torneo Deterministico":
			sa= new DeterministicTournamentSelection(tam_conjunto);
			break;
		case "Torneo Probabilistico":
			sa= new ProbabilisticTournamentSelection(tam_conjunto,(int)prob_peor);
			break;
		}
		//Referente al elitismo.
		if(elitismo){ ea= new BasicElitismAlgorithm(prob_elitismo); }
		
	}
	
	//Ejecuta el algoritmo gen�tico. Utiliza los objetos creados en "parametrizarCalculos()".
	public static void realizarCalculos(){
		
		Chromosome[] elite=null;
		Chromosome[] population;

		double fitnessAverage=0;
		
		//Creamos la poblaci�n.
		population= p.createPopulation(poblacion);
		Arrays.sort(population);
		Chromosome winner = population[population.length-1].getCopy();
		
		double[] media_fitness = new double[generaciones]; 
		double[] mejorAbsoluto = new double[generaciones];
		double[] mejorEnGeneracion= new double[generaciones];
		Chromosome bestInGeneration;
		
		for(int i=0; i<generaciones; i++){
			try {
				
				//Recogemos la elite si procede.
				if(elitismo) elite=ea.pickElite(population);
				
				//Selecci�n.
				population=sa.select(population.length,population);
				
				//Cruce.
				population=ca.crossPopulation(population);
				
				//Mutaci�n.
				population= ma.mutatePopulation(population);

				//Introducimos la �lite si procede.
				if(elitismo) population=ea.putElite(population, elite);
				
				//Calculamos la media del fitness y obtenemos el individuo con mejor fitness (si ha cambiado).
				fitnessAverage=0;
				bestInGeneration=population[0].getCopy();
				
				
				for(int z=0; z<population.length; z++){
					
					fitnessAverage+=population[z].getFitness();
					
					//Si population[Z] es mayor que bestInGeneration.
					if(population[z].compareTo(bestInGeneration)==1){
						bestInGeneration=population[z].getCopy();
						
						if(population[z].compareTo(winner)==1){
							winner=population[z].getCopy();
						}
					}	
				}
				
				mejorAbsoluto[i]=winner.getFitness();
				media_fitness[i]=(fitnessAverage/poblacion);
				mejorEnGeneracion[i]=bestInGeneration.getFitness();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		

		//Mostramos por pantalla los resultados.
		ventana.plot.removeAllPlots();
		ventana.plot.addLinePlot("Best Absolute", mejorAbsoluto);
		ventana.plot.addLinePlot("Avg Fitness", media_fitness);
		ventana.plot.addLinePlot("Best in Generation", mejorEnGeneracion);
		ventana.mejor_valor.setText(String.format("%.4f", winner.getFitness()));

	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controlador.ventana = new Window();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}
	
	
	
	

	
}
