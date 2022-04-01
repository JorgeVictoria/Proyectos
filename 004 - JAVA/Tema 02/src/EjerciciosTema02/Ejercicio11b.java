package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio11b {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //write data
		String dni; //almacena el dni
		String valor;  //almacena si es correcto o no el numero
				
		//entrada de datos
		System.out.print("Teclee DNI(8 digitos): ");
		dni = stdin.nextLine();
		stdin.close();
				
		//ver si es correcto o incorrecto
		valor = ((dni.length() == 8) && ((dni.charAt(0)>=48 && dni.charAt(0)<=57) && (dni.charAt(1)>=48 && dni.charAt(1)<=57) &&
				(dni.charAt(2)>=48 && dni.charAt(2)<=57) && (dni.charAt(3)>=48 && dni.charAt(3)<=57) &&
				(dni.charAt(4)>=48 && dni.charAt(4)<=57) && (dni.charAt(5)>=48 && dni.charAt(5)<=57) &&
				(dni.charAt(6)>=48 && dni.charAt(6)<=57) && (dni.charAt(7)>=48 && dni.charAt(7)<=57)))? "correcto":"incorrecto";

		//salida por pantalla
	    System.out.print(valor);

	}

}
