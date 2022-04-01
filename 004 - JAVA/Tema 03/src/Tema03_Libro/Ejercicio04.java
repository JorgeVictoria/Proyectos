package Tema03_Libro;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Ejercicio04 {

	public static void main(String[] args) {
		
		//definicion de variables
		int dia; //dia de la semana
		Scanner stdin = new Scanner (System.in);
		
		//lectura de datos
		System.out.print("Introduce dia de la semana(1-7): ");
		try {
			dia = stdin.nextInt();
		} catch (InputMismatchException e) {
			dia = 0;
		}
		
		stdin.close();
		
		//mostramos datos por pantalla
		switch (dia) {
		case 1 : System.out.println("Lunes");
			break;
		case 2 : System.out.println("Martes");
			break;
		case 3 : System.out.println("Miercoles");
			break;
		case 4 : System.out.println("Jueves");
			break;
		case 5 : System.out.println("Viernes");
			break;
		case 6 : System.out.println("Sabado");
			break;
		case 7 : System.out.println("Domingo");
			break;
		default : System.out.println("Mi no entender");
		}
	}

}
