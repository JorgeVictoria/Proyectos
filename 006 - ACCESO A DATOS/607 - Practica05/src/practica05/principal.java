/**
 * 
 */
package practica05;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/**
 * @author Jorge Victoria Andreu
 * @version 1
 * @since 20/09/1976
 */
public class principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//variables locales. Creamos 2 objetos persona.
		Persona persona1 = new Persona("Jorge", "Victoria", "Andreu", 45);
		//creamos un objeto con un dni, para probar que no se serializa
		Persona persona2 = new Persona("Jordi", "Victoria", "Andreu", 55, "12345678P");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		
		try {
			
			//creamos un objeto FileOutputStream
			fos = new FileOutputStream("C://Workspace/dataPersona.dat");
			
			//creamos un objeto ObjectOutputStream
			oos = new ObjectOutputStream(fos);
			
			//escribimos los objetos en el fichero
			oos.writeObject(persona1);
			oos.writeObject(persona2);
			
			//creamos un objeto FileOutputStream
			fis = new FileInputStream("C://Workspace/dataPersona.dat");
			
			//creamos un objeto ObjectOutputStream
			ois = new ObjectInputStream(fis);
			
			//leemos el primer objeto
			Persona personaAux = new Persona();
			personaAux = (Persona)ois.readObject();
		
			//mientras haya objetos
			while (personaAux!=null)
			{
				//imprimimos el serial id
				System.out.println("SerialVersionUID = " + ObjectStreamClass.lookup(persona1.getClass()).getSerialVersionUID());
				//usamos el metodo toString de la clase
				System.out.println(personaAux);
				//leemos un nuevo objeto
				personaAux = (Persona)ois.readObject();
			}
			
					
		} catch (ClassNotFoundException e) {
			System.err.print(e.getMessage());
		} catch (IOException e) {
			//esta excepcion me devuelve null
			if(e.getMessage() != null)System.err.print(e.getMessage());
		} finally {
			//cerramos los streams
			fos.close();
			oos.close();
			fis.close();
			ois.close();
		}
		
		
		
		
	}

}
