package juegos;

import java.util.Scanner;

public class AdivinaNumero {
	 
	//variables de la clase
	private static Scanner stdin;
	private static int partidasJugadas; //nº de partidas jugadas
	private static int partidasGanadas; //nº de partidas ganadas
	
	//variables de los objetos

	public static void main(String[] args) {
		
		//variables locales
		boolean otraVez;
		
		//inicializacion de variables
		stdin = new Scanner (System.in);
		partidasJugadas = 0;
		partidasGanadas = 0;
		
		//presentacion por pantalla
		System.out.println("Vamos a jugar a que adivines un numero");
		System.out.println("entre 1 y 100.");
		
		do {
			juegaPartida(); //llamada a subrutina
			System.out.print("Juegas otra vez? (S/N)");
			otraVez = stdin.next().equals("S")? true:false;
			} while (otraVez);
		
		//mensaje de despedida
		System.out.println("Has jugado " + partidasJugadas);
		System.out.println("Has ganado " + partidasGanadas);
		System.out.println("Gracias por jugar. Chao");

	} //fin main

	private static void juegaPartida() {
		
		//variables locales
		int numero;    //el numero aleatorio
		int respuesta; //el numero indicado por el usuario
		int intentos;  //nº de intentos
		
		//inicializamos variables
		numero = (int)(100 * Math.random()) + 1;
		intentos = 0;
		
		//primer numero
		System.out.print("Introduce numero: ");
		//juego
		while(true) {
			respuesta = stdin.nextInt(); //leer respuesta
			intentos++;
			
			//si acertamos
			if(respuesta==numero) {
				System.out.println("Acertado en " + intentos + " intentos! Has ganado. El numero era " + numero);
				partidasGanadas++;
				break;
			}
			
			//si agotamos las vidas
			if(intentos == 6) {
				System.out.println("Has perdido, limite 6 intentos.");
				System.out.println("El numero era " + numero);
				break;
			}
			
			//si vamos fallando, damos pistas
			if (respuesta < numero) System.out.print("Demasiado bajo. Prueba otra vez: ");
			else if (respuesta > numero)  System.out.print("Demasiado alto. Prueba otra vez: ");
				
		}
		System.out.println();
		partidasJugadas++;
	} //fin juegaPartida
} //fin clase AdivinaNumero
