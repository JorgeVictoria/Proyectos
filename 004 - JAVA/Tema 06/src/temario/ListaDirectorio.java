package temario;

import java.io.File;
import java.util.Scanner;

public class ListaDirectorio {

	public static void main(String[] args) {
		
		//variables locales
		String nombre; 	//nombre del directorio
		File d; 		//directorio
		String[] f; 	//Array de nombres de ficheros
		Scanner stdin;		//Leer del usuario
		
		//inicializamos variables
		stdin = new Scanner(System.in);
		System.out.print("Teclee un nombre de directorio:");
		nombre = stdin.nextLine().trim();
		d = new File(nombre);
		
		if(!d.isDirectory()) { //si no es directorio
			if( !d.exists()) System.out.println("No existe"); //si el directorio no existe
			else System.out.println("No es un directorio.");  //si no es directorio
		}
		else {
			f = d.list(); //devuelve array con los ficheros que contiene
			System.out.println("Ficheros en \"" + d + "\":");
			for(int i = 0;i < f.length; i++) System.out.println(" " + f[i]);
				
		}
		

	}

}
