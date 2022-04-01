package temario;

import java.io.File;
import java.util.Scanner;

public class Dir1 {

	public static void main(String[] args) {
		
		//variables locales
		String rutaActual;	//almacena la ruta actual
		File d; 			//directorio
		String[] f; 		//Array de nombres de ficheros
		
		//inicializamos variables
		rutaActual = System.getProperty("user.dir");
		d = new File(rutaActual);
		
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


