package TorresHanoi;

import javax.swing.JOptionPane;

public class Jugar {

	public static void main(String[] args) {
		
		//variables locales
		TorresDeHanoi k;
		
		//creamos el objeto
		k = new TorresDeHanoi();
		//se declara y crea la variable de la primera clase
		
		k.Captura();
		
		k.Intercambio(k.getNumeroDeDiscos(), 'A', 'B', 'C');
		// se reciben los parametros de el metodo Intercambio
		
		k.Movimientos();

	}

}
