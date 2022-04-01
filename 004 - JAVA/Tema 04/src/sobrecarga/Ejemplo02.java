package sobrecarga;

import java.util.Scanner;

public class Ejemplo02 {
	
	//variables globales
	private static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		
		//variables locales
		char letra; //letra que queremos repetir
		int rep; //cuantas veces queremos repetir la cadena
		
		//entrada de datos
		System.out.print("Introduzca una letra: ");
		letra = stdin.next().charAt(0);
		System.out.print("Introduzca nº repeticiones:");
		rep = stdin.nextInt();
		
		//llamada a funcion
		rellena(letra,rep);
		
		//fin
		System.out.println("fin");

	} //fin main
	
	private static void rellena(char c,int n) {
		
		if (n <= 0)System.out.println(); //si n es cero, se imprime una linea en blanco
		else {
			for (int i = 0; i < n; i++) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}

}
