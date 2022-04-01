package ejercicio04;

import java.util.Scanner;

public class U04E04 {

	public static void main(String[] args) {
		//variables locales
		long num,a,b;
		Scanner stdin = new Scanner(System.in);
		
		//creamos un modulo
		System.out.print("Introduzca modulo: ");
		num = stdin.nextLong();
		Zp63 mod1 = new Zp63(num);
		
		//probamos metodo modulo
		if(mod1.getPrimo() == true) System.out.println(mod1.getModulo() + " es primo");
		else System.out.println(mod1.getModulo() + " no es primo");
		
		//suma de numeros
		//SUMA
		System.out.print("Introduzca numero1: ");
		a = stdin.nextLong();
		System.out.print("Introduzca numero2: ");
		b = stdin.nextLong();
		System.out.println(mod1.suma(a, b));
		
		//resta de numeros
		//RESTA
		System.out.print("Introduzca numero1: ");
		a = stdin.nextLong();
		System.out.print("Introduzca numero2: ");
		b = stdin.nextLong();
		System.out.println(mod1.resta(a, b));
		
	}

}
