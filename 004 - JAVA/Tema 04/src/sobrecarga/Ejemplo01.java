package sobrecarga;

import java.util.Scanner;

public class Ejemplo01 {
	
	//variables globales
	private static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		
		//variables locales
		int num; //numero que pedimos
		
		//pedimos numero
		do {
			System.out.println("Introduzca un numero entero: ");
			num = stdin.nextInt();
			if (num > 0) imprimeDivisores(num);
		}while (num > 0);

	} //fin de main
	
	static void imprimeDivisores (int n) {
		
		//variables locales
		int d; //uno de los divisores de n
		
		//mostramos info por pantalla
		System.out.print("Los divisores de " + n + " son:");
		
		//calculamos y mostramos los divisores de n
		for (d = 1; d <= n;d++ ) {
			if(n % d == 0)  System.out.print(" " + d); //d divide a n?
		}
		
		System.out.println();
		
	} //fin de imprimeDivisores

}
