package Test01_Libro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio05 {

	public static void main(String[] args) {
		
		int num=0;
		Scanner stdin = new Scanner (System.in);
		
		System.out.print("Introduzca un numero: ");
		
		try {
			num = stdin.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("entrada incorrecta");
			System.exit(0);
		}
		
		stdin.close();
		
		if (num%2==0) {
			System.out.print("El numero " + num + " es par");
		} else System.out.print("El numero " + num + " es impar");

	}

}
