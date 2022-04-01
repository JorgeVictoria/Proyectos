package temario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Caracteres {

	public static void main(String[] args) {
		
		try {
			
			PrintWriter out = null; //duda
			String ruta = "C:\\Users\\jvand\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\prueba.txt";
			//ruta = separadoresOk(ruta);
			//fileWriter para flujos de caracteres, FileOutputStream para flujo de binarios. True es por si existe el fichero, para no sobreescribir
			out = new PrintWriter(new FileWriter(ruta, true));
			//InputStreamReader es un puente de flujos de bytes a caracteres, envuelto en un bufferedreader
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			//leemos una linea, y sino escribimos salir lo pasamos al fichero
			while (!(s = br.readLine()).equals("salir")) { out.println(s); }
			out.close();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }
	}
	
	//Metodo al que le pasamos una ruta y nos devuelve la adecuada segun el separador del sistema actual
	public static String separadadoresOk(String ruta) {
		String separador = "\\";
		try {
			if(File.separator.equals(separador))  //en Windows
				separador = "/";
			return ruta.replaceAll(separador, File.separator);
		} catch(Exception e) {
			return ruta.replaceAll(separador+separador, File.separator);
		}
	}
}
