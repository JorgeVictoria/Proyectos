package temario;

import java.io.File;
import java.util.Scanner;

public class Dir4 {

	public static void main(String[] args) {
		//variables locales
		String nombre; 		//nombre del directorio
		File d; 			//directorio
		File[] f; 		//Array de nombres de ficheros
		Scanner stdin;		//Leer del usuario
		long total = 0;
		int numFicheros=0;
		
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
			f = d.listFiles();
			for(int i = 0;i < f.length; i++) {
				if (f[i].isFile()) {
					total = total + f[i].length();
					numFicheros++;
				}
			} //fin for
			
			System.out.println("El directorio \"" + d + "\":\" tiene "  + numFicheros + " ficheros y pesan " + total + " bytes ");
				
		}//fin else
		
		

	} //fin main


}//fin clase