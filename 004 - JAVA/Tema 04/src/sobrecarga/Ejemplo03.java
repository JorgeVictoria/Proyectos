package sobrecarga;

import java.util.Scanner;

public class Ejemplo03 {
	
	//variables globales
	private static Scanner stdin = new Scanner (System.in);

	public static void main(String[] args) {
	  
		//variables locales
		String cadena; //cadena que leemos
		
		//entrada de datos
		System.out.print("Introduce un texto: ");
		cadena = stdin.nextLine();
		
		//llamada a funcion
		rellena25(cadena);
		
		//FIN
		System.out.println("fin");

	} //fin del main
	
	private static void rellena25 (String s) {
		
		for(int i = 0; i < s.length(); i++) {
			rellena (s.charAt(i),25); //llamada a subrutina
		}
	}//fin rellena 25
	
	private static void rellena(char c, int n) {
		
		for (int j = 0; j < n; j++) {
			System.out.print(c + " ");
		}
		System.out.println();
		
	}

}
