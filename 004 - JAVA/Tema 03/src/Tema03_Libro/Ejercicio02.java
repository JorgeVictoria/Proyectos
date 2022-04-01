package Tema03_Libro;

import java.util.Scanner;

public class Ejercicio02 {

	public static void main(String[] args) {

		//definicion de variables
		int aprobados = 0;
		int suspendidos = 0;
		int flag = 0;
		int nota;
		Scanner stdin = new Scanner (System.in);
		
		
		//vamos pidiendo notas
		while (flag==0) {
			System.out.print("Introduce nota: ");
			nota = stdin.nextInt();
			if (nota <0 || nota > 10 ) {
				flag = 1;
			} else if (nota >=0 && nota <= 5) {
				suspendidos++;
			} else 
				{ 
				aprobados ++;
				}
		}
		
		//cierre del stdin
		stdin.close();
		
		System.out.println("aprobados= " + aprobados);
		System.out.println("suspendidos= " + suspendidos);

	}

}
