package calculadora;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CalClient {

	public static void main(String[] args) {
		
		try {
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexion");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			System.out.println("Enviando petición de suma"); 
			os.write("+". getBytes());
			System.out.println("Enviando primer operando");
			os.write(59);
			System.out.println("Enviando segundo operando");
			os.write(130);
			System.out.println("Recibiendo resultado");
			int result = is.read();
			System.out.println("Resultado de la suma: "+ result);
			System.out.println("Cerrando el socket cliente");
			clientSocket.close(); System.out.println("Terminado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
