package Practicas;

import java.util.Scanner;

public class U3P05 {

	public static void main(String[] args) {
		
		//variables
		String texto1=""; //almacena la primera cadena
		String texto2=""; //almacena la segunda cadena
		
		Scanner stdin=new Scanner(System.in); //lectura de datos
		
		String [] cadena1; //almacena las palabras del texto 1
		String [] cadena2; //almacena las palabras del texto 2
		
		int espacios=0; //almcena la cantidad de espacios de las cadenas
		int a=0; //acumula palabras comunes de ambas cadenas
		int b=0; //acumula palabras de a que no estan en b
		int c=0; //acumula palabras de b que no estan en a
		
		boolean flag=false;
		
		//Borrado de pantalla
		System.out.print( "S\033[H\033[2J");
		System.out.flush();
							
		//Cabecera
		System.out.println("          COMPARAR CADENAS");
		System.out.println("          ================");
		System.out.println();
		
		//lectura de datos
		System.out.print("Texto1: ");
		texto1 = stdin.nextLine();
		System.out.print("Texto2: ");
		texto2 = stdin.nextLine();
		
		//para construir los arrays, vamos a utilizar el caracter espacio
		//como separador de palabras. Necesitamos saber cuantos espacios hay
		//y le añadiremos una celda mas al constructor del array
		
		//array 1
		for(int i = 0; i < texto1.length(); i++) {
			if (texto1.charAt(i) == 32) espacios++;
		}
		cadena1 = new String[espacios+1];
		
		espacios=0; //inicializamos variable para volver a contar
		
		//array 2
		for(int i = 0; i < texto2.length(); i++) {
			if (texto2.charAt(i) == 32) espacios++;
		}
		cadena2 = new String[espacios+1];
		
		//quitamos el valor null de todas las celdas de los arrays
		//array 1
		for (int i = 0; i < cadena1.length; i++) {
			cadena1[i] = "";
		}
		
		//array 2
		for (int i = 0; i < cadena2.length; i++) {
			cadena2[i] = "";
		}
		
		//pasamos los textos a los arrays
		//los espacios son los separadores de palabras
		
		espacios=0; //inicializamos variable para volver a contar
		
		//array 1
		for (int i = 0 ; i < texto1.length(); i++) {
			if(texto1.charAt(i) != 32) cadena1[espacios] = cadena1[espacios] + texto1.charAt(i);
			else espacios++;
		}
		
		espacios=0; //inicializamos variable para volver a contar
		
		//array 2
		for (int i = 0 ; i < texto2.length(); i++) {
			if(texto2.charAt(i) != 32) cadena2[espacios] = cadena2[espacios] + texto2.charAt(i);
			else espacios++;
		}
		
		//recorremos el array a y lo comparamos con el array b
		//contamos las palabras que coinciden, variable a
		//contamos las palabras del array 1 que no están en el array 2
		for(int i = 0; i < cadena1.length; i++) {
			for(int j = 0; j < cadena2.length; j++) {
				if (cadena1[i].equals(cadena2[j])) {
					a++;
					flag = true;
				}
			}
			if (flag == false)b++;
			flag = false;
		}
		
		//ahora contamos las palabras del array 2 que no estan en el array 1
		for(int i = 0; i < cadena2.length; i++) {
			for(int j = 0; j < cadena1.length; j++) {
				if (cadena2[i].equals(cadena1[j])) flag=true;
			}
			if (flag == false)c++;
			flag = false;
		}
		
		//mostramos los resultados
		System.out.println("  a = " + a + " b = " + b + " c = " + c);
		System.out.println("  Parecido [0-1]: " + (((float)a)/(a+b+c)));
		stdin.close();
	}

}
