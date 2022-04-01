import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;

public class HoraServer {
	
	private static DatagramSocket datagramSocket = null;
	private static InetAddress clientAddr;
	private static int clientPort;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		System.out.println("Arrancando servidor de hora");
		
		try {
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			datagramSocket = new DatagramSocket(addr);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while(datagramSocket != null) {
			try {
				
				System.out.println("Esperando mensaje");
				
				byte[] buffer = new byte[10];
				DatagramPacket datagramal = new DatagramPacket(buffer, 10);
				datagramSocket.receive(datagramal);
				
				String mensaje = new String(datagramal.getData());
				
				mensaje = mensaje.trim();
				
				System.out.println(mensaje.length());
				
				clientAddr = datagramal.getAddress();
				clientPort = datagramal.getPort();
				
				System.out.println("Mensaje recibido desde: " + clientAddr + ", puerto " + clientPort);
				System.out.println("Contenido del mensaje: " + mensaje);
				
				Date d = new Date(System.currentTimeMillis());
				
				if(mensaje.equals("hora")) {
					
					String hora;
					if(d.getHours() < 10) hora = "0" + String.valueOf(d.getHours());
					else hora = String.valueOf(d.getHours());
					
					String minutos;
					if(d.getMinutes() < 10) minutos = "0" + String.valueOf(d.getMinutes());
					else minutos = String.valueOf(d.getMinutes());
					
					String segundos;
					if(d.getSeconds() < 10) segundos = "0" + String.valueOf(d.getSeconds());
					else segundos = String.valueOf(d.getSeconds());
					
					String cadena = "Hora: " + hora + ":" + minutos + ":" + segundos;
						
					enviarMensaje(cadena);
					
				} else if (mensaje.equals("fecha")) {
					
					String day;
					if(d.getDate() < 10) day = "0" + String.valueOf(d.getDate());
					else day = String.valueOf(d.getDate());
					
					String month;
					if(d.getMonth()+1 < 10) month = "0" + String.valueOf(d.getMonth()+1);
					else month = String.valueOf(d.getMonth()+1);
					
					String cadena = "Fecha: " + day + "-" + month + "-" + String.valueOf(d.getYear()+1900);
						
					enviarMensaje(cadena);
					
				} else {
					
					System.out.println("Mensaje recibido no reconocido");
					enviarMensaje("Error en el mensaje");
				}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			System.out.println("Terminado");
			
			datagramSocket.close();

	}

	private static void enviarMensaje(String cadena) throws IOException {
		
		byte[] respuesta = cadena.getBytes();
		
		System.out.println("Enviando respuesta");
		
		DatagramPacket datagrama2 = new DatagramPacket(respuesta, respuesta.length, clientAddr, clientPort);
		
		datagramSocket.send(datagrama2);
		
		System.out.println("Mensaje enviado");
	}

}
