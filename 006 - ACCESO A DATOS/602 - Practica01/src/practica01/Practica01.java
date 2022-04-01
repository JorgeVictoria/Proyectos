/**
 * Leer el nombre de un directorio desde consola
 * y mostrar el listado de los directorios y ficheros que contiene
 * haciendo uso de los metodos .list() y .listFiles
 */
package practica01;

import java.io.File;

/**
 * @author Jorge Victoria Andreu (2º DAM)
 * @version 1
 * @since 13/09/2021
 */
public class Practica01 {

	/**
	 * @param args: recibe como entrada el nombre de un directorio
	 */
	public static void main(String[] args) {
		
		try  {
			
			//guardamos el primer argumento que recibimos a traves de args. Creamos un objeto file
			File f = new File(args[0]);
			
			//vemos si el fichero existe
			if(!f.exists()) System.out.println("El fichero no existe.");
			
			//vemos que sea un directorio
			else if(!f.isDirectory()) System.out.println("El fichero no es un directorio.");
			
			//si existe y es un directorio, creamos un array de String y un array de objetos file
			//con los ficheros y directorios del directorio origen
			else {
				
				String[] ficheros = f.list();
				File[] fitxers = f.listFiles();
				
				//si el directorio está vacio, lo indicamos
				if(ficheros.length == 0) System.out.println("El directorio está vacio.");
				
				//si no esta vacio, mostramos su contenido
				else {
				
					//corremos y mostramos el array String de ficheros
					System.out.println("Listado del array de String");
					for(int i = 0; i < ficheros.length;i++) {
						System.out.println(ficheros[i]);
					}
					
					//corremos y mostramos el array de objetos File de fitxers
					System.out.println("\nListado del array de Files");
						for(int i = 0; i < fitxers.length;i++) {
							System.out.println(fitxers[i].getName());
						}
				}
					
			} //fin else
		
		} catch(Exception e) {
			System.err.print(e.getMessage());
		}
	}//fin main

}//fin clase
