package Practicas_Libro;

import java.util.Scanner;

public class Ejercicio05b {

	public static void main(String[] args) {
		
		//declaracion de variables
		String cadena; //cadena que introducimos
		Scanner stdin = new Scanner(System.in);  //para la lectura de datos
		int longitudCadena;  //variable que almacena la longitud de la cadena
		
		//entrada de datos
		System.out.print("Introduce cadena: ");
		cadena = stdin.nextLine();
		
		//cerramos la entrada de datos
		stdin.close();
		
		//calculo de la longitud de la cadena
		longitudCadena = cadena.length();
		
		//corremos la cadena, caracter a caracter.
		//Si el caracter no un espacio, se imprime
		//Si es un espacio, salto de linea
		for(int i = 0 ; i < longitudCadena; i++ ) {
			if (cadena.charAt(i)==32) System.out.println();
			else System.out.print((char)cadena.charAt(i));
		}
		
	}

}
