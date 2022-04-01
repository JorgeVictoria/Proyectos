package temario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModoSecuencial {

	public static void main(String[] args) {
		
		DataOutputStream fo = null; //creamos el objeto
		DataInputStream fi = null;  //creamos el objeto
		String nombre = null;		//almacena el nombre
		int edad = 0;				//almacena la edad
		
		try {
			//crea o abre para añadir al archivo
			fo = new DataOutputStream( new FileOutputStream("D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\secuencial.dat", true));
			fo.writeUTF("Antonio Lopez Perez");
			fo.writeInt(33);
			fo.writeUTF("Pedro Piqueras Peñaranda");
			fo.writeInt(45);
			fo.writeUTF("Jose Antonio Ruiz Perez");
			fo.writeInt(51);
			fo.close(); //cerramos el fichero para escritura
			//abrimos de nuevo para leer
			fi = new DataInputStream( new FileInputStream("D:\\One drive\\OneDrive\\Estudios\\Workspace\\Eclipse\\Tema 06\\src\\temario\\secuencial.dat"));
			//leer primer registro
			nombre = fi.readUTF();
			System.out.println(nombre);
			edad = fi.readInt();
			System.out.println(edad);
			//leer segundo registro
			nombre = fi.readUTF();
			System.out.println(nombre);
			edad = fi.readInt();
			System.out.println(edad);
			//leer tercer registro
			nombre = fi.readUTF();
			System.out.println(nombre);
			edad = fi.readInt();
			System.out.println(edad);
			//cerramos la lectura
			fi.close();
		}
		catch(FileNotFoundException fnfe) {System.out.println("No existe el fichero");}
		catch(IOException ioe) {System.out.println("Error al escribir");}
		catch(Exception e) {System.out.println(e.getMessage());}
	}

}
