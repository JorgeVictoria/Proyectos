package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio06 {

	public static void main(String[] args) {
		
		//variables definition
		Scanner stdin = new Scanner( System.in ); //write data
		int huevos; //cuantos huevos
		int envases; //cuantos envases
		
		//lectura de datos
		System.out.print("Cuantos huevos han puesto hoy? ");
		huevos = stdin.nextInt();
		
		//dividimos en envases grandes
		envases = (huevos > 144)? huevos/144:0;
		huevos = huevos % 144;
		System.out.println(envases + " envases grandes");
		
		//dividimos en envases de una docena
		envases = (huevos > 12)? huevos/12:0;
		huevos = huevos % 12;
		System.out.println(envases + " envases de una docena");
		
		//dividimos en envases de media docena
		envases = (huevos > 6)? huevos/6:0;
		huevos = huevos % 6;
		System.out.println(envases + " envases de media docena");
		
		//se imprimen los huevos sin envasar
		System.out.println("Te quedan " + huevos + " sin envasar.");
		stdin.close();
	}

}
