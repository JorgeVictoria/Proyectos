package Ejercicio05;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class U06E05 {

	public static void main(String[] args) throws IOException {
		
		//variables locales
		//creamos variables finales que van a ser cada una de las letras del ADN
		final int a = 0;
		final int g = 1;
		final int t = 2;
		final int c = 3;
		
		int contador = 0;	//esta variable la uso para ir formando grupos de 4 letras
		int binario = 0;	//esta variable almacena el numero binario que vamos a ir cosntruyendo
		int[] caracteres = new int[4]; //este array ira almacenando los caracteres leidos, hasta formar un bloque de 4
		int ultimoByte = 0; //almacenamos el valor del ultimo byte cuando comprimimos. 
		
		BufferedReader br = null; //declaramos una variable BufferedReader
		BufferedWriter bw = null; //declaramos una variable BufferedWriter
		
		OutputStream salida = null; //declaramos una variable outputstream
		FileInputStream st = null;  //declaramos una variable fileinputstream
		BufferedInputStream bis = null;	//declaramos un bufferedInputStream
		FileWriter salida2 = null;
		
		String sinComprimir = "prueba.adn";
		String comprimido = "prueba.adc";
		
		//pendiente de pedir los ficheros de entrada y salida al usuario
		//en principio vamos al grano y vamos a trabajar con ficheros especificos
		
		//comprimir
		
		try {
			//crear un objeto BufferedReader al que se le pasa
			//un objeto FileReader con el nombre del fichero
			br = new BufferedReader(new FileReader(sinComprimir));
			File ficheroBinario = new File(comprimido);
			salida = new FileOutputStream(ficheroBinario);
			
			int charRead = br.read();
			
			//Ponemos el array a -1
			for(int i = 0; i <4; i++) {
				caracteres[i] = -1;
			}
			
			while (charRead != -1) {
				
				
				if(contador < caracteres.length) {
					//System.out.println(charRead);
					if(charRead == 65 || charRead == 97) caracteres[contador] = a;
					if(charRead == 71 || charRead == 103) caracteres[contador] = g;
					if(charRead == 84 || charRead == 116) caracteres[contador] = t;
					if(charRead == 67 || charRead == 99) caracteres[contador] = c; 
					contador++;
					if(contador < caracteres.length) charRead = br.read();
					
				} else {
					//primera letra
					binario = caracteres[0];
					//segunda letra
					binario = binario << 2;
					binario = binario + caracteres[1];
					//tercera letra
					binario = binario << 2;
					binario = binario + caracteres[2];
					//ultima letra
					binario = binario << 2;
					binario = binario + caracteres[3];
					//sacamos la info
					salida.write(binario);
					//Ponemos el array a -1
					for(int i = 0; i <4; i++) {
						caracteres[i] = -1;
					}
					binario = 0;
					contador=0;
					charRead = br.read();
				}
				
				if (charRead == -1) {
					binario = 0;
					//primera letra
					if(caracteres[0] != -1) binario = binario + caracteres[0];
					else binario = binario + a; //
					
					//segunda letra
					if(caracteres[1] != -1) {
						binario = binario << 2;
						binario = binario + caracteres[1];
					}
					else {
						binario = binario << 2;
						binario = binario + a;
					}
					//tercera letra
					if(caracteres[2] != -1) {
						binario = binario << 2;
						binario = binario + caracteres[2];
					}
					else {
						binario = binario << 2;
						binario = binario + a;
					}
					//ultima letra
					if(caracteres[3] != -1) {
						binario = binario << 2;
						binario = binario + caracteres[3];
					}
					else {
						binario = binario << 2;
						binario = binario + a;
					}
					//sacamos la info
					//System.out.println(binario);
					salida.write(binario);
					
					//añadimos el ultimo byte
					if (contador==0) {
						salida.write(4);
						ultimoByte = 4;
					}
					if (contador==1) {
						salida.write(1);
						ultimoByte = 1;
					}
					if (contador==2) {
						salida.write(2); 
						ultimoByte = 2;
					}
					if (contador==3) {
						salida.write(3); 
						ultimoByte = 3;
					}
				} 
					
			}
			//tratamiento de errores
		} catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
			finally {
				try {
				if(br != null)
						br.close();
			} catch (Exception e) {
					System.out.println("Error al cerrar el fichero");
					System.out.println(e.getMessage());
			}
		}
		
		//descomprimir
		
try {			
			
			//creamos el FileInputStream
			st = new FileInputStream(comprimido);
			//lo encapsulamos en un bufferedInputStream
			bis = new BufferedInputStream(st);
			
			//creamso el fichero de salida
			File archivo2 = new File("descomprimido.adn");
			salida2 = new FileWriter(archivo2,true);
			
			//miramos cuantos bytes tiene el fichero comprimido
			int totalBytes = st.available();
			int cuentaBytes = 0; //para contar las veces que leemos 1 byte en el fichero comprimido
			
			//leemos el primer byte
			int valor=bis.read();
			
			//hemos leido un byte, incrementamos el contador
			cuentaBytes++;
			
			//mientras hay bytes que leer almacenamos en cada posicion del array las veces que aparece
			while(valor != -1) {
				//vemos si hemos leido el penultimo byte
				if(cuentaBytes != totalBytes-1) {
					//pasamos el primer byte a binario
					String numBinario = Integer.toBinaryString(valor);
					//como los primeros ceros se ignoran, los ponemos nosotros
					while(numBinario.length()<8) {
						numBinario = "0" + numBinario;
					}
					//vamos leyendo los bits y trasnformandolos a caracter
					for (int i = 0; i < 8; i=i+2) {
						String trozo = numBinario.substring(i, i+2);
						//System.out.println(trozo);
						if(trozo.equals("00")) salida2.write("A");
						if(trozo.equals("01")) salida2.write("G");
						if(trozo.equals("10")) salida2.write("T");
						if(trozo.equals("11")) salida2.write("C");
					}
					
					valor=bis.read();
					cuentaBytes++;
				} else {
					//pasamos el byte a binario
					String numBinario = Integer.toBinaryString(valor);
					//como los primeros ceros se ignoran, los ponemos nosotros
					while(numBinario.length()<8) {
						numBinario = "0" + numBinario;
					}
					//vamos leyendo los bits y trasnformandolos a caracter, pero solo los que nos marque el ultimo byte
					ultimoByte = ultimoByte * 2;
					if(ultimoByte == 8) ultimoByte = 0;
					for (int i = 0; i < ultimoByte; i=i+2) {
						String trozo = numBinario.substring(i, i+2);
						//System.out.println(trozo);
						if(trozo.equals("00")) salida2.write("A");
						if(trozo.equals("01")) salida2.write("G");
						if(trozo.equals("10")) salida2.write("T");
						if(trozo.equals("11")) salida2.write("C");
					}
					valor=bis.read(); //leemos el ultimo byte, de control
					valor=bis.read(); //volvemos a leer para forzar la salida
					cuentaBytes++;
				}
			}
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		} finally {
			st.close();
			bis.close();
			salida2.close();
		}

	}

}
