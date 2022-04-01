import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejemplo05 {

	private JFrame frmPruebaDeWb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo05 window = new Ejemplo05();
					window.frmPruebaDeWb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ejemplo05() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPruebaDeWb = new JFrame();
		frmPruebaDeWb.setTitle("Prueba de wb");
		frmPruebaDeWb.setBounds(100, 100, 450, 300);
		frmPruebaDeWb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPruebaDeWb.getContentPane().setLayout(null);
		
		JLabel lbTexto = new JLabel("Hola");
		lbTexto.setOpaque(true);
		lbTexto.setBackground(Color.MAGENTA);
		lbTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lbTexto.setBounds(173, 110, 60, 38);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbTexto.setBackground(Color.cyan);
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		frmPruebaDeWb.getContentPane().add(btnNewButton);
		
		
		frmPruebaDeWb.getContentPane().add(lbTexto);
	}
}
