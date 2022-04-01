
import java.awt.*;
import javax.swing.*;

public class Ejemplo02b {

	public static void main(String[] args) {
		
		SimpleFrame f1 = new SimpleFrame();
		 f1.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 f1.setTitle("ventana1");
		 f1.setVisible(true);
		 
		 SimpleFrame f2 = new SimpleFrame();
		 f2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 f2.setTitle("ventana2");
		 f2.setVisible(true);
		 
		 SimpleFrame f3 = new SimpleFrame();
		 f3.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 f3.setTitle("ventana3");
		 f3.setVisible(true);
		 
		 }
		}

		class SimpleFrame extends JFrame {
		 private static final int ANCHO = 300;
		 private static final int ALTO = 200;
		 
		public SimpleFrame() { // constructor
		 setSize(ANCHO, ALTO);
		 }

		}

