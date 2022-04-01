package E702;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class E702 {
	
	public static String valor = ""; 		//almacena el valor introducido
	public static boolean flag = false;		//para ver si se puede realizar la conversion
	public static Double valorPtas;			//para pasar de ptas a euros
	public static String patron = "";		//patron de la cadena a mostrar
	public static String cadena = "";		//cadena formateada

	private JFrame frmConversorJorgeVictoria; //el Jframe creado
	private JTextField tfImportePtas;		  //el JTextField creado
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E702 window = new E702();
					window.frmConversorJorgeVictoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public E702() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Jframe 
		frmConversorJorgeVictoria = new JFrame();
		frmConversorJorgeVictoria.setTitle("Conversor Jorge Victoria Andreu");
		frmConversorJorgeVictoria.getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		frmConversorJorgeVictoria.setBounds(100, 100, 450, 300);
		frmConversorJorgeVictoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorJorgeVictoria.getContentPane().setLayout(null);
		
		//Jlabel 
		JLabel lb1 = new JLabel("Importe en pesetas");
		lb1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(134, 37, 154, 14);
		frmConversorJorgeVictoria.getContentPane().add(lb1);
		
		//campo de texto para introducir las ptas
		tfImportePtas = new JTextField();
		tfImportePtas.setHorizontalAlignment(SwingConstants.RIGHT);
		tfImportePtas.setBounds(74, 78, 280, 20);
		frmConversorJorgeVictoria.getContentPane().add(tfImportePtas);
		tfImportePtas.setColumns(10);
		
		//Jlabel
		JLabel lb2 = new JLabel("Pulse para convertir a texto");
		lb2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setBounds(124, 117, 184, 20);
		frmConversorJorgeVictoria.getContentPane().add(lb2);
		
		//boton para convertir
		JButton btConvertir = new JButton("Convertir");
		btConvertir.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btConvertir.setBounds(134, 158, 174, 23);
		frmConversorJorgeVictoria.getContentPane().add(btConvertir);
		
		//zona donde se muestra la conversion a euros
		JLabel lbImporteEuros = new JLabel("0 Euros");
		lbImporteEuros.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbImporteEuros.setHorizontalAlignment(SwingConstants.CENTER);
		lbImporteEuros.setBounds(165, 216, 123, 14);
		frmConversorJorgeVictoria.getContentPane().add(lbImporteEuros);
		
		//listener para el boton convertir
		btConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//recogemos el valor
				valor = tfImportePtas.getText();
				
				//si no hay nada, lo anunciamos, cambiamos el color de fondo y el color de la fuente
				if(valor.equals("")) {
					tfImportePtas.setBackground(Color.red);
					lbImporteEuros.setForeground(Color.red);
					lbImporteEuros.setText("Introduzca un valor");
				}
				else {
					//comprobamos que el valor de la cadena se puede pasar a numero
					try {
						valorPtas = Double.parseDouble(valor);
						//sino se puede convertir, mostramos mensaje en pantalla
					} catch(NumberFormatException ex) {
						lbImporteEuros.setText("valor incorrecto");
						tfImportePtas.setBackground(Color.red);
						lbImporteEuros.setForeground(Color.red);
						flag = true;
					} //fin try catch
					
					//se puede convertir, convertimos y mostramos el valor
					if(!flag) {
						patron ="%.2f Euros";
						cadena = String.format(patron, valorPtas/166.6);
						tfImportePtas.setBackground(Color.white);
						lbImporteEuros.setForeground(Color.black);
						lbImporteEuros.setText(cadena);
					}
					else flag = false; //para volver a introducir datos
				} //fin else
				
				
			} //fin actionperformed
		}); //fin listener
		
		
	} //fin initialize
} //fin clase
