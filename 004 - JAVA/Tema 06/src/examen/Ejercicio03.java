package examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio03 {
	
	

	public static void main(String[] args) throws IOException {
		
		String fichero = "munipoblacion.dat";
		String pueblo;
		IndiceMuni[] municipios = new IndiceMuni[8104];
		int valor = 0;
		Scanner stdin = new Scanner(System.in);
		long posi = -1;
		
		
		try {
			RandomAccessFile archivo = new RandomAccessFile(fichero,"r");
			
			for(int i = 0; i < 8104; i++) {
				archivo.readByte();
				archivo.readUTF();
				archivo.readShort();
				pueblo = archivo.readUTF();
				municipios[i] = new IndiceMuni(pueblo,i);
				archivo.readInt();
				archivo.readInt();
				archivo.readInt();
			}
			
			//ordenar el array
			for(int i = 0; i < 8104; i++) {
				for(int j = 1; j < 8104; j++) {
					valor = municipios[i].compareTo(municipios[j]);
					if (valor == -1) {
						String pob = municipios[i].municipio;
						long pos = municipios[i].posicion;
						municipios[i].municipio = municipios[j].municipio;
						municipios[i].posicion = municipios[j].posicion;
						municipios[j].municipio = pob;
						municipios[j].posicion = pos;
					}
				}
			}
			
			
			
			//preguntamos por un municio
			System.out.print("Introduce un pueblo: ");
			pueblo = stdin.nextLine();
			pueblo = pueblo.toLowerCase();
			pueblo = pueblo.trim();
			
			//recorremos el array de pueblos
			for(int i = 0; i < 8104; i++) {
				String pueblo2 = municipios[i].municipio.toLowerCase();
				pueblo2 = pueblo2.trim();
				if(pueblo.equals(pueblo2)) posi = municipios[i].posicion;
			}
			
			if(posi == -1) System.out.println("Pueblo no encontrado");
			else {
				archivo.seek(0);
				System.out.println(posi*90);
				archivo.seek(posi*90);
				archivo.readByte();
				String texto = archivo.readUTF();
				System.out.println("Provincia: " + texto);
				archivo.readShort();
				texto = archivo.readUTF();
				System.out.println("Pueblo: " + texto);
				System.out.println("Poblacion: " + archivo.readInt());
				System.out.println("Hombres: " + archivo.readInt());
				System.out.println("Mujeres: " + archivo.readInt());
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
