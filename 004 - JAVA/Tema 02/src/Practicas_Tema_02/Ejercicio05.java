package Practicas_Tema_02;

import java.util.Scanner;

public class Ejercicio05 {

	public static void main(String[] args) {
		
		//declaracion de constantes para el calculo de la nota escalada
		final int A = 0;
		final int B = 10;
		final int C = 10;
		final int D = 50;
		

		//declaracion variables
		double pesoCuestionario; //almacena el peso del cuestionario
		double pesoActividad; //almacena el peso de la actividad
		String mensajeError; //almacena un mensaje que mostraremos por pantalla
		double notaCuestionario;
		double notaActividad;
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		double notaPesos; //nota final por pesos
		double notaEscalada; //nota final escalada
		
		
		//inicializacion de variables
		mensajeError ="";
		
		//entrada de pesos
		System.out.print("Teclee peso del cuestionario (0.0-1.0): ");
		pesoCuestionario = stdin.nextDouble();
		System.out.print("Teclee peso de la actividad:¡ (0.0-1.0): ");
		pesoActividad = stdin.nextDouble();
		
		//comprobamos que la suma del peso de ambas pruebas sea 1. Mostramos mensaje
		//Tambien comprobamos que ambos pesos no sean negativos
		mensajeError += (pesoCuestionario >= 0) && (pesoActividad >= 0) && (pesoCuestionario + pesoActividad == 1)? "": "  ERROR EN PESOS!!";
		System.out.println(mensajeError);
		
		//entrada de notas
		System.out.print("Nota de cuestionario en [0-10]: ");
		notaCuestionario = stdin.nextDouble();
		System.out.print("Nota de actividad en [0-10]: ");
		notaActividad = stdin.nextDouble();
		
		//cerramos la entrada de datos
		stdin.close();
		
		//calculo de notas
		notaPesos = (notaCuestionario * pesoCuestionario)+(notaActividad * pesoActividad);
		notaEscalada = C+(((notaPesos-A)/(B-A))*(D-C));
		
		//salida de notas
		System.out.printf("Nota en [0,  10] es %.2f\n",notaPesos );
		System.out.printf("Nota en [10,  50] es %.2f",notaEscalada );
		
		
	}

}
