package Tema03_Libro;

import java.util.Scanner;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		//definicion de variables
		int num;
		Scanner stdin = new Scanner (System.in);

		
		//entrada de datos
		System.out.print("Introduce un numero: ");
		num = stdin.nextInt();
		
		stdin.close();
		
		//mostramos los numeros divisibles
		System.out.print("Los numeros divisibles de " + num + " son: ");
		for (int i = num ; i>0 ; i--) {
			if (num%i==0) System.out.print(i + " ");
		}
		

	}

}
