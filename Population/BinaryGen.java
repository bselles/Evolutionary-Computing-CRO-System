package Population;


import java.util.Random;


/*
 * Representa un gen binario. 
 * -Recordemos que, en esta implementación, un gen forma parte de un cromosoma
 * y a cada variable del problema se le asocia un gen.
 */
public class BinaryGen extends Gen{
	
	//Attributes.
	protected boolean bits[];
	private double xmin;
	private double xmax;
	
	//Constructores.
	public BinaryGen(){
	}
	
	public BinaryGen(double xmin2, double xmax2, double TOL) {
		super();
		this.xmin=xmin2;
		this.xmax=xmax2;
		this.bits= new boolean[calculate_bits_length(xmin2,xmax2, TOL)];
		generate_random_bits();
	}
	
	//Métodos
	@Override
	//Genera un string que representa este Gen.
	public String toString(){
		String result="";
		result=result+ "BinaryGen: ";
		for(int i=0; i<this.bits.length; i++){
			result= result + " "+ this.bits[i];
		}
		result=result+"\n";
		result=result+ "Phenotype: "+ this.getPhenotype()+ "\n";
		return result;
	}
	
	//Dada la cadena de bits, obtiene el fenotipo de este gen.
	public double getPhenotype(){
		//Aplicamos la funcion de la trasparencia 40 del tema 3 para obtener el fenotipo.
		double result =(this.xmax-this.xmin);
		result=result/(Math.pow(2, this.bits.length)-1);
		return result * bin2dec()+this.xmin;
	}
	
	//Devuelve la representación decimal de los bits del gen binario.
	private double bin2dec(){
		int i=0;
		double result=0;
		for (int j=this.bits.length-1; j>=0;j--){
			if(this.bits[j]){
				result= result+Math.pow(2,i);
			}
			i++;			
		}
		return result;
	}
	
	//Devuelve el tamaño que debe tener el array de booleanos dadas las especificaciones del problema.
	private int calculate_bits_length(double xmin, double xmax, double TOL){
		/*Aplicamos la fórmula lcrom=|log2(1+(xmax-xmin)/(2^lcrom-1))| 
		para obtener el tamaño de la codificación.*/
		double result= 1+(xmax-xmin)/TOL;
		result=(Math.log(result) / Math.log(2));;
		return (int) Math.ceil(result);
	}	
	
	
	//Inserta en el bits aleatorios al array bits[].
	private void generate_random_bits(){
		Random randomGenerator = new Random();
		int res;
		for(int i=0; i<this.bits.length; i++){
			res=randomGenerator.nextInt(2);
			if(res==0) this.bits[i]= false;
			else this.bits[i]=true;
		}		
	}
	
	//Devuelve una copia identica del gen binario (para evitar conflictos de referencias).
	@Override
	public Gen copyGen() {
		BinaryGen result= new BinaryGen();
		result.bits= new boolean[this.bits.length];
		for(int i=0; i<this.bits.length; i++){
			result.bits[i]=this.bits[i];
		}
		result.xmin=this.xmin;
		result.xmax=this.xmax;
		return result;
	}
	
	//GETTERS AND SETTERS.

	public boolean[] getBits() {
		return bits;
	}	
}
