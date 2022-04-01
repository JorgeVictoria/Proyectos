package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio05 {

	public static void main(String[] args) {
		
		//variables definition
		Scanner stdin = new Scanner( System.in ); //para poder escribir datos
		double total; //total de dinero
		double euros; //para calcular cuantos euros hay en cada una de las monedas
		int monedas;  //iremos almacenando monedas
		
		// variables initialization
		total = 0;
		
		//reading data
		System.out.print("Cuantas monedas de 50 centimos?: ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.5;
		total = total + euros;
		
		System.out.print("Cuantas monedas de 20 centimos?: ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.2;
		total = total + euros;
		
		System.out.print("Cuantas monedas de 10 centimos?: ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.1;
		total = total + euros;
		
		System.out.print("Cuantas monedas de  5 centimos?: ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.05;
		total = total + euros;
		
		System.out.print("Cuantas monedas de  2 centimos?: ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.02;
		total = total + euros;
		
		System.out.print("Cuantas monedas de  1 centimo?:  ");
		monedas = stdin.nextInt(); //lectura de las monedas
		euros = (double)monedas * 0.01;
		total = total + euros;
		
		//print data
		System.out.printf("\nEn total tiene %.2f euros.", total);
		stdin.close();
	}

}
