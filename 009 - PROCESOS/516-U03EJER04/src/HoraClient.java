import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HoraClient {

	public static void main(String[] args) {
		
		try {
			
			System.out.println("Creando socket datagrama");
			
			DatagramSocket datagramSocket = new DatagramSocket();
			
			System.out.println("Enviando peticion al servidor");
			
			String mensaje = new String("fech");
			
			InetAddress serverAddr = InetAddress.getByName("localhost");
			
			DatagramPacket datagrama1 = new DatagramPacket ( mensaje.getBytes(), mensaje.getBytes().length, serverAddr, 5555);
			
			datagramSocket.send(datagrama1);
			
			System.out.println("mensaje enviado");
			
			System.out.println("recibiendo respuesta");
			
			byte[] respuesta = new byte[100];
			
			DatagramPacket datagrama2 = new DatagramPacket(respuesta, respuesta.length);
			
			datagramSocket.receive(datagrama2);
			
			System.out.println("Mensaje recibido: " + new String(respuesta));
			
			System.out.println("Cerrando el socket datagrama");
			
			datagramSocket.close();
			
			System.out.println("Terminado");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
