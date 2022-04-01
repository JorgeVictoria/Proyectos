package Practicas_Libro;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		//declaracion de variables
		int[] divisores = new int[10000]; //array donde vamos a guardar los divisores de los 10000 primeros numeros
		int contador = 0; //variable para contar los divisores
		int pos = 0;  //posicion del array que iremos re
		
		//calculamos el numero de divisores y vamos rellenando el array
		for (int i=1; i<=10000; i++) {
			for (int j=i; j>=1; j--) {
				if(i%j==0) contador++;
			}
			divisores[pos]=contador;
			pos++;
			contador=0;
		}
		
		System.out.print(divisores[6]);
		
		
	}

}
