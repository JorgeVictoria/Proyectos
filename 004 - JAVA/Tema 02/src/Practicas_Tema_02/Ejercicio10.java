package Practicas_Tema_02;

import java.util.Scanner;

public class Ejercicio10 {

	public static void main(String[] args) {
		
		//definicion de variables
		StringBuilder cadena;
		String apellido;
		String temp;
		String edad;
		Scanner stdin = new Scanner (System.in);
		
		//inicialimos las variables
		cadena = new StringBuilder("Select * from emp");
		temp = "";
		apellido = "";
		edad = "";
		
		//Entrada de datos
		System.out.print("El apellido comienza por: ");
		apellido = stdin.nextLine();
		System.out.print("Edad:");
		edad = stdin.nextLine();
		stdin.close();
		
		//vemos si hay datos
		temp += apellido.equals("")?"":" where apellido like '" + apellido + "*' ";
		temp += !(temp.equals("")) && !(edad.equals(""))?"and edad = " + edad:"";
		temp += temp.equals("") && !(edad.equals(""))?" where edad = " + edad:"";
		
		//añadimos la cadena
		cadena = cadena.append(temp);
		
		//salida por pantalla
		System.out.println("\n" + cadena);
	
	}

}
