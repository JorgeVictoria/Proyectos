package E701;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class E701 {
	
	//variables globales
	public static String valor = ""; 	//recoge el valor del texto introducido
	public static Double valorEuros;	//pasamos el valor a un double
	public static boolean flag = false;	//nos indica si podemos hacer la conversion a ptas
	public static String patron = "";	//cadena a mostrar inicial
	public static String cadena = ""; 	//cadena formateada. Muestra el importe convertido a ptas
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(
				new Runnable() {
					public void run() {
						
						//creamos el JFrame
						JFrame frame = new JFrame();
						
						//configuramos el JFrame
						frame.setTitle("Conversor Jorge Victoria Andreu");
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setSize(400, 280);
						frame.setVisible(true);
						
						//creamos el layout del JFrame
						frame.getContentPane().setLayout(new GridLayout(9,1));
						
						//fila1
						JLabel label1 = new JLabel("Importe en euros", JLabel.CENTER);
						frame.getContentPane().add(label1);
						
						//fila2
						JLabel label2 = new JLabel();
						frame.getContentPane().add(label2);
						
						//fila3
						JTextField tfImporteEuros = new JTextField();
						frame.getContentPane().add(tfImporteEuros);
						
						//fila4
						JLabel label3 = new JLabel();
						frame.getContentPane().add(label3);
						
						//fila5
						JButton btConvertir = new JButton("Convertir");
						frame.getContentPane().add(btConvertir);
						
						//fila6
						JLabel label4 = new JLabel();
						frame.getContentPane().add(label4);
						
						//fila7
						JLabel label5 = new JLabel("Pulse para obtener el importe en pesetas", JLabel.CENTER);
						frame.getContentPane().add(label5);
						
						//fila8
						JLabel label6 = new JLabel();
						frame.getContentPane().add(label6);
						
						//fila9
						JLabel lbImportePtas = new JLabel("0 pts", JLabel.CENTER);
						frame.getContentPane().add(lbImportePtas);
						
						//listener para el boton convertir
						btConvertir.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								//recogemos el valor
								valor = tfImporteEuros.getText();
								
								//si no hay nada, lo anunciamos
								if(valor.equals("")) lbImportePtas.setText("Introduzca un valor");
								else {
									//comprobamos que el valor de la cadena se puede pasar a numero
									try {
										valorEuros = Double.parseDouble(valor);
										//sino se puede convertir, mostramos mensaje en pantalla
									} catch(NumberFormatException ex) {
										lbImportePtas.setText("valor incorrecto");
										flag = true;
									}
									
									//se puede convertir, convertimos y mostramos el valor
									if(!flag) {
										patron ="%.1f ptas";
										cadena = String.format(patron, valorEuros*166.6);
										lbImportePtas.setText(cadena);
									}
									else flag = false; //para volver a introducir datos
								}
							} //fin actionpertformed
						}); //fin actionlistener
					} //fin run
				} //fin runnable
			); //fin eventqueue

	} //fin main

} //fin clase
