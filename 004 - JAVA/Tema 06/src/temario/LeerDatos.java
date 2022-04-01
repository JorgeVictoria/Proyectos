package temario;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;

public class LeerDatos {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Console c = System.console(); //creamos la consola
		if (c == null) throw new RuntimeException("Consola no disponible");
		else {
			c.writer().print("Como ha ido el dia? ");
			c.flush(); //fuerza que cualquier mensaje en los buffers sea escrito
			String dia = c.readLine(); //leemos de teclado y almacenamos en dia
			String nombre = c.readLine("Su nombre: "); //leemos directamente el nombre
			Integer edad = null; //dejamos la edad a null
			c.writer().print("Su edad? ");
			c.flush();
			BufferedReader br = new BufferedReader(c.reader()); //envolvemos el flujo de lectura en un buffer
			String valor = br.readLine(); //leemos la edad del teclado
			edad = Integer.valueOf(valor); //pasamos la edad a tipo int y la guardamos en su variable
			c.writer().println(); //linea en blanco
			c.format("Se llama " + nombre); //imprimimos el nombre con formato
			c.writer().println(); //linea en blanco
			c.format("Su edad es " + edad); //imprimimos la edad con formato
			c.writer().println(); //linea en blanco
			c.printf("Su dia ha ido: " + dia); //imprimimos sin formato
		}

	}

}
