//Contar los bytes que tiene  fichero
package temario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class U06EJ01 {
	static private Scanner stsdin = new Scanner (System.in);
	
	public static void main(String[] args) {
		int b;
		int contador=0;
		System.out.print("Fichero: ");
		String nomFichero = "C:/Users/jvand/OneDrive/Estudios/Workspace/Eclipse/Tema 06/src/sesion1/prueba.txt";
		FileInputStream fis;
		FileOutputStream fos;
		try {
			byte[] buffer = new byte[8196]; //creamos el buffer
			fis = new FileInputStream(nomFichero);
			fos = new FileOutputStream(nomFichero + "copia");
			long tiempo = System.currentTimeMillis();
			do {
				//b = fis.read();
				b = fis.read(buffer);
				//if(b != -1) contador++;
				//fos.write(b);
				fos.write(buffer,0,b);
			//} while (b >= 0);
			} while (b == 0); 
			fis.close();
			fos.close();
			tiempo = System.currentTimeMillis() -tiempo;
			System.out.println("Hemos tardado " + (tiempo/1000.0) + " segundos");
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println("Error trabajando con el fichero " + nomFichero);
			System.out.println("Error" + e.getMessage());
		}
		
		System.out.println(contador + " bytes");
		
	} 

}
