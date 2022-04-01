package calculadora;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CalServer extends Thread {
	
	//variables miembro
	private Socket clientSocket;
	
	//constructor
	public CalServer (Socket socket) {
		clientSocket = socket;
	}
	
	//comportamiento de cada hilo
	public void run() {
		
		
		try {
			System.out.println("Arrancando hilo");
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			System.out.println("Esperando mensaje de operacion.");
			
			byte[] buffer = new byte[1];
			is.read(buffer);
			String operacion = new String(buffer);
			
			System.out.println("Operacion recibida: " + new String(operacion));
			
			if(operacion.equals("+") || operacion.equals("-") || operacion.equals("*") || operacion.equals("/")) {
				System.out.println("Esperando el primer operador");
				int op1 =  is.read();
				System.out.println("Primer operador: " + op1);
				System.out.println("Esperando el segundo operador");
				int op2 = is.read();
				System.out.println("Segundo operador: " + op2);
				System.out.println("Calculando resultado");
				int result = Integer.MIN_VALUE;
				if(operacion.equals("+")) result = op1 + op2;
				else if(operacion.equals("-")) result = op1 - op2;
				else if(operacion.equals("*")) result = op1 * op2;
				else if(operacion.equals("/"))result = op1 / op2;
				
				System.out.println("Enviando resultado");
				os.write(result);
			} else {
				System.out.println("Operacion no reconocida");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Hilo terminado");
		
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
				CalServer hilo = new CalServer(newSocket);
				hilo.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Terminado");

	}

}
