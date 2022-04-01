package reto01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {

	public static void main(String[] args) {
		try {
			
			//se crea el socket cliente
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			
			//se establece la conexion con el servidor
			System.out.println("Estableciendo la conexion");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);
			
			//creamos un par de objetos para la entrada y salida de datos
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			//pedimos un numero entero
			int numero = pedirEntero();
			System.out.println("Enviando mensaje");
			String mensaje = String.valueOf(numero);
			
			//enviamos el dato
			os.write(mensaje.getBytes());
			System.out.println("Mensaje enviado");
			
			//recibimos el resultado
			byte[] resultado = new byte[25];
			is.read(resultado);
			
			//imprimimos por pantalla
			System.out.println("resultado recibido");
			System.out.println("Cuadrado de " +  numero + " = " + new String(resultado));
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			
			System.out.println("Terminado");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static int pedirEntero() {
		
		//variables locales
		boolean esValido = false;
		int numero = 0;
		try (Scanner stdin = new Scanner(System.in)) {
			while(!esValido) {
				System.out.printf("Introduzca un numero entero:");
				try {
					esValido = true;
					numero = stdin.nextInt();
				} catch (Exception ex) {
					stdin.next();
					System.out.println("El valor no es valido");
					esValido = false;
				}
				if(esValido && numero <= 0) {
					System.out.println("Debe introducir un numero entero positivo");
					esValido = false;
				}
				
			}
		}
		
		return numero;
	}

}
