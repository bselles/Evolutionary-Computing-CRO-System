import java.awt.event.ActionEvent;

import modelo.Estocastico;
import modelo.Ruleta;
import modelo.Seleccion;
import modelo.Torneo;
import modelo.Truncamiento;

import org.math.plot.Plot2DPanel;

import Funciones.Funcion;
import Funciones.Funcion1;
import Funciones.Funcion2;
import Funciones.Funcion3;
import Funciones.Funcion4;
import Funciones.Funcion5;

public class E_S extends javax.swing.JFrame {
	
	 private javax.swing.JButton jButton1;
	    private javax.swing.JComboBox<String> jComboBox1; //Elegir función
	    private javax.swing.JComboBox<String> jComboBox2; //Método selección
	    private javax.swing.JComboBox<String> jComboBox3; //Operador cruce reales
	    private javax.swing.JComboBox<String> jComboBox4; //Tipo de mutación reales
	    private javax.swing.JLabel jLabel1; 
	    private javax.swing.JLabel jLabel11; 
	    private javax.swing.JLabel jLabel12; 
	    private javax.swing.JLabel jLabel13; 
	    private javax.swing.JLabel jLabel14; 
	    private javax.swing.JLabel jLabel15; 
	    private javax.swing.JLabel jLabel2; 
	    private javax.swing.JLabel jLabel3; 
	    private javax.swing.JLabel jLabel4; 
	    private javax.swing.JLabel jLabel5; 
	    private javax.swing.JLabel jLabel6; 
	    private javax.swing.JLabel jLabel7; 
	    private javax.swing.JLabel jLabel8; 
	    private javax.swing.JLabel jLabel9; 
	    private javax.swing.JLabel jLabel16;
	    private javax.swing.JPanel jPanel2; 
	    private javax.swing.JRadioButton jRadioButton1; //Representación reales
	    private javax.swing.JRadioButton jRadioButton2; //Elitismo
	    private javax.swing.JSpinner jSpinner1; // Spinner elitismo
	    private javax.swing.JSpinner jSpinner4; // Spinner tamaño conjunto
	    private javax.swing.JSplitPane jSplitPane2; 
	    private javax.swing.JTextField jTextField1; //Tamaño poblacion
	    private javax.swing.JTextField jTextField2; //Numero generaciones
	    private javax.swing.JTextField jTextField3; //Porcentaje cruce
	    private javax.swing.JTextField jTextField4; //Porcentaje mutación
	    private javax.swing.JTextField jTextField5; //Precisión
	    private javax.swing.JTextField jTextField6; //Mejor absoluto
	    private javax.swing.JTextField jTextField7; //Truncamiento
	    private javax.swing.JTextField jTextField8; //n de F5
	    private  Plot2DPanel plot; //Grafica
    
    

	//Entrada
	int tam_pob;
	int num_generaciones = 0;
	double pc_cruces;
	double pc_mutaciones;
	double precision;
	int num_mejores;
	Funcion funcAct;
	
	//Salida
	double mejor_abs;
	double mejor_gen;
	double media;
	
    private double[] x = new double[100];
    private double[] y1 = new double[100];
    private double[] y2 = new double[100];
    private double[] y3 = new double[100];
    
    
	public E_S() {
		 
	     initComponents();
	     jRadioButton1.setEnabled(false);

	}
	
	private void initComponents() {


		 jSplitPane2 = new javax.swing.JSplitPane();
	        jPanel2 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jComboBox1 = new javax.swing.JComboBox<>();
	        jRadioButton1 = new javax.swing.JRadioButton();
	        jLabel3 = new javax.swing.JLabel();
	        jComboBox2 = new javax.swing.JComboBox<>();
	        jRadioButton2 = new javax.swing.JRadioButton();
	        jLabel4 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jTextField2 = new javax.swing.JTextField();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jTextField5 = new javax.swing.JTextField();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jComboBox3 = new javax.swing.JComboBox<>();
	        jComboBox4 = new javax.swing.JComboBox<>();
	        jSpinner1 = new javax.swing.JSpinner();
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jSpinner4 = new javax.swing.JSpinner();
	        jLabel15 = new javax.swing.JLabel();
	        jTextField6 = new javax.swing.JTextField();
	        jTextField7 = new javax.swing.JTextField();
	        jLabel16 = new javax.swing.JLabel();
	        jTextField8 = new javax.swing.JTextField();
	        plot = new Plot2DPanel();
	        
	        plot.addLegend("SOUTH");

            // add a line plot to the PlotPanel


	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jLabel1.setText("Función elegida:");

	        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        jLabel2.setText("Opciones Algoritmo Genético");

	        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcion 1", "Funcion 2", "Funcion 3", "Funcion 4", "Funcion 5" }));
	        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jComboBox1ActionPerformed(evt);
	            }
	        });

	        jRadioButton1.setText("Representacion Reales");
	        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	JRadioButton1ActionPerformed(evt);
	            }

	        });

	        jLabel3.setText("Método de selección:");

	        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ruleta", "Torneo", "Truncamiento", "Estocástico universal" }));

	        jRadioButton2.setText("Elitismo");


	        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	        jLabel4.setText("Parámetros");

	        jTextField1.setText("100");


	        jTextField2.setText("100");

	        jTextField3.setText("0.6");

	        jTextField4.setText("0.05");

	        jTextField5.setText("0.001");
	        
	        jTextField7.setText("0.5");

	        jLabel5.setText("Tamaño población: ");

	        jLabel6.setText("Número de generaciones:");

	        jLabel7.setText("Porcentaje cruce:");

	        jLabel8.setText("Porcentaje mutación");

	        jLabel9.setText("Precisión:");

	        jButton1.setText("Empieza");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	JButton1ActionPerformed(evt);
	            }
	        });



	        jLabel11.setText("Operador de cruce:\n\n");

	        jLabel12.setText("Tipo de mutación:\n\n");

	        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monopunto cromosoma", "Monopunto gen a gen" }));

	        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mutacion cromosoma", "Mutacion gen a gen" }));

	        jLabel13.setText("Truncamiento:");

	        jLabel14.setText("Tamaño conjunto:");

	        jLabel15.setText("Mejor absoluto:");
	        
	        jLabel16.setText("nº variables F5:");

	        jTextField8.setText("2");

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

	                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jButton1)
	                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(jLabel11)
	                                            .addComponent(jLabel12))
	                                        .addGap(75, 75, 75)
	                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                                .addGap(30, 30, 30)
	                                .addComponent(jLabel15)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addGap(0, 57, Short.MAX_VALUE))
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addComponent(jLabel3)
	                                            .addComponent(jLabel4)
	                                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(jRadioButton1)))
	                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                                .addGap(4, 4, 4)
	                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addComponent(jLabel8)
	                                                    .addComponent(jLabel9)
	                                                    .addComponent(jLabel16))
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                            
	                                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                                .addGap(54, 54, 54)
	                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                                        .addComponent(jLabel14)
	                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                        .addComponent(jSpinner4))
	                                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                                        .addComponent(jLabel13)
	                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))))
	                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                                .addComponent(jLabel7)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                                .addComponent(jLabel6)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                                .addComponent(jLabel5)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
	                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                .addComponent(jRadioButton2)))
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(0, 0, Short.MAX_VALUE)))
	                                .addGap(79, 79, 79)))
	                        .addContainerGap())))
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jRadioButton1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(jLabel13)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jRadioButton2)
	                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel14)
	                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(32, 32, 32)
	                .addComponent(jLabel4)
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(jLabel8)
	                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6)
	                    .addComponent(jLabel9)
	                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel16)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(33, 33, 33)

	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jLabel15)
	                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap())
	        );

	        jSplitPane2.setLeftComponent(jPanel2);
	        jSplitPane2.setRightComponent(plot);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addComponent(jSplitPane2)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jSplitPane2)
	        );

	        pack();
	    }

	 private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
	        // TODO add your handling code here:
		 	String seleccion = (String) jComboBox1.getSelectedItem();
		 	if(seleccion == "Funcion 5") jRadioButton1.setEnabled(true);
		 	else{
		 		jRadioButton1.setEnabled(false);
		 		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monopunto cromosoma", "Monopunto gen a gen", }));
		 		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mutacion cromosoma", "Mutacion gen a gen" }));


		 	}
		 	jRadioButton1.setSelected(false);
		 		 	
	    }       
	 

		private void JRadioButton1ActionPerformed(ActionEvent evt) {
			boolean accion = jRadioButton1.isSelected();
			if(accion){
				jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Un punto", "Discreto uniforme", "Aritmético", "SBX" }));
				jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mutacion uniforme" }));
			}
			else{
				jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monopunto cromosoma", "Monopunto gen a gen" }));
				jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mutacion cromosoma", "Mutacion gen a gen" }));
			}
		}
	    
	 	

		private void JButton1ActionPerformed(ActionEvent evt) {
			int tam_pob = Integer.parseInt(jTextField1.getText());
			int num_generaciones = Integer.parseInt(jTextField2.getText()); 
			double prob_cruce = Double.parseDouble(jTextField3.getText());
			double prob_mut = Double.parseDouble(jTextField4.getText());
			double tol = Double.parseDouble(jTextField5.getText());
			String funcion = (String) jComboBox1.getSelectedItem();
			Funcion func = null;
			String seleccion = (String) jComboBox2.getSelectedItem();
			Seleccion selecc = null;
			String cruce = (String) jComboBox3.getSelectedItem();
			int tipo_cruce = 0;
			String mutacion = (String) jComboBox4.getSelectedItem();
			int tipo_mut = 0; //Integer.parseInt((String) jComboBox4.getSelectedItem());
			int n = Integer.parseInt(jTextField8.getText()); //La n de la funcion 5
			boolean repr = jRadioButton1.isSelected();
			int numElite = (int) jSpinner1.getValue();
			boolean elitismo = jRadioButton2.isSelected();
			
			
			
			plot.removeAll();
			
			
			switch (funcion) {
				case "Funcion 1": func = new Funcion1(tol,prob_cruce); break;
				case "Funcion 2": func = new Funcion2(tol,prob_cruce); break;
				case "Funcion 3": func = new Funcion3(tol,prob_cruce); break;
				case "Funcion 4": func = new Funcion4(tol,prob_cruce); break;
				default: func = new Funcion5(repr, tol, prob_cruce, n); break;
			}
			
			switch (seleccion) {
				case "Ruleta": 
					selecc = new Ruleta(); 
					break;
				case "Torneo": 
					selecc = new Torneo((int)jSpinner4.getValue()); 
					break;
				case "Truncamiento": 
					selecc = new Truncamiento(Double.parseDouble(jTextField7.getText())); 
					break;
				case "Estocástico universal": 
					selecc = new Estocastico(); 
					break;
				default :
					
					break;
			}
			
			switch (cruce) {
			case "Monopunto cromosoma":
			case "Un punto" :
				tipo_cruce = 1;
				break;
			case "Monopunto gen a gen":
			case "Discreto uniforme" :
				tipo_cruce = 2;
				break;
			case "Aritmético" :
				tipo_cruce = 3;
				break;
			case "SBX" :
				tipo_cruce = 4;
				break;

			default:
				break;
			}
			
			switch (mutacion) {
			case "Mutacion uniforme" :
			case "Mutacion cromosoma" :
				tipo_mut = 1;
				break;
			case "Mutacion gen a gen" :
				tipo_mut = 2;
				break;
			default:
				break;
			}
			
 			AlgoritmoGenetico AG = new AlgoritmoGenetico(tam_pob, prob_mut,
 					tipo_mut, tipo_cruce, func, selecc);
			
			AG.construyePoblacion();
			AG.evaluarPoblacion();
			
			double mejor=0.0;
			
			this.x = new double[num_generaciones];
			this.y1 = new double[num_generaciones];
			this.y2 = new double[num_generaciones];
			this.y3 = new double[num_generaciones];
			
			for(int i=0; i<num_generaciones; i++) {

				if(elitismo)
					AG.elitismo(numElite);
				AG.seleccion();
				AG.reproduccion();
				AG.mutacion();
				if(elitismo)
					AG.sustituyeElite(numElite);
				x[i] = i;
				y3[i] = AG.evaluarPoblacion();
				y2[i] = AG.mejorPoblacion();
				if(func.getMinimizar()){
					if(y2[i] < mejor)
						mejor = y2[i];
				}else if(y2[i] > mejor)
						mejor = y2[i];
				y1[i] = mejor;


			}
			plot = new Plot2DPanel();
		        
		    plot.addLegend("SOUTH");
			plot.addLinePlot("Mejor absoluto", x, y1);
	        plot.addLinePlot("Mejor de la generación", x, y2);
	        plot.addLinePlot("Media de la generación", x, y3);
	        jSplitPane2.setRightComponent(plot);
			jTextField6.setText( String.format("%.4f", mejor) );
			
		}
		
	    
}
