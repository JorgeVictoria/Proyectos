package practica03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Jorge Victoria Andreu (2º DAM)
 * @version 1
 * @since 17/09/2021
 */

public class practica03 {
	
	private static int contador;

	public static void main(String[] args) throws IOException {
		
		//variables locales
		String origen = "C://Workspace//origen.txt";		//fichero de origen
		String destino = "C://Workspace//destino.txt";		//fichero de destino
		String cadena = "";									//para almacenar las lineas que vamos leyendo
		String cadenaInvertida ="";							//para almacenar las cadenas que debemos invertir
		
		try {
			
			//1º Implementa un programa Java que Copie el contenido revertido de un fichero a otro. Procura que tenga varias lineas.
			
			//creamos un FileReader para leer el fichero de origen
			FileReader fr = new FileReader(origen);
			
			//creamos un fileWriter para escribir el fichero. No marcamos true para poder reescribir el fichero en cada ejecucion de prueba
			FileWriter fw = new FileWriter(destino);
			
			//almacenamos el fileReader en un BufferedReader
			BufferedReader bf = new BufferedReader(fr);
			
			//creamos una lista enlazada, que nos permite correrla de forma inversa
			LinkedList<String> list = new LinkedList<String>();
			
			//las lecturas de las lineas las hacemos con el metodo readLine
			//y su insercion en la lista mediante el metodo .add.
			//almacenamos una linea en una cadena y construimos una nueva cadena 
			//donde almacenamos la cadena original invertida, que añadiremos a la lista
			while ((cadena = bf.readLine())!=null) {
				for(int i = cadena.length()-1; i >=0; i--){
					cadenaInvertida = cadenaInvertida + cadena.charAt(i);
				}
				list.add(cadenaInvertida);
				
				//reiniciamos la cadenaInvertida para almacenar invertidamente la siguiente linea
				cadenaInvertida="";	
			}
			
			//ahora corremos la lista de forma inversa, de forma que leemos las lineas de abajo hacia arriba
			Iterator<String> it = list.descendingIterator();
			
			//leemos el iterator y mientras haya datos, vamos escribiendo lineas en el fichero de salida
			while(it.hasNext()) {
				//si existen mas elementos, añadimos un salto de lineas
				if(it.hasNext())fw.write((String)it.next() + "\n");
				//si no hay mas elementos, no añadimos salto de linea
				else fw.write((String)it.next());
				fw.flush();			//importante, sino no se escribe en el fichero
			}
			
			fr.close();
			fw.close();
			
			//2º En un segundo paso se abrirá el archivo copiado, para añadir texto al final del mismo (tu nombre y apellidos).

			//creamos un nuevo objeto filewriter. Con true nos aseguramos el poder añadir lineas sin borrar lo anterior
			FileWriter nuevoFw = new FileWriter(destino,true);
			
			//añadimos una linea
			nuevoFw.write("Jorge Victoria");
			nuevoFw.flush();
			
			//cerramos el filewriter
			nuevoFw.close();
			
			//3º En la tercera parte, se abrira el archivo copiado con el nuevo texto y se mostrará por pantalla linea a linea (utiliza para 
			//ello la clase BufferedReader)
			
			//volvemos a crear un nuevo FileReader, para leer el fichero destino
			FileReader nuevoFr = new FileReader(destino);
			
			//creamos un nu nuevo bufferedReader para poder leer el FileReader
			BufferedReader nuevoBr = new BufferedReader(nuevoFr);
			
			//a continuacion vamos leyendo linea a linea el archivo destino.txt, almacenamos en un String y mostramos por pantalla su contenido
			while ((cadena = nuevoBr.readLine())!=null) {
				System.out.println(cadena);
			}
			
			//cerramos el stream
			nuevoFr.close();
			
		} catch(Exception e) {
			System.err.print(e.getMessage());
		}
		
	}
}
