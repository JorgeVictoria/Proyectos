package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio09 {

	public static void main(String[] args) {
		
		//definicion de variables
		Scanner stdin = new Scanner( System.in ); //write data
		String nombre; //almacena el nombre
		int nota1,nota2,nota3; //notas de clase
		double media;  //nota media del alumno
		
		//lectura de datos
		nombre = stdin.nextLine();
		nota1 = stdin.nextInt();
		nota2 = stdin.nextInt();
		nota3 = stdin.nextInt();
		stdin.close();
		
		//calculo de la media
		media = (nota1 + nota2 + nota3) / (double)3;
		
		//mostramos datos por pantalla
		System.out.println(" Alumno: " + nombre);
		System.out.println(" Notas: " + nota1 + ", " + nota2 + " y " + nota3);
		System.out.printf(" Media: %.2f",media);
		
	}

}
