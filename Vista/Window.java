package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.control.ComboBox;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import Controlador.Controlador;

import org.math.plot.*;
@SuppressWarnings("unused")
public class Window {

	//Componentes de la vista
	
	public JFrame pevol;
	public Plot2DPanel plot;
	public JPanel frmPevol;
	public JTextField mejor_valor;

	@SuppressWarnings("rawtypes")
	public JComboBox problema;
	
	
	@SuppressWarnings("rawtypes")
	public JComboBox numProblema;
	
	@SuppressWarnings("rawtypes")
	public JComboBox seleccion;
	
	@SuppressWarnings("rawtypes")
	public JComboBox mutacion;
	
	public JSpinner num_pob;
	public JSpinner num_generaciones;
	public JSpinner prob_muta;
	public JSpinner prob_cruce;
	public JTextArea precision;
	public JCheckBox elitismo;
	public JSpinner num_elitismo;
	public JSpinner num_conjuntos;
	public JSpinner prob_peor;
	public JSpinner prob_trunc;
	
	public JButton btnCalcular;
	
	private JLabel lblElementosPorConjunto;
	private JLabel lblProbabilidadElegirPeor;
	private JLabel lblProbabilidadElegirTruncamiento;
	
	/**
	 * @wbp.nonvisual location=67,79
	 */
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		pevol = new JFrame();
		pevol.setTitle("Practica 1 PEVOL");
		pevol.setVisible(true);
		pevol.setBounds(100, 100, 750, 629);
		pevol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pevol.getContentPane().setLayout(null);
		pevol.setLayout(null);
		
		JPanel principal=new JPanel();
		principal.setLayout(new BorderLayout());
		pevol.setContentPane(principal);
		
		JPanel frmPevol=new JPanel();
		
		
		
		JLabel lblProblemaElegido = new JLabel("Problema elegido");
		lblProblemaElegido.setBounds(10, 11, 105, 14);
		frmPevol.add(lblProblemaElegido);
		
		//Seleccion de problema a probar
		this.problema = new JComboBox();
		this.problema.setModel(new DefaultComboBoxModel(new String[] {"Problema 1", "Problema 2", "Problema 3", "Problema 4", "Problema 5"}));
		this.problema.setSelectedIndex(0);
		this.problema.setBounds(119, 5, 119, 27);
		frmPevol.add(this.problema);
		
		
		JLabel lblNewLabel = new JLabel("Metodo de Seleccion");
		lblNewLabel.setBounds(258, 11, 105, 14);
		frmPevol.add(lblNewLabel);

		this.seleccion = new JComboBox();
		this.seleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Estocastico", "Truncamiento", "Torneo Deterministico", "Torneo Probabilistico"}));
		this.seleccion.setBounds(362, 5, 119, 27);
		this.seleccion.setSelectedIndex(0);
		//En funcion de la seleccion seleccionada bloqueamos o no la entrada de datos correspondientes
		this.seleccion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        if(seleccion.getSelectedItem()=="Ruleta"){
		        	
		        }
		        switch((String)seleccion.getSelectedItem()){
			        case "Ruleta":
			        	prob_trunc.setEnabled(false);
			        	num_conjuntos.setEnabled(false);
			        	prob_peor.setEnabled(false);
			        	lblElementosPorConjunto.setEnabled(false);
			        	lblProbabilidadElegirPeor.setEnabled(false);
			        	lblProbabilidadElegirTruncamiento.setEnabled(false);
			        	break;
			        case "Estocastico":
			        	prob_trunc.setEnabled(false);
			        	num_conjuntos.setEnabled(false);
			        	prob_peor.setEnabled(false);
			        	lblElementosPorConjunto.setEnabled(false);
			        	lblProbabilidadElegirPeor.setEnabled(false);
			        	lblProbabilidadElegirTruncamiento.setEnabled(false);
			        	break;
			        case "Truncamiento":
			        	prob_trunc.setEnabled(true);
			        	num_conjuntos.setEnabled(false);
			        	prob_peor.setEnabled(false);
			        	lblElementosPorConjunto.setEnabled(false);
			        	lblProbabilidadElegirPeor.setEnabled(false);
			        	lblProbabilidadElegirTruncamiento.setEnabled(true);
			        	break;
			        case "Torneo Deterministico":
			        	num_conjuntos.setEnabled(true);
			        	prob_trunc.setEnabled(false);
			        	prob_peor.setEnabled(false);
			        	lblElementosPorConjunto.setEnabled(true);
			        	lblProbabilidadElegirPeor.setEnabled(false);
			        	lblProbabilidadElegirTruncamiento.setEnabled(false);
			        	break;
			        case "Torneo Probabilistico":
			        	num_conjuntos.setEnabled(true);
			        	prob_trunc.setEnabled(false);
			        	prob_peor.setEnabled(true);
			        	lblElementosPorConjunto.setEnabled(true);
			        	lblProbabilidadElegirPeor.setEnabled(true);
			        	lblProbabilidadElegirTruncamiento.setEnabled(false);
			        	break;
		        }
		    }
		});
		frmPevol.add(this.seleccion);
		
		
		//Seleccion cantidad de poblacion
		JLabel lblTamaoPoblacion = new JLabel("Tama\u00F1o poblacion");
		lblTamaoPoblacion.setBounds(10, 49, 95, 14);
		frmPevol.add(lblTamaoPoblacion);
		
		this.num_pob = new JSpinner();
		this.num_pob.setBounds(119, 43, 119, 27);
		this.num_pob.setValue(100);
		frmPevol.add(this.num_pob);
		
		
		
		//Seleccion numero de generaciones a realizar
		JLabel lblNewLabel_1 = new JLabel("Numero generaciones");
		lblNewLabel_1.setBounds(10, 87, 105, 14);
		frmPevol.add(lblNewLabel_1);
		
		this.num_generaciones = new JSpinner();
		this.num_generaciones.setBounds(119, 81, 119, 27);
		this.num_generaciones.setValue(100);
		frmPevol.add(this.num_generaciones);
		
		
		
		//Seleccion probabilidades de mutacion
		JLabel lblProbabilidadMutacion = new JLabel("Porcentaje Mutacion (%)");
		lblProbabilidadMutacion.setBounds(10, 125, 105, 14);
		frmPevol.add(lblProbabilidadMutacion);
		
		this.prob_muta = new JSpinner();
		this.prob_muta.setBounds(119, 119, 119, 27);
		this.prob_muta.setValue(10);
		frmPevol.add(this.prob_muta);
		
		//Seleccion probabilidad cruce
		JLabel lblProbabilidadCruce = new JLabel("Porcentaje cruce (%)");
		lblProbabilidadCruce.setBounds(10, 163, 105, 14);
		frmPevol.add(lblProbabilidadCruce);
		
		
		this.prob_cruce = new JSpinner();
		this.prob_cruce.setBounds(119, 160, 119, 27);
		this.prob_cruce.setValue(60);
		frmPevol.add(this.prob_cruce);
		
		//Seleccion de valor de precision
		JLabel lblPrecision = new JLabel("Precision");
		lblPrecision.setBounds(10, 199, 46, 14);
		frmPevol.add(lblPrecision);
		
		this.precision = new JTextArea();
		this.precision.setBounds(119, 198, 119, 27);
		this.precision.setText("0.001");
		frmPevol.add(this.precision);
		
		//Checkbox de elitismo o no
		this.elitismo = new JCheckBox("Elitismo    (%)");
		this.elitismo.setBounds(10, 244, 82, 23);
		//Si esta activado elitismo , permitimos meter datos en su recuadro
		this.elitismo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        if(elitismo.isSelected()){
		        	
		        	num_elitismo.setEnabled(true);
		        }else{
		        	
		        	num_elitismo.setEnabled(false);
		        }
		    }
		});

		frmPevol.add(elitismo);
		//Seleccion de elitismo si esta activado
		this.num_elitismo = new JSpinner();
		this.num_elitismo.setEnabled(false);
		this.num_elitismo.setBounds(119, 242, 119, 27);
		this.num_elitismo.setValue(2);
		frmPevol.add(this.num_elitismo);
		
		//Seleccion de valor de N (en el caso de que P5 esté seleccionado).
				
		JLabel nLabel = new JLabel("Valor de N  (P5)");
		nLabel.setBounds(579, 11, 46, 14);
		frmPevol.add(nLabel);		
		this.numProblema = new JComboBox();
		this.numProblema.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5","6","7"}));
		this.numProblema.setBounds(119, 5, 119, 27);
		frmPevol.add(this.numProblema);

		//Elementos por conjunto Z, Torneos
		this.lblElementosPorConjunto = new JLabel("Elementos por conjunto");
		this.lblElementosPorConjunto.setEnabled(false);
		this.lblElementosPorConjunto.setBounds(258, 49, 119, 14);
		frmPevol.add(this.lblElementosPorConjunto);
		
		this.num_conjuntos = new JSpinner();
		this.num_conjuntos.setEnabled(false);
		this.num_conjuntos.setBounds(387, 43, 94, 27);
		this.num_conjuntos.setValue(3);
		frmPevol.add(this.num_conjuntos);
		
		//Probabilidad elegir peor Torneo Probabilistico
		this.lblProbabilidadElegirPeor = new JLabel("Probabilidad elegir peor   (%)");
		this.lblProbabilidadElegirPeor.setEnabled(false);
		this.lblProbabilidadElegirPeor.setBounds(258, 87, 119, 14);
		frmPevol.add(this.lblProbabilidadElegirPeor);
		
		this.prob_peor = new JSpinner();
		this.prob_peor.setEnabled(false);
		this.prob_peor.setBounds(387, 84, 94, 24);
		this.prob_peor.setValue(50);
		frmPevol.add(this.prob_peor);
		
		//Porcentaje truncamiento
		this.lblProbabilidadElegirTruncamiento = new JLabel("Porcentaje truncamiento  (%)");
		this.lblProbabilidadElegirTruncamiento.setEnabled(false);
		this.lblProbabilidadElegirTruncamiento.setBounds(258, 125, 132, 14);
		frmPevol.add(this.lblProbabilidadElegirTruncamiento);
		
		this.prob_trunc = new JSpinner();
		this.prob_trunc.setEnabled(false);
		this.prob_trunc.setBounds(387, 122, 94, 24);
		this.prob_trunc.setValue(50);
		frmPevol.add(this.prob_trunc);
		
		//Campo que muestra el mejor valor absoluto calculado.
		JLabel lblMejor = new JLabel("Mejor valor absoluto");
		lblMejor.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblMejor.setBounds(335, 217, 146, 27);
		frmPevol.add(lblMejor);
		
		mejor_valor = new JTextField();
		mejor_valor.setEditable(false);
		mejor_valor.setBounds(333, 245, 134, 29);
		frmPevol.add(mejor_valor);
		mejor_valor.setColumns(10);
		
		
		
		//Boton de calcular
		this.btnCalcular = new JButton("Calcular");
		this.btnCalcular.setFont(new Font("Bell Gothic Std Black", Font.PLAIN, 13));
		this.btnCalcular.setBounds(579, 244, 119, 35);
		this.btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Controlador.calcularBoton();
			}
		});
		frmPevol.add(this.btnCalcular);

		
		JLabel lblMutacion = new JLabel("Tipo de  mutacion");
		lblProblemaElegido.setBounds(10, 11, 105, 14);
		frmPevol.add(lblMutacion);
		
		//Seleccion de problema a probar
		this.mutacion = new JComboBox();
		this.mutacion.setModel(new DefaultComboBoxModel(new String[] {"Mutacion multiple", "Mutacion unica"}));
		this.mutacion.setSelectedIndex(0);
		this.mutacion.setBounds(119, 5, 119, 27);
		frmPevol.add(this.mutacion);

		frmPevol.setLayout(new GridLayout(5,6));
		principal.add(frmPevol, BorderLayout.NORTH);
		
		//Instancia de plot para dibujar las gráficas.		
		this.plot = new Plot2DPanel();		
		this.plot.addLegend("SOUTH");
		principal.add(this.plot, BorderLayout.CENTER);		
	}
}
