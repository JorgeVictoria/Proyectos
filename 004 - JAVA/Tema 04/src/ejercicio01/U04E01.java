package ejercicio01;

import java.util.Scanner;

public class U04E01 {

	public static void main(String[] args) {
		
		//variables locales
		Fraccion f1; //primera fraccion
		Fraccion f2; //segunda fraccion
		Fraccion f3; //tercera fraccion
		
		int numer; //para introducir un numerador
		int denom; //para introducir un denominador
		int entero; //para introducir un entero
		
		double decimal; //para introducir un numero decimal
		
		Scanner stdin = new Scanner(System.in);
		
		//datos iniciales
		System.out.print("Introduce un numerador:");
		numer = stdin.nextInt();
		System.out.print("Introduce un denominador:");
		denom = stdin.nextInt();
		System.out.print("Introduce un numero decimal:");
		decimal = stdin.nextDouble();
		System.out.print("Introduce un numero entero:");
		entero = stdin.nextInt();
		
		//creamos la primera fraccion
		f1 = new Fraccion();
		
		//mostramos datos
		System.out.println("Primera: " + f1.toString() );
		
		//creamos la segunda fraccion
		f2 = new Fraccion (numer,denom);
		
		//mostramos datos
		System.out.println("Segunda: " + f2.toString() );
		
		//creamos la tercera fraccion
		f3 = new Fraccion (decimal);
		
		//mostramos datos
		System.out.println("Equivalente de " + f3.getDecimal() + ":" + f3.toString());
		System.out.println("Numerador de " + f3.toString() + " es " + f3.getNumerador());
		System.out.println("Denominador de " + f3.toString() + " es " + f3.getDenominador());
		
		//suma de fracciones. La segunda + la tercera
		System.out.println("La suma de " + f2.toString() + " y " + f3.toString() + " es " + f2.suma(f3) );
		
		//suma de una fraccion y entero
		System.out.println("La suma de " + f2.toString() + " y " + entero + " es " + f2.suma(entero) );
		
		//comparar fracciones
		System.out.println( f2.toString() + " es igual a " + f3.toString() + " : " + f2.equals(f3));
		System.out.println( f1.toString() + " es igual a " + f3.toString() + " : " + f1.equals(f3));
	}

}
