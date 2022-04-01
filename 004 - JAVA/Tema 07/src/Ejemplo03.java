import java.awt.*;
import javax.swing.*;

public class Ejemplo03 {

	public static void main( String[] args) {
			
			//ejecuta sentencias dentro del event dispatch thread
			EventQueue.invokeLater( 
					 new Runnable() {
						 public void run() {
							 
							 //para que el programa averigue las dimensiones de la pantalla
							 Toolkit kit = Toolkit.getDefaultToolkit();
							 Dimension pantalla = kit.getScreenSize();
							 int ancho = pantalla.width;
							 int alto = pantalla.height;
							 System.out.println(ancho + " , " + alto);
							 
							 
							 SimpleFrame f= new SimpleFrame();
							 //f.setLocationByPlatform(true); //será el sistema operativo quien gestione la posicion
							 f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
							 f.setVisible(true);
							 //f.setLocation(100, 100); //para modificar la posicion donde queremos que aparezca
							 //f.setBounds(100, 100, 400, 400); //modifica la posicion de salida y su tamaño
							 //f.setUndecorated(false); //supuestamente elimina decoraciones del frame. Da errores
						 } // fin método run de Runnable
					 } //fin clase y Objeto Runnable, es una clase anónima!!
				); // fin llamada a EventQueue.invokelater()
			} //fin del main
		
	}//fin de la clase


		class SimpleFrame extends JFrame {
		 private static final int ANCHO = 300;
		 private static final int ALTO = 200;
		 
		// constructor
		 public SimpleFrame() { // constructor
		 setSize(ANCHO, ALTO);
		 }
		}
