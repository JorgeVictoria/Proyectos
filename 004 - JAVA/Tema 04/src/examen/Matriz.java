package examen;

import java.util.Scanner;

public class Matriz {
	
	//variables globales
	private static Scanner stdin = new Scanner (System.in);  //para la entrada de datos
	private static int filas; //numero de filas de la matriz
	private static int columnas; //numero de columnas de la matriz
	private static int[][] matriz;
	private static int longitud; //almacena la longitud de la matriz

	public static void main(String[] args) {
		
		//preguntamos por el numero de filas
		System.out.print("Introduce el numero de filas:");
		filas = stdin.nextInt();

		//preguntamos por el numero de columnas
		System.out.println("introduce el numero de columnas:");
		columnas = stdin.nextInt();
		
		//creamos el array
		matriz = new int[filas][columnas];
		
		//rellenamos el array con numeros
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = (int)(Math.random() * 10);
			}
		}
		
		//si la matriz solo tiene una fila/columna
		if (filas == 1 && columnas == 1) {
			longitud = matriz[0][0];
			System.out.print("la longitud es " + longitud);
		}
		
		
		//si la matriz es 2x2
		if (filas == 2 && columnas == 2) {
			longitud = matriz[0][0]*matriz[1][1] - matriz[0][1]*matriz[1][0];
			System.out.print("la longitud es " + longitud);
		}
		

	}

}
