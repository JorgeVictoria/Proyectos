package temario;

import java.io.IOException;

//flujos predefinidos

public class LeeEstandar {

	public static void main(String[] args) { //inicio del main
		
		//variables locales
		StringBuilder str = new StringBuilder(); //cadena donde ir almacenando los caracteres
		char c; //caracter que vamos leyendo
		
		//por si ocurre una excepcion usamos el bloque try-catch
		try {
			//mientras la entrada de teclado distinta de Intro
			while ( (c=(char)System.in.read()) != '\n' ){
				//añadir el caracter leido a str
				str.append(c);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		//Escribir la cadena que se ha ido tecleando
		System.out.println("Cadena introducida: " + str);
	}

}
