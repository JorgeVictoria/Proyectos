import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ejemplo04 {

	public static void main(String[] args) {
			
		
		
		//creamos el borde
		JFrame f = new JFrame("Eventos");
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creamos el panel
		JPanel p = new JPanel();
		p.setLayout(null);
		
		//creamos un boton
		JButton bBoton = new JButton("Salir");
		p.add(bBoton);
		bBoton.setBounds(10, 10, 80, 40);
		
		//creamos un jlabel
		JLabel lbTexto = new JLabel("Hola");
		
		lbTexto.setBounds(80, 80, 60, 40);
		lbTexto.setOpaque(true);
		lbTexto.setBackground(Color.red);
		p.add(lbTexto);
		
		
		//creamos un listener
		bBoton.addActionListener( (e) -> lbTexto.setBackground(Color.green) );
		
		//le pasamos el panel al frame
		f.setContentPane(p);
		f.setVisible(true);
		
	}

}
