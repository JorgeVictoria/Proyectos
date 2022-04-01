package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		//variables definition
		Scanner stdin = new Scanner( System.in ); //write data
		double fuerza; //almacena la fuerza
		double distancia; //almacena la distancia
		double grados;  //almacena los grados
		double trabajo; //almacena el trabajo
		
		//entrada de datos
		System.out.print("Teclee fuerza (Newtons):");
		fuerza = stdin.nextDouble();
		System.out.print("Teclee distancia (metros):");
		distancia = stdin.nextDouble();
		System.out.print("Teclee angulo (grados):");
		grados = stdin.nextDouble();
		
		//calculo del trabajo
		grados = Math.toRadians(grados); //para calcula el coseno hay que pasar el angulo a radianes
		trabajo = fuerza * distancia * Math.cos(grados);
		
		//salida por pantalla
		System.out.printf("Trabajo = %.6f julios", trabajo);
		stdin.close();

	}

}
