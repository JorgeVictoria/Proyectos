package temario;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter {
	//variable miembro
	String extension;
	
	//constructor
	public Filtro(String extension) {this.extension = extension;}
	
	//interfaz
	public boolean accept (File dir, String name) {
		return name.toLowerCase().endsWith(this.extension);
	}
	
	public static void main(String[] args) {

		try {
			File f = new File("C:\\Users\\jvand\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario");
			String[] archivos = f.list();
			archivos = f.list(new Filtro(".java"));
			int num = archivos.length;
			if(num < 1) System.out.println("No hay archivos que listar");
			else {
				for(int i = 0; i < archivos.length; i++) System.out.println(archivos[i]);
			}
		} catch (Exception ex) { System.out.println("Error al buscar en la ruta"); }

	}

}
