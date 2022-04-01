package ejercicio01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Servidor extends Thread {
	
	//variables miembro
	private Socket clientSocket;
	
	//constructor
	public Servidor(Socket socket) {
		clientSocket = socket;
	}
	
	//comportamiento de cada hilo
	public void run() {
		
		//variables locales
		String respuesta = "";
		boolean identificado = false;
		Scanner stdin = new Scanner(System.in);
		
		
		try {
			System.out.println("Arrancando hilo");
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			System.out.println("Esperando mensaje de entrada.");
			
			byte[] buffer = new byte[512];
			is.read(buffer);
			String cadenaEntrada = new String(buffer);
			cadenaEntrada = cadenaEntrada.trim();
			cadenaEntrada = cadenaEntrada.toLowerCase();
			
			//debemos ver si la cadena recibida es correcta
			if(!cadenaEntrada.equals("hola servidor")) {
				respuesta = "Conexion no aceptada";
				System.out.println(respuesta);
				os.write(respuesta.getBytes());
				os.flush();
				System.out.println("Hilo terminado");}
			
			//si la cadena es correcta
			else {
				//primero vemos si el cliente se ha identificado
				//sino pedimos su usuarios
				while(!identificado) {
					System.out.println("Conexion aceptada");
					respuesta = "nombre de usuario:";
					os.write(respuesta.getBytes());
					os.flush();
					identificado = true;
				}
				while(identificado) {
					System.out.print("Server:");
					respuesta = stdin.nextLine();
					respuesta = "Server => " + respuesta;
					os.write(respuesta.getBytes());
					os.flush();
				}
			}
			
	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		
		System.out.println("Creando socket servidor");
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Aceptando conexiones");
		
		while (serverSocket != null) {
			try {
				Socket newSocket = serverSocket.accept();
				System.out.println("Conexion recibida");
				Servidor hilo = new Servidor(newSocket);
				hilo.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Terminado");

	}

}
