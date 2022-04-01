package temario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerCodificacion {

	public static void main(String[] args) {
		
		FileInputStream fichero; //creamos el objeto para el flujo de bytes
		
		try {
			//elegir el fichero para leer flujos de bytes "crudos"
			fichero = new FileInputStream("D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\bin\\temario\\binarios.class");
			//InputStreamReader como puente de flujos de byte a caracteres
			InputStreamReader isr = new InputStreamReader(fichero);
			//vemos la codificiacion actual
			System.out.println(isr.getEncoding());
		} catch (FileNotFoundException ex) {
			//Logger.getLogger(textos.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("no hay fichero");
		}

	}

}
