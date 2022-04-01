package ejercicio01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{
	
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		boolean conectado = true;
		String mensaje = "";
		String respuestaServer = "";
		byte[] buffer = new byte[512];
	
	try {
		System.out.println("Creando socket cliente");
		Socket clientSocket = new Socket();
		System.out.println("Estableciendo la conexion");
		InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
		clientSocket.connect(addr);
		
		DataInputStream is = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
		
		//Intentamos establecer la conexion
		System.out.print("Mensaje de conexion: ");
		mensaje = stdin.nextLine();
		os.write(mensaje.getBytes());
		
		//vemos la respuesta inicial del servidor
		is.read(buffer);
		respuestaServer = new String(buffer);
		respuestaServer = respuestaServer.trim();
		
		//si la respuesta no es correcta, lo indicamos
		if(respuestaServer.equals("Conexion no aceptada")) {
			clientSocket.close();
			System.out.println("Conexion no aceptada");
		}
		
		//si el servidor acepta la conexion vamos enviando mensajes
		else {
			//mientras estemos conectados
			while(conectado) {
				
				System.out.println(respuestaServer.toString());
				mensaje = stdin.nextLine();
				os.write(mensaje.getBytes());
				is.read(buffer);
				respuestaServer = new String(buffer);
				respuestaServer = respuestaServer.trim();
				
			}
		}
		
			
	} catch (Exception e) {
		e.printStackTrace();
	}

}

}