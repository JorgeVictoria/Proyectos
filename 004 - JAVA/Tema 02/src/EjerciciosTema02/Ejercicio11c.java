package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio11c {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //write data
		String dni; //almacena el dni
		String valor;  //almacena si es correcto o no el numero
		
		//entrada de datos
		System.out.print("Teclee DNI(8 digitos): ");
		dni = stdin.nextLine();
		stdin.close();
		
		//comprobamos si el dni cumple un patron
		valor = (dni.matches("[0-9]{8}") == true)? "correcto":"incorrecto";
		
		//salida por pantalla
	    System.out.print(valor);


	}

}
