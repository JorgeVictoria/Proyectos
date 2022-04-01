
import java.awt.*;
import javax.swing.*;

public class ejemplo02c {

	public static void main( String[] args) {
		for(int i = 0; i < 1000; i++) {
			SimpleFrame f = new SimpleFrame();
			f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			f.setTitle("ventana" + i);
			f.setVisible(true);
		}
	}
}

		class SimpleFrame extends JFrame {
		 private static final int ANCHO = 300;
		 private static final int ALTO = 200;
		 public SimpleFrame() { // constructor
		 setSize(ANCHO, ALTO);
		 }
		}
