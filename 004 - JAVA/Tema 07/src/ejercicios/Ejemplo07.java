package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class Ejemplo07 {

	private JFrame frame;
	boolean arrastrando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo07 window = new Ejemplo07();
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
	public Ejemplo07() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbUp = new JLabel("Hola");
		lbUp.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		frame.getContentPane().add(lbUp, BorderLayout.NORTH);
		
		JLabel lbDown = new JLabel("h");
		lbDown.setSize(new Dimension(5096, 20));
		
		lbDown.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(lbDown, BorderLayout.SOUTH);
		
		lbUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lbUp.getText().isEmpty()) return;
				arrastrando = true;
			}
		});
		
		lbDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!arrastrando) return;
				arrastrando = false;
				if( e.getSource() == lbDown ) lbDown.setText(lbUp.getText());
			}
		});
		
		
		lbUp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}
			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});
		
		
	}

}
