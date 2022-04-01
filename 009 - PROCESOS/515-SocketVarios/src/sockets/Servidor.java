package sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	public static void main(String[] args) {
		
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		
		try {
			while(true) {
		
				System.out.println("Creando socket servidor");
				ServerSocket serverSocket = new ServerSocket();
				
				System.out.println("Realizando el bind");
				InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
				//serverSocket.bind(addr);
				
				System.out.println("Aceptando conexiones");
				Socket newSocket = serverSocket.accept();
				sockets.add(newSocket);
				System.out.println("Conexion recibida");
				
				//String direccionIP = InetAddress.getLocalHost().getHostAddress();
				//System.out.println("IP:" + direccionIP);
				
				//InputStream is = newSocket.getInputStream();
				//OutputStream os = newSocket.getOutputStream();
				//byte[] mensaje = new byte[25];
				//is.read(mensaje);
				//System.out.println("Mensaje recibido: " + new String(mensaje));
				
				
				
				
				//System.out.println("Cerrando el socket servidor");
				//serverSocket.close();
				
				//System.out.println("Terminado");
			}
		
		} catch(IOException e) {
			e.printStackTrace();
		}


	}

}
