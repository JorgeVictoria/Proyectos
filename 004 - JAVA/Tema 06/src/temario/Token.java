package temario;

//importamos paquetes
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

//esta clase sirve para contar numeros y palabras de un fichero de texto
public class Token { //inicio de la clase

	public static void main(String[] args) {
		//variables locales
		String nomFichero = "prueba.txt" ; //ruta del fichero de lectura
		cuentaPalanum(nomFichero); //llamada a la funcion para contar palabras y numeros
	}
	
	//metodo token para contar palabras y numeros de un fichero que pasamos como parametro
	//en este caso cada palabra o bloque de numero es un token
	public static void cuentaPalanum(String fichero) {
		
		//variables locales
		int np = 0; //para almacenar el numero de palabras
		int nn = 0; //para almacenar la cantidad de numeros
		int ta; //para almacenar el token actual
		int tt=0; //total de tokens
		StreamTokenizer st = null; //creamos el flujo streamtokenizer
		
		try {
			//se construye el objeto StreamTokneizer con el flujo filereader como argumento
			st = new StreamTokenizer (new FileReader(fichero));
			
			//se empieza a iterar sobre el token. NextToken() devuelve un int que indica el tipo de token leido
			while ((ta = st.nextToken()) != StreamTokenizer.TT_EOF) { //mientras no sea el final. EOL para fin de linea

				if (st.ttype == StreamTokenizer.TT_WORD) //si el token es una palabra
					np++;
				else if (st.ttype == StreamTokenizer.TT_NUMBER) //si el token es una letra
					nn++;
				tt++;
			}
			System.out.println("Palabras en el fichero: " + np);
			System.out.println("Numeros en el fichero: " + nn);
			System.out.println("Numero de tokens: " + tt);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}//fin cuentaPalanum

} //fin de la clase
