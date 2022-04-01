package practica04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jorge Victoria Andreu (2º DAM)
 * @version 1
 * @since 20/09/2021
 */
public class Practica04 {

	public static void main(String[] args) throws IOException {
		
		//variables locales
		String origen = "C://Workspace//primitivos.txt";
		String destino = "C://Workspace//primitivos.txt";
		
		try {
			//creamos un objeto FileOutputStream
			FileOutputStream fos = new FileOutputStream(destino);
			
			//creamos un objeto DataOutputStream
			DataOutputStream dos = new DataOutputStream(fos);
			
			//introducimos datos en el fichero
			dos.writeBoolean(true);
			dos.writeInt(1976);
			dos.writeFloat(18.2f);
			dos.writeDouble(23.6);
			dos.writeChar('J');
			dos.writeUTF("Hola que Access");
			dos.writeByte(6);
			
			//creamos un objeto FileInputStream
			FileInputStream fis = new FileInputStream(origen);
			
			//creamos un objetp DataInputStream
			DataInputStream dis = new DataInputStream(fis);
			
			//si intentamos leer el primer dato con un tipo diferente, dara error o salen datos que no corresponden
			//System.out.println(dis.readInt());
			
			//leemos los datos por pantalla. Se deben leer en el orden que se han escrito
			System.out.println(dis.readBoolean());
			System.out.println(dis.readInt());
			System.out.println(dis.readFloat());
			System.out.println(dis.readDouble());
			System.out.println(dis.readChar());
			System.out.println(dis.readUTF());
			System.out.println(dis.readByte());
			
			//cerramos el stream
			dis.close();
			dos.close();
			
		}catch(Exception e) {
			System.err.print(e.getMessage());
		}
		
	}

}
