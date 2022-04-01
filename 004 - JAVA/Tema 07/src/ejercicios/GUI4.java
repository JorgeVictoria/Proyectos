package ejercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI4 {

	private JFrame frame;
	private JList lstColores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI4 window = new GUI4();
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
	public GUI4() {
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
		
		bAceptar.setBounds(10, 179, 89, 23);
		frame.getContentPane().add(bAceptar);
		
		JLabel lResultado = new JLabel("");
		lResultado.setBorder(new LineBorder(new Color(0, 0, 0)));
		lResultado.setBounds(10, 224, 408, 26);
		frame.getContentPane().add(lResultado);
		
		JScrollPane Panel = new JScrollPane();
		Panel.setBounds(10, 28, 135, 130);
		frame.getContentPane().add(Panel);
		
		lstColores = new JList();
		lstColores.setModel(new AbstractListModel() {
			String[] values = new String[] {"Rojo", "Verde", "Azul"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		Panel.setViewportView(lstColores);
		
		bAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lstColores.getSelectedIndex() == -1) lResultado.setText("Color seleccionado : ninguno");
				if(lstColores.getSelectedIndex() == 0) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
				if(lstColores.getSelectedIndex() == 1) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
				if(lstColores.getSelectedIndex() == 2) lResultado.setText("Color seleccionado : " + lstColores.getSelectedValue().toString());
			}
		});
	}
}
