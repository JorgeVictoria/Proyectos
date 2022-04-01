package temario;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class PedirPassword {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Console c = System.console(); //creamos la consola
		if (c == null) { throw new RuntimeException("Consola no existe");} //si la consola no existe
		
		else {
			char[] pwd = c.readPassword("Password: "); //se crea un array por seguridad
			c.format("Teclee de nuevo: "); //volvemos a pedir la password, pero con formato
			c.flush(); //vaciamos el buffer
			char[] verifica = c.readPassword(); //volvemos a introducir la password, pero en otro array
			boolean ok = Arrays.equals(pwd, verifica); //vemos si las dos passwords son iguales
			//eliminamos los passwords de memoria
			Arrays.fill(pwd, 'x');
			Arrays.fill(verifica, 'x');
			//mostramos por pantalla el resultado
			c.format("Su password es " + (ok? "correcto" : "incorrecto"));
		}

	}

}
