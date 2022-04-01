package temario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Demo {

	public static void main(String[] args) {
		// serializacion
		try {
			UnaClase ob1 = new UnaClase("Hola", -7, 2.7e10); //creamos el objeto. Llamada a constructor
			System.out.println("ob1: " + ob1); //imprimimos el objeto
			FileOutputStream fos = new FileOutputStream("serial"); //nos creamos un flujo de bytes, bajo nivel
			ObjectOutputStream oos = new ObjectOutputStream(fos); //creamos un flujo de objeto y usamos el flujo anterior
			oos.writeObject(ob1); //mandamos el objeto 1
			oos.flush(); //para confirmar el envio
			oos.close(); //cerramos el flujo
		} catch(IOException e) {
			System.out.println("Fallo al serializar: " + e);
			System.exit(0);
		}
		//deserializar
		try {
			UnaClase ob2; //creamos un nuevo objeto, donde recibiremos los datos del objeto anterior
			FileInputStream fis = new FileInputStream("serial");  //creamos el flujo de lectura de bytes
			ObjectInputStream ois = new ObjectInputStream(fis); //creamos el flujo de entrada de objetos
			ob2 = (UnaClase)ois.readObject(); //hacemos casting para la entrada y leemos el objeto
			ois.close(); //cerramos el flujo
			System.out.println("ob2: " + ob2); //mostramos el objeto
		} catch(Exception e) {
			System.out.println("Fallo al deserializar: " + e);
			System.exit(0);
		}
	}

} //fin de la clase

class UnaClase implements Serializable {
	
	//variables miembro
	String s;
	int i;
	double d;
	
	//constructor
	public UnaClase(String s, int i, double d) {
		this.s = s;
		this.i = i;
		this.d = d;
	}
	
	@Override
	public String toString() {
		return "s=" + s + "; i=" + i + "; d= " + d;
	}
}