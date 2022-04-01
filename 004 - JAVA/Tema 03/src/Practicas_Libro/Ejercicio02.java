package Practicas_Libro;

public class Ejercicio02 {

	public static void main(String[] args) {
		
		//definicion de variables
		int dado1;
		int dado2;
		int contador;
		
		//inicializamos las variables
		contador = 0;
		
		//pantalla inicial
		System.out.println("DADO1 DADO2");
		
		//tirada de dados
		do {
			dado1 = (int)(Math.random() * 6 ) + 1;
			dado2 = (int)(Math.random() * 6 ) + 1;
			contador++;
			System.out.print("  " + dado1 + "     ");
			System.out.println(dado2);
		} while (dado1 != 1 || dado2 != 1);
		
		System.out.println("Snake Eyes en " + contador + " veces");
			
	}

}
