package reto03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServer {

	public static void main(String[] args) {
		
		try {
			
			//creamos el socket para el datagrama
			DatagramSocket socketUDP = new DatagramSocket(6789);
			
			//creamos un bufer para ir almacenando datos
			byte[] bufer = new byte[1000];
			
			while(true) {
				
				//construimos el datagramPacket para recibir peticiones
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				
				//leemos una peticion del DatagramSocket
				socketUDP.receive(peticion);
				
				//vemos lo que hemos recibido
				String mensajeRecibido = new String(bufer);
		
				//quitamos espacion extra
				mensajeRecibido = mensajeRecibido.trim();
				
				//impresion por pantalla de los datos
				System.out.println("Datagrama recibido del host: " + peticion.getAddress());
				System.out.println("Desde el puerto : " + peticion.getPort());
				System.out.println("Mensaje : " + mensajeRecibido);
				
				//calculamos la clave
				int claveVerificacion = calcularClaveVerificacion(mensajeRecibido);
				
				//contruimos el buffer para enviar la clave
				byte[] clave = String.valueOf(claveVerificacion).getBytes();
				
				//construimos el datagramPacket para enviar la respuesta
				DatagramPacket respuesta = new DatagramPacket(clave, clave.length , peticion.getAddress(), peticion.getPort());
				
				//enviamos la respuesta
				socketUDP.send(respuesta);
				
				//cerramos el socket
				socketUDP.close();
				
			}
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}

	}

	private static int calcularClaveVerificacion(String mensajeRecibido) {
		
		int clave = 0;
		
		for(int i = 0; i < mensajeRecibido.length(); i++) {
			
			clave = clave + mensajeRecibido.charAt(i);
			
		}
		
		System.out.println(clave);
		return clave;
	}

}
