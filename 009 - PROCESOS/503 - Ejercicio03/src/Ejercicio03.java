import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio03 {

	public static void main(String[] args) {
		
		try {
	
			//si no se han pasado argumentos, mostramos warning por pantalla
			if(args.length == 0) System.out.println("No se han pasado argumentos");
			
			//si se han pasado argumentos, intentamos ejecutar los procesos
			else {
				
				
				Runtime runtime = Runtime.getRuntime(); //Obtenemos proceso actual
				Process process=null;
				String comando="";						//cadena donde guardarmos el comando
				
				//vamos leyendo el array de args de entrada y montamos la cadena
				for(int i = 0; i < args.length; i++){
					comando = comando + args[i] +  " ";
				}
	
				//ejecutamos el proceso, pasando la cadena creada con el comando
				process = runtime.getRuntime().exec(comando);
				
				//capturamos la salida del proceso y la mostramos por pantalla
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				
				while((line=br.readLine()) != null) {
					System.out.println(line);
				}
			

			}
			
		} catch(IOException ex) {
			System.err.println("Excepcion de E/S");
			System.exit(-1);
		}

	}

}
