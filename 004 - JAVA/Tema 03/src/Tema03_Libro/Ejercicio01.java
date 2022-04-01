package Tema03_Libro;

import java.util.Scanner;

public class Ejercicio01 {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin= new Scanner (System.in);
		int n1,n2,n3;
		
		//entrada de datos
		System.out.print("Teclee un primer entero n1: ");
		n1= stdin.nextInt();
		System.out.print("Teclee un primer entero n2: ");
		n2= stdin.nextInt();
		System.out.print("Teclee un primer entero n3: ");
		n3= stdin.nextInt();
		stdin.close();
		
		//ordenacion y salida por pantalla
		if (n1 > n2 && n1 > n3) {
			if (n2 > n3) System.out.println(n1 + " " + n2 + " " + n3);
			else System.out.println(n1 + " " + n3 + " " + n2);
		} else if (n1 < n2 && n1 < n3) {
			if (n2 > n3) System.out.println(n2 + " " + n3 + " " + n1);
			else System.out.println(n3 + " " + n2 + " " + n1);
		} else {
			if (n2 < n3) System.out.println(n3 + " " + n1 + " " + n2);
			else System.out.println(n2 + " " + n1 + " " + n3);
		}
	}

}
