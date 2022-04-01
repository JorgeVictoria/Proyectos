package Ejercicio03;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class U6E03 {
	//variables globales
	public static String extension;		//almacena una extension de archivo
	
	public static FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
				String tmp = file.getName().toLowerCase();
				if (tmp.endsWith(extension)){
					return true;
				}
					return false;
				}
			};
	
	
	
	public static void main(String[] args) throws IOException {
		
		//variables locales
		String carpetaActual; 	//almacena la carpeta actual
		String carpeta;			//almacena una carpeta
		
		
		Scanner stdin = new Scanner(System.in); //para la entrada de datos
		
		boolean flag=false;
		
		File e = null;
		
		//carpeta actual
		carpetaActual = System.getProperty("user.dir");
		System.out.println("Carpeta actual: " + carpetaActual);
		
		//creamos el objeto File
		File d = new File(carpetaActual);
		File[] listado = d.listFiles();
		
		//preguntamos por la carpeta a examinar
		System.out.print("Carpeta a examinar: ");
		carpeta = stdin.nextLine();
		
		//preguntamos por la extension
		System.out.print("Explorar ficheros que acaban en: ");
		extension = stdin.nextLine();
		
		//vemos si existe la carpeta en el directorio actual
		for(int i = 0; i < listado.length; i++) {
			if(listado[i].isDirectory()) {
				if(listado[i].getName().equals(carpeta)) flag = true;
				e = new File(listado[i].getAbsolutePath());
			}
		}
		
		//si la carpeta a examinar no existe
		if (!flag) System.out.print("La carpeta no existe");
		//si existe
		else {
			listado = e.listFiles();
			//imprimimos la primera linea
			System.out.println("[" + carpeta + "]");
			
			//llamada a funcion
			recorreFichero(listado,2);
			
			//vemos si hay ficheros con la extension
			File[] listaFicheros = e.listFiles(filter);
			//si hay archivos con la extension
			if(listaFicheros.length > 0) {
				for(int i = 0; i < listaFicheros.length; i++) {
					//imprimir el nombre del fichero
					System.out.print(listaFicheros[i].getName()+ " ");
					System.out.print(listaFicheros[i].length() + " bytes ");
					// imprimir la fecha de la ultima modificacion
		            long millisec = listaFicheros[i].lastModified();
		            Date dt = new Date(millisec);
		            System.out.print(" Ul.Mod ");
		            System.out.print(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss ").format(dt));
		            //imprimir los permisos
		            System.out.print("Permisos (RWX):");
		            if(listaFicheros[i].canRead())System.out.print("R");
		            else System.out.print("-");
		            if(listaFicheros[i].canWrite())System.out.print("W");
		            else System.out.print("-");
		            if(listaFicheros[i].canExecute())System.out.print("X");
		            else System.out.print("-");
		            //imprimir usuario
		            Path p = listaFicheros[i].toPath();
		            UserPrincipal userPrincipal = null;
					try {
						userPrincipal = Files.getOwner(p);
					} catch (IOException f) {
						// TODO Bloque catch generado automáticamente
						f.printStackTrace();
					}
		            System.out.print(" Propietario: " + userPrincipal);
		            
				}
			}
			
		}
		
	}
	
	public static void recorreFichero(File[] listado, int espacios) {
		File[] listadoTemporal = null;
		//corremos el directorio
		if(listado.length > 0) {
			for(int i = 0; i < listado.length; i++) {
				if(listado[i].isDirectory()) {
					for(int e = 0; e < espacios; e++) System.out.print(" ");
					System.out.println("[" + listado[i].getName() + "]");
					File temp = new File(listado[i].getAbsolutePath());
					listadoTemporal = temp.listFiles();
					File[] archivosTemporal = temp.listFiles(filter);
					if(archivosTemporal.length > 0) {
						//hay ficheros con la extension
						for(int j = 0; j < archivosTemporal.length; j++) {
							for(int e = 0; e < espacios; e++) System.out.print(" ");
							//imprimir nombre
							System.out.print(archivosTemporal[j].getName()+ " ");
							//imprimir peso
							System.out.print(archivosTemporal[j].length() + " bytes ");
							// imprimir fecha modificacion
				            long millisec = archivosTemporal[j].lastModified();
				            Date dt = new Date(millisec);
				            System.out.print(" Ul.Mod ");
				            System.out.print(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss ").format(dt));
				            //imprimir permisos
				            System.out.print("Permisos (RWX):");
				            if(archivosTemporal[j].canRead())System.out.print("R");
				            else System.out.print("-");
				            if(archivosTemporal[j].canWrite())System.out.print("W");
				            else System.out.print("-");
				            if(archivosTemporal[j].canExecute())System.out.print("X");
				            else System.out.print("-");
				            //imprimir usuario
				            Path p = archivosTemporal[j].toPath();
				            UserPrincipal userPrincipal = null;
							try {
								userPrincipal = Files.getOwner(p);
							} catch (IOException e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
							}
				            System.out.print(" Propietario: " + userPrincipal);
				            System.out.print("\n");
						}
					}
					recorreFichero(listadoTemporal,espacios+2);
					
				}
				
			}
		}
	}
	
}
