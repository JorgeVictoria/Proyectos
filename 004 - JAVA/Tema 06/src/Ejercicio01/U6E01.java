package Ejercicio01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class U6E01 {
	
	static Scanner stdin = new Scanner(System.in); //para preguntar la provincia
	
	public static void main(String[] args) throws IOException  {
		
		//variables locales
		String provincia; 					//almacena la provincia
		int contador = 1; 					//para contar las lineas que vamos leyendo del fichero csv
		BufferedReader br = null;			//para leer texto usando buffer
		int totalHA=0;						//almacena el total de HA de los lugares de la provincia
		boolean existeProvincia = false;	//sirve para ver que hay datos de la provincia
		
		//preguntamos por la provincia
		System.out.print("Espacios Naturales de la Provincia: ");
		provincia = stdin.nextLine();
		stdin.close(); //cerramos la lectura
		
		//transformamos la cadena
		
		//eliminamos los espacios
		provincia = provincia.trim();
		
		//ponemos la cadena en minuscula
		provincia = provincia.toLowerCase();
		
		try {
			//nos creamos un filereader envuelto en un buffer de lectura
			br = new BufferedReader(new FileReader("espacios1.csv"));
			
			//leemos la primera linea
			String linea = br.readLine();
			
			//mientras hayan lineas que leer
			while (linea != null) {
			    contador++; //vamos contando lineas leidas
			    //construimos un array con los campos de la linea. Separamos los campos con split
				String [] fields = linea.split(";"); 
		
				//si hay info de la provincia, la mostramos por pantalla
				if(fields[0].toLowerCase().contains(provincia) && fields.length == 5) {
					existeProvincia = true; 
					System.out.print("Espacio: ");
					System.out.printf("%35s", fields[1]);
					System.out.print("\tTipo: ");
					System.out.printf("%35s", fields[3]);
					System.out.print("\tSuperficie(HA): ");
					System.out.print(fields[4]);
					System.out.println();
					totalHA = totalHA + Integer.parseInt(fields[4]); //calculo de HA
					
				} //mostramos las lineas que no tienen el numero exacto de campos 
				else if (fields.length != 5) System.out.println("  LINEA " + contador + " INCORRECTA " + linea);
				
				 //leemos la siguiente linea
				linea = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		finally {  //cerramos cosas
           br.close();
         }
		
		//si hemos encontrado datos de la provincia, mostramos el total de hectareas
		if(existeProvincia) System.out.println("Total de superficie: " + totalHA + " HA");
		else System.out.println("No hemos encontrado nada sobre " + provincia);
		
      }//fin main
	
}//fin clase

