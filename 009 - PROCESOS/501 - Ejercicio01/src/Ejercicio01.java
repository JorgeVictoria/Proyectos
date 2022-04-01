import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio01 {

	public static void main(String[] args) throws InterruptedException {
		
		try {
			
			Runtime runtime = Runtime.getRuntime(); //Obtenemos proceso actual
			Process process=null;
			String comando = "cmd /c ping 192.168.1.1"; // Comando a ejecutar

			//ejecutamos el proceso
			process = runtime.getRuntime().exec(comando);
			
			//capturamos la salida y la mostramos por pantalla
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}

			
			//control de excepciones
		} catch(IOException ex) {
			System.err.println("Excepcion de E/S");
			System.exit(-1);
		}

	}

}
