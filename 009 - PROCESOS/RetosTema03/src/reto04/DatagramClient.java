package reto04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class DatagramClient {
	
	//variables globales
	private static String password;
	private static final int claveVerificacion = 198;
	private static Scanner stdin = new Scanner(System.in);
	private static String direccionServidor = "localhost";

	public static void main(String[] args) {
		
		//leemos la password desde un fichero
		leerPassword();
		
		try {
			
			//creamos el socket para el cliente
			DatagramSocket socketUDP = new DatagramSocket();
			
			//creamos un buffer donde almacenamos la info. Cogemos la info desde fuera
			byte[] mensaje = password.getBytes();
			
			//definimos los datos del servidor al que nos vamos a conectar: direccion y puerto
			InetAddress hostServidor = InetAddress.getByName(direccionServidor);
			int puertoServidor = 6789;
			
			//construimos un datagrama para enviar el mensaje al servidor
			DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
			
			//enviamos el datagrama
			socketUDP.send(peticion);
			
			//construimos el DatagramPacket que contendrá la respuesta
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			
			//recibimos la respuesta
			socketUDP.receive(respuesta);
			
			//construimos la cadena con la respuesta
			String claveVerificada = new String(respuesta.getData());
			claveVerificada = claveVerificada.trim();
			
			//comparamos claves
			if(Integer.valueOf(claveVerificada) == claveVerificacion) {
				System.out.println("La clave es correcta");
				escribirEnFichero(true);
			}
			else {
				System.out.println("Clave incorrecta");
				escribirEnFichero(false);
			}
			
			//cerramos el socket
			socketUDP.close();
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}

	}

		private static int pedirClave() {
		
		//variables locales
		boolean esValido = false;
		int numero = 0;
		
		while(!esValido) {
			System.out.printf("Introduzca la clave de verificacion:");
			try {
				esValido = true;
				numero = stdin.nextInt();
			} catch (Exception ex) {
				stdin.next();
				System.out.println("El valor no es valido");
				esValido = false;
			}
			if(esValido && numero <= 0) {
				System.out.println("Debe introducir un numero entero positivo");
				esValido = false;
			}
						
		}
				
		return numero;
	}
		
		private static void leerPassword() {
			
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				//apertura del fichero y creacion de BufferedReader para poder leer desde el fichero
				archivo = new File ("..\\..\\files\\Password.txt");
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				//lectura del fichero, suponemos que la clave está en una única linea
				password = br.readLine();
				if(password.length() > 0) password = password.trim();
			} catch(Exception e){
		         e.printStackTrace();
		      }finally{
		         // En el finally cerramos el fichero, para asegurarnos
		         // que se cierra tanto si todo va bien como si salta 
		         // una excepcion.
		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }
		}
		
		private static void escribirEnFichero(boolean queEscribir) {
			
			FileWriter fichero = null;
			PrintWriter pw = null;
			
			try {
				fichero = new FileWriter("..\\..\\files\\Password.txt");
				pw = new PrintWriter(fichero);
				
				pw.println(password);
				
				if(queEscribir)pw.println(claveVerificacion);
				else pw.println("La clave es incorrecta");
			} catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		}
}
