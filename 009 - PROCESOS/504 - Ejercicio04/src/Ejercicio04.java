import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio04 {

	public static void main(String[] args) throws InterruptedException {
		
		try {
			
			//si no se pasan argumentos, mostramos un warning
			if(args.length == 0) System.out.println("No se han pasado argumento");
			
			
			/*//si se pasa mas de un argumento, mostramos un warning
			else if(args.length > 1) System.out.println("Solo debe pasar un argumento");*/
			
			//si el numero de parametros es correcto, ejecutamos el programa
			else {
				for(int i = 0; i < args.length; i++) {
					Runtime runtime = Runtime.getRuntime(); //Obtenemos proceso actual
					Process process=null;
					String comando = args[i];				//almacenamos el comando
				
					//ejecutamos el proceso
					process = runtime.getRuntime().exec(comando);
				
					//esperamos a que se cierre el proceso y mostramos mensaje por pantalla
					process.waitFor();
				
				//System.out.println("La aplicacion se ha cerrado.");
				}

			}
			
		} catch(IOException ex) {
			System.err.println("Excepcion de E/S");
			System.exit(-1);
		}
		
		System.out.println("Se han cerrado las aplcaciones.");

	}

}
