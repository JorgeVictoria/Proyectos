package reto02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {
	
	//variables globales
	private static double cateto01;
	private static double cateto02;
	private static Scanner stdin = new Scanner(System.in);

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
			cateto01 = pedirCateto("cateto 1");
			cateto02 = pedirCateto("cateto 2");
			System.out.println("Enviando mensaje");
			String mensaje = String.valueOf(cateto01 + ";" + cateto02);
			
			//enviamos el dato
			os.write(mensaje.getBytes());
			System.out.println("Mensaje enviado");
			
			//recibimos el resultado
			byte[] resultado = new byte[25];
			is.read(resultado);
			
			//ordenamos los catetos
			ordenarCatetos();
			
			//imprimimos por pantalla
			System.out.println("Se ha calculado la hipotenusa a partir de los catetos");
			System.out.println("-----------------------------------------------------");
			System.out.println("Cateto 1 -> " + cateto01);
			System.out.println("Cateto 2 -> " + cateto02);
			System.out.println("Hipotenusa -> " + new String(resultado));
			System.out.println("-----------------------------------------------------");
			System.out.println();
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			
			System.out.println("Terminado");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void ordenarCatetos() {
		
		double temp;
		
		if(cateto01 > cateto02 ) {
			temp = cateto02;
			cateto02 = cateto01;
			cateto01 = temp;
		}
		
	}

	private static double pedirCateto(String cateto) {
		
		//variables locales
		boolean esValido = false;
		double numero = 0;
			
		while(!esValido) {
			System.out.printf("Introduzca el valor del " + cateto + " :");
			try {
				esValido = true;
				numero = stdin.nextDouble();
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
	
		return numero;
	}

}
