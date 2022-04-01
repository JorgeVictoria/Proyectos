package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio11a {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //write data
		int dni; //almacena el dni
		String cadena;  //para convertir el dni a cadena
		String valor;  //almacena si es correcto o no el numero
		
		//entrada de datos
		System.out.print("Teclee DNI(8 digitos): ");
		dni = stdin.nextInt();
		stdin.close();
		
		// Convertimos el numero a cadena
		cadena = Integer.toString(dni);
		
		//ver si es correcto o incorrecto
		valor = (cadena.length() == 8)? "correcto":"incorrecto";

		//salida por pantalla
		System.out.print(valor);
	}

}
