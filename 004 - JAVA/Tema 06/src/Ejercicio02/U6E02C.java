package Ejercicio02;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class U6E02C {
	

	public static void main(String[] args) throws IOException {
		//variables globales
		File file = leerFichero();  					//objeto File. Llamamos al metodo para seleccionar el fichero
		float[] cantidad = new float[256];  			//para almacenar las veces que aparece cada uno de los 256 bytes
		FileInputStream st=null;								//objeto FileInputStream
		BufferedInputStream bis=null;						//objeto BufferedInputStream
		int bytes=0;									//almacena el total de bytes
		long tiempo;									//almacena el tiempo transcurrido
		int pos = 0;
		double entropia=0;
		
		
		//rellenamos el array con ceros
		for(int i = 0; i < cantidad.length; i++) {
			cantidad[i] = 0;
		}
		
		//cogemos el tiempo de inicio
		tiempo= System.currentTimeMillis();
		
		
		try {			
			
			//creamos el FileInputStream
			st = new FileInputStream(file);
			
			//lo encapsulamos en un bufferedInputStream
			bis = new BufferedInputStream(st);
			
			//calculamos cuantos bytes tiene el archivo seleccionado
			bytes = bis.available();
			
			//creamos el buffer
			byte [] buffer = new byte[64];
			
			//leemos el primer byte
			int valor=0;
			
			//mientras hay bytes que leer almacenamos en cada posicion del array las veces que aparece
			while(0 < (valor=bis.read(buffer))) {
				for(int i = 0; i < valor; i++) {
					if (buffer[i] < 0) pos = buffer[i]+256;
					else pos = buffer[i];
					cantidad[pos]++;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		finally {  //cerramos cosas
			st.close();
			bis.close();
         }
		
		//calculamos el histograma
		for(int i = 0; i < cantidad.length; i++)cantidad[i] = cantidad[i]/bytes;
		
		//calculamos la entropia
		for(int i = 0; i < cantidad.length; i++) {
			entropia = entropia + (cantidad[i]*(Math.log(1/cantidad[i])/Math.log(2)));
		}
		
		//paramos el cromo
		tiempo = System.currentTimeMillis() - tiempo;

		
		//mostramos el nombre del fichero, su peso y el tiempo de calculo del histograma
		System.out.println("Fichero: " + file.getName());
		System.out.print("Peso: " + bytes + " bytes. ");
		System.out.printf("Proceso realizado en %5.4f segundos.",(float)tiempo/1000);
		System.out.println();
		System.out.printf("La entropia del fichero es: %7.6f bits", entropia);
		System.out.println();
		System.out.printf("La redundancia es %7.6f bits", 8-entropia);
		System.out.println();
		if(8-entropia < 0.1) System.out.println("Fichero comrprimido o aleatorio");
		else if(8-entropia >= 0.1 && 8-entropia <= 3.5) System.out.println("Fichero parcialmente comprimido");
		else System.out.println("Fichero no comprimido");
		
		
		
		
	}
	
	//metodo para seleccionar un fichero
	public static File leerFichero() {
		//variables
		File archivo;
		
		//creamos el objeto
		JFileChooser fd = new JFileChooser();
		
		//mostramos por pantalla
		fd.setDialogTitle("Selecciona el fichero a leer");
		
		fd.setSelectedFile(null); //no hay ninguno seleccionado
		
		int opcion = fd.showOpenDialog(null);
		
		if (opcion != JFileChooser.APPROVE_OPTION ) {
			archivo = null;
			return archivo;
		}
		
		archivo = fd.getSelectedFile();
		return archivo;
		
	}

}


