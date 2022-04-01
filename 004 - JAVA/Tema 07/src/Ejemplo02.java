// Fichero U7Ejemplo2.java
//package simpleFrame; // Comenta la línea para ejecutar desde consola
import java.awt.*;
import javax.swing.*;


public class Ejemplo02 {

	public static void main( String[] args) {
		
		 SimpleFrame f = new SimpleFrame();
		 f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  	//define que pasará cuando el usuario cierre el frame
		 f.setVisible(true);									//hacemos el frame visible, ya que por defecto no lo es
		 }
		}

		class SimpleFrame extends JFrame {
		 private static final int ANCHO = 300;
		 private static final int ALTO = 200;
		 public SimpleFrame() { // constructor
		 setSize(ANCHO, ALTO);
		 }
		}
