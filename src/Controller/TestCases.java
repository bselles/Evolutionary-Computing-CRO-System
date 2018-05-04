package Controller;

public class TestCases {
	private int N;
	private int M;
	private int generations;		//N�mero de generaciones a evaluar.
	
	private double occupationRatio;	//Ratio inicial de celdas ocupadas.
	private double broodingRatio;	//Ratio de elementos que har�n reproducci�n sexual externa
	private int survivingAttempts;	//N�mero de intentos que tiene cada larva para ubicarse en la poblaci�n en la fase 3.
	private double arRatio;			//Porcentaje de soluciones que se intentar�n reproducir asexualmente (fase 4).
	
	private double depredationPercentage; 	//Porcentaje de poblaci�n a la que se aplicar� la fase 5 (depredation).
	private double depredationProbability;	//Probabilidad de que cada individuo del porcentaje anterior muera (depredation phase).
	
	private double crossProbability;		//Probabilidad de que los individuos se crucen mediante reproducci�n sexual externa
	private double mutationProbability;		//Probabilidad de que ocurra la reproducci�n sexual interna
	
	private int mutationType;
	private int crossType;
	
	private int problem; 			//Problema a resolver. Los posibles valores son de 1 a 5.
	private int n;					//En el caso de que resolvamos el problema 5, valor de n.
	
	
	//Ejecuta el problema con los par�metros introducidos por par�metro y devuelve el resultado.
	public double test(int N, int M, int generation, double ocupationRatio, double broodingRatio, int survivingAttemps,
			double arRatio, double depredationPercentage, double depredationProbability, double crossProbability,
			double mutationProbability, int mutationType, int crossType, int problem, int n){
		
		
		
		return 0;
	}
	
	
}
