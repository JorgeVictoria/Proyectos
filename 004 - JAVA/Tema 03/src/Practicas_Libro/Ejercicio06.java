package Practicas_Libro;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Ejercicio06 {

	public static void main(String[] args) {
		
		//variables
		Scanner stdin = new Scanner(System.in);
		String cadena="";
		int flag = 1;
		int catched=0;
		int pos;
		String numero="";
		double cantidad=0;
		double total=0;
		int totalCiudades=0;
		int totalCiudadesVentas=0;
		
		
		//entrada de datos
		
		while (flag != 0) {
			//comprobamos que se lean datos o que el fichero no está vacio
			try {	
				cadena = stdin.nextLine();
			} catch (NoSuchElementException e) {
				//System.out.println("fin de entrada");
			   break;
			}
			
			
			totalCiudades++;  //interpretamos que cada linea es una ciudad
			//vemos si está el separador
			pos = cadena.indexOf(":"); 
			if (pos > -1) {
				numero += cadena.substring(pos+1);
				
				//comprobamos que el campo que pertenece a cantidad es un numero y se puede convertir
				try {
					cantidad = Double.valueOf(numero);
					
				} catch (NumberFormatException e) {
					catched = 1;
				}
				if (catched == 0) {
					total = total + cantidad;
					totalCiudadesVentas++;
				}
				
			}
			numero = "";
			catched = 0;
		}
		
		System.out.println("Numero de Ciudades:" + totalCiudades);
		System.out.println("Numero de Ciudades con ventas:" +totalCiudadesVentas);
		System.out.println("Total de ventas:" + total);
		
		stdin.close();
		
	}  //fin del mains
} //fin de la clase

