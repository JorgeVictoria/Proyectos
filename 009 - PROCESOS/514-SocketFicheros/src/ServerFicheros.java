import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFicheros {

	public static void main(String[] args) {
		
		try {
			
			
			FileInputStream fis = new FileInputStream("C:\\Proyectos\\Saludo.txt");
			
			System.out.println("Creando socket servidor");
			ServerSocket serverSocket = new ServerSocket();
			
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
			
			System.out.println("Aceptando conexiones");
			Socket newSocket = serverSocket.accept();
			System.out.println("Conexion recibida");
			
			//InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			InputStream is = fis;
			
			byte[] b = new byte[1024];
			int n = is.read(b);
			while(n != -1) {
				os.write(b);
				os.flush();
				n = is.read(b);
			}
			
			
			/**is.read(mensaje);
			System.out.println("Mensaje recibido: " + new String(mensaje));**/
			
			is.close();
			os.close();
			
			System.out.println("Cerrando el snuevo socket");
			newSocket.close();
			
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
			
			System.out.println("Terminado");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
