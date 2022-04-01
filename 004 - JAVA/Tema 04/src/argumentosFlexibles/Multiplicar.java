package argumentosFlexibles;

import java.util.Scanner;

public class Multiplicar {
	
	//variables globales
	private static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		
		//variables locales
		int num; //numero que pedimos
		
		//pedimos un numero
		System.out.print("Introduce un numero: ");
		num = stdin.nextInt();
				
		//llamada a funcion
		tablaMultiplicar(num,1,2,3,4,5,6,7,8,9,10);

	}
	
	private static void tablaMultiplicar(int n, int... v) {
		
		//mostramos datos
		System.out.println("Tabla de multiplicar de: " + n);
		for (int i = 0; i < v.length; i++) {
			System.out.println(v[i] + " x " + n + " = " + (v[i] * n));
		}
	}

}
