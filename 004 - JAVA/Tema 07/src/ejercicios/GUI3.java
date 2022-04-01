package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI3 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI3 window = new GUI3();
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
	public GUI3() {
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
		
		bAceptar.setBounds(10, 161, 89, 23);
		frame.getContentPane().add(bAceptar);
		
		JLabel lResultado = new JLabel("");
		lResultado.setBorder(new LineBorder(new Color(0, 0, 0)));
		lResultado.setBounds(10, 227, 348, 23);
		frame.getContentPane().add(lResultado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 124, 113);
		frame.getContentPane().add(scrollPane);
		
		JList lstColores = new JList();
		scrollPane.setViewportView(lstColores);
		lstColores.setModel(new AbstractListModel() {
			String[] values = new String[] {"Rojo", "Verde", "Azul"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		bAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lstColores.getSelectedIndex() == -1) lResultado.setText("Color seleccionado : ninguno");
				if(lstColores.getSelectedIndex() == 0) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
				if(lstColores.getSelectedIndex() == 1) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
				if(lstColores.getSelectedIndex() == 2) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
			}
			
		});
	}
}
