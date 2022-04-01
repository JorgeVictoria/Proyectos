package temario;

import java.io.File;
import java.util.Scanner;

public class Dir2 {

	public static void main(String[] args) {
		
		//variables locales
				String nombre; 		//nombre del directorio
				File d; 			//directorio
				File temp;			//para ver si lo de dentro del directorio son directorios o archivos
				String[] f; 		//Array de nombres de ficheros
				Scanner stdin;		//Leer del usuario
				int numDirectorios; //numero de directorios
				int numFicheros;	//numero de ficheros
				
				//inicializamos variables
				stdin = new Scanner(System.in);
				System.out.print("Teclee un nombre de directorio:");
				nombre = stdin.nextLine().trim();
				d = new File(nombre);
				numDirectorios = 0;
				numFicheros = 0;
				
				if(!d.isDirectory()) { //si no es directorio
					if( !d.exists()) System.out.println("No existe"); //si el directorio no existe
					else System.out.println("No es un directorio.");  //si no es directorio
					
				}
				else {
					f = d.list(); //devuelve array con los ficheros que contiene
					for(int i = 0;i < f.length; i++) {
						temp = new File(f[i]);  //creamos un nuevo objeto File con cada elemento del array
						if (temp.isDirectory()) numDirectorios++; //si el objeto File es directorio
						else if(temp.isFile()) numFicheros++;	  //si el objeto File es fichero
					} //fin for
					
					System.out.println("El directorio \"" + d + "\":\" tiene " + numDirectorios + " directorios y " + numFicheros + " ficheros.");
						
				}//fin else
				
				

			} //fin main


	}//fin clase


