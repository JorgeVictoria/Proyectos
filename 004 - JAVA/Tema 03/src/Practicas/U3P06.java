package Practicas;

import java.util.Arrays;


public class U3P06 {

	public static void main(String[] args) {
		
		//definicion de variables
		int [] miArray = new int[1000000]; //creamos el array
		int total=0; //para almacenar cuantos numeros coinciden
		int num; //numero aleatorio
		int pos=0; //para la busqueda binaria
		long tInicial;
		long tFinal;
		
		double tiempo;
		
		float porcentaje; //porcentaje de numeros encontrados
		
		boolean encontrado = false;
		
		//rellenamos el array
		for(int i = 0; i < miArray.length; i++) {
			miArray[i] = (int)(Math.random() * 50000);
		}
		
		System.out.println();
		System.out.println("Generando y buscando numeros de forma secuencial...");
		
		//generamos numeros aleatorios y los vamos comparando
		tInicial = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			num = (int)(Math.random() * 100000);
			for (int j = 0; j < miArray.length; j++) {
				if(num == miArray[j] && encontrado == false) {
					total++;
					encontrado = true;
				}
				
			}
			encontrado=false;
		}
		
		porcentaje = (float)(100*total)/1000;
		System.out.printf("Encontrados:    %4.2f", porcentaje);
		System.out.printf("\nNo encontrados: %4.2f", (100-porcentaje));
		tFinal = System.currentTimeMillis();
		
		tiempo = (float)(tFinal - tInicial)/1000;
		System.out.printf("\nTiempo: %4.2f seg", tiempo);
		
		//ordenamos el array
		Arrays.sort(miArray);
		
		total=0;
		System.out.print("\n\nGenerando y buscando numeros de forma binaria...");
		//generamos numeros aleatorios y usamos la funcion binarysearch
		tInicial = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			num = (int)(Math.random() * 100000);
			pos = Arrays.binarySearch(miArray, num);
			if(pos >= 0) total++;
		}
	
		porcentaje = (float)(100*total)/1000;
		System.out.printf("\nEncontrados:    %4.2f", porcentaje);
		System.out.printf("\nNo encontrados: %4.2f", (100-porcentaje));
		tFinal = System.currentTimeMillis();
		
		tiempo = (float)(tFinal - tInicial)/1000;
		System.out.printf("\nTiempo: %4.2f seg", tiempo);
		
	}

}
