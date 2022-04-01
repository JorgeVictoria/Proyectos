package temario;

import java.io.File;
import java.io.FileFilter;

public class Dir6 {

	public static void main(String[] args) {

		//variables locales
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				String tmp = file.getName().toLowerCase();
				if (tmp.endsWith(".pdf")){
					return true;
				}
				return false;
			}
		};
		
		File micarpeta = new File("D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06");
		File[] listaFicheros = micarpeta.listFiles(filter);
		
		//vemos si hay contenido
		if(listaFicheros.length == 0) {
			System.out.println("dentro de la carpeta no hay nada"); //si no hay nada
		}
		else {
			System.out.println(listaFicheros.length);
			for(int i = 0; i < listaFicheros.length;i++) {
				listaFicheros[i].delete();
			}
		}
		
	}

}
