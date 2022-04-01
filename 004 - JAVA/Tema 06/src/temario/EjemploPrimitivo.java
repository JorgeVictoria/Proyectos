package temario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploPrimitivo {

	public static void main(String[] args) throws IOException {
		
		//Creamos un flujo de entrada envuelto en un buffer
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String entrada = br.readLine(); //Leemos la linea
		System.out.println("Has tecleado: " + entrada); //mostramos en pantalla el texto tecleado
	}

}
