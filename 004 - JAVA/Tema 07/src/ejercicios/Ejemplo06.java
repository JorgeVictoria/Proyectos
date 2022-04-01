package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ejemplo06 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo06 window = new Ejemplo06();
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
	public Ejemplo06() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbMensajes = new JLabel("");
		
		frame.getContentPane().add(lbMensajes, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbMensajes.setText("Has hecho click en la coordena x: " + e.getX() + " y coordenada y: " + e.getY());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(Color.GREEN);
			}
		});
	}

}
