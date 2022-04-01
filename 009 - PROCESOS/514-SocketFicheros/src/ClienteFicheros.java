import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteFicheros {

	public static void main(String[] args) {
		
try {
			
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			
			System.out.println("Estableciendo la conexion");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			byte[] mensaje = new byte[25];
			is.read(mensaje);
			System.out.println("Mensaje recibido: " + new String(mensaje));
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			
			System.out.println("Terminado");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
