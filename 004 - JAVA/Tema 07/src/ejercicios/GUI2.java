package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Rectangle;

public class GUI2 {

	private JFrame frame;
	private final ButtonGroup bgColores = new ButtonGroup();
	private JRadioButton rbRojo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 window = new GUI2();
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
	public GUI2() {
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
		
		bAceptar.setBounds(10, 153, 89, 23);
		frame.getContentPane().add(bAceptar);
		
		JLabel lResultado = new JLabel("");
		lResultado.setBorder(new LineBorder(new Color(0, 0, 0)));
		lResultado.setBounds(0, 236, 434, 25);
		frame.getContentPane().add(lResultado);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "colores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 108, 131);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		rbRojo = new JRadioButton("Rojo");
		rbRojo.setBounds(new Rectangle(6, 18, 47, 23));
		rbRojo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rbRojo.setSelected(true);
		bgColores.add(rbRojo);
		rbRojo.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(rbRojo);
		
		JRadioButton rbAzul = new JRadioButton("Azul");
		rbAzul.setBounds(new Rectangle(6, 51, 47, 23));
		bgColores.add(rbAzul);
		rbAzul.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(rbAzul);
		
		JRadioButton rbVerde = new JRadioButton("Verde");
		rbVerde.setBounds(new Rectangle(6, 84, 55, 23));
		panel.add(rbVerde);
		bgColores.add(rbVerde);
		rbVerde.setHorizontalAlignment(SwingConstants.LEFT);
		
		bAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbRojo.isSelected()) {
					lResultado.setText(rbRojo.getText());
					lResultado.setForeground(Color.red);
				}
				
				if(rbAzul.isSelected()) {
					lResultado.setText(rbAzul.getText());
					lResultado.setForeground(Color.blue);
				}
				
				if(rbVerde.isSelected()) {
					lResultado.setText(rbVerde.getText());
					lResultado.setForeground(Color.green);
				}
			}
		});
	}
}
