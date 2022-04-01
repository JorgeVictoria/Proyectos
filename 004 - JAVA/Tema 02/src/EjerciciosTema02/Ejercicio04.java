package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio04 {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //para poder escribir datos
		String nombre; //nombre del usuario
		
		//entrada de datos
		System.out.print("Como se llama?: "); //con print no hay salto de linea
		nombre = stdin.nextLine(); //lectura del nombre
		stdin.close();
		
		//salida de datos, en mayusculas
		System.out.println("Hola," + nombre.toUpperCase());
	}

}
