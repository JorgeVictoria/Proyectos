package returns;

import java.util.Scanner;

public class Pedrsco03 {
	
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
			n = sigTermino(n);
			contador++;  //un nuevo termino
			System.out.println(n); //se imprime el termino
		}
		
		System.out.println();
		System.out.println("Hay " + contador + " terminos.");
		
	} //fin secuenciaPedrisco
	
	static int sigTermino(int actual) {
		
		//variables locales
		int respuesta;
		
		//calculo de siguiente termino
		if (actual % 2 == 1) respuesta = 3 * actual + 1;
		else respuesta =  actual / 2;
		
		//retorno
		return respuesta;
	} //fin funcion sigTermino
	
} //fin de la clase Pedrisco