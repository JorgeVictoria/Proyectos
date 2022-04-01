import java.io.IOException;

public class Ejercicio02 {

	public static void main(String[] args) throws InterruptedException {
		
		try {
			
			//creamos y lanzamos el proceso
			Process process = Runtime.getRuntime().exec("mspaint.exe");
			
			//esperamos a que el proceso termine
			process.waitFor();
			
			//mostramos por pantalla el fin del proceso
			System.out.println("La aplicacion se ha cerrado.");
			
			
			//control de excepciones
		} catch(IOException ex) {
			System.err.println("Excepcion de E/S");
			System.exit(-1);
		}

	}


}
