package temario;

import java.io.Console;

public class EjemploPrimitivoConsole {

	public static void main(String[] args) {
		
		Console c = System.console(); //creamos el objeto consola
		if(c != null) {
			String entrada = c.readLine(); //si existe la consola, leemos una linea
			c.writer().println("Ha tecleado: " + entrada); //sacamos en consola lo escrito. Ojo, no funciona en eclipse.
		}
	}

}
