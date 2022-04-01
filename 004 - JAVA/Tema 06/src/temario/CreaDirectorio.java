package temario;

import java.io.File;

public class CreaDirectorio {

	public static void main(String[] args) {
		
		try {
			String d = "D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\miCarpeta"; //ruta donde vamos a crear la carpeta
			String varios ="carpeta1/carpeta2/carpeta3"; //lista de carpetas internas
			boolean exito = (new File(d).mkdir()); //intentamos crear el directorio
			if(exito) System.out.println("Directorio: " + d + " creado");
			//crear todos los directorios de la ruta
			exito = (new File(varios).mkdirs());
			if(exito) System.out.println("Directorios: " + varios + " creados");
			
		} catch(Exception e) { System.err.println("Error: " + e.getMessage());}

	}

}
