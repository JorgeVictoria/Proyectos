package temario;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerConBuffer {

	public static void main(String[] args) {

		int tama;
		try {
			//creamos un nuevo file, la ruta hasta el fichero
			File f = new File("D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\prueba.txt");
			//construir flujo de tipo fileinputStream sobre el objeto file y luego el buffer
			FileInputStream entra = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(entra);
			//Escribimos el tamaño del fichero en bytes
			tama = bis.available();
			System.out.println("Bytes disponibles: " + tama);
			//Indicar que vamos a intentar leer 50 bytes
			System.out.println("Leyendo 50 bytes...");
			//crear array de 50 bytes como buffer
			byte bArray[] = new byte[50];
			if (bis.read(bArray) != 50) System.out.println("No se pudieron leer 50 bytes");
			//crear un nuevo String a partir de los bytes
			System.out.println(new String(bArray,0,50));
			bis.close();
		} catch (IOException e) {
			System.err.println("No se encuentra el fichero");
		}

	}

}
