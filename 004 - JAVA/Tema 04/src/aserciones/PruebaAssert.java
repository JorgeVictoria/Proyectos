package aserciones;

import java.util.Scanner;

public class PruebaAssert {
	
	//variables globales
	static Scanner stdin = new Scanner(System.in);


	public static void main(String[] args) {
		
		//variables locales
		int n; //numero que introducimos
		
		//entrada de datos
		System.out.print("Escriba un numero entre 0 y 10: ");
		n = stdin.nextInt();
		
		//asegura que el valor sea >=0 y <=10
		//hay que activar las aserciones con -ea en run configurations
		assert (n >= 0 && n <= 10) : "numero incorrecto: " + n;
		System.out.printf("Usted escribio %d%n", n);

	}

}
