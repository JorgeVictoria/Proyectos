package temario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Binarios {

	public static void main(String[] args) throws IOException {

		//variables locales
		String origen = "D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\OrigenBinario.txt";
		String destino = "D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\DestinoBinario.txt";
		
		//llamada a funcion
		copia(origen,destino);

	}
	
	public static void copia(String origen, String destino) throws IOException {
		
		try {
			//obtener nombres de ficheros de origen y destino
			//y abrir conexion a los ficheros
			InputStream entrada = new FileInputStream(origen);
			OutputStream salida = new FileOutputStream(destino);
			//crear una variable para leer el flujo de bytes del origen
			byte[] buffer=new byte[256];
			while(true) {
				//leer el flujo de bytes
				int n = entrada.read(buffer);
				if(n<0) break; //si no queda nada por leer, salir
				salida.write(buffer, 0, n); //escribir el flujo de bytes
			}
			//cerrar los ficheros
			entrada.close();
			salida.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
