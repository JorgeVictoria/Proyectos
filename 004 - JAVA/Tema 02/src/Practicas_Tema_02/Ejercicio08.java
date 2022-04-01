package Practicas_Tema_02;

import java.util.Scanner;
public class Ejercicio08 {

	public static void main(String[] args) {
		
		//definicion de variables
		int velocidad;
		double multa;
		Scanner stdin = new Scanner (System.in); //variable de tipo scanner para lectura de datos
		
		//lectura de la velocidad
		System.out.print("Velocidad (Kms/h): ");
		velocidad = stdin.nextInt();
		stdin.close();
		
		multa = (velocidad>=0 && velocidad<60) ? 300:(velocidad>=60 && velocidad<120)?0:(velocidad>=120 && velocidad<140)?300:
			(velocidad>=140 && velocidad<180)?600:1200;
		
		System.out.printf("Multa: %.2f €", multa);
		

	}

}
