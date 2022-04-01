package examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio02 {
	
	

	public static void main(String[] args) throws IOException {
		
		String fichero = "munipoblacion.dat";
		String pueblo;
		IndiceMuni[] municipios = new IndiceMuni[8104];
		int valor = 0;
		
		
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
			
			//buscamos el pueblo 100
			String texto = municipios[100].toString();
			System.out.println(texto);
			
			//buscamos el pueblo 101
			texto = municipios[101].toString();
			System.out.println(texto);
			
			
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
			
			//vemos que esta ordenado
			for(int i = 0; i < 102; i++) {
				texto = municipios[i].toString();
				System.out.println(texto);
			}
			
			//buscamos el pueblo 100
			texto = municipios[100].toString();
			System.out.println(texto);
			
			//buscamos el pueblo 101
			texto = municipios[101].toString();
			System.out.println(texto);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}


