package TorresHanoi;

import javax.swing.JOptionPane;

public class TorresDeHanoi {
	
	//variables del objeto
	private int numeroDeDiscos; 		//nº de discos
	private int numeroDeMovimientos;	//nº de movimientos
	
	//getters
	public int getNumeroDeDiscos() {
		return numeroDeDiscos;
	}
	
	public int getNumeroDeMovimientos() {
		return numeroDeMovimientos;
	}
	
	//setters
	public void setNumeroDeDiscos(int NumeroDeDiscos) {
		this.numeroDeDiscos = NumeroDeDiscos;
	}

	public void setNumeroDeMovimientos(int NumeroDeMovimientos) {
		this.numeroDeMovimientos = NumeroDeMovimientos;
	}
	
	//subrutinas
	
	public void Captura() {
		numeroDeDiscos = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos discos)"));
	}
	
	public void Intercambio(int NumDiscos, char A, char B, char C) {
		//los parametros se envian a la segunda clase
		/*Tomemos en cuenta que el parámetro A, tomará el lugar de
		 la torre inicio, la que inicialmente contiene todos los
		 discos; el parámetro B, será la torre auxiliar; y el
		 parámetro C será la torre destino, donde quedarán
		 al final de juego todas las fichas */
		
		if(NumDiscos == 1) {
			/* si el número de discos es igual a uno, lógicamente se
			 moverá el disco de la torre inicio directamente a la
			 de destino*/
			setNumeroDeMovimientos (getNumeroDeMovimientos()+1);
			JOptionPane.showMessageDialog(null, "Mover disco " + NumDiscos + " de la torre " + A + " a la torre " + C + "\nMOVIMIENTOS: " + numeroDeMovimientos);
		}
		else {
			/* se realizarán mas movimientos y serán los siguientes*/
			Intercambio(NumDiscos-1,A, C, B);
			/*... y entonces el método se llama a sí mismo, moviendo
			 primero, el disco de la torre A, inicio, a la torre C,
			 destino */
			setNumeroDeMovimientos(getNumeroDeMovimientos()+1);
			JOptionPane.showMessageDialog(null, "Mover disco " + NumDiscos + " de la torre " + A + " a la torre " + C + "\nMMOVIMIENTOS: " + numeroDeMovimientos);
			Intercambio(NumDiscos-1,B,A,C);
			/*... el método vuelve a llamarse a sí mismo, esta vez
			 los parámetros donde de se encontraba la variable
			 A ahora estará la variable B, para indicar el siguiente
			 movimiento*/

		}
	}
	
	public void Movimientos() {
		JOptionPane.showMessageDialog(null, "TOTAL DE MOVIMIENTOS: " + numeroDeMovimientos);
	}
}
