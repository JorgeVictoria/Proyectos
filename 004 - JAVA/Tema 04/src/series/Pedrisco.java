package series;

import java.util.Scanner;

public class Pedrisco {
	
	//variables globales
	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		
		//variables locales
		int k;
		
		//informacion inicial
		System.out.println("Usar la secuencia Pedrisco (3N+1");
		System.out.println("comenzando por el valor que indiques.");
		System.out.println();
		
		do {
			System.out.println("Valor inicial (0 para acabar): ");
			k = stdin.nextInt(); //leer valor
			if (k>0) secuenciaPedrisco(k);  //llamada a funcion
		} while (k > 0); //continua si k es mayor que 0

	} //fin main

	static void secuenciaPedrisco(int inicio) {
		
		//variables locales
		int n;         //un termino de la secuencia
		int contador;  //el numero de terminos
		
		//inicializacion de variables
		n=inicio;
		contador=1;
		
		//msotramos info por pantalla
		System.out.println("La secuencia 3N+1 comienza en " + n);
		System.out.println();
		System.out.println(n); //se imprime el primer termino
		
		while(n > 1) {
			if (n % 2 == 1) n = 3 * n + 1; //si n es impar
			else n = n / 2; //si n es par
			contador++;  //un nuevo termino
			System.out.println(n); //se imprime el termino
		}
		
		System.out.println();
		System.out.println("Hay " + contador + " terminos.");
		
	} //fin secuenciaPedrisco
} //fin de la clase Pedrisco
