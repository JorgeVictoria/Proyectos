package temario;

import java.io.Console;
import java.io.IOException;

public class Salida {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Console c = System.console(); //creamos la consola
		if(c==null) throw new RuntimeException("No hay consola"); //si no hay consola
		else {
			c.writer().println("Bienvenido al zoo"); //salida por pantalla
			c.format("Nuestro zoo tiene 491 animales y 25 empleados."); //salida por pantalla con formato
			c.writer().println(); //linea en blanco
			c.printf("Tiene una extension de %d Km2 y %1$d es mucho", 10);
		}

	}

}
