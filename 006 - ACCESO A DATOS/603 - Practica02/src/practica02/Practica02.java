/**
 * Leemos un directorio base a traves de consola
 * A partir de ese directorio base creamos 3 subdirectorios
 * Y tambien creamos en el ultimo subdirectorio el fichero "miFichero.txt"
 */
package practica02;

import java.io.File;

/**
 * @author Jorge Victoria Andreu (2º DAM)
 * @version 1
 * @since 13/09/2021
 */
public class Practica02 {

	/**
	 * @param args: debemos recibir como entrada un directorio.
	 */
	public static void main(String[] args) {
		
		//variables locales
		boolean exito = false;		//para controlar que los ficheros y directorios se crean correctamente
		
		//almacenamos el directorio de entrada.
		File base = new File(args[0]);
		
		//comprobamos que es un directorio y que existe
		if(base.isDirectory() && base.exists()) {
			try {
				//creamos una cadena con el nombre del directorio base y los subdirectorios
				String cadenaDirectorios = args[0] + "\\PrimerDirctorio\\SegundoDirectorio\\TercerDirectorio\\";
				
				//creamos un objeto file con la cadena completa y lo usamos para comprobar que no existe la estructura de directorios
				File directorio = new File(cadenaDirectorios);
				
				//comprobamos si la estrutura existe y sino creamos el directorio y sus directorios
				if (directorio.exists())  exito = true;
				else exito =  (new File(cadenaDirectorios)).mkdirs();
				
				//si se ha creado con exito o ya existia la estructura, mostramos por pantalla y creamos el fichero
				if(exito) {
					System.out.println("Directorios creados");
					
					//para crear el fichero, creamos un objeto file con la ruta completa y el nombre del fichero
					File f = new File(cadenaDirectorios +"miFichero.txt");
					
					//comprobamos que el fichero no exista y creamos el fichero
					if (f.exists()) System.out.println("El archivo ya existe");
					else {
						exito = f.createNewFile();
						
						//si se crea, o no,  el fichero lo indicamos por pantalla
						if(exito) System.out.println("Fichero creado");
						else System.out.println("No se ha podido crear el fichero.");
					}
					
				//si no se han podido crear los ficheros, se indica por pantalla
				} else System.out.println("No se han podido crear los directorios");
				
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		
		else {
			
			//si el directorio base introducido no existe o no es un directorio, lo indicamos
			if (!base.exists()) System.out.println("El directorio introducido no existe.");
			else System.out.println("El fichero introducido no es un directorio");
		}

	}

}
