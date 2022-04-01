package reto01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	public static void main(String[] args) {
		
		try {
			
			//creamos el socket
			System.out.println("Creando socket servidor");
			ServerSocket serverSocket = new ServerSocket();
			
			//realizamos el bind
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
			
			//cogemos las conexiones
			System.out.println("Aceptando conexiones");
			Socket newSocket = serverSocket.accept();
			System.out.println("Conexion recibida");
			
			//creamos 2 objetos para el envio y recepcion de mensajes
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			//recepcionamos el mensaje
			byte[] mensaje = new byte[25];
			is.read(mensaje);
			System.out.println("dato recibido: " + new String(mensaje));
			String dato = new String(mensaje);
			dato = dato.trim();
			int valor = Integer.valueOf(dato.toString());
			
			//calculamos el cuadrado
			String cuadrado = calcularCuadrado(valor);
			
			//enviamos el resultado
			os.write(cuadrado.getBytes());
			
			System.out.println("Cerrando el snuevo socket");
			newSocket.close();
			
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
			
			System.out.println("Terminado");
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	private static String calcularCuadrado(int numero) {
		
		System.out.println("El cuadrado de " + numero + " es " + Math.pow(numero, 2));
		return String.valueOf(Math.pow(numero, 2));
	}

}
