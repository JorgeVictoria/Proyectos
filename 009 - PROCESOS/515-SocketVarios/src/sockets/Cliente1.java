package sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente1 {

	public static void main(String[] args) throws InterruptedException {
		
		try {
		
		System.out.println("Creando socket cliente");
		Socket clientSocket = new Socket();
		
		System.out.println("Estableciendo la conexion");
		InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
		clientSocket.connect(addr);
		
		/**InputStream is = clientSocket.getInputStream();
		OutputStream os = clientSocket.getOutputStream();
		System.out.println("Enviando mensaje");
		String mensaje = "mensaje desde el cliente";
		os.write(mensaje.getBytes());
		System.out.println("Mensaje enviado");**/
		
		Thread.sleep(10000);
	
		
		System.out.println("Cerrando el socket cliente");
		clientSocket.close();
		
		System.out.println("Terminado");
		
	} catch (IOException e) {
		e.printStackTrace();
	}

	}

}
