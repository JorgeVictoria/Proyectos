import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejemplo00 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("Dibujar"); //creamos el JFrame
		f.setBounds(100, 100, 400, 400);  //le damos posicion y tamaño
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer cuando tamaño
		f.setVisible(true); //hacemos visible el jframe
		
		JPanel p = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.blue);
				g.drawOval(10, 10,30, 30);
				g.dispose();
			}
		}; //creamos la superficie
		p.setLayout(null);   	  //anulamos el gestor de las cosas de la superficie
		f.setContentPane(p);	  //añadimos la superficie al frame, como content pane
		
		//dibujamos el panel
		//Graphics g = p.getGraphics(); //pedimos el contexto grafico
		
		
		
		
		
	}

}
