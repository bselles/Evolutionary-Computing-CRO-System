


public class main {
	

    
	public static void main(String[] args) {
	
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
              new E_S().setVisible(true);
              
            }
        });
		
		/*
		int num_generaciones = 100;
		int tam_pob = 100;
		double prob_cruce = 0.6;
		double prob_mut = 0.05;
		int tipo_mut = 0;
		double tol = 0.001;
		int n = 2; //Numeros genes funcion 5
		Funcion func = new Funcion1(tol,prob_mut);
		Seleccion selecc = new Ruleta(); 
		

		    
		int[] x = {0};
		double[] y1 = {0};
		double[] y2 = {0};
		double[] y3 = {0};
		
		AlgoritmoGenetico AG = new AlgoritmoGenetico(tam_pob, prob_cruce, prob_mut, tipo_mut, tol, func, selecc);
		
		AG.construyePoblacion();
		AG.evaluarPoblacion();
		
		double mejor=0.0;
		
		for(int i=0; i<num_generaciones; i++) {
			AG.seleccion();
			AG.reproduccion();
			AG.mutacion();
			x[i] = i;
			y3[i] = AG.evaluarPoblacion();
			y2[i] = AG.mejorPoblacion();
			if(y2[i] > mejor)
				mejor = y2[i];
			y1[i] = mejor;

		}
		
	*/

	}
	                                       

}
