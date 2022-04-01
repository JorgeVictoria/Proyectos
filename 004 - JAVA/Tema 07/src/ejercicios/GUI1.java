package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI1 window = new GUI1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton bAceptar = new JButton("Aceptar");
		
		bAceptar.setBounds(10, 140, 89, 23);
		frame.getContentPane().add(bAceptar);
		
		JCheckBox chPerro = new JCheckBox("Perro");
		chPerro.setBounds(0, 7, 97, 23);
		frame.getContentPane().add(chPerro);
		
		JCheckBox chGato = new JCheckBox("Gato");
		chGato.setBounds(0, 48, 97, 23);
		frame.getContentPane().add(chGato);
		
		JCheckBox chRaton = new JCheckBox("Raton");
		chRaton.setBounds(0, 87, 97, 23);
		frame.getContentPane().add(chRaton);
		
		JLabel lbResultado = new JLabel("");
		lbResultado.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbResultado.setBounds(10, 205, 399, 34);
		frame.getContentPane().add(lbResultado);
		
		bAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadena = "";
				if(chPerro.isSelected()) cadena = cadena + chPerro.getText() + " ";
				if(chGato.isSelected()) cadena = cadena + chGato.getText() + " ";
				if(chRaton.isSelected()) cadena = cadena + chRaton.getText() + " ";
					lbResultado.setText(cadena);
			}
		});	
	}
}
