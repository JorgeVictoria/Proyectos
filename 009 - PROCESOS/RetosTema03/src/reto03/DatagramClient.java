package reto03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class DatagramClient {
	
	//variables globales
	private static String password;
	private static int claveVerificacion;
	private static Scanner stdin = new Scanner(System.in);
	private static String direccionServidor = "localhost";

	public static void main(String[] args) {
		
		//solicitamos al cliente un password y la clave de verificacion
		System.out.printf("Introduzca la password: ");
		password = stdin.nextLine();
		claveVerificacion = pedirClave();
		
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
			if(Integer.valueOf(claveVerificada) == claveVerificacion) System.out.println("La clave es correcta");
			else System.out.println("La clave es incorrecta");
			
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

}
