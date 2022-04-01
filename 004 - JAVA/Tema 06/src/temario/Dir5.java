package temario;

import java.io.File;

public class Dir5 {
	//variables globales
	static int totalFicheros = 0;
	static long total = 0;
	
	public static void main(String[] args) {
		
		//variables locales
		File micarpeta = new File("C:\\Workspace");
		File[] listaFicheros = micarpeta.listFiles();
		
		//vemos si hay contenido
		if(listaFicheros.length == 0) {
			System.out.println("dentro de la carpeta no hay nada"); //si no hay nada
		}
		
		//si hay contenido...
		else cuentaNiveles(listaFicheros);
		
		System.out.println("El directorio \"" + micarpeta + "\":\" tiene "  + totalFicheros + " ficheros y pesan " + total + " bytes ");
		
	}
	
	public static void cuentaNiveles (File[] listado) {
		//variables locales
		File carpetaTemp;
		File[] listadoTemp;
		
		for(int i = 0; i < listado.length; i++) {
			if(listado[i].isFile()) {
				total = total + listado[i].length();
				totalFicheros++;
			}
			else {
				carpetaTemp = listado[i];
				listadoTemp = carpetaTemp.listFiles();
				if( listadoTemp != null) cuentaNiveles(listadoTemp);
			}
		}
		
	}
	
	

}
