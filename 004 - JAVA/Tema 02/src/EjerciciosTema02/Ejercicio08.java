package EjerciciosTema02;

import java.util.Scanner;

public class Ejercicio08 {

	public static void main(String[] args) {
		
		//variables definition
		Scanner stdin = new Scanner( System.in ); //write data
		String nombreCompleto; //variable que almacena el nombrr completo
		String nombre; //guarda el nombre
		String apellido1; //guarda el primer apellido
		String apellido2; //guarda el segundo apellido
		int pos; //para almacenar la posicion en la cadena
		int pos2; //para almacenar la posicion en la cadena
		
		//entrada por pantalla
		System.out.print("Teclee su nombre y los dos apellidos (sin particulas): ");
		nombreCompleto = stdin.nextLine();
		stdin.close();
		
		//quitamos los espacios sobrantes
		nombreCompleto = nombreCompleto.trim();
		
		//almacenamos donde esta el primer espacio
		pos = nombreCompleto.indexOf(" ");
		
		//almacenamos el nombre
		nombre = nombreCompleto.substring(0, pos);
		System.out.println("Su nombre es " + nombre + " y tiene " + nombre.length() + " caracteres.");
		
		//almacenamos siguiente espacio
		pos2 = nombreCompleto.lastIndexOf(" ");
		
		//almacenamos el primer apellido
		apellido1 = nombreCompleto.substring(pos+1, pos2);
		System.out.println("Su primer apellido es " + apellido1 + " y tiene " + apellido1.length() + " caracteres.");
		
		//almacenamos el segundo apellido
		apellido2 = nombreCompleto.substring(pos2+1);
		System.out.println("Su segundo apellido es " + apellido2 + " y tiene " + apellido2.length() + " caracteres.");
		
	}

}
