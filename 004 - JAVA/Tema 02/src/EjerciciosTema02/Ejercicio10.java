package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio10 {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //write data
		int caballos; //almacena los caballos
		double kilowatios; //almacena la potencia en kilowatios
		final double watios = 735.499; //constante que almacena el valor en watios de 1 cv
		
		//datos de entrada
		System.out.print("Introduce los CV: ");
		caballos = stdin.nextInt();
		stdin.close();
		
		//calculo
		kilowatios = (double)caballos * watios / 1000;
		
		//mostrar en pantalla
		System.out.printf("Equivalen a %.2f Kw", kilowatios);
		
	}

}
