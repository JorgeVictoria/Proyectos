package Practicas_Libro;

import java.util.Scanner;

public class Ejercicio05a {

	public static void main(String[] args) {
		
		//Definicion de variables
		String cadena;
		Scanner stdin = new Scanner (System.in);
		
		//lectura de datos
		System.out.print("Introduce una cadena: ");
		cadena = stdin.nextLine();
		
		//creamos el array
		String[] arrOfStr = cadena.split(" ");
		
		//corremos el array e imprimimos la cadena partida
		for(String a : arrOfStr) System.out.println(a);
		
		//cierre de stdin
		stdin.close();

	}

}
